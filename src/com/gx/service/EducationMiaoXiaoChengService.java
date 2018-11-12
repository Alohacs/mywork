package com.gx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.EducationMiaoXiaoCheng;



public interface EducationMiaoXiaoChengService {
//    int deleteByPrimaryKey(String id);

	void insert(EducationMiaoXiaoCheng detail);

//    int insertSelective(Result record);

	EducationMiaoXiaoCheng selectByPrimaryKeyforMXC(Integer id);


	void updateByPrimaryKeyEducationMXC(EducationMiaoXiaoCheng record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
	List<EducationMiaoXiaoCheng> findBySQLforEducationMXC(@Param(value="findBySQLforEducationMXC") String findBySQL);
    
	EducationMiaoXiaoCheng findforEMXC(EducationMiaoXiaoCheng detail);
//    
//      List<EducationCustomer> select_education_customerForLeftJoin(String findBySQL);
      
//    List<ReportDetail> findBySQLforEachTime(String SQL);
    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
