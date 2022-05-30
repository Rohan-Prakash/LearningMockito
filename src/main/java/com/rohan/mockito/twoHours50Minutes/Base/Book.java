package com.rohan.mockito.twoHours50Minutes.Base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private String bookId;
    private String title;
    private int price;
    private LocalDate publishedDate;
}
