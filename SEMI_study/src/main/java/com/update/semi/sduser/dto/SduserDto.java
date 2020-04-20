package com.update.semi.sduser.dto;

import java.util.Date;

//회원 
public class SduserDto {

	// 유저시퀀스
	private int sduseq;

	// 아이디
	private String sdueamil;

	// 비번
	private String sdupw;

	// 이름
	private String sduname;

	// 성별
	private String sdusex;

	// 닉네임
	private String sdunick;

	// 생년월일
	private Date sdudob;

	// 탈퇴여부
	private String sdudeact;

	// 회원등급
	private String sdugrade;

	// 탈퇴날짜
	private Date sdudeactdate;

	// 가입날짜
	private Date sduregdate;

// 생성자
	
	public SduserDto() {
		
	}
	
	public SduserDto(int sduseq, String sdueamil, String sdupw, String sduname, String sdusex, String sdunick,
			Date sdudob, String sdudeact, String sdugrade, Date sdudeactdate, Date sduregdate) {
		super();
		this.sduseq = sduseq;
		this.sdueamil = sdueamil;
		this.sdupw = sdupw;
		this.sduname = sduname;
		this.sdusex = sdusex;
		this.sdunick = sdunick;
		this.sdudob = sdudob;
		this.sdudeact = sdudeact;
		this.sdugrade = sdugrade;
		this.sdudeactdate = sdudeactdate;
		this.sduregdate = sduregdate;
	}

// Getter & Setter

	public int getSduseq() {
		return sduseq;
	}

	public void setSduseq(int sduseq) {
		this.sduseq = sduseq;
	}

	public String getSdueamil() {
		return sdueamil;
	}

	public void setSdueamil(String sdueamil) {
		this.sdueamil = sdueamil;
	}

	public String getSdupw() {
		return sdupw;
	}

	public void setSdupw(String sdupw) {
		this.sdupw = sdupw;
	}

	public String getSduname() {
		return sduname;
	}

	public void setSduname(String sduname) {
		this.sduname = sduname;
	}

	public String getSdusex() {
		return sdusex;
	}

	public void setSdusex(String sdusex) {
		this.sdusex = sdusex;
	}

	public String getSdunick() {
		return sdunick;
	}

	public void setSdunick(String sdunick) {
		this.sdunick = sdunick;
	}

	public Date getSdudob() {
		return sdudob;
	}

	public void setSdudob(Date sdudob) {
		this.sdudob = sdudob;
	}

	public String getSdudeact() {
		return sdudeact;
	}

	public void setSdudeact(String sdudeact) {
		this.sdudeact = sdudeact;
	}

	public String getSdugrade() {
		return sdugrade;
	}

	public void setSdugrade(String sdugrade) {
		this.sdugrade = sdugrade;
	}

	public Date getSdudeactdate() {
		return sdudeactdate;
	}

	public void setSdudeactdate(Date sdudeactdate) {
		this.sdudeactdate = sdudeactdate;
	}

	public Date getSduregdate() {
		return sduregdate;
	}

	public void setSduregdate(Date sduregdate) {
		this.sduregdate = sduregdate;
	}

	@Override
	public String toString() {
		return "RegisterDto [sduseq=" + sduseq + ", sdueamil=" + sdueamil + ", sdupw=" + sdupw + ", sduname=" + sduname
				+ ", sdusex=" + sdusex + ", sdunick=" + sdunick + ", sdudob=" + sdudob + ", sdudeact=" + sdudeact
				+ ", sdugrade=" + sdugrade + ", sdudeactdate=" + sdudeactdate + ", sduregdate=" + sduregdate + "]";
	}

}
