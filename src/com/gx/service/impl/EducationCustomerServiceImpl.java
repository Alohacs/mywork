package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.EducationCustomerMapper;
import com.gx.po.EducationCustomer;
import com.gx.service.EducationCustomerService;




@Transactional
@Service(value="educationCustomerService")
public class EducationCustomerServiceImpl implements EducationCustomerService {

	@Autowired
	private EducationCustomerMapper educationCustomerMapper;

	@Override
	public void insert(EducationCustomer detail) {
		// TODO Auto-generated method stub
		educationCustomerMapper.insert(detail);
	}

	@Override
	public EducationCustomer selectByPrimaryKeyforEducationCustomer(Integer id) {
		// TODO Auto-generated method stub
		return educationCustomerMapper.selectByPrimaryKeyforEducationCustomer(id);
	}

	@Override
	public void updateByPrimaryKeyEducationCustomer(EducationCustomer record) {
		// TODO Auto-generated method stub
		educationCustomerMapper.updateByPrimaryKeyEducationCustomer(record);
	}

	@Override
	public List<EducationCustomer> findBySQLforEducationCustomer(String SQL) {
		// TODO Auto-generated method stub
		return educationCustomerMapper.findBySQLforEducationCustomer(SQL);
	}

	@Override
	public EducationCustomer findforEducationCustomer(EducationCustomer detail) {
		// TODO Auto-generated method stub
		return educationCustomerMapper.findforEducationCustomer(detail);
	}

	@Override
	public List<EducationCustomer> select_education_customerForLeftJoin(String findBySQL) {
		// TODO Auto-generated method stub
		return educationCustomerMapper.select_education_customerForLeftJoin(findBySQL);
	}



}
