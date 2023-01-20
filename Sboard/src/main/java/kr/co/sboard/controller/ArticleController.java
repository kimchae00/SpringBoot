package kr.co.sboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.sboard.service.ArticleService;
import kr.co.sboard.vo.ArticleVO;
import kr.co.sboard.vo.FileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	@GetMapping("list")
	public String list(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);

		int total = service.selectCountTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getpageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<ArticleVO> articles = service.selectArticles(start);
		model.addAttribute("articles", articles);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		return "list";
	}
	
	@GetMapping("modify")
	public String modify(Model model, int no, String pg) {
		int currentPage = service.getCurrentPage(pg);
		ArticleVO article = service.selectArticle(no);
		model.addAttribute("article",article);
		model.addAttribute("currentPage", currentPage);
		return "modify";
	}
	
	
	@PostMapping("modify")
	public String modify(ArticleVO vo, String pg) {
		service.updateArticle(vo);
		int currentPage = service.getCurrentPage(pg);
		return "redirect:/view?no="+vo.getNo()+"&pg="+currentPage;
	}
	
	@GetMapping("view")
	public String view(int no, Model model, String pg) {
		int currentPage = service.getCurrentPage(pg);
		
		ArticleVO article = service.selectArticle(no);
		model.addAttribute("article",article);
		model.addAttribute("currentPage", currentPage);
		return "view";
	}
	
	@GetMapping("download")
	public ResponseEntity<Resource> download(int fno) throws IOException {
		// 파일정보 조회
		FileVO vo = service.selectFile(fno);
		// 파일 다운로드 카운터 증가
		service.updateFileDownload(fno);
		
		// 파일 다운로드
		ResponseEntity<Resource> respEntity = service.fileDownload(vo);
		
		return respEntity;
	}

	@GetMapping("write")
	public String write() {
		return "write";
	}
	
	@PostMapping("write")
	public String write(ArticleVO vo, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		
		service.insertArticle(vo);
		return "redirect:/list";
	}
	
	@PostMapping("delete")
	public String delete(int no, String pg) {
		int currentPage = service.getCurrentPage(pg);
		service.deleteArticle(no);
		String fname = service.deleteFile(no);
		
		if(fname != null) {
		}
		
		return "redirect:/view?no="+no+"&pg="+currentPage;
	}
	
}
