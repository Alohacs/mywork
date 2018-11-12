package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.PhoneNUMMapper;
import com.gx.po.PhoneNUM;
import com.gx.service.PhoneNUMService;


@Transactional
@Service(value="phoneNUMService")
public class PhoneNUMServiceImpl implements PhoneNUMService{

	@Autowired
	private PhoneNUMMapper phoneNUMMapper;

	@Override
	public void insert(PhoneNUM record) {
		// TODO Auto-generated method stub
		phoneNUMMapper.insert(record);
	}

	@Override
	public List<PhoneNUM> findBySQLforPhoneNUM(String SQL) {
		// TODO Auto-generated method stub
		return phoneNUMMapper.findBySQLforPhoneNUM(SQL);
	}

	@Override
	public void updateByPrimaryKeyforPhoneNUM(PhoneNUM record) {
		// TODO Auto-generated method stub
		phoneNUMMapper.updateByPrimaryKeyforPhoneNUM(record);
	}

	@Override
	public PhoneNUM findforPhoneNum(PhoneNUM record) {
		// TODO Auto-generated method stub
		return phoneNUMMapper.findforPhoneNum(record);
	}




}
