package main.beans.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by earayu on 2016/7/4.
 */
public class XmlClassPathResourceTest
{
    @org.junit.Test
    public void getInputStream() throws Exception
    {
        URL url = this.getClass().getClassLoader().getResource("file");

        InputStream inputStream = new FileInputStream(url.getFile());
        File file = new File(url.getFile());
        System.out.println(file);
        System.out.println(url.getFile());
    }

}