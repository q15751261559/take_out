package com.takeout.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takeout.Service.SetmealService;
import com.takeout.entity.Setmeal;
import com.takeout.mapper.SetmealMapper;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>implements SetmealService {
}
