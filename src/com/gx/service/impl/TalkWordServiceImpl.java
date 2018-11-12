package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.TalkWordMapper;
import com.gx.po.TalkWord;
import com.gx.service.TalkWordService;



@Transactional
@Service(value="talkWordService")
public class TalkWordServiceImpl implements TalkWordService{

	@Autowired
	private TalkWordMapper talkWordMapper;

	@Override
	public void insert(TalkWord record) {
		// TODO Auto-generated method stub
		talkWordMapper.insert(record);
	}

	@Override
	public List<TalkWord> findBySQLforTalkWord(String SQL) {
		// TODO Auto-generated method stub
		return talkWordMapper.findBySQLforTalkWord(SQL);
	}

	@Override
	public void updateByPrimaryKeyforTalkWord(TalkWord record) {
		// TODO Auto-generated method stub
		talkWordMapper.updateByPrimaryKeyforTalkWord(record);
	}




}
