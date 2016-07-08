package main.beans.factory.BeanReader;

import main.beans.factory.beanDefinition.AbstractBeanDefinition;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanDefinition.PropertyValues;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class XmlParser implements ResourceParser{

    private XmlClassPathBeanDefinitionReader xmlClassPathBeanDefinitionReader;

    private Document document;

    public XmlParser(){}

    public XmlParser(XmlClassPathBeanDefinitionReader xmlClassPathBeanDefinitionReader)
    {
        this.xmlClassPathBeanDefinitionReader = xmlClassPathBeanDefinitionReader;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public XmlClassPathBeanDefinitionReader getXmlClassPathBeanDefinitionReader() {
        return xmlClassPathBeanDefinitionReader;
    }

    public void setXmlClassPathBeanDefinitionReader(XmlClassPathBeanDefinitionReader xmlClassPathBeanDefinitionReader) {
        this.xmlClassPathBeanDefinitionReader = xmlClassPathBeanDefinitionReader;
    }




    public List<BeanDefinition> getBeanDefinitions(Document document)
    {
        Element root = document.getRootElement();

        List<Element> beans = root.elements("bean");
        List<BeanDefinition> beanDefinitionList = new ArrayList<BeanDefinition>();
        for(Element bean:beans) {

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
