package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.ProjectCount;



public interface ProjectCountMapper {

	void insert(ProjectCount record);
	
	void updateByPrimaryKey(ProjectCount record);
	
	ProjectCount findByProjectCount(String camp_id);
	
	
	
}