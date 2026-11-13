package com.testvue.sample.board;

/**
 * <pre>
 * 샘플게시판 글(TBTS_SAMPLE_BOARD). 삭제하면 USE_YN = 'N'.
 * </pre>
 */
public class Board {

	private Long seq;
	private String title;
	private String content;
	private String writerNm;
	private String inDtm;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriterNm() {
		return writerNm;
	}

	public void setWriterNm(String writerNm) {
		this.writerNm = writerNm;
	}

	public String getInDtm() {
		return inDtm;
	}

	public void setInDtm(String inDtm) {
		this.inDtm = inDtm;
	}
}
