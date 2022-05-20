package com.lanrenspace.site.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author lanrenspace@163.com
 * @Description: 上下文全局对象 辅助类
 **/
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> cla) {
        return getApplicationContext().getBean(cla);
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> Map<String, T> getBeans(Class<T> cls) {
        return getApplicationContext().getBeansOfType(cls);
    }

    public static Object getComponent(String name) {
        Object obj = null;
        ApplicationContext applicationContext = getApplicationContext();
        if (applicationContext.containsBean(name)) {
            obj = applicationContext.getBean(name);
        } else {
            return null;
        }
        return obj;
    }


    /**
     * 获取应用名称
     * @return
     */
    public static String getAppName() {
        return getApplicationContext().getEnvironment().getProperty("spring.application.name","NONE");
    }
}
