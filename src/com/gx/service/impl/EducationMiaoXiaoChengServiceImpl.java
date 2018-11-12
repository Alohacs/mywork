package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.EducationMiaoXiaoChengMapper;
import com.gx.po.EducationMiaoXiaoCheng;
import com.gx.service.EducationMiaoXiaoChengService;





@Transactional
@Service(value="educationMiaoXiaoChengService")
public class EducationMiaoXiaoChengServiceImpl implements EducationMiaoXiaoChengService {

	@Autowired
	private EducationMiaoXiaoChengMapper educationMiaoXiaoChengMapper;

	@Override
	public void insert(EducationMiaoXiaoCheng detail) {
		// TODO Auto-generated method stub
		educationMiaoXiaoChengMapper.insert(detail);
	}

	@Override
	public EducationMiaoXiaoCheng selectByPrimaryKeyforMXC(Integer id) {
		// TODO Auto-generated method stub
		return educationMiaoXiaoChengMapper.selectByPrimaryKeyforMXC(id);
	}

	@Override
	public void updateByPrimaryKeyEducationMXC(EducationMiaoXiaoCheng record) {
		// TODO Auto-generated method stub
		educationMiaoXiaoChengMapper.updateByPrimaryKeyEducationMXC(record);
	}

	@Override
	public List<EducationMiaoXiaoCheng> findBySQLforEducationMXC(String findBySQL) {
		// TODO Auto-generated method stub
		return educationMiaoXiaoChengMapper.findBySQLforEducationMXC(findBySQL);
	}

	@Override
	public EducationMiaoXiaoCheng findforEMXC(EducationMiaoXiaoCheng detail) {
		// TODO Auto-generated method stub
		return educationMiaoXiaoChengMapper.findforEMXC(detail);
	}

	
	

}
