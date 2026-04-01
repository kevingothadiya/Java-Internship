package com.example.librarymanagement.service.implementation;

import com.example.librarymanagement.model.LibraryManagement;
import com.example.librarymanagement.service.LibraryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryImpl implements LibraryService {
    List<LibraryManagement> book = new ArrayList<>();

    @Override
    public String addBook(LibraryManagement l) {
        boolean check = book.stream().anyMatch(x -> x.getId().equals(l.getId()));
        if(check){
            return "Book has All ready Available";
        }
        else {
            book.add(l);
            return "Successfully added";
        }
    }

    @Override
    public List<LibraryManagement> getAllBooks() {
        if(book.isEmpty()){
            book.add(new LibraryManagement(null,null,null,null));
            return book;
        }
        return book;
    }

    @Override
    public LibraryManagement getbookById(Long id) {
        Optional<LibraryManagement> byId = book.stream().filter(x -> x.getId().equals(id)).findFirst();
        if(byId.isPresent()){
            return byId.get();
        }
        else {
            return new LibraryManagement(null,null,null,null);
        }
    }

    @Override
    public String updateBook(LibraryManagement l, Long id) {
        Optional<LibraryManagement> update = book.stream().filter(a -> a.getId().equals(id)).findFirst();
        if(update.isPresent()){
            LibraryManagement updatedData = update.get();
            updatedData.setId(l.getId());
            updatedData.setTitle(l.getTitle());
            updatedData.setAuthor(l.getAuthor());
            updatedData.setPrice(l.getPrice());

            return "Successfylly Updated";
        }
        else {
            return "Book is not available with ID " + id;
        }
    }

    @Override
    public String deleteBookById(Long id) {
        if(book.removeIf(x->x.getId().equals(id))){
            return "Book With ID " + id + " is Removed";
        }
        else {
            return "Book With ID " + id + " is not Available";
        }
    }

    @Override
    public LibraryManagement searchByTitle(String title) {
        Optional<LibraryManagement> byTitle = book.stream().filter(t -> t.getTitle().equals(title)).findFirst();
        if(byTitle.isPresent()){
            return byTitle.get();
        }
        else {
            return new LibraryManagement(null,null,null,null);
        }
    }

    @Override
    public LibraryManagement searchByAuthor(String author) {
        Optional<LibraryManagement> byAuthor = book.stream().filter(a -> a.getAuthor().equals(author)).findFirst();
        if(byAuthor.isPresent()){
            return byAuthor.get();
        }
        else {
            return new LibraryManagement(null,null,null,null);
        }
    }

    @Override
    public Long countBooks() {
        Long countBooks = book.stream().count();
        return countBooks;
    }

    @Override
    public List<LibraryManagement> sortByPrice() {
        return book.stream().sorted((b1,b2)->b1.getPrice().compareTo(b2.getPrice())).collect(Collectors.toList());
    }

    @Override
    public String bulkAdd(List<LibraryManagement> l) {
        book.addAll(l);
        return "Successfully added";
    }

    @Override
    public String deleteAll() {
        if(book.isEmpty()){
            return "No Books Available";
        }
        else {
            book.clear();
            return "All Books are Deleted";
        }
    }
}
