package com.milotnt.service;

import com.milotnt.pojo.Order;
import com.milotnt.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order o);

    void delete(int id);

    void update(Order o);

    Order get(int id);

    List list();

    float add(Order o, List<OrderItem> ois);

    List list(int uid, String excludedStatus);

}
