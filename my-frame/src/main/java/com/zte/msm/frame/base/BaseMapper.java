package com.zte.msm.frame.base;

import java.util.List;
import java.util.Map;
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
	List<T> getList(Map<String, Object> map);
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	int delete(Long id);
	/**
	 * 新建记录
	 * @param record
	 * @return
	 */
	int insert(T record);
	/**
	 * 通过主键获取一条记录
	 * @param id
	 * @return
	 */
	T get(Long id);
	/**
	 * 更新记录
	 * @param record
	 * @return
	 */
	int update(T record);

}
