package kr.co.farmstory.controller;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private ArticleService service;

    @GetMapping("board/list")
    public String list(Model model, String group, @Param("cate") String cate, String pg){

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);

        int total = service.selectCountTotal(cate);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getpageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPageNum);

        List<ArticleVO> articles = service.selectArticles(start, cate);

        model.addAttribute("articles", articles);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "board/list";
    }


    @GetMapping("board/modify")
    public String modify(){
        return "board/modify";
    }

    @GetMapping("board/view")
    public String view(){
        return "board/view";
    }

    @GetMapping("board/write")
    public String write(Model model,String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/write";
    }

    @PostMapping("board/write")
    public String write(Model model,ArticleVO vo, HttpServletRequest req, String group, String cate){
        String regip = req.getRemoteAddr();
        vo.setRegip(regip);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        service.insertArticle(vo);

        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

}
