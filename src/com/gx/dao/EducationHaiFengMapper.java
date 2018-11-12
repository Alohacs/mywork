package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationHaiFeng;




public interface EducationHaiFengMapper {

	
//	int deleteByPrimaryKey(String id);

	
	void insert(EducationHaiFeng detail);

	
	EducationHaiFeng selectByPrimaryKeyforHF(Integer id);


	void updateByPrimaryKeyEducationHF(EducationHaiFeng record);

	
//	int updateByPrimaryKey(Result record);
    
//	List<ReportDetail> findBySQLforReportDetailEachTime(@Param(value="findBySQLforReportDetailEachTime") String findBySQL);
	
	List<EducationHaiFeng> findBySQLforEducationHF(@Param(value="findBySQLforEducationHF") String findBySQL);
	
	EducationHaiFeng findforEHF(EducationHaiFeng detail);
  
//	List<EducationMiaoXiaoCheng> select_education_customerForLeftJoin(@Param(value="select_education_customerForLeftJoin") String findBySQL);
	
}