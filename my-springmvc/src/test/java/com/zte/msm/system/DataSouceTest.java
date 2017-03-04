package com.zte.msm.system;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.spring.user.access.dao.UserMapper;
import com.zte.msm.spring.user.access.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources") //1 web资源的位置，默认是src/main/webapp
//@ContextConfiguration(locations = { "classpath*:spring-config.xml", "classpath*:spring-mvc.xml" })
@ContextHierarchy({
    @ContextConfiguration(name="parent",locations = "classpath:spring-config.xml"),
    @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
})
public class DataSouceTest {
	private static final Logger logger = LoggerFactory.getLogger(LanguageTest.class);
	
	@Autowired @Qualifier("mainDataSource")
	private DataSource mainDataSource;
	
	@Autowired @Qualifier("readOnlyDataSource")
	private DataSource readOnlyDataSource;
	
	@Autowired @Qualifier("mainSqlSessionFactory")
	private SqlSessionFactory mainSqlSessionFactory;
	@Autowired @Qualifier("readOnlySqlSessionFactory")
	private SqlSessionFactory readOnlySqlSessionFactory;
	@Value("#{settings['db1.url']}")
	public String url;
	
	@Test
	public void testMainDataSource() throws Exception {
		logger.debug("--{}",mainDataSource.toString());
		logger.debug("--{}",mainDataSource.getConnection().toString());
		Assert.assertNotNull(mainDataSource);
		Assert.assertNotNull(mainDataSource.getConnection());
	}
	@Test
	public void testMainReadOnlyDataSource() throws Exception {
		logger.debug("--{}",readOnlyDataSource.toString());
		logger.debug("--{}",readOnlyDataSource.getConnection().toString());
		Assert.assertNotNull(readOnlyDataSource);
		Assert.assertNotNull(readOnlyDataSource.getConnection());
	}
	@Test
	public void testMainSqlSessionFactory() throws Exception {
		SqlSession session = mainSqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserVO user = mapper.get(1L);
		logger.debug(user.toString());
	}
	
	/**
	 * 配置多个相同路径的org.mybatis.spring.mapper.MapperScannerConfigurer
	 * 默认使用第一个数据源，该测试不通过
	 * @throws Exception
	 */
	@Test
	public void testReadOnlySqlSessionFactory() throws Exception {
		SqlSession session = readOnlySqlSessionFactory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		UserVO user = mapper.get(1L);
		logger.debug(user.toString());
	}
	
	
	@Test
	public void testSpringEL(){
		Assert.assertEquals("jdbc:mysql://10.5.172.151:3306/msm_720_systemmanager_db", url);
	}
	
	
	
}
