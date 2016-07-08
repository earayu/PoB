package main.beans.io.ResourceParser;

import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.io.resource.Resource;
import org.dom4j.Document;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface ResourceParser {

    List<BeanDefinition> getBeanDefinitions();

}
