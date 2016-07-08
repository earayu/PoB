package main.beans.factory.beanFactory;

import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanRegistry.BeanRegistry;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by earayu on 2016/7/4.
 */
public class DefaultListableBeanFactory implements BeanFactory, BeanRegistry
{

    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(16);

    private static final String SINGLETON = "singleton";
    private static final String PROTOTYPE = "prototype";
    private static final String LAZY_INIT_TRUE = "true";
    private static final String LAZY_INIT_FALSE = "false";


    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        checkNotNull(beanDefinition);

        Object bean = null;
        if(beanDefinition.getScope().equals(SINGLETON))
        {
            bean = beanDefinition.getBean();
        }

        return bean!=null?bean:createBean(beanDefinition);
    }

    //这里的参数beanName对应的实际上是BeanDefinition中的BeanId
    public <T> T getBean(String beanId, Class<T> clazz) {

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        Object bean = getBean(beanId);

        if(beanDefinition.getBeanClass()!=clazz)
            throw new RuntimeException("类型不匹配");

        return bean!=null?clazz.cast(bean):clazz.cast(createBean(beanDefinition));
    }

    public void registerBeanDefinitions(String beanId, BeanDefinition beanDefinition) {
        // TODO: 2016/7/8
        if(beanDefinition.getLazyInit().equals(LAZY_INIT_TRUE))
        {
            beanDefinition.setBean(createBean(beanDefinition));
        }

        beanDefinitionMap.put(beanId, beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition)
    {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        setProperties(bean, beanDefinition);

        return bean;
    }

    private <T> void setProperties(Object bean, BeanDefinition beanDefinition)
    {
        Field[] fields = beanDefinition.getBeanClass().getDeclaredFields();

        for(Field field:fields)
        {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(beanDefinition.getPropertyValues().getPropertyNames().contains(fieldName))
            {
                Object fieldValue = beanDefinition.getPropertyValues().getValue(fieldName);
                try
                {
                    field.set(bean, fieldValue);
                }
                catch (IllegalAccessException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
