package main;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Dog {

    public String name;

    public Object friend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
