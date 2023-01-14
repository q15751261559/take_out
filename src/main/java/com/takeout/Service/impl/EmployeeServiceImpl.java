package com.takeout.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takeout.Service.EmployeeService;
import com.takeout.entity.Employee;
import com.takeout.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
