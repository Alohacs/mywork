package com.gx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationHaiFeng;




public interface EducationHaiFengService {
//    int deleteByPrimaryKey(String id);

	void insert(EducationHaiFeng detail);

//    int insertSelective(Result record);

	EducationHaiFeng selectByPrimaryKeyforHF(Integer id);


	void updateByPrimaryKeyEducationHF(EducationHaiFeng record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
	List<EducationHaiFeng> findBySQLforEducationHF(@Param(value="findBySQLforEducationHF") String findBySQL);
    
	EducationHaiFeng findforEHF(EducationHaiFeng detail);
//    
//      List<EducationCustomer> select_education_customerForLeftJoin(String findBySQL);
      
//    List<ReportDetail> findBySQLforEachTime(String SQL);
    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
