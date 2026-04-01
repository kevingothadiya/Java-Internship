package com.example.ormlibrarymanagement.service.implementation;

import com.example.ormlibrarymanagement.entity.LibraryEntity;
import com.example.ormlibrarymanagement.repository.LibraryRepo;
import com.example.ormlibrarymanagement.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepo libraryRepo;
    @Override
    public String addBook(LibraryEntity lib) {
        LibraryEntity save = libraryRepo.save(lib);
        return save.toString();
    }

    @Override
    public List<LibraryEntity> getAllBook() {
        return libraryRepo.findAll();
    }

    @Override
    public LibraryEntity getBookById(Long id) {
        if(libraryRepo.findById(id).isPresent()){
            return libraryRepo.findById(id).get();
        }
        else {
            return new LibraryEntity(null,null,null,null);
        }
    }

    @Override
    public String updateBook(LibraryEntity lib, Long id) {
        Optional<LibraryEntity> byId = libraryRepo.findById(id);
        if(byId.isPresent()){
            LibraryEntity libraryEntity = byId.get();
            libraryEntity.setId(lib.getId());
            libraryEntity.setTitle(lib.getTitle());
            libraryEntity.setAuthor(lib.getAuthor());
            libraryEntity.setPrice(lib.getPrice());
            libraryRepo.save(libraryEntity);

            return "Update Book Successfully of Id " + id;
        }
        else {
            return "No Book Found With ID " + id;
        }
    }

    @Override
    public String deleteBookById(Long id) {
        if(libraryRepo.findById(id).isPresent()){
            libraryRepo.deleteById(id);
            return "Successfully Delete Book OF ID " + id;
        }
        else{
            return "No Book Found With ID " +id;
        }
    }

    @Override
    public List<LibraryEntity> searchByTitle(String title) {
        return libraryRepo.findByTitle(title);
    }

    @Override
    public List<LibraryEntity> searchByAuthor(String author) {
        return libraryRepo.findByAuthor(author);
    }

    @Override
    public Long countBook() {
        return libraryRepo.count();
    }

    @Override
    public List<LibraryEntity> sortByPrice() {
        return libraryRepo.findAll().stream().sorted().toList();
    }

    @Override
    public String bulkAdd(List<LibraryEntity> lib) {
        return libraryRepo.saveAll(lib).toString();
    }

    @Override
    public String deleteAll() {
        if(libraryRepo.findAll().isEmpty()){
            return "No Data Available";
        }
        else {
            libraryRepo.deleteAll();
            return "Successfully Deleted";
        }
    }
}
