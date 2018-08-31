package com.ysl.demo.security;

import com.ysl.demo.bean.Auth;
import com.ysl.demo.bean.User;
import com.ysl.demo.repository.UserRepository;
import com.ysl.demo.utils.CommenUtils;
import com.ysl.demo.utils.ResultMsg;
import com.ysl.demo.utils.ResultStatusCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面编程-登录校验
 * Created by turing_ios on 2018/8/31.
 */
@Aspect
@Component
public class ControllerAspect {

    private final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Autowired
    UserRepository userRepository;

    /**
     * 拦截注解 @CheckLogin的方法
     */
    @Pointcut("@annotation(CheckLogin)")
    public void loginOnly() {

    }

    /**
     * 相当于构造方法
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("loginOnly()")
    public Object trackInfo(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String remoteHost = request.getRemoteHost();
//        Login loginByIp = loginDao.getLoginByIp(remoteHost);

        Auth auth = CommenUtils.getInstance().hsrToAuth(request);
        String userId = auth.getUserId();
        String token = auth.getToken();
        logger.info("userId===" + userId + "  |token==" + token);

        User userBean = userRepository.findByUserIdAndToken(userId, token);
        ResultMsg resultMsg;
        if (null == userBean) {
            resultMsg = new ResultMsg(ResultStatusCode.USER_ERR.getErrcode(), ResultStatusCode.USER_ERR.getErrmsg(), null);
            logger.info("-------------没有登录-------------");
            return resultMsg;
        }
        logger.info("-------------登录通过-------------");
        return pjp.proceed();
    }


    /**
     * loginOnly()执行之前
     */
    @Before("loginOnly()")
    public void deoBefore(JoinPoint joinPoint) {
        logger.info("方法执行前...");

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.info("url:" + request.getRequestURI());
        logger.info("ip:" + request.getRemoteHost());
        logger.info("method:" + request.getMethod());
        logger.info("class_method:" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("args:" + joinPoint.getArgs());
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            System.out.println("参数===" + joinPoint.getArgs()[i]);
        }
    }

    /**
     * 执行之后
     * @param joinPoint
     */
    @After("loginOnly()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("方法执行后...");
    }


    /**
     * 执行之后返回值
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "loginOnly()")
    public void doAfterReturning(Object result) {
        logger.info("执行返回值：" + result);
    }


}
