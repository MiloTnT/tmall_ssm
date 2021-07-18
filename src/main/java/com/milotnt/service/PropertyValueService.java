package com.milotnt.service;

import com.milotnt.pojo.Product;
import com.milotnt.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void init(Product p);

    void update(PropertyValue pv);

    PropertyValue get(int ptid, int pid);

    List<PropertyValue> list(int pid);
}

