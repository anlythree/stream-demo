package top.anlythree.myinterface;

/**
 * @author wangli
 * @date 2020/9/4 11:02
 */
@FunctionalInterface
public interface MyInterface {

    String test1();

    default String test2(){
        return "interface2";
    }

    default String test3(){
        return "interface3";
    }
}
