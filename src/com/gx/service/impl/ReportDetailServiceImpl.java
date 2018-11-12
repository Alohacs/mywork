package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.ReportDetailMapper;
import com.gx.po.ReportDetail;
import com.gx.service.ReportDetailService;


@Transactional
@Service(value="reportDetailService")
public class ReportDetailServiceImpl implements ReportDetailService {

	@Autowired
	private ReportDetailMapper reportDetailMapperMapper;


	@Override
	public List<ReportDetail> findBySQL(String SQL) {
		// TODO Auto-generated method stub
		return reportDetailMapperMapper.findBySQLforReportDetail(SQL);
	}

	@Override
	public int insert(ReportDetail record) {
		// TODO Auto-generated method stub
		return reportDetailMapperMapper.insert(record);
	}

	@Override
	public ReportDetail selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return reportDetailMapperMapper.selectByPrimaryKeyforReportDetail(id);
	}

	@Override
	public List<ReportDetail> findBySQLforEachTime(String SQL) {
		// TODO Auto-generated method stub
		return reportDetailMapperMapper.findBySQLforReportDetailEachTime(SQL);
	}
	


}
