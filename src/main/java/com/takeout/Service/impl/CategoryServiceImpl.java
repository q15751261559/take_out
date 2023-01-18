package com.takeout.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takeout.Service.CategoryService;
import com.takeout.Service.DishService;
import com.takeout.Service.SetmealService;
import com.takeout.common.CustomException;
import com.takeout.entity.Category;
import com.takeout.entity.Dish;
import com.takeout.entity.Setmeal;
import com.takeout.mapper.CategoryMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     *
     * @param id
     */
    public void remove(Long id){
        //查询当前分类是否关联菜品，如果已关联，抛出业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishLambdaQueryWrapper);
        if (count>0)
        {
            throw new CustomException("当前分类下关联了菜品不能删除");
        }
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);
        if (count1>0)
        {
            throw new CustomException("当前分类下关联了套餐不能删除");
        }
        super.removeById(id);
    }
}
