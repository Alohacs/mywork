package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.EducationEfMapper;
import com.gx.po.EducationEf;
import com.gx.service.EducationYinFuService;

@Transactional
@Service
public class EducationYinFuServiceImpl implements EducationYinFuService {

	@Autowired
	private EducationEfMapper educationEfMapper;

	@Override
	public void insert(EducationEf detail) {
		// TODO Auto-generated method stub
		educationEfMapper.insert(detail);
	}

	@Override
	public EducationEf selectByPrimaryKeyforEf(Integer id) {
		// TODO Auto-generated method stub
		return educationEfMapper.selectByPrimaryKeyforEf(id);
	}

	@Override
	public void updateByPrimaryKeyEducationEf(EducationEf record) {
		educationEfMapper.updateByPrimaryKeyEducationEf(record);
		
	}

	@Override
	public List<EducationEf> findBySQLforEducationEf(String findBySQL) {
		// TODO Auto-generated method stub
		return educationEfMapper.findBySQLforEducationEf(findBySQL);
	}

	@Override
	public EducationEf findforEEF(EducationEf detail) {
		// TODO Auto-generated method stub
		return educationEfMapper.findforEEF(detail);
	}

	@Override
	public List<EducationEf> SelectYinFu(String sQL, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return educationEfMapper.selectYinFu(sQL, currentPage, pageSize);
	}
	

	
	
		
		
	

	
}
