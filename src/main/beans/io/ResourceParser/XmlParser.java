package main.beans.io.ResourceParser;

import main.beans.factory.beanDefinition.AbstractBeanDefinition;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanDefinition.BeanReference;
import main.beans.factory.beanDefinition.PropertyValues;
import main.beans.io.resource.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/8.
 * todo
 * 1. Alias
 * 2. ref
 * 3. constructor
 */
public class XmlParser implements ResourceParser{



    private Document document;

    private Resource resource;

    public XmlParser(Resource resource)
    {
        init(resource);
    }

    public Set<BeanDefinition> getBeanDefinitions()
    {
        Element root = document.getRootElement();
        List<Element> beans = root.elements("bean");

        Set<BeanDefinition> beanDefinitionList = new HashSet<BeanDefinition>();
        for(Element bean:beans) {
            beanDefinitionList.add(parseBean(bean));
        }

        return beanDefinitionList;
    }

    private BeanDefinition parseBean(Element bean)
    {
        BeanDefinition beanDefinition = new AbstractBeanDefinition();

        beanDefinition = parseBeanAttributes(bean, beanDefinition);
        beanDefinition = parseProperties(bean, beanDefinition);
        return beanDefinition;
    }

    private BeanDefinition parseBeanAttributes(Element bean, BeanDefinition beanDefinition)
    {
        beanDefinition.setBeanId(bean.attributeValue("id"));
        try {
            beanDefinition.setBeanClass(Class.forName(bean.attributeValue("class")));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String scope = bean.attributeValue("scope");
        if(scope==null)
            scope = "singleton";
        if(!scope.equals("singleton") && !scope.equals("prototype"))
            throw new RuntimeException("scope 的值必须为\"singleton\"或\"prototype\"");
        beanDefinition.setScope(scope);

        String lazyInit = bean.attributeValue("lazy-init");
        if(lazyInit==null)
            lazyInit = "false";
        if(!lazyInit.equals("true") && !lazyInit.equals("false"))
            throw new RuntimeException("lazy-init 的值必须为\"true\"或\"false\"");
        beanDefinition.setLazyInit(lazyInit);

        return beanDefinition;
    }

    private BeanDefinition parseProperties(Element bean, BeanDefinition beanDefinition)
    {
        PropertyValues propertyValues = new PropertyValues();
        List<Element> properties = bean.elements("property");
        for(Element property:properties)
        {
            String propertyName = property.attributeValue("name");
            Object propertyValue = property.attributeValue("value");
            if(propertyValue!=null) {
                //直接赋值, TODO: 2016/7/9 应该要处理不同类型的值
                propertyValues.add(propertyName, propertyValue);
            }else {
                //处理Bean的依赖关系
                BeanReference beanReference = new BeanReference(property.attributeValue("ref"));
                propertyValues.add(propertyName, beanReference);
            }
        }
        beanDefinition.setPropertyValues(propertyValues);
        return beanDefinition;
    }

    public Resource getResource() {
        return resource;
    }


    public void setResource(Resource resource) {
        this.resource = resource;
    }

    private void init(Resource resource)
    {
        this.resource = resource;
        this.document = loadDocumentFromString(resource.getContentAsString());
    }


    private Document loadDocumentFromString(String strRes)
    {
        String xmlString = strRes.substring(strRes.indexOf("<?xml"));
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.document = document;
        return document;
    }



}
