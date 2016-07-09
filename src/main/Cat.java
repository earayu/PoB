package main;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Cat {

    private String name;

    private String brand;

    private Object friend;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", friend=" + friend +
                '}';
    }
}
