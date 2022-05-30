package com.rohan.mockito.twoHours50Minutes.stubbing;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Book {
    private String bookId;
    private String title;
    private int price;
    private LocalDate publishedDate;
}
