package com.takeout.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.takeout.entity.Category;

public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
