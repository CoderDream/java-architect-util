// 堆栈的应用——斐波拉契数列
import java.io.*;
class ch01_02
{
   public static void main(String args[]) throws IOException    
   {
      int num;
      String str;
      BufferedReader buf;
      buf=new BufferedReader(new InputStreamReader(System.in));
      System.out.print("使用递归计算斐波拉契数列\n");
      System.out.print("请输入一个整数:");
      str=buf.readLine();
      num=Integer.parseInt(str);
      if (num<0)
         System.out.print("输入的数字必须大于0\n");
      else
         System.out.print("Fibonacci("+num+")="+Fibonacci(num)+"\n") ; 
   }
   public static int Fibonacci(int n)
   {  
      if (n==0)      // 第0项为 0
         return (0) ;
      else if (n==1) // 第1项为 1
         return (1) ;
      else
         return( Fibonacci(n-1)+Fibonacci(n-2));
      // 递归调用函数：第N项为n-1 与 n-2 项之和
   }
}