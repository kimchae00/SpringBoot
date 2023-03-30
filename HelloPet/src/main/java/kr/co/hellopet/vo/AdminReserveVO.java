package kr.co.hellopet.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminReserveVO {
	
	private int id;
	private Integer revNo;
	private String uid;
	private String medNo;
	private String oPh;
	private String oName;
	private String petNum;
	private String petType1;
	private String petType2;
	private String petName1;
	private String petName2;
	private int petAge1;
	private int petAge2;
	private String department;
	private String memo;
	private String revDate;
	private String revTime;
	private String rdate;
	private int status;
	
	private String medicalName;
	private int coupon;
	
	// 메시지
	private int msgNo;
	private String medical;
	private String title;
	private String content;
	private int msgStatus;
	private String msgRdate;

}
