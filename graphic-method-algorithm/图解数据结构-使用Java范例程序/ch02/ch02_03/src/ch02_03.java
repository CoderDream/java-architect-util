// 找出三维数组中所存储数值中的最小值
import java.io.*;
class ch02_03
{
   public static void main(String[] args)
   {
      int num[][][]={{{33,45,67},
                     {23,71,56},
                     {55,38,66}},
                     {{21,9,15 },
                     {38,69,18},
                     {90,101,89}}};   //声明三维数组 
      int min=num[0][0][0];  //设置min为num数组的第一个元素 
    
      for(int i=0;i<2;i++)
         for(int j=0;j<3;j++)    
            for(int k=0;k<3;k++)   
               if(min>=num[i][j][k])
                  min=num[i][j][k];  //使用三重循环找出最小值 
    
      System.out.println("最小值= "+min+'\n');   
   }                                  
}
