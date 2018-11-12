package com.gx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationCustomer;



public interface EducationCustomerService {
//    int deleteByPrimaryKey(String id);

	void insert(EducationCustomer detail);

//    int insertSelective(Result record);

    EducationCustomer selectByPrimaryKeyforEducationCustomer(Integer id);

    void updateByPrimaryKeyEducationCustomer(EducationCustomer record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
      List<EducationCustomer> findBySQLforEducationCustomer(String SQL);
    
      EducationCustomer findforEducationCustomer(EducationCustomer detail);
    
      List<EducationCustomer> select_education_customerForLeftJoin(String findBySQL);
      
//    List<ReportDetail> findBySQLforEachTime(String SQL);
    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
