package com.milotnt.service.impl;

import com.milotnt.mapper.OrderMapper;
import com.milotnt.pojo.Order;
import com.milotnt.pojo.OrderExample;
import com.milotnt.pojo.OrderItem;
import com.milotnt.pojo.User;
import com.milotnt.service.OrderItemService;
import com.milotnt.service.OrderService;
import com.milotnt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order o) {
        orderMapper.insert(o);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order o) {
        orderMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);

        if (false)
            throw new RuntimeException();

        for (OrderItem oi : ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
        }
        return total;
    }

    @Override
    public List list(int uid, String excludedStatus) {
        OrderExample example = new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }

    public void setUser(List<Order> os) {
        for (Order o : os)
            setUser(o);
    }

    public void setUser(Order o) {
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }

}
