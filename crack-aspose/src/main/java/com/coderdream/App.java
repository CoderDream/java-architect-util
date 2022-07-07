package com.coderdream;

import com.aspose.cells.Workbook;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.FileOutputStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        crack();
    }

    public static void crack() {
        try {
            ClassPool pool = ClassPool.getDefault();
            //取得需要反编译的jar文件，设定路径
            pool.insertClassPath("C:\\Users\\CoderDream\\Downloads\\aspose-cells-21.2-java\\JDK 1.6\\aspose-cells-21.2-java\\lib\\aspose-cells-21.2.jar");
            CtClass ctClass = pool.get("com.aspose.cells.License");
            CtMethod ctMethod = ctClass.getMethod("setLicense", "(Ljava/io/InputStream;)V");
            ctMethod.setBody("{ this.a = new com.aspose.cells.License();com.aspose.cells.zbje.a();}");
            ctMethod = ctClass.getMethod("setLicense", "(Ljava/lang/String;)V");
            ctMethod.setBody("{ this.a = new com.aspose.cells.License();com.aspose.cells.zbje.a();}");
            CtMethod kMethod = ctClass.getDeclaredMethod("l");
            kMethod.setBody("{return new java.util.Date(4070880000000L);}");


            //
            // 获取String类型参数集合
			/*CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
			for(CtMethod m:declaredMethods) {
				System.out.println(m.getMethodInfo());
			}*/
            //CtMethod atMethod = ctClass.getMethod("a", "(Ljava/lang/String;Ljava/lang/String;ZZ)Z");
            //atMethod.setBody("{ return true;}");
            //这里会将这个创建的类对象编译为.class文件
            ctClass.writeFile("D:/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void excelToPdf(String sourceFile, String targetFile) {
        try {
            long old = System.currentTimeMillis();
            FileOutputStream os = new FileOutputStream(targetFile);
            Workbook excel = new Workbook(sourceFile);//加载源文件数据
            excel.save(os, com.aspose.cells.SaveFormat.PDF);//设置转换文件类型并转换
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
