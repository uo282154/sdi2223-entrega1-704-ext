package com.uniovi.sdi.sdi2223entrega171.interceptors;

import com.uniovi.sdi.sdi2223entrega171.controllers.UsersController;
import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class LogInterceptor  implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);

    @Autowired
    private LogService logService;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        String mapping = request.getRequestURI();
//        String httpMethod = request.getMethod();
//        String params = request.getQueryString();
//
////            TODO:
//        String msg = mapping + " " + httpMethod + " " + params;
//        System.out.println("Nuevo caso ------------------"+Log.LogItemType.PET+"\n"+msg);
//
////        saveLog(Log.LogItemType.PET, mapping+httpMethod + params );
//        return true;
//    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        if(handler instanceof UsersController && request.getMethod().equals("POST")) {
            String mapping = request.getRequestURI();
            String email = request.getParameter("email");

            // Registrar el alta en el log
            String logMsg = String.format("Alta de usuario: email=%s",  email);
//            LOGGER.info(logMsg);
////            TODO:
        String msg = mapping + " " + logMsg;
        System.out.println("Nuevo caso ------------------"+Log.LogItemType.ALTA+"\n"+msg);

        }
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        if (response.getStatus() == HttpServletResponse.SC_CREATED) {
//            String mapping = request.getRequestURI();
//            String httpMethod = request.getMethod();
//            String params = request.getQueryString();
////            TODO:
//            String msg = mapping + " " + httpMethod + " " + params;
//            System.out.println("Nuevo caso ------------------"+Log.LogItemType.ALTA+"\n"+msg);
////            saveLog(Log.LogItemType.ALTA, mapping, httpMethod + params);
//        }
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//                                Exception ex) throws Exception {
//        String mapping = request.getRequestURI();
//        String httpMethod = request.getMethod();
//        String params = request.getQueryString();
//        String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "";
//
//        if (response.getStatus() == HttpServletResponse.SC_OK) {
////            saveLog(Log.LogItemType.LOGIN_EX, mapping, username);
//            //            TODO:
//            String msg = mapping + " " + httpMethod + " " + params;
//            System.out.println("Nuevo caso ------------------"+Log.LogItemType.LOGIN_EX+"\n"+msg);
//        } else if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
////            saveLog(Log.LogItemType.LOGIN_ERR, mapping, username);
//            //            TODO:
//            String msg = mapping + " " + httpMethod + " " + params;
//            System.out.println("Nuevo caso ------------------"+Log.LogItemType.LOGIN_ERR+"\n"+msg);
//
//        } else if (request.getMethod().equals("DELETE") && request.getRequestURI().contains("/logout")) {
////            saveLog(Log.LogItemType.LOGOUT, mapping, username);
////            TODO:
//        String msg = mapping + " " + httpMethod + " " + params;
//        System.out.println("Nuevo caso ------------------"+Log.LogItemType.LOGOUT+"\n"+msg);
//        }
//    }

    private void saveLog(Log.LogItemType type, String action, String description) {
        Log log = new Log();
        log.setType(type);
        log.setAction(action);
        log.setDescription(description);
        log.setTimestamp(new Timestamp(new Date().getTime()));
        logService.addLog(log);
    }
}
