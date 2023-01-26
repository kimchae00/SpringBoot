package kr.co.farmstory.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileVO {
    private int fno;
    private int parent;
    private String newName;
    private String oriName;
    private int download;
    private String rdate;

}
