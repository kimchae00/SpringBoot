package kr.co.ch09.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user1")
public class User2VO {
	@Id
	private String uid;
	private String pass;
	private String name;
	private String hp;
	private int age;
	private String rdate;
}
