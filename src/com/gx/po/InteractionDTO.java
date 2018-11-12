package com.gx.po;

import java.util.List;

/**
 * 交互详情对象
 */
public class InteractionDTO implements Comparable<InteractionDTO> {

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 输出
     */
    private String output;

    /**
     * 输入
     */
    private String input;

    /**
     * 问题ID
     */
    private String questionID;

    /**
     * 角色 包括 customer 和 role
     */
    private String role;

    /**
     * asr的sessionId
     */
    private String contentId;

    /**
     * 节点类型
     */
    private String nodeType;

    /**
     * 节点ID
     */
    private String nodeId;

    /**
     * 排序
     */
    private int seq;
    
    private String tagName;
    
    

    /**
     * 命中详情
     */
    private List<HitDTO> hits;

    
    
    public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public List<HitDTO> getHits() {
        return hits;
    }

    public void setHits(List<HitDTO> hits) {
        this.hits = hits;
    }


    @Override
    public int compareTo(InteractionDTO o) {
        return this.seq - o.seq;
    }
}
