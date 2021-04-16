package com.chen;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.mapper.UserMapper;
import com.chen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    // 继承了BaseMapper，所有的方法都来自自己父类
    // 我们也可以编写自己的扩展方法

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 参数是一个 wrapper，条件构造器
        // 查询全部用户
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    // 测试插入
    @Test
    void testInsert() {
        User user = new User();
        user.setName("小陈说");
        user.setAge(18);
        user.setEmail("111@qq.com");
        int result = userMapper.insert(user); //自动生成id
        System.out.println(result);
        System.out.println(user); // 发现id会自动回填
    }

    //测试更新
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1382507168988733443L);
        user.setName("小陈说传神说");
        user.setAge(17);
        user.setEmail("111@qq.com");

        // 注意：updateById，但是参数是一个对象
        int update = userMapper.updateById(user);
        System.out.println(update);
    }

    //测试乐观锁成功
    @Test
    void testOptimisticLockerInterceptor() {
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2.修改用户信息
        user.setName("xiaochen");
        user.setEmail("232321@qq.com");
        //3.执行更新操作
        userMapper.updateById(user);
    }

    //测试乐观锁失败
    @Test
    void testOptimisticLockerInterceptor2() {
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("xiaochen111");
        user.setEmail("232321@qq.com");

        //模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("xiaochen222");
        user2.setEmail("232321@qq.com");
        userMapper.updateById(user2);

        //可以使用自旋锁来多次尝试提交
        userMapper.updateById(user); //如果没有乐观锁就会覆盖插队线程的值
    }

    //测试查询
    @Test
    void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //测试批量查询
    @Test
    void testSelectBatchIds() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        userList.forEach(System.out::println);
    }

    //按条件查询之一使用map操作
    @Test
    void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("name", "小陈说");
        map.put("age", "18");

        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    // 测试分页查询
    @Test
    void testPage() {
        Page<User> page = new Page<>(1, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
    }

    // 测试删除
    @Test
    void testDeleteById() {
        userMapper.deleteById(1L);
    }

    // 批量删除
    @Test
    void testDeleteBatchIds() {
        userMapper.deleteBatchIds(Arrays.asList(1382507168988733441L, 1382507168988733442L));
    }

    // 通过map删除
    @Test
    void testDeleteMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "小陈说传神说");
        userMapper.deleteByMap(map);
    }

}
