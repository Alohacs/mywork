package com.gx.service;

import java.util.List;

import com.gx.po.Result;
import com.gx.vo.RowCount;

public interface ResultService {
//    int deleteByPrimaryKey(String id);

    int insert(Result record);

//    int insertSelective(Result record);

    Result selectByPrimaryKey(String id);

//    int updateByPrimaryKeySelective(Result record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
    List<Result> findBySQL(String SQL);
//    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
