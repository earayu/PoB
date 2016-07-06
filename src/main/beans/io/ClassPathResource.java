package main.beans.io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by earayu on 2016/7/4.
 */
public class ClassPathResource extends AbstractResource
{

    public ClassPathResource(File file)
    {
        this.file = file;
    }

    public InputStream getInputStream() throws FileNotFoundException
    {
        return new FileInputStream(file);
    }

    public boolean exists()
    {
        return this.file!=null;
    }

}
