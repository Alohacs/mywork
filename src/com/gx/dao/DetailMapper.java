package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.Detail;

public interface DetailMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int deleteByPrimaryKey(String id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
	void insert(Detail detail);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int insertSelective(Result record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	ReportDetail selectByPrimaryKeyforReportDetail(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int updateByPrimaryKeySelective(Result record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user
	 * @mbggenerated  Mon Nov 06 14:52:45 CST 2017
	 */
//	int updateByPrimaryKey(Result record);
    
//	List<ReportDetail> findBySQLforReportDetailEachTime(@Param(value="findBySQLforReportDetailEachTime") String findBySQL);
	
	List<Detail> findBySQLforDetail(@Param(value="findBySQLforDetail") String findBySQL);
	
	Detail findBySQLforDetailByCLSP(Detail detail);
	
	Detail findBySQLforDetailByULS(Detail detail);
	
  
}