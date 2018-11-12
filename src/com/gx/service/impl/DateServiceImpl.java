package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.DateMapper;
import com.gx.po.Date;
import com.gx.service.DateService;


@Transactional
@Service(value="dateService")
public class DateServiceImpl implements DateService{

	@Autowired
	private DateMapper dateMapper;


	@Override
	public List<Date> findBySQL(String SQL) {
		// TODO Auto-generated method stub
		return dateMapper.findBySQLforDate(SQL);
	}

	@Override
	public void insert(Date record) {
		// TODO Auto-generated method stub
		dateMapper.insert(record);
	}
	
	
	@Override
	public Date findBySQLforCLD(Date record) {
		// TODO Auto-generated method stub
		return dateMapper.findBySQLforCLD(record);
	}
	
//
//	@Override
//	public ReportDate selectByPrimaryKey(Integer id) {
//		// TODO Auto-generated method stub
//		return reportDateMapperMapper.selectByPrimaryKeyforReportDetail(id);
//	}
//	


}
