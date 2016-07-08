package main.beans.io.ResourceParser;

import main.beans.factory.beanDefinition.AbstractBeanDefinition;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanDefinition.PropertyValues;
import main.beans.io.resource.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class XmlParser implements ResourceParser{

    private Document document;

    private Resource resource;

    public XmlParser(Resource resource)
    {
        this.resource = resource;
    }

    public List<BeanDefinition> getBeanDefinitions()
    {
        Document document = loadDocumentFromString(resource.getContentAsString());

        Element root = document.getRootElement();
        List<Element> beans = root.elements("bean");

        List<BeanDefinition> beanDefinitionList = new ArrayList<BeanDefinition>();
        for(Element bean:beans) {

            // TODO: 2016/7/8
//            parseBean(bean);

            BeanDefinition beanDefinition = new AbstractBeanDefinition();
            beanDefinition.setBeanId(bean.attributeValue("id"));
            try {
                beanDefinition.setBeanClass(Class.forName(bean.attributeValue("class")));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            beanDefinition = parseProperties(bean, beanDefinition);

            beanDefinitionList.add(beanDefinition);
        }

        return beanDefinitionList;
    }




    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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


    private BeanDefinition parseProperties(Element bean, BeanDefinition beanDefinition)
    {
        PropertyValues propertyValues = new PropertyValues();
        List<Element> properties = bean.elements("property");
        for(Element property:properties)
        {
            propertyValues.add(property.attributeValue("name"), property.attributeValue("value"));
        }
        beanDefinition.setPropertyValues(propertyValues);
        return beanDefinition;
    }



}
