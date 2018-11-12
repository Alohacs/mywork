package com.gx.service;

import java.util.List;

import com.gx.po.User;
import com.gx.vo.RowCount;
import com.gx.vo.UserVo;

public interface IUserService {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 自定义方法
     */
    List<User> findAll();
    
    List<User> findBySQL(String SQL);
    
    List<UserVo> findVoBySQL(String SQL);
    
    RowCount rowCount(String SQL);
    
    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
