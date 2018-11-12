package com.gx.po;

import java.util.List;

public class TokenCode {

	
	private Integer id;
	private String randomNUM;
	private String systemTime;
	private String tokenCode;

	

	
	
	
	public TokenCode() {
		
	}
	
	
	public TokenCode(Integer id,String randomNUM,String systemTime,String tokenCode) {
		
		super();
		this.id = id;
		this.randomNUM = randomNUM;
		this.systemTime = systemTime;
		this.tokenCode = tokenCode;
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRandomNUM() {
		return randomNUM;
	}


	public void setRandomNUM(String randomNUM) {
		this.randomNUM = randomNUM;
	}


	public String getSystemTime() {
		return systemTime;
	}


	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}


	public String getTokenCode() {
		return tokenCode;
	}


	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	
	
	
}
