package main.beans.factory.BeanReader;

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

    public void loadBeanDefinitions(Resource resource)
    {
        loadBeanDefinitions(resource.getContentAsString());
    }

    public void loadBeanDefinitions(String strRes){
        Document document = getDocument(strRes);
        XmlParser xmlParser = XmlParser.class.cast(getResourceParser());

        List<BeanDefinition> beanDefinitionList = xmlParser.getBeanDefinitions(document);

        beanDefinitionSet.addAll(beanDefinitionList);
    }

    public Set<BeanDefinition> getBeanDefinitions() {
        return beanDefinitionSet;
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

    private ResourceParser getResourceParser()
    {
        return new XmlParser(this);
    }



}
