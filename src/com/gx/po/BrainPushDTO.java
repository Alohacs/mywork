package com.gx.po;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Brain推送数据的DTO对象
 */
public class BrainPushDTO {

    /**
     * DNIS
     */
    private String originalDnis;

    /**
     * session-id
     */
    private String userData;

    /**
     * 动态信息存储
     */
    private JSONObject extention;

    /**
     * 话术ID
     */
    private String speechSkillId;

    /**
     * 被叫号码
     */
    private String callee;

    /**
     * 主叫号码
     */
    private String caller;

    /**
     * 任务数据ID
     */
    private long taskDataId;

    /**
     * calluuid唯一标识
     */
    private String callUUID;

    /**
     * 交互信息详情
     */
    private List<InteractionDTO> interactions;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 录音路径
     */
    private String mediaPath;

    /**
     * 事件
     */
    private int event;

    /**
     * 任务ID
     */
    private long taskId;


    private int direction;

    /**
     * ANI
     */
    private String originalAni;


    public String getOriginalDnis() {
        return originalDnis;
    }

    public void setOriginalDnis(String originalDnis) {
        this.originalDnis = originalDnis;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public JSONObject getExtention() {
        return extention;
    }

    public void setExtention(JSONObject extention) {
        this.extention = extention;
    }

    public String getSpeechSkillId() {
        return speechSkillId;
    }

    public void setSpeechSkillId(String speechSkillId) {
        this.speechSkillId = speechSkillId;
    }

    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public long getTaskDataId() {
        return taskDataId;
    }

    public void setTaskDataId(long taskDataId) {
        this.taskDataId = taskDataId;
    }

    public String getCallUUID() {
        return callUUID;
    }

    public void setCallUUID(String callUUID) {
        this.callUUID = callUUID;
    }

    public List getInteractions() {
        return interactions;
    }

    public void setInteractions(List interactions) {
        this.interactions = interactions;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getOriginalAni() {
        return originalAni;
    }

    public void setOriginalAni(String originalAni) {
        this.originalAni = originalAni;
    }


}