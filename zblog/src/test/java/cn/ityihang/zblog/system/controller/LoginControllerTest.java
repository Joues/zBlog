package cn.ityihang.zblog.system.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("登录测试用例")
class LoginControllerTest {

    @BeforeEach
    public void BeforeEach() {
        System.out.println("BeforeEach do it ...........");
    }

    @DisplayName("测试用例-1")
    @Test
    public void Test() {
        System.out.println("11111111111111");
        printVariable("aaa", "bbb", "ccc");
        printVariableNoStatic("ddd", "eee", "fff");
    }

    public static void printVariable(String... args) {
        for (String arg : args) {
            System.out.println("args........" + arg);
        }
    }

    public void printVariableNoStatic(String... args) {
        for (String arg : args) {
            System.out.println("NoStatic args........." + arg);
        }
    }

    @AfterEach
    public void AfterEach() {
        System.out.println("AfterEach do it ............");
    }

    public static void main(String[] args) {
        printVariable("aaa", "bbb", "ccc");
        LoginControllerTest test = new LoginControllerTest();
        test.printVariableNoStatic("ddd", "eee", "fff");
    }
}