package com.gx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationEf;

public interface EducationYinFuService {

	void insert(EducationEf detail);

	EducationEf selectByPrimaryKeyforEf(Integer id);

	void updateByPrimaryKeyEducationEf(EducationEf record);

	List<EducationEf> findBySQLforEducationEf(@Param(value="findBySQLforEducationEf") String findBySQL);

	EducationEf findforEEF(EducationEf detail);

	List<EducationEf> SelectYinFu(String sQL, int currentPage, int pageSize);
	
	

}

