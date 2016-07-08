package main.beans.io.ResourceParser;

import main.beans.factory.beanDefinition.BeanDefinition;

import java.util.Set;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface ResourceParser {

    Set<BeanDefinition> getBeanDefinitions();

}
