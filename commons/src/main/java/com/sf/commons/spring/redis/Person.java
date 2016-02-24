package com.sf.commons.spring.redis;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月26日 下午4:54:47		204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class Person {
	private Address nameAddress;

	public void setNameAddress(Address nameAddress) {
		this.nameAddress = nameAddress;
	}
	@Override
	public String toString() {
		return nameAddress.toString();
	}
}
