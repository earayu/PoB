package main.beans.factory.beanDefinition;

import java.util.Set;

/**
 * Created by Administrator on 2016/7/4.
 */
public interface BeanDefinition {

    public PropertyValues getPropertyValues();

    public void setPropertyValues(PropertyValues propertyValues);

    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    Object getBean();

    void setBean(Object bean);

    String getBeanId();

    void setBeanId(String beanId);

    String getBeanName();

    void setBeanName(String beanName);

    String getBeanAlias();

    void setBeanAlias(String beanAlias);

    Class getBeanClass();

    void setBeanClass(Class beanClass);

}
