package main.beans.io.resourceloader;

/**
 * Created by earayu on 2016/7/7.
 */
public abstract class AbstractResourceLoader implements ResourceLoader
{

    protected String location;

    public AbstractResourceLoader(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
