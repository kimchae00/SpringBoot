package kr.co.todo.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.todo.service.AppService;
import kr.co.todo.vo.TodoVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppController {

	@Autowired
	private AppService service;
	
	@CrossOrigin(origins = "*")
	@GetMapping("todos")
	public List<TodoVO> todos() {
		return service.selectTodos();
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("todo")
	public void todo(@RequestBody TodoVO vo) {
		//log.info("content : " + vo.getContent());
		service.insertTodo(vo);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("remove")
	public void delete(int no) {
		//log.info("글번호 : " + no);
		service.deleteTodo(no);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("clear")
	public void clear() {
		service.deleteTodoAll();
	}
}
