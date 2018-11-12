package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.CompanyMapper;
import com.gx.po.Company;
import com.gx.service.CompanyService;



@Transactional
@Service(value="CompanyMapper")
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public void insert(Company record) {
		// TODO Auto-generated method stub
		companyMapper.insert(record);
	}

	@Override
	public List<Company> findBySQLforCompany(String SQL) {
		// TODO Auto-generated method stub
		return companyMapper.findBySQLforCompany(SQL);
	}

	@Override
	public void updateByPrimaryKeyforCompany(Company record) {
		// TODO Auto-generated method stub
		companyMapper.updateByPrimaryKeyforCompany(record);
	}

	@Override
	public Company findforCompany(Company record) {
		// TODO Auto-generated method stub
		return companyMapper.findforCompany(record);
	}

	@Override
	public Company selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return companyMapper.selectByPrimaryKey(id);
	}

	




}
