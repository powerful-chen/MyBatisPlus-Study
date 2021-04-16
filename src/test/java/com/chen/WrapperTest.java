package com.chen;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.mapper.UserMapper;
import com.chen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName WrapperTest
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/4/15 15:25
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询name不为空的用户，并且邮箱不为空的用户，年龄大于等于20岁的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 20);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test2() {
        // 查询名字为Jack
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Jack");
        User user = userMapper.selectOne(wrapper); //查询一个数据，出现多个结果使用List 或者 Map
        System.out.println(user);
    }

    @Test
    void test3() {
        // 查询年龄在20-30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);//区间
        Integer count = userMapper.selectCount(wrapper);// 查询结果数
        System.out.println(count);
    }

    // 模糊查询
    @Test
    void test4() {
        // 查询name不包含e的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // likeRight likeLeft 是以 % 的位置确定的 例如 %t 代表左 t% 代表右
        wrapper
                .notLike("name", "e")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    // 嵌套查询 查询id小于3的用户
    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id", "select id from user where id<3");

        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过id进行排序
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);


    }


}
