package com.qzy.springboot.entities;

// better to use private + getter + setter
public class Paper {
    public String author;
    public String title;
    public String publisher;
    public String url;
    public String note;
    public Integer pageStart;
    public Integer pageEnd;
    public Integer vol;
    public Integer num;
    public Integer date;
    public Integer type;

    Paper() {
        author = null;
        title = null;
        publisher = null;
        url = null;
        note = null;
        pageStart = 0;
        pageEnd = 0;
        vol = 0;
        num = 0;
        date = 0;
        type = 1;
    }
}
