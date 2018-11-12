package com.gx.service;

import java.util.List;

import com.gx.po.PhoneNUM;



public interface PhoneNUMService {
//    int deleteByPrimaryKey(String id);

    void insert(PhoneNUM record);

//    int insertSelective(Result record);

//    ReportDetail selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Result record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
      List<PhoneNUM> findBySQLforPhoneNUM(String SQL);
    
      void updateByPrimaryKeyforPhoneNUM(PhoneNUM record);
      
//      Date findBySQLforCLD(Date record);
    
      PhoneNUM findforPhoneNum(PhoneNUM record);
//    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
