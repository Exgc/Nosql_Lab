//package com.example.demo.handlerIntercepter;
//
//
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class UserLoginInterceptor extends HandlerInterceptorAdapter {
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws ServletException, IOException {
//        if("GET".equalsIgnoreCase(request.getMethod())){
//
//        }
//        String requestUri = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        String url = requestUri.substring(contextPath.length());
//        String sid = (String) request.getSession().getAttribute("sid");
//        if(null == sid){
//            // 跳转到登录页面
//            request.getRequestDispatcher("www.baidu.com").forward(request, response);
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
//        System.out.println("postHandle...");
//        if(modelAndView != null){
//            Map<String, String> map = new HashMap<String, String>();
//            modelAndView.addAllObjects(map);
//        }
//    }
//    // 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
//        System.out.println("afterCompletion...");
//    }
//}
