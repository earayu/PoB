package main.beans.factory.BeanReader;

import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.io.ResourceParser.ResourceParser;
import main.beans.io.ResourceParser.XmlParser;
import main.beans.io.resource.Resource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by earayu on 2016/7/7.
 */
public class XmlClassPathBeanDefinitionReader extends AbstractBeanDefinitionReader
{

    protected Set<BeanDefinition> beanDefinitionSet = new HashSet<BeanDefinition>(16);

    protected Resource resource;

    public XmlClassPathBeanDefinitionReader(Resource resource){
        this.resource = resource;
        loadBeanDefinitions();
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    private void loadBeanDefinitions()
    {
        ResourceParser resourceParser = new XmlParser(resource);
        beanDefinitionSet.addAll(resourceParser.getBeanDefinitions());
    }

    public Set<BeanDefinition> getBeanDefinitions() {
        return beanDefinitionSet;
    }


}
