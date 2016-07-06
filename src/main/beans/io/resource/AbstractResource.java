package main.beans.io.resource;

import java.io.File;
import java.net.URL;

/**
 * Created by earayu on 2016/7/5.
 */
public abstract class AbstractResource implements Resource
{
    protected File file;

    protected String location;

    protected URL url;

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public URL getUrl()
    {
        return url;
    }

    public void setUrl(URL url)
    {
        this.url = url;
    }
}
