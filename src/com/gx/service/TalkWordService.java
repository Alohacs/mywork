package com.gx.service;

import java.util.List;

import com.gx.po.TalkWord;




public interface TalkWordService {
//    int deleteByPrimaryKey(String id);

    void insert(TalkWord record);

//    int insertSelective(Result record);

//    ReportDetail selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Result record);

//    int updateByPrimaryKey(Result record);
    
    /**
     * 自定义方法
//     */
//    List<User> findAll();
//    
      List<TalkWord> findBySQLforTalkWord(String SQL);
    
      void updateByPrimaryKeyforTalkWord(TalkWord record);
      
//      Date findBySQLforCLD(Date record);
    
//    
//    List<UserVo> findVoBySQL(String SQL);
//    
//    RowCount rowCount(String SQL);
//    
//    List<UserVo> findPageByVoSQL(String SQL, Integer currentPage, Integer pageSize);
    
}
