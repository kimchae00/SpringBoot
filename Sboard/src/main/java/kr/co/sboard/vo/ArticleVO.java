package kr.co.sboard.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ArticleVO {
	private int no;
	private int parent;
	private int comment;
	private String cate;
	private String title;
	private String content;
	private MultipartFile fname;
	private int file;
	private int hit;
	private String uid;
	private String regip;
	private String rdate;
	
	private String nick;
	private String oriName;
	private int download;
	
}
