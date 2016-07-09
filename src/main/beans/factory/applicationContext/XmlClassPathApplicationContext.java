package main.beans.factory.applicationContext;

import main.beans.factory.BeanReader.XmlClassPathBeanDefinitionReader;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanFactory.DefaultListableBeanFactory;

import java.util.Set;

/**
 * Created by Administrator on 2016/7/7.
 */
public class XmlClassPathApplicationContext extends DefaultListableBeanFactory implements ApplicationContext {

    private XmlClassPathBeanDefinitionReader xmlClassPathBeanDefinitionReader;

    public XmlClassPathApplicationContext(String location)
    {
        setXmlClassPathBeanDefinitionReader(location);
        registerAllBeanDefinitions(xmlClassPathBeanDefinitionReader.getBeanDefinitions());
        initLazyInitTrueBeans();
    }

    private void setXmlClassPathBeanDefinitionReader(String location)
    {
        this.xmlClassPathBeanDefinitionReader = new XmlClassPathBeanDefinitionReader(location);
    }

    private void registerAllBeanDefinitions(Set<BeanDefinition> beanDefinitionSet)
    {
        for(BeanDefinition beanDefinition:beanDefinitionSet)
        {
            registerBeanDefinitions(beanDefinition.getBeanId(), beanDefinition);
        }
    }

    private void initLazyInitTrueBeans()
    {
        for(BeanDefinition beanDefinition:beanDefinitionMap.values())
        {
            if(beanDefinition.getLazyInit().equals("false"))
            {
                beanDefinition.setBean(createBean(beanDefinition));
            }
        }
    }

}
