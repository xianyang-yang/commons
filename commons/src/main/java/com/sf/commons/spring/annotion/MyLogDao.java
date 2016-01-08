package com.sf.commons.spring.annotion;

import org.springframework.stereotype.Repository;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月8日 下午9:07:55	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
@Repository("myLogDao")
public class MyLogDao implements IMyDao {

	@Override
	public void save(String text) {
		System.out.println("mylogdao "+text);

	}

}
