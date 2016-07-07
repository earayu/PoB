package main.beans.factory.BeanReader;

import main.beans.io.resource.Resource;
import org.dom4j.DocumentException;

import java.io.UnsupportedEncodingException;

/**
 * Created by earayu on 2016/7/7.
 */
public interface BeanDefinitionReader
{

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(String strRes);

}
