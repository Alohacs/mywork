package com.gx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.ReportResult;



public interface ReportResultMapper {

//新增
	void insert(ReportResult record);

//根据id 查找对象	
	ReportResult selectByPrimaryKey(Integer userid);
	
//查询所有
	List<ReportResult> findAll();

//查询账号密码
	List<ReportResult> findBySQL(@Param(value="findBySQL") String findVoBySQL);
	
//删除
	void deleteByDATE(@Param(value="deleteByDATE") String findVoBySQL);

	
//	//权限
//	  List<AiUserRole> findRoleBySQL(@Param(value="findRoleBySQL") String findVoBySQL);
//	
//    //修改
	 void updateByPrimaryKey(ReportResult record);
	  
}