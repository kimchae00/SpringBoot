package kr.co.farmstory.controller;

import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    @GetMapping("board/view")
    public String view(Model model, int no,String group, String cate,String pg){
        int currentPage = service.getCurrentPage(pg);
        ArticleVO article = service.selectArticle(no);
        List<ArticleVO> comments = service.selectComments(no);
        service.updateArticleHit(no);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", currentPage);
        return "board/view";
    }

    @GetMapping("board/download")
    public ResponseEntity<Resource> download(int fno) throws IOException {
        FileVO vo = service.selectFile(fno);
        service.updateFileDownload(fno);
        ResponseEntity<Resource> respEntity = service.fileDownload(vo);

        return respEntity;

    }
    @GetMapping("board/modify")
    public String modify(Model model,int no,String group, String cate,String pg){
        ArticleVO article = service.selectArticle(no);
        int currentPage = service.getCurrentPage(pg);
        model.addAttribute("article", article);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("currentPage", currentPage);
        return "board/modify";
    }

    @PostMapping("board/modify")
    public String modify(ArticleVO vo,String group, String cate, String pg){
        int currentPage = service.getCurrentPage(pg);
        service.updateArticle(vo);
        return "redirect:/board/view?group="+group+"&cate="+cate+"&no="+vo.getNo()+"&pg="+currentPage;
    }

    @GetMapping("board/write")
    public String write(Model model,String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        return "board/write";
    }

    @PostMapping("board/write")
    public String write(Model model,ArticleVO vo, HttpServletRequest req, String group, String cate, String pg){
        int currentPage = service.getCurrentPage(pg);
        String regip = req.getRemoteAddr();
        vo.setRegip(regip);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        service.insertArticle(vo);

        return "redirect:/board/list?group="+group+"&cate="+cate+"&pg="+currentPage;
    }


    @GetMapping("board/delete")
    public String delete(ArticleVO vo,String group, String cate, String pg, HttpServletRequest req){
        int currentPage = service.getCurrentPage(pg);
        service.deleteArticle(vo.getNo());
        int fname = service.deleteFile(vo.getNo());

        if(fname != 1){

            ServletContext ctx = req.getServletContext();
            String path = ctx.getRealPath("/file");

            File file = new File(path, String.valueOf(fname));

            if(file.exists()){
                file.delete();
            }
        }

        return "redirect:/board/list?group="+group+"&cate="+cate+"&pg="+currentPage;
    }

}
