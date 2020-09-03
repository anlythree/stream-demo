package top.anlythree;

import org.junit.Test;
import top.anlythree.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author wangli
 * @date 2020/9/3 17:15
 */
public class StreamTest {

    /**
     * 流的创建
     */
    @Test
    public void streamCreateTest(){
        ArrayList<String> strList = new ArrayList();
        // 1、调用ArrayList中的.stream()生成
        Stream<String> stream = strList.stream();
        // 2、使用Arrays中的.stream()生成
        Arrays.stream(new int[]{1,2,3});
        // 3、使用Stream中的.of()生成
        Stream.of("12","13","14");
    }

    /**
     * 流是不可复用的，下面的stream1已经用过了就会被作废，所以下面的
     * Stream<Person> personStream3 = personStream1.filter(a -> a.getAge() > 3)就会报错
     */
    @Test
    public void streamCreateNoReusability(){
        ArrayList<Person> personList = new ArrayList();
        Stream<Person> personStream1 = personList.stream();
        Stream<Person> personStream2 = personStream1.filter(a -> a.getName().substring(0, 1).equals("王"));
        Stream<Person> personStream3 = personStream1.filter(a -> a.getAge() > 3);
    }
}
