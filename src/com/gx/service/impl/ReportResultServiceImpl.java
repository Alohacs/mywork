package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.ReportResultMapper;
import com.gx.po.ReportResult;
import com.gx.service.ReportResultService;


@Transactional
@Service(value="reportResultService")
public class ReportResultServiceImpl implements ReportResultService {

	@Autowired
	private ReportResultMapper reportResultMapper;

	
	@Override
	public void insert(ReportResult record) {
		// TODO Auto-generated method stub
		reportResultMapper.insert(record);
	}

	@Override
	public ReportResult selectByPrimaryKey(Integer userid) {
		// TODO Auto-generated method stub
		return reportResultMapper.selectByPrimaryKey(userid);
	}

	@Override
	public List<ReportResult> findAll() {
		// TODO Auto-generated method stub
		return reportResultMapper.findAll();
	}

	@Override
	public List<ReportResult> findBySQL(String SQL) {
		// TODO Auto-generated method stub
		return reportResultMapper.findBySQL(SQL);
	}

	@Override
	public void deleteByDATE(String deleteByDATE) {
		// TODO Auto-generated method stub
		reportResultMapper.deleteByDATE(deleteByDATE);
	}

	@Override
	public void updateByPrimaryKey(ReportResult record) {
		// TODO Auto-generated method stub
		reportResultMapper.updateByPrimaryKey(record);
	}

	
	

}
