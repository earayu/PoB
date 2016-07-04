package main.beans.io;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by earayu on 2016/7/4.
 */
public abstract class ClassPathResource extends AbstractResource
{

    private final File file;

    public ClassPathResource(File file)
    {
        this.file = file;
    }

    public InputStream getInputStream() throws FileNotFoundException
    {
        return new FileInputStream(this.file);
    }

}
