package main.beans.factory.beanDefinition;

/**
 * Created by Administrator on 2016/7/7.
 */
public class AbstractBeanDefinition implements BeanDefinition{

    protected Object bean;

    protected String beanId;

    protected String beanName;

    protected String beanAlias;

    protected Class beanClass;

    protected String beanClassName;

    protected PropertyValues propertyValues;

    protected String scope;

    public String getScope()
    {
        return scope;
    }

    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanAlias() {
        return beanAlias;
    }

    public void setBeanAlias(String beanAlias) {
        this.beanAlias = beanAlias;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public String toString()
    {
        return "AbstractBeanDefinition{" +
                "bean=" + bean +
                ", beanId='" + beanId + '\'' +
                ", beanName='" + beanName + '\'' +
                ", beanAlias='" + beanAlias + '\'' +
                ", beanClass=" + beanClass +
                ", beanClassName='" + beanClassName + '\'' +
                ", propertyValues=" + propertyValues +
                ", scope='" + scope + '\'' +
                '}';
    }
}
