package com.gx.service;

import java.util.List;

import com.gx.po.ReportDate;


public interface ReportDateService {
//    int deleteByPrimaryKey(String id);

//    int insert(ReportDetail record);

//    int insertSelective(Result record);

//    ReportDetail selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Result record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
    List<ReportDate> findBySQL(String SQL);
//    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
