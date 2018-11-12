package com.gx.service;

import java.util.List;

import com.gx.po.Company;



public interface CompanyService {
//    int deleteByPrimaryKey(String id);

    void insert(Company record);

//    int insertSelective(Result record);

    Company selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Result record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
      List<Company> findBySQLforCompany(String SQL);
    
      void updateByPrimaryKeyforCompany(Company record);
      
//      Date findBySQLforCLD(Date record);
    
      Company findforCompany(Company record);
//    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
