package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationCustomer;


public interface EducationCustomerMapper {

	
//	int deleteByPrimaryKey(String id);

	
	void insert(EducationCustomer detail);

	
	EducationCustomer selectByPrimaryKeyforEducationCustomer(Integer id);


	void updateByPrimaryKeyEducationCustomer(EducationCustomer record);

	
//	int updateByPrimaryKey(Result record);
    
//	List<ReportDetail> findBySQLforReportDetailEachTime(@Param(value="findBySQLforReportDetailEachTime") String findBySQL);
	
	List<EducationCustomer> findBySQLforEducationCustomer(@Param(value="findBySQLforEducationCustomer") String findBySQL);
	
	EducationCustomer findforEducationCustomer(EducationCustomer detail);
  
	List<EducationCustomer> select_education_customerForLeftJoin(@Param(value="select_education_customerForLeftJoin") String findBySQL);
	
}