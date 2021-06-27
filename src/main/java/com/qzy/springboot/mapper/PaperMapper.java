package com.qzy.springboot.mapper;

import com.qzy.springboot.entities.Paper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PaperMapper {

    @Select("select * from publication where visible = 1 and type = 1")
    List<Paper> getVisibleJouPaper();

    @Select("select * from publication where visible = 1 and type = 2")
    List<Paper> getVisibleConPaper();

    @Select("select * from publication where visible = 1 and type = 3")
    List<Paper> getVisiblePrePaper();
}


//Initionalization SQL for table publication:
//        CREATE TABLE publication (
//        id INT PRIMARY KEY auto_increment,
//        publisher VARCHAR(100),
//        title VARCHAR(200),
//        author VARCHAR(100),
//        url VARCHAR(120),
//        note VARCHAR(100),
//        page_start INT,
//        page_end INT,
//        num INT,
//        vol INT,
//        date YEAR,
//        visible TINYINT,
//        type TINYINT
//        );
//        INSERT INTO publication VALUES (1, 'XXX Journal', 'Paper Title', 'Authors', 'Paper Link', 'Your Note', 0, 100, 1, 1, 2020, 1, 1);
//        INSERT INTO publication VALUES (1, 'XXX Conference', 'Paper Title', 'Authors', 'Paper Link', 'Your Note', 0, 100, 1, 1, 2020, 1, 2);
//        INSERT INTO publication VALUES (1, 'Pre-print', 'Paper Title', 'Authors', 'Paper Link', 'Your Note', 0, 100, 1, 1, 2020, 1, 3);