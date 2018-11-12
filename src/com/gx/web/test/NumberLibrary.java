package com.gx.web.test;

public class NumberLibrary {

	
	private Integer id;
	private String mts;
	private String province;
	private String catName;
	private String telString;
	private String areaVid;
	private String ispVid;
	private String carrier;

	
	
	public NumberLibrary(Integer id,String mts,String province,String catName,
			String telString,String areaVid,String ispVid,String carrier){
		this.id = id;
		this.mts = mts;
		this.province = province;
		this.catName = catName;
		this.telString = telString;
		this.areaVid = areaVid;
		this.ispVid = ispVid;
		this.carrier = carrier;
		
		
		
	}
	
	
	public NumberLibrary() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMts() {
		return mts;
	}


	public void setMts(String mts) {
		this.mts = mts;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}


	public String getTelString() {
		return telString;
	}


	public void setTelString(String telString) {
		this.telString = telString;
	}


	public String getAreaVid() {
		return areaVid;
	}


	public void setAreaVid(String areaVid) {
		this.areaVid = areaVid;
	}


	public String getIspVid() {
		return ispVid;
	}


	public void setIspVid(String ispVid) {
		this.ispVid = ispVid;
	}


	public String getCarrier() {
		return carrier;
	}


	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	
	
}
