package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.ShippingAddress;

import java.util.List;

public interface ShippingAddressMapper extends Mapper<ShippingAddress> {

    List<ShippingAddress> list(ShippingAddress shippingAddress);

}