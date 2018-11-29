package com.yyl.rpc.common;

import javafx.fxml.Initializable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 *
 * @author yyl
 * @date 2018年11月29日 15:24
 */
@Component
public class SpringBeanFactory implements ApplicationContextAware,InitializingBean,BeanPostProcessor {
    /** ApplicationContext 对象 */
    private ApplicationContext applicationContext;

    /** 单子 */
    private static SpringBeanFactory instance = new SpringBeanFactory();

    public static void setInstance(SpringBeanFactory instance) {
        SpringBeanFactory.instance = instance;
    }

    private static SpringBeanFactory getInstance() {
        return SpringBeanFactory.instance;
    }

    public static <T> T getBean(Class<T> type) {
        return instance.getInnerBean(type);
    }
    private <T> T getInnerBean(Class<T> type) {
        String[] names = this.applicationContext.getBeanNamesForType(type);
        if (names == null || names.length == 0) {
            return null;
        }
        return (T) this.applicationContext.getBean(names[0]);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        SpringBeanFactory.setInstance(this);
        String[] names = this.applicationContext.getBeanDefinitionNames();
        for (int i = 0; i < names.length; i++) {
            System.out.println(" == names[" + i + "]:" + names[i]);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
