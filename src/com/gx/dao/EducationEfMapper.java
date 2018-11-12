package com.gx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationEf;



public interface EducationEfMapper {


	/*插入数据*/
	void insert(EducationEf detail);
    /*通过主键查找数据*/
	EducationEf selectByPrimaryKeyforEf(Integer id);
    /*通过主键更新数据*/
	void updateByPrimaryKeyEducationEf(EducationEf record);
    /*通过条件查询数据*/
	List<EducationEf> findBySQLforEducationEf(@Param(value="findBySQLforEducationEf") String findBySQL);
	/*判断是否已存在*/
	EducationEf findforEEF(EducationEf detail);
	/*分页查询*/
	List<EducationEf> selectYinFu(@Param(value="selectYinFu") String selectYinFu, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
	
}
