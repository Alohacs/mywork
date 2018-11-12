package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationMiaoXiaoCheng;



public interface EducationMiaoXiaoChengMapper {

	
//	int deleteByPrimaryKey(String id);

	
	void insert(EducationMiaoXiaoCheng detail);

	
	EducationMiaoXiaoCheng selectByPrimaryKeyforMXC(Integer id);


	void updateByPrimaryKeyEducationMXC(EducationMiaoXiaoCheng record);

	
//	int updateByPrimaryKey(Result record);
    
//	List<ReportDetail> findBySQLforReportDetailEachTime(@Param(value="findBySQLforReportDetailEachTime") String findBySQL);
	
	List<EducationMiaoXiaoCheng> findBySQLforEducationMXC(@Param(value="findBySQLforEducationMXC") String findBySQL);
	
	EducationMiaoXiaoCheng findforEMXC(EducationMiaoXiaoCheng detail);
  
//	List<EducationMiaoXiaoCheng> select_education_customerForLeftJoin(@Param(value="select_education_customerForLeftJoin") String findBySQL);
	
}