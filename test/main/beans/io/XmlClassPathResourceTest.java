package main.beans.io;

import main.Cat;
import main.beans.factory.BeanReader.XmlClassPathBeanDefinitionReader;
import main.beans.factory.applicationContext.ApplicationContext;
import main.beans.factory.applicationContext.XmlClassPathApplicationContext;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanFactory.BeanFactory;
import main.beans.factory.beanFactory.DefaultListableBeanFactory;
import main.beans.factory.beanFactory.XmlClassPathBeanFactory;
import main.beans.io.resource.Resource;
import main.beans.io.resourceloader.ClassPathResourceLoader;
import main.beans.io.resourceloader.ResourceLoader;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by earayu on 2016/7/4.
 */
public class XmlClassPathResourceTest
{
    @Test
    public void testF()
    {
        BeanFactory beanFactory = new XmlClassPathBeanFactory("file.xml");

        Cat cat = beanFactory.getBean("Cat", Cat.class);
        Cat cat2 = beanFactory.getBean("Cat", Cat.class);

        System.out.println(cat);
        System.out.println(cat2);

    }

    @Test
    public void testread()
    {
        ResourceLoader resourceLoader = new ClassPathResourceLoader("file.xml");
        new XmlClassPathBeanDefinitionReader().loadBeanDefinitions(resourceLoader.getResource());



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