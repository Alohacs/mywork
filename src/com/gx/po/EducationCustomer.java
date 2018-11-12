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
public class EducationCustomer {


    
  
    private Integer id;
    
    private String parent_name;
    private String student_name;
    private String student_sex;
    private String student_grade;
    private String phone_number;
    
    private String assignment_name;
    
    private String list_id;
    private String call_id;
    private String city;
    private String improve_subject;
    private String subject_record;
    private String interest_degree;
    private String convenient_time;
    private String invitation_time;
    private String remark;
    private String status;
    
    private String creation_time;
    private String update_time;
    
    
    private String type_parameter;
    private String scene_recognition;
    private String biggest_problem;

    private String camp_id;
    

    
    public EducationCustomer() {
//		super();
	}
    
    public EducationCustomer(Integer id,String parent_name,String student_name,
    		String student_sex,	String student_grade,String phone_number, String assignment_name,String list_id,
    		String call_id,String city, String improve_subject,String subject_record,
    		String interest_degree,String convenient_time,String invitation_time,
    		String remark,String status,String creation_time,String update_time,
    		String type_parameter,String scene_recognition,String biggest_problem,String camp_id) {
    	super();
    	this.id = id;
    	this.parent_name = parent_name;
    	this.student_name = student_name;
    	this.student_sex = student_sex;
    	this.student_grade = student_grade;
    	this.phone_number = phone_number;
    	this.list_id = list_id;
    	this.call_id = call_id;
    	this.city = city;
    	this.improve_subject = improve_subject;
    	this.subject_record = subject_record;
    	this.interest_degree = interest_degree;
    	this.convenient_time = convenient_time;
    	this.invitation_time = invitation_time;
    	this.remark = remark;
    	this.status = status;
    	this.assignment_name = assignment_name;
    	this.creation_time = creation_time;
    	this.update_time = update_time;
    	
    	this.type_parameter = type_parameter;
    	this.scene_recognition = scene_recognition;
    	this.biggest_problem = biggest_problem;
    	this.camp_id = camp_id;
    }




	public String getCamp_id() {
		return camp_id;
	}

	public void setCamp_id(String camp_id) {
		this.camp_id = camp_id;
	}

	public String getType_parameter() {
		return type_parameter;
	}

	public void setType_parameter(String type_parameter) {
		this.type_parameter = type_parameter;
	}

	public String getScene_recognition() {
		return scene_recognition;
	}

	public void setScene_recognition(String scene_recognition) {
		this.scene_recognition = scene_recognition;
	}

	public String getBiggest_problem() {
		return biggest_problem;
	}

	public void setBiggest_problem(String biggest_problem) {
		this.biggest_problem = biggest_problem;
	}

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getAssignment_name() {
		return assignment_name;
	}

	public void setAssignment_name(String assignment_name) {
		this.assignment_name = assignment_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_sex() {
		return student_sex;
	}

	public void setStudent_sex(String student_sex) {
		this.student_sex = student_sex;
	}

	public String getStudent_grade() {
		return student_grade;
	}

	public void setStudent_grade(String student_grade) {
		this.student_grade = student_grade;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getList_id() {
		return list_id;
	}

	public void setList_id(String list_id) {
		this.list_id = list_id;
	}

	public String getCall_id() {
		return call_id;
	}

	public void setCall_id(String call_id) {
		this.call_id = call_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getImprove_subject() {
		return improve_subject;
	}

	public void setImprove_subject(String improve_subject) {
		this.improve_subject = improve_subject;
	}

	public String getSubject_record() {
		return subject_record;
	}

	public void setSubject_record(String subject_record) {
		this.subject_record = subject_record;
	}

	public String getInterest_degree() {
		return interest_degree;
	}

	public void setInterest_degree(String interest_degree) {
		this.interest_degree = interest_degree;
	}

	public String getConvenient_time() {
		return convenient_time;
	}

	public void setConvenient_time(String convenient_time) {
		this.convenient_time = convenient_time;
	}

	public String getInvitation_time() {
		return invitation_time;
	}

	public void setInvitation_time(String invitation_time) {
		this.invitation_time = invitation_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    

}