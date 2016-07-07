package main.beans.factory.BeanReader;

import main.beans.factory.beanDefinition.AbstractBeanDefinition;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanDefinition.PropertyValue;
import main.beans.io.resource.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by earayu on 2016/7/7.
 */
public class XmlClassPathBeanDefinitionReader extends AbstractBeanDefinitionReader
{

    protected Set<BeanDefinition> beanDefinitionSet = new HashSet<BeanDefinition>(16);



    public XmlClassPathBeanDefinitionReader(){}


    public void loadBeanDefinitions(String strRes){
        Document document = getDocument(strRes);
        List<BeanDefinition> beanDefinitionList = getBeanDefinitions(document);
        beanDefinitionSet.addAll(beanDefinitionList);
    }

    private Document getDocument(String strRes)
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
        return document;
    }

    private List<BeanDefinition> getBeanDefinitions(Document document)
    {
        Element root = document.getRootElement();

        List<Element> beans = root.elements("bean");
        List<BeanDefinition> beanDefinitionList = new ArrayList<BeanDefinition>();
        for(Element bean:beans) {

            AbstractBeanDefinition beanDefinition = new AbstractBeanDefinition();
            beanDefinition.setBeanId(bean.attributeValue("id"));
            try {
                beanDefinition.setBeanClass(Class.forName(bean.attributeValue("class")));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            PropertyValue propertyValue = new PropertyValue();

            propertyValue.setPropertyName(bean.element("property").attributeValue("name"));
            propertyValue.setPropertyValue(bean.element("property").attributeValue("value"));

            beanDefinitionList.add(beanDefinition);
        }
        return beanDefinitionList;
    }


    public Set<BeanDefinition> getBeanDefinitionSet() {
        return beanDefinitionSet;
    }

    public void setBeanDefinitionSet(Set<BeanDefinition> beanDefinitionSet) {
        this.beanDefinitionSet = beanDefinitionSet;
    }
}
