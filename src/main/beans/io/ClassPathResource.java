package main.beans.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by earayu on 2016/7/4.
 */
public abstract class ClassPathResource implements Resource
{

    public InputStream getInputStream() throws FileNotFoundException
    {
        return new FileInputStream(this.file);
    }

}
