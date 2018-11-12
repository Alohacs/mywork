/**
  * Copyright 2018 bejson.com 
  */
package com.gx.po;
import java.util.Date;

/**
 * Auto-generated: 2018-06-11 15:21:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Role {


	private Integer id;
	private String RoleName;
	private String Level;
	private String CreatTime;
	private String Status;

    
    public Role() {
//		super();
	}
    
    public Role(Integer id, String RoleName,String Level,String CreatTime,String Status) {
    	super();
    	this.id = id;
    	this.RoleName = RoleName;
    	this.Level = Level;
    	this.CreatTime = CreatTime;
    	this.Status = Status;
    			
    	
    	
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