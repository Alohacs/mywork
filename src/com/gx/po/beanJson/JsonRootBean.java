/**
  * Copyright 2018 bejson.com 
  */
package com.gx.po.beanJson;
import java.util.List;

import com.gx.po.Result;

/**
 * Auto-generated: 2018-06-11 15:21:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String errResult;
    private int isSuccess;
    private List<Result> result;
    public void setErrResult(String errResult) {
         this.errResult = errResult;
     }
     public String getErrResult() {
         return errResult;
     }

    public void setIsSuccess(int isSuccess) {
         this.isSuccess = isSuccess;
     }
     public int getIsSuccess() {
         return isSuccess;
     }

    public void setResult(List<Result> result) {
         this.result = result;
     }
     public List<Result> getResult() {
         return result;
     }

}