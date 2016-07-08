package main.beans.factory.beanRegistry;

import main.beans.factory.beanDefinition.BeanDefinition;

/**
 * Created by Administrator on 2016/7/7.
 * 功能接口, 非调用
 */
public interface BeanRegistry {

    void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition);

}
