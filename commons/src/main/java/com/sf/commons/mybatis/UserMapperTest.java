package com.sf.commons.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年2月24日 上午11:23:28		204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class UserMapperTest {
	private static Logger logger = LoggerFactory.getLogger(UserMapperTest.class);
	public static void main(String[] args) throws IOException {
		String resource = "com/sf/commons/mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();
//		testInsert(session);
		testUpdate(session);
	}

	private static void testInsert(SqlSession session) {
		try {
			User user = new User();
			user.setName("yang");
			user.setAge(30);
			session.insert("com.sf.commons.mybatis.UserMapper.insertUser", user);
			session.commit();
		} finally {
			session.close();
		}
		logger.info("insert finish");

	}
	private static void testUpdate(SqlSession session) {
		try {
			User user = new User();
			user.setId(1);
			user.setName("yang1");
			user.setAge(31);
			session.insert("com.sf.commons.mybatis.UserMapper.updateUser", user);
			session.commit();
		} finally {
			session.close();
		}
		logger.info("insert finish");
	}
//	private static void testFind(SqlSession session) {
//		try {
//			User user = new User();
//			session.select("com.sf.commons.mybatis.UserMapper.updateUser", user);
//			session.commit();
//		} finally {
//			session.close();
//		}
//		logger.info("insert finish");
//	}
}
