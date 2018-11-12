package com.gx.po;

public class EducationEf {
	private Integer id;
	private String stu_name;
	private String stu_sex;
	private String stu_grade;
	private String parent_name;
	private String parent_phone;
	private String list_id;
	private String lead_id;
	private String call_id;
	private String city;
	private String status;
	private String created_time;
	private String update_time;
	private String q_Other;
	private String q_ChildAge;
	private String q_RegistrationActivities;
	private String interest_degree;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public String getStu_grade() {
		return stu_grade;
	}
	public void setStu_grade(String stu_grade) {
		this.stu_grade = stu_grade;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public String getParent_phone() {
		return parent_phone;
	}
	public void setParent_phone(String parent_phone) {
		this.parent_phone = parent_phone;
	}
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
	public String getLead_id() {
		return lead_id;
	}
	public void setLead_id(String lead_id) {
		this.lead_id = lead_id;
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
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getQ_Other() {
		return q_Other;
	}
	public void setQ_Other(String q_Other) {
		this.q_Other = q_Other;
	}
	public String getQ_ChildAge() {
		return q_ChildAge;
	}
	public void setQ_ChildAge(String q_ChildAge) {
		this.q_ChildAge = q_ChildAge;
	}
	public String getQ_RegistrationActivities() {
		return q_RegistrationActivities;
	}
	public void setQ_RegistrationActivities(String q_RegistrationActivities) {
		this.q_RegistrationActivities = q_RegistrationActivities;
	}
	public String getInterest_degree() {
		return interest_degree;
	}
	public void setInterest_degree(String interest_degree) {
		this.interest_degree = interest_degree;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public EducationEf() {
		
	}
	public EducationEf(Integer id, String stu_name, String stu_sex, String stu_grade, String parent_name,
			String parent_phone, String list_id, String lead_id, String call_id, String city, String status,
			String created_time, String update_time, String q_Other, String q_ChildAge, String q_RegistrationActivities,
			String interest_degree, String remark) {
		super();
		this.id = id;
		this.stu_name = stu_name;
		this.stu_sex = stu_sex;
		this.stu_grade = stu_grade;
		this.parent_name = parent_name;
		this.parent_phone = parent_phone;
		this.list_id = list_id;
		this.lead_id = lead_id;
		this.call_id = call_id;
		this.city = city;
		this.status = status;
		this.created_time = created_time;
		this.update_time = update_time;
		this.q_Other = q_Other;
		this.q_ChildAge = q_ChildAge;
		this.q_RegistrationActivities = q_RegistrationActivities;
		this.interest_degree = interest_degree;
		this.remark = remark;
	}
	
}
