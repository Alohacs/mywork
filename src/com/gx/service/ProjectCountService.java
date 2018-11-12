package com.gx.service;

import java.util.List;


import com.gx.po.Mission;
import com.gx.po.ProjectCount;


public interface ProjectCountService {

	void insert(ProjectCount record);
	
	void updateByPrimaryKey(ProjectCount record);
	
	ProjectCount findByProjectCount(String camp_id);
    
}
