package com.gx.po;

import java.util.HashMap;
import java.util.Map;

public class ReportResult {

	
	private Integer id;
	private String listID;
	private String status;
	private String phoneNumber;
	private String startTime;
	private String callTime;
	private String ringTime;
	private String talkTime;
	private String aiText;
	private String recordDownload;
	private String clientLevel;

	private Map<String,String> mapforQA;
	
	
	public ReportResult() {
		
	}
	
	public ReportResult( Integer id,String listID,String status,String phoneNumber,String startTime,String callTime,String ringTime
			,String talkTime,String aiText,String recordDownload,String clientLevel) {
		super();
		this.id = id;
		this.listID = listID;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.startTime = startTime;
		this.callTime = callTime;
		this.ringTime = ringTime;
		this.talkTime = talkTime;
		this.aiText = aiText;
		this.recordDownload = recordDownload;
		this.clientLevel = clientLevel;
		
		
	}

//	public void setMap(String key,Map<String, String> mapForQA){
//		Map<String,Map<String, String>> map=new HashMap();
//		map.put(key, mapForQA);
//	}
//	
//	
//	public Map<String,Map<String, String>> getMap(){
//		return 
//	}
	
	
	
	public Integer getId() {
		return id;
	}

	
	
	public Map<String, String> getMapforQA() {
		return mapforQA;
	}

	public void setMapforQA(Map<String, String> mapForQA2) {
		this.mapforQA = mapForQA2;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getRingTime() {
		return ringTime;
	}

	public void setRingTime(String ringTime) {
		this.ringTime = ringTime;
	}

	public String getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(String talkTime) {
		this.talkTime = talkTime;
	}

	public String getAiText() {
		return aiText;
	}

	public void setAiText(String aiText) {
		this.aiText = aiText;
	}

	public String getRecordDownload() {
		return recordDownload;
	}

	public void setRecordDownload(String recordDownload) {
		this.recordDownload = recordDownload;
	}

	public String getClientLevel() {
		return clientLevel;
	}

	public void setClientLevel(String clientLevel) {
		this.clientLevel = clientLevel;
	}
	
	
	
}
