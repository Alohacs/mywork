package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.ProjectCountMapper;
import com.gx.po.ProjectCount;
import com.gx.service.ProjectCountService;



@Transactional
@Service(value="projectCountService")
public class ProjectCountServiceImpl implements ProjectCountService{

	@Autowired
	private ProjectCountMapper projectCountMapper;
	
	@Override
	public void insert(ProjectCount record) {
		// TODO Auto-generated method stub
		projectCountMapper.insert(record);
	}

	@Override
	public void updateByPrimaryKey(ProjectCount record) {
		// TODO Auto-generated method stub
		projectCountMapper.updateByPrimaryKey(record);
	}

	@Override
	public ProjectCount findByProjectCount(String camp_id) {
		// TODO Auto-generated method stub
		return projectCountMapper.findByProjectCount(camp_id);
	}


	

}
