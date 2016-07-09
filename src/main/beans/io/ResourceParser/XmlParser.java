package main.beans.io.ResourceParser;

import main.beans.factory.beanDefinition.AbstractBeanDefinition;
import main.beans.factory.beanDefinition.BeanDefinition;
import main.beans.factory.beanDefinition.BeanReference;
import main.beans.factory.beanDefinition.PropertyValues;
import main.beans.io.resource.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/8.
 * todo
 * 1. Alias
 * 2. ref
 * 3. constructor
 */
public class XmlParser implements ResourceParser{



    private Document document;

    private Resource resource;

    public XmlParser(Resource resource)
    {
        init(resource);
    }

    public Set<BeanDefinition> getBeanDefinitions()
    {
        Element root = document.getRootElement();
        List<Element> beans = root.elements("bean");

        Set<BeanDefinition> beanDefinitionList = new HashSet<BeanDefinition>();
        for(Element bean:beans) {
            beanDefinitionList.add(parseBean(bean));
        }

        return beanDefinitionList;
    }

    private BeanDefinition parseBean(Element bean)
    {
        BeanDefinition beanDefinition = new AbstractBeanDefinition();

        beanDefinition = parseBeanAttributes(bean, beanDefinition);
        beanDefinition = parseProperties(bean, beanDefinition);
        return beanDefinition;
    }

    private BeanDefinition parseBeanAttributes(Element bean, BeanDefinition beanDefinition)
    {
        beanDefinition.setBeanId(bean.attributeValue("id"));
        try {
            beanDefinition.setBeanClass(Class.forName(bean.attributeValue("class")));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String scope = bean.attributeValue("scope");
        if(scope==null)
            scope = "singleton";
        if(!scope.equals("singleton") && !scope.equals("prototype"))
            throw new RuntimeException("scope 的值必须为\"singleton\"或\"prototype\"");
        beanDefinition.setScope(scope);

        String lazyInit = bean.attributeValue("lazy-init");
        if(lazyInit==null)
            lazyInit = "false";
        if(!lazyInit.equals("true") && !lazyInit.equals("false"))
            throw new RuntimeException("lazy-init 的值必须为\"true\"或\"false\"");
        beanDefinition.setLazyInit(lazyInit);

        return beanDefinition;
    }

    private BeanDefinition parseProperties(Element bean, BeanDefinition beanDefinition)
    {
        PropertyValues propertyValues = new PropertyValues();
        List<Element> properties = bean.elements("property");
        for(Element property:properties)
        {
            String propertyName = property.attributeValue("name");
            Object propertyValue = property.attributeValue("value");
            String type = property.attributeValue("type");
            if(propertyValue!=null) {
                //直接赋值, TODO: 2016/7/9 应该要处理不同类型的值
                if(type!=null)
                {
                    type = changeType(type);
                    propertyValue = resolve(type, propertyValue);
                }
                propertyValues.add(propertyName, propertyValue);
            }else {
                //处理Bean的依赖关系
                BeanReference beanReference = new BeanReference(property.attributeValue("ref"));
                propertyValues.add(propertyName, beanReference);
            }
        }
        beanDefinition.setPropertyValues(propertyValues);
        return beanDefinition;
    }

    public Resource getResource() {
        return resource;
    }


    public void setResource(Resource resource) {
        this.resource = resource;
    }

    private void init(Resource resource)
    {
        this.resource = resource;
        this.document = loadDocumentFromString(resource.getContentAsString());
    }


    private Document loadDocumentFromString(String strRes)
    {
        String xmlString = strRes.substring(strRes.indexOf("<?xml"));
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        this.document = document;
        return document;
    }

    // TODO: 2016/7/9 简陋形式的类型转换 
    private String changeType(String type)
    {
        switch (type)
        {
            case "int":
                return "java.lang.Integer";
            case "boolean":
                return "java.lang.Boolean";
            case "short":
                return "java.lang.Short";
            case "byte":
                return "java.lang.Byte";
            case "long":
                return "java.lang.Long";
            case "char":
                return "java.lang.Character";
            case "float":
                return "java.lang.Float";
            case "double":
                return "java.lang.Double";
            default:
                // TODO: 2016/7/9 应该支持引用类型
                throw new RuntimeException();
        }
    }

    private <T> T resolve(String type, Object value)
    {
        try
        {
            System.out.println(type);
            Class<?> c = Class.forName(type);
            Constructor<T> con = (Constructor<T>) c.getConstructor(String.class);
            return (T) con.newInstance(value);
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;
    }





}
