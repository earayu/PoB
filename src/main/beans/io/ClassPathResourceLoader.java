package main.beans.io;

import com.google.common.base.Preconditions;

import java.io.File;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ClassPathResourceLoader implements ResourceLoader {

    public Resource getResource(String location) {
        Preconditions.checkNotNull(location);
        URL url = this.getClass().getClassLoader().getResource(location);
        Preconditions.checkNotNull(url);
        return new ClassPathResource(new File(url.getFile()));
    }

}
