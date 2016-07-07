package main.beans.factory.beanFactory;

import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanRegistry.BeanRegistry;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by earayu on 2016/7/4.
 */
public class DefaultListableBeanFactory implements BeanFactory, BeanRegistry
{

    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(16);

    protected Set<BeanDefinition> beanDefinitionSet;


    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        checkNotNull(beanDefinition);

        Object bean = beanDefinition.getBean();
        if(bean!=null)
            return bean;

        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    public <T> T getBean(String beanName, Class<T> clazz) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        checkNotNull(beanDefinition);

        if(beanDefinition.getBeanClass()!=clazz)
            throw new RuntimeException("类型不匹配");

        Object bean = beanDefinition.getBean();
        if(bean==null)
        {
            try
            {
                synchronized (this)
                {
                    bean = beanDefinition.getBeanClass().newInstance();
                    setProperties(bean, beanName, clazz);
                    beanDefinition.setBean(bean);
                }
            }
            catch (InstantiationException e)
            {
                throw new RuntimeException(e);
            }
            catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        }


        return clazz.cast(bean);
    }

    public void registerBeanDefinitions(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    private <T> void setProperties(Object bean, String beanName, Class<T> clazz)
    {
        Field[] fields = clazz.getFields();

        for(Field field:fields)
        {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(beanDefinitionMap.get(beanName).getPropertyValues().getPropertyNames().contains(fieldName))
            {
                Object fieldValue = beanDefinitionMap.get(beanName).getPropertyValues().getValue(fieldName);
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
