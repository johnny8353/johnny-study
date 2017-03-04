package com.zte.msm.frame.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zte.msm.frame.common.PageRows;
import com.zte.msm.frame.log.EnableLog;

public class BaseServiceImpl<T> {

    /**
     * 根据主键获取实体对象
     * @param id 主键ID
     * @return 对应的User实体对象
     * @throws Exception
     */
    @EnableLog("获取用户")
    public T get(Long id)  throws Exception
    {
    	return null;
    }

    /**
     * 获取符合条件的实体列表,按指定属性排序
     * @param map 查询参数的map
     * @param orderField 排序指定的列
     * @param order 排序规则
     * @return 查询到的集合列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @EnableLog("获取符合条件的实体列表,按指定属性排序")
    public List<T> getList(Map<String, Object> map, String orderField, String order)  throws Exception
    {
        return null;
    }

    /**
     * 删除指定记录
     * @param id 记录id
     * @return 删除的行号
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)  
    @EnableLog("删除指定记录")
    public int delete(Long id) throws Exception
    {
    	return 0;
    }

    /**
     * 新增指定记录
     * @param user 插入的用户实体对象
     * @return 插入的行号
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    @EnableLog("新增指定记录")
    public int insert(T user)  throws Exception
    {
        return 0;
    }

    /**
     *  修改指定记录
     * @param user 要更新的实体对象
     * @return 更新的行号
     * @throws Exception
     */
    @EnableLog("修改指定记录")
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public int update(T user)  throws Exception
    {
    	return 0;
    }

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
    @SuppressWarnings("unchecked")
    @EnableLog("获取符合条件的记录列表,先按指定属性排序,在分页")
    public PageRows<T> getPage(Map<String, Object> map, String orderField, String order, Long page, Long rows)  throws Exception
    {
    	return null;
    }

}
