package com.fonyou.finaltest.controller;

import com.fonyou.finaltest.dto.EmployeeDto;
import com.fonyou.finaltest.dto.ResponseGenericDto;

import com.fonyou.finaltest.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/redis")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class RedisController
{
    private IEmployeeService service;

    @Autowired
    public RedisController(IEmployeeService service)
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseGenericDto<EmployeeDto> getEmployee(@PathVariable("id") String id) throws Exception
    {
        return new ResponseGenericDto<>("0", "OK", this.service.findByIdRedis(id), true);
    }

    @PostMapping("/")
    public ResponseGenericDto<String> saveEmployee(@RequestBody EmployeeDto body) throws Exception
    {
        return new ResponseGenericDto<String>("0", "OK", this.service.saveRedis(body), true);
    }

    @PostMapping("/{id}")
    public ResponseGenericDto<String> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto body) throws Exception
    {
        return new ResponseGenericDto<String>("0", "OK", this.service.updateRedis(id, body), true);
    }

    @DeleteMapping("/{id}")
    public ResponseGenericDto<String> deleteEmployee(@PathVariable("id") String id) throws Exception
    {
        return new ResponseGenericDto<>("0", "OK", this.service.deleteRedis(id), true);
    }
}
