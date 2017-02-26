package com.zte.msm.spring.user.business;

import org.springframework.stereotype.Service;

import com.zte.msm.spring.user.model.UserBO;

/**
 * 服务类
 */
@Service
public class UserServiceImpl implements UserService
{
	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("UserServiceImpl...");
	}
	@Override
	public UserBO get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return new UserBO();
	}

	@Override
	public int delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

//    @Autowired
//    private UserMapper userDAO;
//
//    /**
//     * 根据主键获取实体对象
//     * @param id 主键ID
//     * @return 对应的User实体对象
//     * @throws Exception
//     */
//    @Override
//    public User get(Long id) throws Exception
//    {
//        UserVO userVO = userDAO.get(id);
//        User user = new User();
//        BeanUtils.copyProperties(userVO, user);
//        return user;
//    }
//
//    /**
//     * 获取符合条件的实体列表,按指定属性排序
//     * @param map 查询参数的map
//     * @param orderField 排序指定的列
//     * @param order 排序规则
//     * @return 查询到的集合列表
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<User> getList(Map<String, Object> map, String orderField, String order) throws Exception
//    {
//
//        map.put("orderField", orderField);
//        map.put("order", order);
//
//        List<UserVO> list = userDAO.getList(map);
//        
//        return BeanHelper.copyList(list, User.class);
//    }
//
//    /**
//     * 删除指定记录
//     * @param id 记录id
//     * @return 删除的行号
//     * @throws Exception
//     */
//    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
//    @Override
//    public int delete(Long id) throws Exception
//    {
//        return userDAO.delete(id);
//    }
//
//    /**
//     * 新增指定记录
//     * @param user 插入的用户实体对象
//     * @return 插入的行号
//     * @throws Exception
//     */
//    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
//    @Override
//    public int insert(User user) throws Exception
//    {
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(user, userVO);
//        return userDAO.insert(userVO);
//    }
//
//    /**
//     *  修改指定记录
//     * @param user 要更新的实体对象
//     * @return 更新的行号
//     * @throws Exception
//     */
//    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_UNCOMMITTED)
//    @Override
//    public int update(User user) throws Exception
//    {
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(user, userVO);
//        return userDAO.update(userVO);
//    }
//
//    /**
//     * 获取符合条件的记录列表,先按指定属性排序,在分页
//     * @param map 参数map
//     * @param orderField 排序指定的列
//     * @param order 排序规则
//     * @param page 查询页码
//     * @param rows 一页中数据数量
//     * @return 查询到的数据集合
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public PageRows<User> getPage(Map<String, Object> map, String orderField, String order, Long page, Long rows)
//            throws Exception
//    {
//
//        map.put("orderField", orderField);
//        map.put("order", order);
//
//        PageHelper.startPage(page.intValue(), rows.intValue());
//
//        List<UserVO> list = userDAO.getList(map);
//
//        PageInfo<UserVO> pageInfo = new PageInfo<UserVO>(list);
//
//        PageRows<User> pageRows = new PageRows<User>();
//        pageRows.setCurrent(page);
//        pageRows.setTotal(pageInfo.getTotal());
//        pageRows.setRows(BeanHelper.copyList(list, User.class));
//
//        return pageRows;
//    }

}