package main;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Cat {

    public String name;

    public String brand;

    public Object friend;

    public Integer beautiful;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", friend=" + friend +
                ", beautiful=" + beautiful +
                '}';
    }
}
