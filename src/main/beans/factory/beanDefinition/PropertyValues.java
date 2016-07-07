package main.beans.factory.beanDefinition;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/7/7.
 */
public class PropertyValues {

//    private Set<PropertyValue> propertyValues;
//
//    public PropertyValues()
//    {
//        propertyValues = new HashSet<PropertyValue>();
//    }
//
//    public Set<PropertyValue> getPropertyValues() {
//        return propertyValues;
//    }
//
//    public void setPropertyValues(Set<PropertyValue> propertyValues) {
//        this.propertyValues = propertyValues;
//    }
//
//    public PropertyValue[] getPropertyValuesArray()
//    {
//        return propertyValues.toArray(new PropertyValue[0]);
//    }
//
//    public void add(String name, Object value)
//    {
//        propertyValues.add(new PropertyValue(name, value));
//    }

    private ConcurrentHashMap<String, Object> propertyValues = new ConcurrentHashMap<String,Object>(16);

    public void add(String name, Object value)
    {
        propertyValues.put(name, value);
    }

    public Set<String> getPropertyNames()
    {
        return propertyValues.keySet();
    }

    public boolean containsPropertyName(String name)
    {
        return propertyValues.keySet().contains(name);
    }

    public Object getValue(String key)
    {
        return propertyValues.get(key);
    }

    public ConcurrentHashMap<String, Object> getPropertyValues()
    {
        return propertyValues;
    }

    public void setPropertyValues(ConcurrentHashMap<String, Object> propertyValues)
    {
        this.propertyValues = propertyValues;
    }
}
