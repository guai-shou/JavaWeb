package com.example.javaweb;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.example.javaweb.entity.Cart;
import com.example.javaweb.entity.Order;
import com.example.javaweb.entity.User;
import com.example.javaweb.entity.vo.NoticeVo;
import com.example.javaweb.entity.vo.OrderVo;
import com.example.javaweb.service.IAdminService;
import com.example.javaweb.service.IBookService;
import com.example.javaweb.service.INoticeService;
import com.example.javaweb.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class JavaWebApplicationTests {

    @Autowired
    private IBookService bookService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IUserService userService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private IAdminService adminService;

    @Test
    public void test() {
        //ArrayList<String> strings = new ArrayList<>();
        //strings.add("a");
        //strings.add("b");
        //ArrayList<String> strings1 = new ArrayList<>();
        //strings1.add("a");
        //System.out.println(strings.contains(strings1.get(0)));

        //System.out.println(adminService.getPermissionListById(1));
        //
        List<NoticeVo> noticeVos = noticeService.selectAll();
        System.out.println(noticeVos.size());
        for (NoticeVo noticeVo : noticeVos) {
            System.out.println(noticeVo);
        }
        //
        //System.out.println(noticeService.selectById(2));
        //User user = new User();
        //user.setCargoName("ccc");
        //user.setId(1);
        //userService.updateById(user);
        //System.out.println(user.getUpdateTime());
        //System.out.println(user);
        //Cart cart = new Cart();
        //System.out.println(cart);

        //Order order = new Order();
        //OrderVo orderVo = new OrderVo();
        //order.setBookList("1");
        //order.setOrderSerial("1");
        //BeanUtils.copyProperties(order, orderVo, OrderVo.class);
        //System.out.println(order);
        //System.out.println(orderVo);
        //
        //System.out.println(UUID.randomUUID().toString());
        //System.out.println(UUID.randomUUID().toString());
        //System.out.println(UUID.randomUUID().toString());
        //
        //List<Integer> list = new ArrayList<>();
        //list.add(1);
        //list.add(2);
        //list.add(3);
        //System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining("#")));


    }

}
