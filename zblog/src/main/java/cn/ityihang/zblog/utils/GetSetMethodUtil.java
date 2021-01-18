package cn.hongxin.srm.supplier.utils;

import cn.ityihang.zblog.blog.entity.BlogComment;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GetSetMethodUtil {


    public static void main(String[] args) throws Exception {
        BlogComment blogComment = new BlogComment();
        blogComment.setContent("99");
        getGetMethod(blogComment,"xb");

    }


    /**
     * 根据属性，获取get,set方法
     * @param ob 对象
     * @return
     * @throws Exception
     */
    private static void  getGetMethod(Object ob,String objName)throws Exception{
        Method[] m = ob.getClass().getMethods();
        Field[] field = ob.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        for (int j = 0; j < field.length; j++) { // 遍历所有属性
            String name = field[j].getName(); // 获取属性的名字

            for (int i = 0; i < m.length; i++) {
                if (("get" + name).toLowerCase().equals(m[i].getName().toLowerCase())) {
                    String name2 = name.substring(0, 1).toUpperCase() + name.substring(1);
                    System.out.println(objName+".set" + name2+"( );");
                }
            }
        }


    }

    /**
     * 根据属性，获取get,set方法
     * @param ob 对象
     * @return
     * @throws Exception
     */
    private static void  getSetMethod(Object ob,String objName)throws Exception{
        Method[] m = ob.getClass().getMethods();
        Field[] field = ob.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组

        for (int j = 0; j < field.length; j++) { // 遍历所有属性
            String name = field[j].getName(); // 获取属性的名字

            for (int i = 0; i < m.length; i++) {
                if (("set" + name).toLowerCase().equals(m[i].getName().toLowerCase())) {
                    String name2 = name.substring(0, 1).toUpperCase() + name.substring(1);
                    System.out.println(objName+".set" + name2+"( );");
                }
            }
        }

    }

}
