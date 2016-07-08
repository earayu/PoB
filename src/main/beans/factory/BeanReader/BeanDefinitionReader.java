package main.beans.factory.BeanReader;

import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.io.resource.Resource;

import java.util.Set;

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
    Set<BeanDefinition> getBeanDefinitions();
}
