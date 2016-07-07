package main.beans.factory.BeanReader;

import main.beans.io.resource.Resource;

/**
 * Created by earayu on 2016/7/7.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader
{

    public void loadBeanDefinitions(Resource resource)
    {
        loadBeanDefinitions(resource.getContentAsString());
    }

}
