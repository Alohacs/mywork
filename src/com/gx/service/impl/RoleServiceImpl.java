package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.RoleMapper;
import com.gx.po.Role;
import com.gx.service.RoleService;




@Transactional
@Service(value="roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public void insert(Role detail) {
		// TODO Auto-generated method stub
		roleMapper.insert(detail);
	}

	@Override
	public List<Role> findBySQLforRole(String findBySQL) {
		// TODO Auto-generated method stub
		return roleMapper.findBySQLforRole(findBySQL);
	}

	@Override
	public Role findBySQLforRL(Role detail) {
		// TODO Auto-generated method stub
		return roleMapper.findBySQLforRL(detail);
	}

	@Override
	public void updateByPrimaryKeyforRole(Role record) {
		// TODO Auto-generated method stub
		roleMapper.updateByPrimaryKeyforRole(record);
	}




}
