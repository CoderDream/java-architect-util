import java.io.*;
class ch01_03
{

   public static void main(String args[]) throws IOException    
   {
      int sum=1;
      
      java.util.Scanner input_obj=new java.util.Scanner(System.in);		
      System.out.print("��Ӽ�������n= ");
      int n =input_obj.nextInt();

      //��forѭ������ n! 
      for(int i=1;i<n+1;i++){
         for (int j=i;j>0;j--)
             sum=sum*j;    // sum=sum*j
         System.out.println(i+"!="+sum);
         sum=1;
      }
   }
}