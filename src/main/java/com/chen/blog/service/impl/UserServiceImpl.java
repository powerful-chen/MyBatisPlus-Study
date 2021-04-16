package com.chen.blog.service.impl;

import com.chen.blog.entity.User;
import com.chen.blog.mapper.UserMapper;
import com.chen.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小陈
 * @since 2021-04-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
