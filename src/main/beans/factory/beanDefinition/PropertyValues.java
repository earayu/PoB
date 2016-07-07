package main.beans.factory.beanDefinition;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/7.
 */
public class PropertyValues {

    private Set<PropertyValue> propertyValues;

    public PropertyValues()
    {
        propertyValues = new HashSet<PropertyValue>();
    }

    public Set<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Set<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public PropertyValue[] getPropertyValuesArray()
    {
        return propertyValues.toArray(new PropertyValue[0]);
    }

    public void add(PropertyValue propertyValue)
    {
        propertyValues.add(propertyValue);
    }


}
