package com.gx.po;

public class Mission {
	
	
	private Integer id;
	private String assignment_name;
	
	private String shut_time;
	private String start_time;
	
	private String created_time;
	
	private String listNAME;
	private String listID;

	
	private String excel_name;
	private String exhalation_time;
	private String status;
	
	private String isCompletel;

	private String ai_campID;
	
	public int callNum;     //外呼数量
	public int unCallNum;   //未呼数量
	
	public Mission() {
		
	}	
	
	public Mission(Integer id,String assignment_name,   
			String shut_time,String start_time,
			String created_time,String listNAME,
			String listID,String excel_name
			,String exhalation_time,String status,String isCompletel,String ai_campID
			) {
		this.id = id;
		this.assignment_name = assignment_name;
		this.created_time = created_time;
		this.listNAME = listNAME;
		this.listID = listID;
		this.excel_name = excel_name; 
		this.exhalation_time = exhalation_time;
		this.status = status;
		this.start_time = start_time;
		this.shut_time = shut_time;
		this.isCompletel = isCompletel;
		this.ai_campID = ai_campID;
			
		
	}
	
	
	

	public String getAi_campID() {
		return ai_campID;
	}

	public void setAi_campID(String ai_campID) {
		this.ai_campID = ai_campID;
	}

	public String getListNAME() {
		return listNAME;
	}

	public void setListNAME(String listNAME) {
		this.listNAME = listNAME;
	}

	public int getCallNum() {
		return callNum;
	}

	public void setCallNum(int callNum) {
		this.callNum = callNum;
	}

	public int getUnCallNum() {
		return unCallNum;
	}

	public void setUnCallNum(int unCallNum) {
		this.unCallNum = unCallNum;
	}

	public String getIsCompletel() {
		return isCompletel;
	}

	public void setIsCompletel(String isCompletel) {
		this.isCompletel = isCompletel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShut_time() {
		return shut_time;
	}

	public void setShut_time(String shut_time) {
		this.shut_time = shut_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getAssignment_name() {
		return assignment_name;
	}

	public void setAssignment_name(String assignment_name) {
		this.assignment_name = assignment_name;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getListID() {
		return listID;
	}

	public void setListID(String listID) {
		this.listID = listID;
	}

	public String getExcel_name() {
		return excel_name;
	}

	public void setExcel_name(String excel_name) {
		this.excel_name = excel_name;
	}

	public String getExhalation_time() {
		return exhalation_time;
	}

	public void setExhalation_time(String exhalation_time) {
		this.exhalation_time = exhalation_time;
		
		
		
		
		
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
