package main.beans.io;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by earayu on 2016/7/4.
 */
public interface Resource
{

    InputStream getInputStream() throws FileNotFoundException;

    boolean exists();

    // TODO: 2016/7/5 其他方法
}
