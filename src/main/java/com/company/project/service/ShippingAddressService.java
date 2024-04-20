package com.company.project.service;
import com.company.project.core.Result;
import com.company.project.model.ShippingAddress;
import com.company.project.core.Service;

public interface ShippingAddressService extends Service<ShippingAddress> {

    Result list(String userId, ShippingAddress shippingAddress);
}
