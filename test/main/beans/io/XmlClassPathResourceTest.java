package main.beans.io;

import main.Cat;
import main.beans.factory.BeanReader.XmlClassPathBeanDefinitionReader;
import main.beans.factory.applicationContext.ApplicationContext;
import main.beans.factory.beanFactory.BeanFactory;
import main.beans.factory.applicationContext.XmlClassPathApplicationContext;
import main.beans.io.resource.Resource;
import main.beans.io.resourceloader.ClassPathResourceLoader;
import main.beans.io.resourceloader.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * Created by earayu on 2016/7/4.
 */
public class XmlClassPathResourceTest
{
    @Test
    public void testF()
    {
        ApplicationContext applicationContext = new XmlClassPathApplicationContext("file.xml");

        Cat cat = applicationContext.getBean("Cat", Cat.class);

        System.out.println(cat);

    }

    @Test
    public void test2() throws NoSuchFieldException, IllegalAccessException
    {
        Cat cat = new Cat();
        Field field = Cat.class.getField("name");
        field.setAccessible(true);
        field.set(cat, "tom");
        System.out.println(cat);
    }

    @Test
    public void testxml() throws UnsupportedEncodingException, DocumentException
    {
        ResourceLoader resourceLoader = new ClassPathResourceLoader("file.xml");
        String strRes = resourceLoader.getResource().getContentAsString();
        String xmlString = strRes.substring(strRes.indexOf("<?xml"));
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));

        Element root = document.getRootElement();
        System.out.println(root.element("bean").element("property").attributeValue("name"));
    }

    @Test
    public void testread()
    {
        ResourceLoader resourceLoader = new ClassPathResourceLoader("file.xml");
        new XmlClassPathBeanDefinitionReader(resourceLoader.getResource());
    }

    @Test
    public void test() throws IOException
    {
        ResourceLoader resourceLoader = new ClassPathResourceLoader("file.xml");
        Resource resource = resourceLoader.getResource();

        System.out.println(resource.getContentAsString());

    }

    @org.junit.Test
    public void getInputStream() throws Exception
    {
        URL url = this.getClass().getClassLoader().getResource("file.xml");

        InputStream inputStream = new FileInputStream(url.getFile());
        File file = new File(url.getFile());
        System.out.println(file);
        System.out.println(url.getFile());
    }

}