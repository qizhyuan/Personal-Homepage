package com.qzy.springboot.entities;

import lombok.Data;

@Data
public class Paper {
    private String author;

    private String title;

    private String publisher;

    private String url;

    private String note;

    private Integer pageStart;

    private Integer pageEnd;

    private Integer vol;

    private Integer num;

    private Integer date;

    private Integer type;
}
