package com.takeout.filter;

import com.alibaba.fastjson.JSON;
import com.takeout.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查是否登录
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
@Component
public class LoginCheckFilter implements Filter {
    //路径匹配器
    public static  final AntPathMatcher PATH_MATCHER=new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        //获取请求uri
        String requestURI=request.getRequestURI();
        //判断是否需要处理
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        boolean check = check(urls, requestURI);
        //如果不需要处理
        if (check)
        {
            log.info("本次请求不需要处理:{}",request.getRequestURI());
            filterChain.doFilter(request,response);
            return;
        }
        if (request.getSession().getAttribute("employee")!=null)
        {
            log.info("用户已登录:{}",request.getRequestURI());
            filterChain.doFilter(request,response);
            return;
        }
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("用户未登录:{}",request.getRequestURI());
        return;
    }

    /**
     * 路径匹配，检查本地请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match= PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
