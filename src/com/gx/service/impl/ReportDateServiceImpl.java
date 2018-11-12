package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.ReportDateMapper;
import com.gx.po.ReportDate;
import com.gx.service.ReportDateService;


@Transactional
@Service(value="repostDetailService")
public class ReportDateServiceImpl implements ReportDateService{

	@Autowired
	private ReportDateMapper reportDateMapperMapper;


	@Override
	public List<ReportDate> findBySQL(String SQL) {
		// TODO Auto-generated method stub
		return reportDateMapperMapper.findBySQLforReportDate(SQL);
	}

//	@Override
//	public int insert(ReportDate record) {
//		// TODO Auto-generated method stub
//		return reportDateMapperMapper.insert(record);
//	}
//
//	@Override
//	public ReportDate selectByPrimaryKey(Integer id) {
//		// TODO Auto-generated method stub
//		return reportDateMapperMapper.selectByPrimaryKeyforReportDetail(id);
//	}
//	


}
