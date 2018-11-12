/**
  * Copyright 2018 bejson.com 
  */
package com.gx.po;

/**
 * Auto-generated: 2018-06-11 15:21:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Date {

	private Integer id;
	private String campID;
	private String listID;
	private String date;
	private String total_calls;
	private String answers;
	private String answer_rate;
	private String no_answers;
	private String no_answer_rate;

	
	
	private String potential_customer;
	private String potential_customer_rate;
	

    
    public Date() {
//		super();
	}
    
    public Date(Integer id,String campID,String listID,String date,String total_calls,String answers,String answer_rate,
    		String no_answers,String no_answer_rate) {
    	super();
    	this.id = id;
    	this.campID = campID;
    	this.listID = listID;
    	this.date = date;
    	this.total_calls = total_calls;
    	this.answers = answers;
    	this.answer_rate = answer_rate;
    	this.no_answers = no_answers;
    	this.no_answer_rate = no_answer_rate;

    	
    }

    
    
 

	public String getPotential_customer() {
		return potential_customer;
	}

	public void setPotential_customer(String potential_customer) {
		this.potential_customer = potential_customer;
	}

	public String getPotential_customer_rate() {
		return potential_customer_rate;
	}

	public void setPotential_customer_rate(String potential_customer_rate) {
		this.potential_customer_rate = potential_customer_rate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCampID() {
		return campID;
	}

	public void setCampID(String campID) {
		this.campID = campID;
	}

	public String getListID() {
		return listID;
	}

	public void setListID(String listID) {
		this.listID = listID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTotal_calls() {
		return total_calls;
	}

	public void setTotal_calls(String total_calls) {
		this.total_calls = total_calls;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public String getAnswer_rate() {
		return answer_rate;
	}

	public void setAnswer_rate(String answer_rate) {
		this.answer_rate = answer_rate;
	}

	public String getNo_answers() {
		return no_answers;
	}

	public void setNo_answers(String no_answers) {
		this.no_answers = no_answers;
	}

	public String getNo_answer_rate() {
		return no_answer_rate;
	}

	public void setNo_answer_rate(String no_answer_rate) {
		this.no_answer_rate = no_answer_rate;
	}

    
    

}