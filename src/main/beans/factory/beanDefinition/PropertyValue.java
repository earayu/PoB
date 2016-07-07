package main.beans.factory.beanDefinition;

/**
 * Created by Administrator on 2016/7/7.
 */
public class PropertyValue {

    private String propertyName;

    private Object propertyValue;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "propertyName='" + propertyName + '\'' +
                ", propertyValue=" + propertyValue +
                '}';
    }
}
