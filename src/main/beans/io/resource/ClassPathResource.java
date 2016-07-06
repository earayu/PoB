package main.beans.io.resource;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by earayu on 2016/7/4.
 */
public class ClassPathResource extends AbstractResource
{
    private static final int READ_SIZE = 4096;

    public ClassPathResource(File file)
    {
        this.file = file;
    }

    public InputStream getInputStream()
    {
        InputStream inputStream;
        try
        {
            inputStream = new FileInputStream(file);
        }catch (java.io.FileNotFoundException e)
        {
            throw new main.exceptions.FileNotFoundException();
        }
        return inputStream;
    }

    public String getContentAsString()
    {
        StringBuffer stringBuffer = new StringBuffer(READ_SIZE);
        byte[] bytes = new byte[READ_SIZE];
        InputStream inputStream = getInputStream();
        try
        {
            while ( inputStream.read(bytes, 0, READ_SIZE)!= -1 )
            {
                stringBuffer.append(new String(bytes, "utf-8"));
            }
        }
        catch (IOException e)
        {
            throw new main.exceptions.IOException("found an exception while reading \"" + url + "\"");
        }
        stringBuffer.trimToSize();
        return stringBuffer.toString();
    }


    public boolean exists()
    {
        return this.file!=null;
    }

    public String getLocation()
    {
        return this.location;
    }

    public URL getURL()
    {
        return this.url;
    }

}
