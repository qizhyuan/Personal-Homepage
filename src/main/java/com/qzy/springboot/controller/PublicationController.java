package com.qzy.springboot.controller;

import com.qzy.springboot.entities.Paper;
import com.qzy.springboot.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class PublicationController {
    @Autowired
    PaperMapper paperMapper;
    @RequestMapping("/publication")
    public String publication(Map<String, Object> map, HttpServletRequest request) {
        List<Paper> jouPaperList = paperMapper.getVisibleJouPaper();
        List<Paper> conPaperList = paperMapper.getVisibleConPaper();
        List<Paper> prePaperList = paperMapper.getVisiblePrePaper();
        map.put("jouPaperList", jouPaperList);
        map.put("conPaperList", conPaperList);
        map.put("prePaperList", prePaperList);
        return "publication";
    }
}
