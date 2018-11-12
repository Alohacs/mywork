package com.gx.vo;

import com.gx.po.AiUser;

public class AiUserRole extends AiUser {
	
	
	private Integer id;
	private String RoleName;
	private String Level;
	private String CreatTime;
	private String Status;


	/**
	 * 必须的构造方法
	 */
	public AiUserRole(Integer id,  String RoleName,String Level,String CreatTime,String Status,Integer userid,String nickname, String username, String password
			,String camp_id,String userstatus) {
		super();
		this.id = id;
		this.RoleName = RoleName;
		this.Level = Level;
		this.CreatTime = CreatTime;
		this.Status = Status;
		
		
		this.setAi_u_id(userid);
		this.setAi_u_nickname(nickname);
		this.setAi_u_username(username);
		this.setAi_u_password(password);
		this.setAi_u_campainID(camp_id);
		this.setAi_status(userstatus);
	}

	public AiUserRole() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getCreatTime() {
		return CreatTime;
	}

	public void setCreatTime(String creatTime) {
		CreatTime = creatTime;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	
	
	
}
