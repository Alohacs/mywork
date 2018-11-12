package com.gx.vo;

public class RowCount {
	private Integer count;//必须Integer类型

	/*
	 * 必须构造函数
	 */
	public RowCount(Integer count) {
		this.count = count;
	}
	
	public RowCount() {
		super();
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
