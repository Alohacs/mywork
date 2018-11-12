package com.gx.web.test;

public class ExcelLibrary {

	
	private Integer id;	
	private Integer pref	;
	private Integer phone	;
	private String province	;
	private String city	;
	private String isp	;
	private Integer post_code	;
	private Integer area_code	;
	private String types;
	
	public  ExcelLibrary() {
		// TODO Auto-generated method stub

	}
	
	
	public ExcelLibrary(Integer id,Integer pref,Integer phone,String province,String city,String isp,Integer post_code,
			Integer area_code,String types) {
		 this.id = id;
		 this.pref = pref;
		 this.phone = phone;
		 this.province = province;
		 this.city = city;
		 this.isp = isp;
		 this.post_code = post_code;
		 this.area_code = area_code;
		 this.types = types;
		
		
		
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPref() {
		return pref;
	}
	public void setPref(Integer pref) {
		this.pref = pref;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIsp() {
		return isp;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public Integer getPost_code() {
		return post_code;
	}
	public void setPost_code(Integer post_code) {
		this.post_code = post_code;
	}
	public Integer getArea_code() {
		return area_code;
	}
	public void setArea_code(Integer area_code) {
		this.area_code = area_code;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}

	
	
}
