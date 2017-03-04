package com.zte.msm.frame.base;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zte.msm.frame.log.EnableLog;
/**
 * 基础Mapper
 * @author JohnnyHuang黄福强
 */
public interface BaseMapper<T> {
	/**
	 * 获取列表记录
	 * @param map
	 * @return
	 */
	@EnableLog("获取列表记录")
	List<T> getList(Map<String, Object> map);
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@EnableLog("删除记录")
	int delete(Long id);
	/**
	 * 新建记录
	 * @param record
	 * @return
	 */
	@EnableLog("新建记录")
	int insert(T record);
	/**
	 * 通过主键获取一条记录
	 * @param id
	 * @return
	 */
	@EnableLog("通过主键获取一条记录")
	T get(Long id);
	/**
	 * 更新记录
	 * @param record
	 * @return
	 */
	@EnableLog("更新记录")
	int update(T record);

}
