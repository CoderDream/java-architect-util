package com.example.demo.p03.dp16.interpreter.client;
/*解释器模式有点像演算法中的个别击破解释的方式，对每一个父节点
 我们剖析出其中的子节点的组合，然后再交由子节点剖析,直接剖析到终端节点为止。
 让我们做一个小小的程式解释器,可以将自定义文法程式文件解释执行。程式的关键字只有PROGRAM、PRINT、SPACE、BREAK、LINEBREAK、REPEAT、END。
 PROGRAM是程式的开始，END是结尾。简单的例子为:
    PROGRAM
   PRINT dog SPACE
   PRINT is SPACE
   PRINT an SPACE
   PRINT animai
   END
程序会印出"dog is an animal",再一个例子:
 PROGRAM
    REPEAT 2
        LINEBREAK
        PRINT dog
        BREAK
    END
 END
程序会印出
 ------------------------------
 dog
 ------------------------------
 dog


 */

import com.example.demo.p03.dp16.interpreter.bean.Node;
import com.example.demo.p03.dp16.interpreter.bean.impl.Context;
import com.example.demo.p03.dp16.interpreter.bean.impl.ProgramNode;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String text;
            while ((text = reader.readLine()) != null) {
                System.out.println("text = \"" + text + "\"");
                Node node = new ProgramNode();
                node.parse(new Context(text));
                System.out.println("node = " + node);
                node.execute();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Usage: java Main yourprogram.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
