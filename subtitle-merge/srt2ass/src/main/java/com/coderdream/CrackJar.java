package com.coderdream;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class CrackJar {
    public static void main(String[] args) {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath("C:\\Users\\CoderDream\\Downloads\\aspose-cells-21.11-java\\JDK 1.6\\aspose-cells-21.11-java\\lib\\aspose-cells-21.11.jar");
            //获取指定的class文件对象
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.cells.License");
            //从class对象中解析获取所有方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
            for (CtMethod ctMethod : methodA) {
                //获取方法获取参数类型
                CtClass[] ps = ctMethod.getParameterTypes();
                //筛选同名方法，入参是Document
                if (ps.length == 1 && ctMethod.getName().equals("a") && ps[0].getName().equals("org.w3c.dom.Document")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    //替换指定方法的方法体
                    ctMethod.setBody("{a = this;com.aspose.cells.zblc.a();}");
                }
            }
            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile("C:\\Users\\coderdream\\Desktop\\");

        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }


}
