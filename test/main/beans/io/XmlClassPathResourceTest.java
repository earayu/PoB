package main.beans.io;

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