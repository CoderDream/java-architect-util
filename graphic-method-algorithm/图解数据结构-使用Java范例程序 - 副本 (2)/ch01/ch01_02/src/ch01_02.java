// ��ջ��Ӧ�á���쳲���������
import java.io.*;
class ch01_02
{
   public static void main(String args[]) throws IOException    
   {
      int num;
      String str;
      BufferedReader buf;
      buf=new BufferedReader(new InputStreamReader(System.in));
      System.out.print("ʹ�õݹ����쳲���������\n");
      System.out.print("������һ������:");
      str=buf.readLine();
      num=Integer.parseInt(str);
      if (num<0)
         System.out.print("��������ֱ������0\n");
      else
         System.out.print("Fibonacci("+num+")="+Fibonacci(num)+"\n") ; 
   }
   public static int Fibonacci(int n)
   {  
      if (n==0)      // ��0��Ϊ 0
         return (0) ;
      else if (n==1) // ��1��Ϊ 1
         return (1) ;
      else
         return( Fibonacci(n-1)+Fibonacci(n-2));
      // �ݹ���ú�������N��Ϊn-1 �� n-2 ��֮��
   }
}