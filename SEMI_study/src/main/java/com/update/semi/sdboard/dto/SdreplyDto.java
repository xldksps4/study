package com.update.semi.sdboard.dto;

//댓글
public class SdreplyDto {

	//댓글 번호(PK)
	private int sdreseq;
	
	//글 번호(FK)
	private int sdbseq;
	
	//댓글 제목
	private String sdretitle;
	
	//댓글 내용
	private String sdrecontent;
	
	//글 작성자
	private String sduemail;

	
	/* Contructer */
	
	public SdreplyDto() {};
	
	public SdreplyDto(int sdreseq, int sdbseq, String sdretitle, String sdrecontent, String sduemail) {
		super();
		this.sdreseq = sdreseq;
		this.sdbseq = sdbseq;
		this.sdretitle = sdretitle;
		this.sdrecontent = sdrecontent;
		this.sduemail = sduemail;
	}

	
	/* Getter Setter */
	
	public int getSdreseq() {
		return sdreseq;
	}

	public void setSdreseq(int sdreseq) {
		this.sdreseq = sdreseq;
	}

	public int getSdbseq() {
		return sdbseq;
	}

	public void setSdbseq(int sdbseq) {
		this.sdbseq = sdbseq;
	}

	public String getSdretitle() {
		return sdretitle;
	}

	public void setSdretitle(String sdretitle) {
		this.sdretitle = sdretitle;
	}

	public String getSdrecontent() {
		return sdrecontent;
	}

	public void setSdrecontent(String sdrecontent) {
		this.sdrecontent = sdrecontent;
	}

	public String getSduemail() {
		return sduemail;
	}

	public void setSduemail(String sduemail) {
		this.sduemail = sduemail;
	}

	/* toString */
	
	@Override
	public String toString() {
		return "SdreplyDto [sdreseq=" + sdreseq + ", sdbseq=" + sdbseq + ", sdretitle=" + sdretitle + ", sdrecontent="
				+ sdrecontent + ", sduemail=" + sduemail + "]";
	}
	
	
	
}
