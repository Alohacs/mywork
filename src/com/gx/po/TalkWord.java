package com.gx.po;

import java.util.List;

public class TalkWord {

	
	private Integer id;
	private String talk_word;
	private String level;
	private String status;
	
	
	


	
	public TalkWord() {
		
	}
	
	
	public TalkWord(Integer id,String talk_word,String level,String status) {
		
		super();
		this.id = id;
		this.talk_word = talk_word;
		this.level = level;
		this.status = status;
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTalk_word() {
		return talk_word;
	}


	public void setTalk_word(String talk_word) {
		this.talk_word = talk_word;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	
}
