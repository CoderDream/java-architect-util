//递归程序的使用
public class ch01_01  //创建类
{
   public static void main(String args[])
   {
      System.out.println("5!="+fac(5));
   }
   public static int fac(int n)
   {
      if(n==0) //递归终止的条件
         return 1;
      else
         return n*fac(n-1); //递归调用
   }
}
