package com.otboss.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otboss.todo.service.DistributionCenterService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private DistributionCenterService distributionCenterService;

    @GetMapping
    public String getCustomer() {
        this.distributionCenterService.getClosestDistributionCenter();
        return "Hello world";
    }
}
