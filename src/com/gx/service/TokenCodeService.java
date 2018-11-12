package com.gx.service;


import com.gx.po.TokenCode;



public interface TokenCodeService {

	
	void insert(TokenCode record);


	TokenCode findByTokenCode(TokenCode record);
	
}
