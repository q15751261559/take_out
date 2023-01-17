package com.takeout.common;

/**
 * 基于ThreadLocal封装线程安全临时存储空间工具类用于保存当前用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
