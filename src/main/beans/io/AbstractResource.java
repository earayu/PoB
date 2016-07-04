package main.beans.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by earayu on 2016/7/5.
 */
public abstract class AbstractResource implements Resource
{
    public InputStream getInputStream() throws FileNotFoundException
    {
        return null;
    }

    public boolean exists()
    {
        return false;
    }
}
