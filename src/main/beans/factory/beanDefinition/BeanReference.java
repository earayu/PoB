package main.beans.factory.beanDefinition;

/**
 * Created by Administrator on 2016/7/9.
 */
public class BeanReference {
    private final String beanId;
    private Object ref;

    public BeanReference(String beanId)
    {
        this.beanId = beanId;
    }

    public BeanReference(String beanId, String ref)
    {
        this.beanId = beanId;
        this.ref = ref;
    }

    public String getBeanId() {
        return beanId;
    }

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }


}
