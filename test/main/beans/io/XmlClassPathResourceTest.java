package main.beans.io;

import java.io.InputStream;

/**
 * Created by earayu on 2016/7/4.
 */
public class XmlClassPathResourceTest
{
    @org.junit.Test
    public void getInputStream() throws Exception
    {
        InputStream inputStream = new XmlClassPathResource().getInputStream("file");
        System.out.println(inputStream);
    }

}