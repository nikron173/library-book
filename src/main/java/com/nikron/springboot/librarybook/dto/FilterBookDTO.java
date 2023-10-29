package com.nikron.springboot.librarybook.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FilterBookDTO {
    private String bookName;
    private String authorName;
    private String genreName;
    private Date createBefore;
    private Date createAfter;
}
