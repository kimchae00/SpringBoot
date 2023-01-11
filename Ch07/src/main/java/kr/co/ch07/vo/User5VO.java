package kr.co.ch07.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user5")
public class User5VO {
	@Id
	private String uid;
	
	private String name;
	private String birth;
	private Integer gender;
	private int age;
	private String addr;
	private String hp;

}
