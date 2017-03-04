package com.zte.msm.spring.user.business;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msm.frame.base.BaseServiceImpl;
import com.zte.msm.frame.common.PageRows;
import com.zte.msm.frame.log.EnableLog;
import com.zte.msm.frame.log.LoggerFactory;
import com.zte.msm.frame.util.bean.BeanHelper;
import com.zte.msm.spring.user.access.dao.UserMapper;
import com.zte.msm.spring.user.access.vo.UserVO;
import com.zte.msm.spring.user.model.UserBO;

/**
 * 服务类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserBO> implements UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired @Qualifier("xUserMapperImpl")
    private UserMapper userMapper;

    /**
     * 根据主键获取实体对象
     * @param id 主键ID
     * @return 对应的User实体对象
     * @throws Exception
     */
    @EnableLog("Service-获取用户")
    @Override
    public UserBO get(Long id) throws Exception
    {
        UserVO userVO = userMapper.get(id);
        UserBO user = new UserBO();
        if(userVO!=null){
        	BeanUtils.copyProperties(userVO, user);
        	return user;
        }
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
    @Override
    @EnableLog("Service-获取符合条件的实体列表,按指定属性排序")
    public List<UserBO> getList(Map<String, Object> map, String orderField, String order) throws Exception
    {

        map.put("orderField", orderField);
        map.put("order", order);

        List<UserVO> list = userMapper.getList(map);
        
        return BeanHelper.copyList(list, UserBO.class);
    }

    /**
     * 删除指定记录
     * @param id 记录id
     * @return 删除的行号
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    @Override
    @EnableLog("Service-删除指定记录")
    public int delete(Long id) throws Exception
    {
        return userMapper.delete(id);
    }

    /**
     * 新增指定记录
     * @param user 插入的用户实体对象
     * @return 插入的行号
     * @throws Exception
     */
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    @Override
    @EnableLog("Service-新增指定记录")
    public int insert(UserBO user) throws Exception
    {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userMapper.insert(userVO);
    }

    /**
     *  修改指定记录
     * @param user 要更新的实体对象
     * @return 更新的行号
     * @throws Exception
     */
    @EnableLog("Service-修改指定记录")
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    @Override
    public int update(UserBO user) throws Exception
    {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userMapper.update(userVO);
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
    @Override
    @EnableLog("Service-获取符合条件的记录列表,先按指定属性排序,在分页")
    public PageRows<UserBO> getPage(Map<String, Object> map, String orderField, String order, Long page, Long rows)
            throws Exception
    {

        map.put("orderField", orderField);
        map.put("order", order);

        PageHelper.startPage(page.intValue(), rows.intValue());

        List<UserVO> list = userMapper.getList(map);

        PageInfo<UserVO> pageInfo = new PageInfo<UserVO>(list);

        PageRows<UserBO> pageRows = new PageRows<UserBO>();
        pageRows.setCurrent(page);
        pageRows.setTotal(pageInfo.getTotal());
        pageRows.setRows(BeanHelper.copyList(list, UserBO.class));

        return pageRows;
    }

	@Override
	@EnableLog("Service-根据用户名称查询")
	public UserBO getByAccount(String account) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}