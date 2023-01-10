package kr.co.ch07.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User5VO {
	private String uid;
	private String name;
	private String birth;
	private int gender;
	private int age;
	private String addr;
	private String hp;

}
