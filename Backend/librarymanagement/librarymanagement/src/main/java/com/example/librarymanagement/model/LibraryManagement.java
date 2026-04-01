package com.example.librarymanagement.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LibraryManagement {
    private Long id;
    private String title;
    private String author;
    private Double price;
}
