package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.TokenCodeMapper;
import com.gx.po.TokenCode;
import com.gx.service.TokenCodeService;



@Transactional
@Service(value="tokenCodeService")
public class TokenCodeServiceImpl implements TokenCodeService{

	@Autowired
	private TokenCodeMapper tokenCodeMapper;

	@Override
	public void insert(TokenCode record) {
		// TODO Auto-generated method stub
		tokenCodeMapper.insert(record);
	}

	@Override
	public TokenCode findByTokenCode(TokenCode record) {
		// TODO Auto-generated method stub
		return tokenCodeMapper.findByTokenCode(record);
	}





}
