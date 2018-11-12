package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.AiUserMapper;
import com.gx.po.AiUser;
import com.gx.service.AiUserService;
import com.gx.vo.AiUserRole;


@Transactional
@Service(value="aiUserService")
public class AiUserServiceImpl implements AiUserService {

	@Autowired
	private AiUserMapper aiUserMapper;

	@Override
	public void insert(AiUser record) {
		// TODO Auto-generated method stub
		aiUserMapper.insert(record);
	}

	@Override
	public AiUser selectByPrimaryKey(Integer userid) {
		// TODO Auto-generated method stub
		return aiUserMapper.selectByPrimaryKey(userid);
	}

	@Override
	public List<AiUser> findAll() {
		// TODO Auto-generated method stub
		return aiUserMapper.findAll();
	}

	@Override
	public List<AiUser> findBySQL(String SQL) {
		// TODO Auto-generated method stub
		return aiUserMapper.findBySQL(SQL);
	}

	@Override
	public List<AiUserRole> findRoleBySQL(String SQL) {
		// TODO Auto-generated method stub
		return aiUserMapper.findRoleBySQL(SQL);
	}

	@Override
	public void updateByPrimaryKeyforAiuser(AiUser record) {
		// TODO Auto-generated method stub
		aiUserMapper.updateByPrimaryKeyforAiuser(record);
	}
	
	

}
