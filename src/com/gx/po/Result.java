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
public class Result {


    
   
    private String id;
    private String answer;
    private String answerTypeName;
    private String channelName;
    private String createdTime;
    private Integer fieldName;
    private String fromUser;
    private Integer inputModel;
    private String inputModelStr;
    private String question;
    private String robotName;
    private String standardQuestion;
    private String standardQuestionId;

    
    public Result() {
//		super();
	}
    
    public Result(String id, String answer,String answerTypeName,String channelName,String createdTime, Integer fieldName,String fromUser,Integer inputModel,
    		String inputModelStr, String question,String robotName,String standardQuestion,String standardQuestionId) {
    	super();
    	this.id = id;
    	this.answer = answer;
    	this.answerTypeName = answerTypeName;
    	this.channelName = channelName;
    	this.createdTime = createdTime;
    	this.fieldName = fieldName;
    	this.fromUser = fromUser;
    	this.inputModel = inputModel;
    	this.inputModelStr = inputModelStr;
    	this.question = question;
    	this.robotName = robotName;
    	this.standardQuestion = standardQuestion;
    	this.standardQuestionId = standardQuestionId;
    	
    	
    }
 
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerTypeName() {
		return answerTypeName;
	}
	public void setAnswerTypeName(String answerTypeName) {
		this.answerTypeName = answerTypeName;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getFieldName() {
		return fieldName;
	}
	public void setFieldName(Integer fieldName) {
		this.fieldName = fieldName;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public Integer getInputModel() {
		return inputModel;
	}
	public void setInputModel(Integer inputModel) {
		this.inputModel = inputModel;
	}
	public String getInputModelStr() {
		return inputModelStr;
	}
	public void setInputModelStr(String inputModelStr) {
		this.inputModelStr = inputModelStr;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getRobotName() {
		return robotName;
	}
	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}
	public String getStandardQuestion() {
		return standardQuestion;
	}
	public void setStandardQuestion(String standardQuestion) {
		this.standardQuestion = standardQuestion;
	}
	public String getStandardQuestionId() {
		return standardQuestionId;
	}
	public void setStandardQuestionId(String standardQuestionId) {
		this.standardQuestionId = standardQuestionId;
	}
    

}