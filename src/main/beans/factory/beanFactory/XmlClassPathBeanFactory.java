package main.beans.factory.beanFactory;

import main.beans.factory.BeanReader.XmlClassPathBeanDefinitionReader;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.io.resource.Resource;
import main.beans.io.resourceloader.ClassPathResourceLoader;
import main.beans.io.resourceloader.ResourceLoader;

import java.util.Set;

/**
 * Created by Administrator on 2016/7/7.
 */
public class XmlClassPathBeanFactory extends DefaultListableBeanFactory {

    private XmlClassPathBeanDefinitionReader xmlClassPathBeanDefinitionReader = new XmlClassPathBeanDefinitionReader();

    public XmlClassPathBeanFactory(String location)
    {
        setXmlClassPathBeanDefinitionReader(location);
        registerAllBeanDefinitions(xmlClassPathBeanDefinitionReader.getBeanDefinitions());
        beanDefinitionSet = xmlClassPathBeanDefinitionReader.getBeanDefinitions();
    }

    private void setXmlClassPathBeanDefinitionReader(String location)
    {
        ResourceLoader resourceLoader = new ClassPathResourceLoader(location);
        Resource resource = resourceLoader.getResource();
        this.xmlClassPathBeanDefinitionReader = new XmlClassPathBeanDefinitionReader();
        this.xmlClassPathBeanDefinitionReader.loadBeanDefinitions(resource.getContentAsString());
    }

    private void registerAllBeanDefinitions(Set<BeanDefinition> beanDefinitionSet)
    {
        for(BeanDefinition beanDefinition:beanDefinitionSet)
        {
            registerBeanDefinitions(beanDefinition.getBeanId(), beanDefinition);
        }
    }

}
