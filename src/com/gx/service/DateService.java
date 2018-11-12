package com.gx.service;

import java.util.List;

import com.gx.po.Date;


public interface DateService {
//    int deleteByPrimaryKey(String id);

    void insert(Date record);

//    int insertSelective(Result record);

//    ReportDetail selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Result record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
      List<Date> findBySQL(String SQL);
    
      Date findBySQLforCLD(Date record);
    
//    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
