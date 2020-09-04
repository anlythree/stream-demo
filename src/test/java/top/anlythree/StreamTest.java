package top.anlythree;

import org.junit.Test;
import top.anlythree.model.Person;
import top.anlythree.myinterface.MyInterface;
import top.anlythree.myinterface.impl.MyInterfaceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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

    /**
     * 中间过程中间创建的节点是懒加载的，只有终止节点是实时加载的。
     * 中间节点是根据终止节点来加载的
     * 返回流的方法通常就是中间节点，剩下的方法通常就是终止节点
     */
    @Test
    public void streamCreateMiddleNode(){
        ArrayList<Person> personList = new ArrayList();
        personList.add(new Person());
        Object[] objects = personList.stream()
                .peek(a -> System.out.println(a.getName()))
                .peek(a -> System.out.println(a.getAge()))
                .toArray();
    }

    /**
     * 流是一个元素一个元素的读取list的
     */
    @Test
    public void streamOneByOneToRead(){
        ArrayList<Person> personList = new ArrayList();
        personList.add(new Person(2,"wangwu"));
        personList.add(new Person(4,"wangli"));
        personList.add(new Person(14,"wangzhengzhi"));
        personList.add(new Person(3,"chenwu2"));
        personList.add(new Person(17,"wangwu3"));
        Object[] objects = personList.stream()
                .peek(a -> System.out.println(a.getName()))
                .peek(a -> System.out.println(a.getAge()))
                .toArray();
    }

    /**
     * 流的上一个节点会影响下一个节点
     */
    @Test
    public void streamEffectNextNode(){
        ArrayList<Person> personList = new ArrayList();
        personList.add(new Person(2,"wangwu"));
        personList.add(new Person(4,"wangli"));
        personList.add(new Person(14,"wangzhengzhi"));
        personList.add(new Person(3,"chenwu2"));
        personList.add(new Person(17,"wangwu3"));
        personList.stream()
                .peek(a -> System.out.println("before:"+a.getName()))
                .map(a->a.getName())
                .distinct()
//                .peek(a -> System.out.println("after:"+a.getName()))
                .toArray();
    }


    public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    public void test(MyInterface myInterface){
        System.out.println(myInterface.test1());;
    }

    @Test
    public void test2(){
        test(()-> "12");
    }

    @Test
    public void test3(){
        MyInterface myInterface = new MyInterfaceImpl();
        test(myInterface);
    }


    /**
     * collect节点方法中的Collectors.toMap方法
     */
    @Test
    public void streamToMap(){
        ArrayList<Person> personList = new ArrayList();
        personList.add(new Person(2,"wangwu"));
        Map<Integer, Person> collect = personList.stream()
                .collect(Collectors.toMap(a -> a.getAge(), a -> a, (a1, a2) -> a1));
    }


}
