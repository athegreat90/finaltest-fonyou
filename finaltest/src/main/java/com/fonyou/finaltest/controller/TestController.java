package com.fonyou.finaltest.controller;

import java.util.List;

import com.fonyou.finaltest.dto.ResponseGenericDto;
import com.fonyou.finaltest.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TestController
{
    private IEmployeeService service;

    @Autowired
    public TestController(IEmployeeService service)
    {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseGenericDto<List<String>> test()
    {
        return new ResponseGenericDto<>("0", "OK", this.service.getMessages(), true);
    }
    @GetMapping("/reset")
    public ResponseGenericDto<String> resetProperties()
    {
        this.service.resetProperties();
        return new ResponseGenericDto<String>("0", "OK", "Reload properties", true);
    }
}
