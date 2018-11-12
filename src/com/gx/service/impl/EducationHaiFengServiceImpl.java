package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.EducationHaiFengMapper;
import com.gx.po.EducationHaiFeng;
import com.gx.service.EducationHaiFengService;






@Transactional
@Service(value="educationHaiFengService")
public class EducationHaiFengServiceImpl implements EducationHaiFengService {

	@Autowired
	private EducationHaiFengMapper educationHaiFengMapper;

	@Override
	public void insert(EducationHaiFeng detail) {
		// TODO Auto-generated method stub
		educationHaiFengMapper.insert(detail);
	}

	@Override
	public EducationHaiFeng selectByPrimaryKeyforHF(Integer id) {
		// TODO Auto-generated method stub
		return educationHaiFengMapper.selectByPrimaryKeyforHF(id);
	}

	@Override
	public void updateByPrimaryKeyEducationHF(EducationHaiFeng record) {
		// TODO Auto-generated method stub
		educationHaiFengMapper.updateByPrimaryKeyEducationHF(record);
	}

	@Override
	public List<EducationHaiFeng> findBySQLforEducationHF(String findBySQL) {
		// TODO Auto-generated method stub
		return educationHaiFengMapper.findBySQLforEducationHF(findBySQL);
	}

	@Override
	public EducationHaiFeng findforEHF(EducationHaiFeng detail) {
		// TODO Auto-generated method stub
		return educationHaiFengMapper.findforEHF(detail);
	}

}
