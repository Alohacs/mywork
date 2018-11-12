package com.gx.vo;

import com.gx.po.User;

public class UserVo extends User {
	private Integer powerid;

	private String powername;

	/**
	 * 必须的构造方法
	 */
	public UserVo(Integer userid, String username, String password, Integer powerid, String powername) {
		this.powerid = powerid;
		this.powername = powername;
		this.setUserid(userid);
		this.setUsername(username);
		this.setPassword(password);
	}

	public UserVo() {
		super();
	}

	public Integer getPowerid() {
		return powerid;
	}

	public void setPowerid(Integer powerid) {
		this.powerid = powerid;
	}

	public String getPowername() {
		return powername;
	}

	public void setPowername(String powername) {
		this.powername = powername;
	}
}
