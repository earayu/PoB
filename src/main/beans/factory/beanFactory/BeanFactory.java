package main.beans.factory.beanFactory;

/**
 * Created by Administrator on 2016/7/4.
 */
public interface BeanFactory {

    Object getBean(String beanName);

    <T> T getBean(String beanName, Class<T> clazz);
}
