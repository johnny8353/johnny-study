package com.zte.msm.frame.base;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.zte.msm.frame.log.EnableLog;

/**
 * 基础Mapper实现类
 * @author JohnnyHuang黄福强
 */
public abstract class BaseMapperImpl<T> implements BaseMapper<T> {
	@Autowired(required=false)
	public SqlSessionTemplate sqlSession;
	/**
	 * 
	 * @return
	 */
	public abstract String getCanonicalName();
	@EnableLog("BaseMapper-获取对象列表")
	@Override
	public List<T> getList(Map<String, Object> map) {
		return sqlSession.selectList(getCanonicalName()+".getList", map);
	}

	@EnableLog("BaseMapper-根据主键删除单个对象")
	@Override
	public int delete(Long id) {
		return sqlSession.delete(getCanonicalName()+".delete", id);
	}
	@EnableLog("BaseMapper-插入单个对象")
	@Override
	public int insert(T record) {
		// TODO Auto-generated method stub
		return sqlSession.insert(getCanonicalName()+".insert", record);

	}
	@EnableLog("BaseMapper-根据主键获取单个对象")
	@Override
	public T get(Long id) {
		return (T)sqlSession.selectOne(getCanonicalName()+".get", id);
	}

	@EnableLog("BaseMapper-更新单个对象")
	@Override
	public int update(T record) {
		return sqlSession.update(getCanonicalName()+".update", record);
	}

}
