package com.example.bloodmanagementproject.service.implementation;

import com.example.bloodmanagementproject.domain.PasswordHistory;
import com.example.bloodmanagementproject.domain.PasswordResetToken;
import com.example.bloodmanagementproject.domain.Users;
import com.example.bloodmanagementproject.helper.MapperHelper;
import com.example.bloodmanagementproject.model.*;
import com.example.bloodmanagementproject.proxy.UserProxy;
import com.example.bloodmanagementproject.repository.PasswordHistoryRepo;
import com.example.bloodmanagementproject.repository.PasswordResetTokenRepo;
import com.example.bloodmanagementproject.repository.UserRepo;
import com.example.bloodmanagementproject.service.AuthService;
import com.example.bloodmanagementproject.model.LoginLockStatus;
import com.example.bloodmanagementproject.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired private UserRepo userRepo;
    @Autowired private MapperHelper helper;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private MyUserDetailService userDetailService;
    @Autowired private JwtUtil util;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private PasswordResetTokenRepo resetTokenRepo;
    @Autowired private PasswordHistoryRepo passwordHistoryRepo;
    @Autowired private EmailService emailService;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    @Override
    public String   register(UserProxy userProxy) {
        if (userRepo.findByEmail(userProxy.getEmail()).isPresent())
            throw new RuntimeException("Email Id Already Exist");
        userProxy.setPassword(passwordEncoder.encode(userProxy.getPassword()));
        Users user = helper.map(userProxy, Users.class);
        user.setFailedAttempts(0);
        user.setLockUntil(null);
        userRepo.save(user);
        return "Saved Successfully";
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Users user = userRepo.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("No account found with this email"));

        // Check if account is locked
        if (user.isLocked()) {
            long secondsLeft = ChronoUnit.SECONDS.between(LocalDateTime.now(), user.getLockUntil());
            throw new RuntimeException("LOCKED:" + secondsLeft);
        }

        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            if (authenticate.isAuthenticated()) {
                saveAttempts(user, 0, null); // reset on success
                UserDetails userDetails = userDetailService.loadUserByUsername(authRequest.getEmail());
                String token = util.generateToken(userDetails);
                String role = userDetails.getAuthorities().stream().findFirst()
                        .map(auth -> auth.getAuthority()).orElse(null);
                AuthResponse response = new AuthResponse();
                response.setToken(token);
                response.setEmail(authRequest.getEmail());
                response.setRole(role);
                response.setId(user.getId());
                return response;
            }
        } catch (BadCredentialsException e) {
            int attempts = user.getFailedAttempts() + 1;

            if (attempts >= 3) {
                saveAttempts(user, 0, LocalDateTime.now().plusMinutes(3));
                throw new RuntimeException("LOCKED:180");
            }

            saveAttempts(user, attempts, null);
            int remaining = 3 - attempts;
            throw new RuntimeException("INVALID:" + remaining);
        }

        throw new RuntimeException("Login failed");
    }

    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
    public void saveAttempts(Users user, int attempts, LocalDateTime lockUntil) {
        user.setFailedAttempts(attempts);
        user.setLockUntil(lockUntil);
        userRepo.saveAndFlush(user);
    }

    @Override
    public LoginLockStatus getLockStatus(String email) {
        Users user = userRepo.findByEmail(email).orElse(null);
        if (user == null) return new LoginLockStatus(false, 0, 0);

        if (user.isLocked()) {
            long secondsLeft = ChronoUnit.SECONDS.between(LocalDateTime.now(), user.getLockUntil());
            return new LoginLockStatus(true, Math.max(secondsLeft, 0), 0);
        }
        return new LoginLockStatus(false, 0, user.getFailedAttempts());
    }

    @Override
    public String forget(ForgetPassWord forgetPassWord) {
        Users user = userRepo.findByEmail(forgetPassWord.getEmail())
                .orElseThrow(() -> new RuntimeException("No Email Id Found"));
        user.setPassword(passwordEncoder.encode(forgetPassWord.getPassword()));
        userRepo.save(user);
        return "Successfully Password Updated";
    }

    @Override
    @Transactional
    public String sendResetLink(ForgotPasswordRequest request) {
        String email = request.getEmail();
        userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("No account found with this email"));
        resetTokenRepo.deleteByEmail(email);
        String token = UUID.randomUUID().toString();
        resetTokenRepo.save(new PasswordResetToken(token, email));
        String resetLink = frontendUrl + "/reset-password?token=" + token;
        emailService.sendEmail(email,
                "Blood Management - Password Reset Request",
                "Hello,\n\nClick the link below to reset your password. This link expires in 10 minutes.\n\n"
                + resetLink + "\n\nIf you did not request this, please ignore this email.");
        return "Password reset link sent to your email";
    }

    @Override
    @Transactional
    public String resetPassword(ResetPasswordRequest request) {
        PasswordResetToken resetToken = resetTokenRepo.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid or expired reset token"));
        if (resetToken.isExpired()) {
            resetTokenRepo.delete(resetToken);
            throw new RuntimeException("Reset token has expired. Please request a new one.");
        }
        Users user = userRepo.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        checkPasswordHistory(user.getId(), request.getNewPassword());

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);
        savePasswordHistory(user.getId(), user.getPassword());
        resetTokenRepo.delete(resetToken);
        return "Password reset successfully";
    }

    // ── OTP-based forgot password ──────────────────────────────────────────────

    @Override
    @Transactional
    public String sendOtp(ForgotPasswordRequest request) {
        String email = request.getEmail();
        userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("No account found with this email"));
        resetTokenRepo.deleteByEmail(email);

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        resetTokenRepo.save(new PasswordResetToken(otp, email));

        emailService.sendEmail(email,
                "Blood Management - OTP for Password Reset",
                "Hello,\n\nYour OTP for password reset is: " + otp
                + "\n\nThis OTP is valid for 10 minutes. Do not share it with anyone."
                + "\n\nIf you did not request this, please ignore this email.");

        return "OTP sent to your email";
    }

    @Override
    public String verifyOtp(VerifyOtpRequest request) {
        PasswordResetToken record = resetTokenRepo.findByToken(request.getOtp())
                .filter(t -> t.getEmail().equals(request.getEmail()))
                .orElseThrow(() -> new RuntimeException("Invalid OTP. Please check and try again."));
        if (record.isExpired()) {
            resetTokenRepo.delete(record);
            throw new RuntimeException("OTP has expired. Please request a new one.");
        }
        return "OTP verified";
    }

    @Override
    @Transactional
    public String resetPasswordWithOtp(OtpResetPasswordRequest request) {
        PasswordResetToken record = resetTokenRepo.findByToken(request.getOtp())
                .filter(t -> t.getEmail().equals(request.getEmail()))
                .orElseThrow(() -> new RuntimeException("Invalid OTP. Please check and try again."));
        if (record.isExpired()) {
            resetTokenRepo.delete(record);
            throw new RuntimeException("OTP has expired. Please request a new one.");
        }
        Users user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);
        resetTokenRepo.delete(record);
        return "Password reset successfully";
    }

    // ── Password History Helpers ───────────────────────────────────────────────

    private void checkPasswordHistory(Long userId, String newRawPassword) {
        List<PasswordHistory> last3 = passwordHistoryRepo.findTop3ByUserIdOrderByCreatedAtDesc(userId);
        for (PasswordHistory history : last3) {
            if (passwordEncoder.matches(newRawPassword, history.getHashedPassword())) {
                throw new RuntimeException("SAME_PASSWORD");
            }
        }
    }

    private void savePasswordHistory(Long userId, String hashedPassword) {
        List<PasswordHistory> all = passwordHistoryRepo.findByUserIdOrderByCreatedAtAsc(userId);
        if (all.size() >= 3) {
            passwordHistoryRepo.delete(all.get(0));
        }
        passwordHistoryRepo.save(new PasswordHistory(userId, hashedPassword));
    }
}
