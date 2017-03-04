package com.zte.msm.frame.base;

import java.util.List;
import java.util.Map;

import com.zte.msm.frame.common.PageRows;

/**
 * 基础Service类
 * @author JohnnyHuang黄福强
 */
public interface BaseService<T> {
	/**
     * 根据主键获取实体对象
     * @param id 主键ID
     * @return 对应的User实体对象
     * @throws Exception
     */
	public T get(Long id) throws Exception;
	/**
     * 获取符合条件的实体列表,按指定属性排序
     * @param map 查询参数的map
     * @param orderField 排序指定的列
     * @param order 排序规则
     * @return 查询到的集合列表
     * @throws Exception
     */
	public List<T> getList(Map<String, Object> map, String orderField, String order) throws Exception;
	/**
     * 删除指定记录
     * @param id 记录id
     * @return 删除的行号
     * @throws Exception
     */
	public int delete(Long id) throws Exception;
	/**
     * 新增指定记录
     * @param user 插入的用户实体对象
     * @return 插入的行号
     * @throws Exception
     */
	public int insert(T entity) throws Exception;
	/**
     *  修改指定记录
     * @param user 要更新的实体对象
     * @return 更新的行号
     * @throws Exception
     */
	public int update(T entity) throws Exception;
	/**
     * 获取符合条件的记录列表,先按指定属性排序,在分页
     * @param map 参数map
     * @param orderField 排序指定的列
     * @param order 排序规则
     * @param page 查询页码
     * @param rows 一页中数据数量
     * @return 查询到的数据集合
     * @throws Exception
     */
	public PageRows<T> getPage(Map<String, Object> map, String orderField, String order, Long page, Long rows) throws Exception;
}
