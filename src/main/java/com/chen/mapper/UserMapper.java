package com.chen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/4/15 9:11
 */
@Repository //代表持久层
// 在对应的Mapper上面继承基本的类BaseMapper就实现了对User的CRUD了
public interface UserMapper extends BaseMapper<User> {

}
