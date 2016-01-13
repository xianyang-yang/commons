package com.sf.commons.spring.annotion;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月8日 下午8:45:22	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
@Repository("myDao") @Scope("prototype")
public class MyDao implements IMyDao {
	@Override
	public void save(String text){
		System.out.println("save "+text);
	}
}
