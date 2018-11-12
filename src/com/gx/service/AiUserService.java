package com.gx.service;

import java.util.List;

import com.gx.po.AiUser;
import com.gx.vo.AiUserRole;


public interface AiUserService {
    
	void updateByPrimaryKeyforAiuser(AiUser record);
	 
    void insert(AiUser record);

    AiUser selectByPrimaryKey(Integer userid);

    /**
     * 自定义方法
     */
    List<AiUser> findAll();
    
    List<AiUser> findBySQL(String SQL);
    
    List<AiUserRole> findRoleBySQL(String SQL);
    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
