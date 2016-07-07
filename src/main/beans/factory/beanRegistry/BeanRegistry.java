package main.beans.factory.beanRegistry;

import main.beans.factory.beanDefinition.BeanDefinition;

/**
 * Created by Administrator on 2016/7/7.
 */
public interface BeanRegistry {

    void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition);

}
