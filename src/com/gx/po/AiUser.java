package com.gx.po;

public class AiUser {

	private Integer ai_u_id;
	private String ai_u_nickname;
	private String ai_u_username;
	private String ai_u_password;
	private String ai_u_roleID;
	private String ai_u_campainID;
	private String ai_status;
	private String ai_campID;
	
	private Role role;
	
	 public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public AiUser() {
//			super();
	 }
	    
	    public AiUser(Integer ai_u_id,String ai_u_nickname,String ai_u_username,String ai_u_password,String ai_u_roleID,
	    		String ai_u_campainID,String ai_status,String ai_campID) {
	    	super();
	    	this.ai_u_id = ai_u_id;
	    	this.ai_u_nickname = ai_u_nickname;
	    	this.ai_u_username = ai_u_username;
	    	this.ai_u_password = ai_u_password;
	    	this.ai_u_roleID = ai_u_roleID;
	    	this.ai_u_campainID = ai_u_campainID;
	    	this.ai_status = ai_status;
	    	this.ai_campID = ai_campID;

	    	
	    }
	
	
	public String getAi_campID() {
			return ai_campID;
		}

		public void setAi_campID(String ai_campID) {
			this.ai_campID = ai_campID;
		}

	public Integer getAi_u_id() {
		return ai_u_id;
	}
	public void setAi_u_id(Integer ai_u_id) {
		this.ai_u_id = ai_u_id;
	}
	public String getAi_u_nickname() {
		return ai_u_nickname;
	}
	public void setAi_u_nickname(String ai_u_nickname) {
		this.ai_u_nickname = ai_u_nickname;
	}
	public String getAi_u_username() {
		return ai_u_username;
	}
	public void setAi_u_username(String ai_u_username) {
		this.ai_u_username = ai_u_username;
	}
	public String getAi_u_password() {
		return ai_u_password;
	}
	public void setAi_u_password(String ai_u_password) {
		this.ai_u_password = ai_u_password == null ? null : ai_u_password.trim();;
	}
	public String getAi_u_roleID() {
		return ai_u_roleID;
	}
	public void setAi_u_roleID(String ai_u_roleID) {
		this.ai_u_roleID = ai_u_roleID;
	}
	public String getAi_u_campainID() {
		return ai_u_campainID;
	}
	public void setAi_u_campainID(String ai_u_campainID) {
		this.ai_u_campainID = ai_u_campainID;
	}
	public String getAi_status() {
		return ai_status;
	}
	public void setAi_status(String ai_status) {
		this.ai_status = ai_status;
	}

	
	
	
	
	
	
	
}