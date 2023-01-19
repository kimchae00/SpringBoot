package kr.co.sboard.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.sboard.service.ArticleService;
import kr.co.sboard.vo.ArticleVO;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	@GetMapping("/list")
	public String list(Principal principal, Model model) {
		List<ArticleVO> articles = service.selectArticles();
		model.addAttribute("articles", articles);
		return "/list";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return "/modify";
	}
	
	@GetMapping("/view")
	public String view(int no, Model model) {
		ArticleVO article = service.selectArticle(no);
		model.addAttribute("article",article);
		return "/view";
	}

	@GetMapping("/write")
	public String write() {
		return "/write";
	}
	
	@PostMapping("/write")
	public String write(ArticleVO vo, HttpServletRequest req) {
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		
		service.insertArticle(vo);
		return "redirect:/list";
	}
	
}
