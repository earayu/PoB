package main.beans.factory.BeanReader;

import main.beans.io.resource.Resource;

/**
 * Created by earayu on 2016/7/7.
 *
 * 这应该是个被使用的接口, 具备功能:
 * 1. 通过ResourceLoader得到Resource
 * 2. 解析Resource, 载入BeanDefinition
 *
 * 需要注册BeanDefinition到BeanFactory
 *
 */
public interface BeanDefinitionReader
{
    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(String strRes);

}
