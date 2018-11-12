package com.gx.service;

import java.util.List;

import com.gx.po.Detail;


public interface DetailService {
//    int deleteByPrimaryKey(String id);

	void insert(Detail detail);

//    int insertSelective(Result record);

//    ReportDetail selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Result record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
      List<Detail> findBySQL(String SQL);
    
      Detail findBySQLforDetailByCLSP(Detail detail);
    
      Detail findBySQLforDetailByULS(Detail detail);
      
//    
//    List<ReportDetail> findBySQLforEachTime(String SQL);
    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
