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
public class ReportDetail {


    
  
    private Integer id;
    private String campaign_id;
    private String list_id;
    private String lead_id;
    private String start_date;
    private String end_date;
    private String phone_number;
    private String uniqueid;
    private String status;
    private String achieve_date;
    private String call_time;
    private String ring_time;
    private String talk_time;
    private String download;

    private String eachtime;
    private String eachcount;
    
    public ReportDetail() {
//		super();
	}
    
    public ReportDetail(Integer id,String campaign_id,String list_id,
    		String lead_id,String start_date,String end_date,
    		String phone_number,String uniqueid,String status,
    		String achieve_date,String call_time,String ring_time,
    		String talk_time,String download,String eachtime,String eachcount) {
    	super();
    	this.id = id;
    	this.campaign_id  = campaign_id;
    	this.list_id = list_id;
    	this.lead_id = lead_id;
    	this.start_date = start_date;
    	this.end_date = end_date;
    	this.phone_number = phone_number;
    	this.uniqueid = uniqueid;
    	this.status = status;
    	this.achieve_date = achieve_date;
    	this.call_time = call_time;
    	this.ring_time = ring_time;
    	this.talk_time = talk_time;
    	this.download = download;

    	this.eachcount = eachcount;
    	this.eachtime = eachtime;
    	
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
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

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAchieve_date() {
		return achieve_date;
	}

	public void setAchieve_date(String achieve_date) {
		this.achieve_date = achieve_date;
	}

	public String getCall_time() {
		return call_time;
	}

	public void setCall_time(String call_time) {
		this.call_time = call_time;
	}

	public String getRing_time() {
		return ring_time;
	}

	public void setRing_time(String ring_time) {
		this.ring_time = ring_time;
	}

	public String getTalk_time() {
		return talk_time;
	}

	public void setTalk_time(String talk_time) {
		this.talk_time = talk_time;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getEachtime() {
		return eachtime;
	}

	public void setEachtime(String eachtime) {
		this.eachtime = eachtime;
	}

	public String getEachcount() {
		return eachcount;
	}

	public void setEachcount(String eachcount) {
		this.eachcount = eachcount;
	}
    	
    	

}