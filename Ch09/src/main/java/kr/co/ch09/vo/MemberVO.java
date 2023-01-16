package kr.co.ch09.vo;

import java.time.LocalDateTime;

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
@Table(name="member")
public class MemberVO {
	@Id
	private String uid;
	private String pass;
	private String name;
	private String hp;
	private String pos;
	private String dep;
	private LocalDateTime rdate;

}
