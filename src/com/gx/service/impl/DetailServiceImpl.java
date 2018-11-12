package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.DetailMapper;
import com.gx.po.Detail;
import com.gx.service.DetailService;



@Transactional
@Service(value="detailService")
public class DetailServiceImpl implements DetailService {

	@Autowired
	private DetailMapper detailMapper;


	@Override
	public List<Detail> findBySQL(String SQL) {
		// TODO Auto-generated method stub
		return detailMapper.findBySQLforDetail(SQL);
	}
//
	@Override
	public void insert(Detail record) {
		detailMapper.insert(record);
	}
//
//	@Override
//	public ReportDetail selectByPrimaryKey(Integer id) {
//		// TODO Auto-generated method stub
//		return reportDetailMapperMapper.selectByPrimaryKeyforReportDetail(id);
//	}
//
//	@Override
//	public List<ReportDetail> findBySQLforEachTime(String SQL) {
//		// TODO Auto-generated method stub
//		return reportDetailMapperMapper.findBySQLforReportDetailEachTime(SQL);
//	}
//	


	@Override
	public Detail findBySQLforDetailByCLSP(Detail detail) {
		// TODO Auto-generated method stub
		return detailMapper.findBySQLforDetailByCLSP(detail);
	}


	@Override
	public Detail findBySQLforDetailByULS(Detail detail) {
		// TODO Auto-generated method stub
		return detailMapper.findBySQLforDetailByULS(detail);
	}

}
