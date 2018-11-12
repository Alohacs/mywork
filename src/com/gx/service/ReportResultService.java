package com.gx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.ReportResult;



public interface ReportResultService {
    
	void updateByPrimaryKey(ReportResult record);
	 
    void insert(ReportResult record);

    ReportResult selectByPrimaryKey(Integer userid);

    /**
     * 自定义方法
     */
    List<ReportResult> findAll();
    
    List<ReportResult> findBySQL(String SQL);
    
    
    void deleteByDATE(String deleteByDATE);
//    
//    List<AiUserRole> findRoleBySQL(String SQL);
    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
