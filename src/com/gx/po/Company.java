package com.gx.po;

import java.util.List;

public class Company {

	
	private Integer id;
	private String company_id;
	private String company_name;
	private String status;
	private String created_time;

	
	public Company() {
		
	}
	
	public Company(Integer id,String company_id,String company_name,String status,String created_time) {
		
		this.id = id;
		this.company_id = company_id;
		this.company_name = company_name;
		this.status = status;
		this.created_time = created_time;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	
	
	
	
	
}
