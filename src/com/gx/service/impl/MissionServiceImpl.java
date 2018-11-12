package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.DateMapper;
import com.gx.dao.MissionMapper;
import com.gx.po.Date;
import com.gx.po.Mission;
import com.gx.service.DateService;
import com.gx.service.MissionService;


@Transactional
@Service(value="missionService")
public class MissionServiceImpl implements MissionService{

	@Autowired
	private MissionMapper missionMapper;

	@Override
	public void insert(Mission record) {
		// TODO Auto-generated method stub
		missionMapper.insert(record);
	}

	@Override
	public Mission selectByPrimaryKeyforMission(Integer id) {
		// TODO Auto-generated method stub
		return missionMapper.selectByPrimaryKeyforMission(id);
	}

	@Override
	public void updateByPrimaryKey(Mission record) {
		// TODO Auto-generated method stub
		missionMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Mission> findBySQLforMission(String findBySQL) {
		// TODO Auto-generated method stub
		return missionMapper.findBySQLforMission(findBySQL);
	}


	

}
