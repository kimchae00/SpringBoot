package kr.co.farmstory.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


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

    public String getRdate() {
        return rdate.substring(2, 10);
    }
    // 추가필드
    private String nick;
    private FileVO fileVO;

}
