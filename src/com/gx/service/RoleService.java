package com.gx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.Role;


public interface RoleService {

	
	void insert(Role detail);
	
	List<Role> findBySQLforRole(@Param(value="findBySQLforRole") String findBySQL);
	
	Role findBySQLforRL(Role detail);
	
	void updateByPrimaryKeyforRole(Role record);
	
}
