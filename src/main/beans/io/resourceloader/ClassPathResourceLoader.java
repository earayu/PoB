package main.beans.io.resourceloader;

import com.google.common.base.Preconditions;
import main.beans.io.resource.ClassPathResource;
import main.beans.io.resource.Resource;

import java.io.File;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ClassPathResourceLoader extends AbstractResourceLoader {

    public ClassPathResourceLoader(String location)
    {
        super(location);
    }

    public Resource getResource() {
        Preconditions.checkNotNull(location);
        URL url = this.getClass().getClassLoader().getResource(location);
        Preconditions.checkNotNull(url);
        File file = new File(url.getFile());
        ClassPathResource classPathResource = new ClassPathResource(file);

        classPathResource.setLocation(location);
        classPathResource.setUrl(url);
        classPathResource.setFile(file);

        return classPathResource;
    }

}
