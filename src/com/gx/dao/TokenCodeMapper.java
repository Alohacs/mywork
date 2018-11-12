package com.gx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gx.po.TokenCode;




public interface TokenCodeMapper {


	void insert(TokenCode record);


	TokenCode findByTokenCode(TokenCode record);

}