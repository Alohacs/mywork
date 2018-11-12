package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.ResultMapper;
import com.gx.po.Result;
import com.gx.service.ResultService;

@Transactional
@Service(value="resultService")
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultMapper resultMapper;

//	@Override
//	public int deleteByPrimaryKey(String id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public int insert(Result record) {
		// TODO Auto-generated method stub
		return resultMapper.insert(record);
	}

//	@Override
//	public int insertSelective(Result record) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public Result selectByPrimaryKey(String id) {
		return resultMapper.selectByPrimaryKeyforResult(id);
	}

//	@Override
//	public int updateByPrimaryKeySelective(Result record) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

//	@Override
//	public int updateByPrimaryKey(Result record) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public List<Result> findBySQL(String SQL) {
		// TODO Auto-generated method stub
		return resultMapper.findBySQLforResult(SQL);
	}
	


}
