package com.sf.commons.spring.annotion;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月8日 下午8:44:36	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
@Service("myService") @Scope("prototype")
public class MyService implements IMyDao {
	
	private IMyDao myDao;
	
	@Override
	public void save(String text){
		System.out.println("myDao:"+myDao);
		myDao.save(text);
	}
	@Resource
	public void setMyDao(IMyDao myDao) {
		this.myDao = myDao;
	}
}
