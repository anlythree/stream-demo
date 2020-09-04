package top.anlythree.model;


import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;

/**
 * @author wangli
 * @date 2020/9/1 17:04
 */
@Data
public class Person {

    private String name;

    private Integer age;


    public Person(Integer age, String name) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
