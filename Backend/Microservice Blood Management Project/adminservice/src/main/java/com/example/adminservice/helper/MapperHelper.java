package com.example.adminservice.helper;

import com.example.adminservice.domain.BloodStock;
import com.example.adminservice.proxy.BloodStockProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Component
public class MapperHelper {

    @Autowired
    private ObjectMapper mapper;

    public <T> T map(Object source, Class<T> targetClass) {
        return mapper.convertValue(source, targetClass);
    }

    //BloodStoke

    public BloodStock getBloodStokeDomain(BloodStockProxy bloodStockProxy){
        return mapper.convertValue(bloodStockProxy, BloodStock.class);
    }

    public BloodStockProxy getBloodStockProxy(BloodStock bloodStock){
        return mapper.convertValue(bloodStock, BloodStockProxy.class);
    }

    public List<BloodStock> getListBloodStockDomain(List<BloodStockProxy> bloodStockProxies){
        return bloodStockProxies.stream().map(this::getBloodStokeDomain).toList();
    }

    public List<BloodStockProxy> getListBloodStockProxy(List<BloodStock> bloodStock){
        return bloodStock.stream().map(this::getBloodStockProxy).toList();
    }
}
