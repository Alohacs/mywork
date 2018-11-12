package com.gx.service;

import java.util.List;


import com.gx.po.Mission;


public interface MissionService {
//    int deleteByPrimaryKey(String id);

	void insert(Mission record);

//    int insertSelective(Result record);

//    ReportDetail selectByPrimaryKey(Integer id);

	Mission selectByPrimaryKeyforMission(Integer id);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
	void updateByPrimaryKey(Mission record);
    
	List<Mission> findBySQLforMission(String findBySQL);
    
//    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
