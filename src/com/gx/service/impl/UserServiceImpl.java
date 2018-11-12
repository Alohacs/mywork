package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.UserMapper;
import com.gx.po.User;
import com.gx.service.IUserService;
import com.gx.vo.RowCount;
import com.gx.vo.UserVo;

@Transactional
@Service(value="userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer userid) {
		return userMapper.deleteByPrimaryKey(userid);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer userid) {
		return userMapper.selectByPrimaryKey(userid);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public List<User> findBySQL(String SQL) {
		return userMapper.findBySQL(SQL);
	}

	@Override
	public RowCount rowCount(String SQL) {
		return userMapper.rowCount(SQL);
	}

	@Override
	public List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize) {
		return userMapper.findPageByVoSQL(SQL, currentPage, pageSize);
	}

	@Override
	public List<UserVo> findVoBySQL(String SQL) {
		return userMapper.findVoBySQL(SQL);
	}

}
