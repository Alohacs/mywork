package com.gx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.AiUser;
import com.gx.vo.AiUserRole;


public interface AiUserMapper {

//新增
	void insert(AiUser record);

//根据id 查找对象	
	AiUser selectByPrimaryKey(Integer userid);
	
//查询所有
	List<AiUser> findAll();

//查询账号密码
	List<AiUser> findBySQL(@Param(value="findBySQL") String findVoBySQL);
	
	//权限
	  List<AiUserRole> findRoleBySQL(@Param(value="findRoleBySQL") String findVoBySQL);
	
    //修改
	 void updateByPrimaryKeyforAiuser(AiUser record);
	  
}