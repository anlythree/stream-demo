package top.anlythree.myinterface.impl;

import top.anlythree.myinterface.MyInterface;

/**
 * @author wangli
 * @date 2020/9/4 11:12
 */
public class MyInterfaceImpl implements MyInterface {

    @Override
    public String test1() {
        return "impl1";
    }

    @Override
    public String test2() {
        return "impl2";
    }

    @Override
    public String test3() {
        return "impl3";
    }
}
