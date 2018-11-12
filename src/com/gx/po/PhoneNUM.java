package com.gx.po;

public class PhoneNUM {

	
	private Integer id;
	private String listID;
	private String callID;
	private String customerName;
	private String phoneNumber;
	private String status;
	private String camp_id;
	public PhoneNUM() {
		
	}
	public PhoneNUM(Integer id, String listID, String callID, String customerName, String phoneNumber, String status,
			String campid) {
		super();
		this.id = id;
		this.listID = listID;
		this.callID = callID;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.camp_id = campid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getListID() {
		return listID;
	}
	public void setListID(String listID) {
		this.listID = listID;
	}
	public String getCallID() {
		return callID;
	}
	public void setCallID(String callID) {
		this.callID = callID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCamp_id() {
		return camp_id;
	}
	public void setCamp_id(String camp_id) {
		this.camp_id = camp_id;
	}
	

}
