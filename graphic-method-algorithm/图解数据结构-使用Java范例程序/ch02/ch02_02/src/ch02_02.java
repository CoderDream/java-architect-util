// 多维数组的应用
import java.util.*;
public class ch02_02
{
   public static void main(String[] args)
   {
      //变量声明
      int intCreate=1000000;//产生随机数次数
      int intRand;          //产生的随机数
      int[][] intArray=new int[2][42];//存放随机数的数组
      //将产生的随机数存放到数组中
      while(intCreate-->0)
      {
         intRand=(int)(Math.random()*42);
         intArray[0][intRand]++;
         intArray[1][intRand]++;
      }
      //对intArray[0]数组进行排序
      Arrays.sort(intArray[0]);
      ////找出重复次数最多的6个随机数
      for(int i=41;i>(41-6);i--)
      {
         //逐一检查次数相同者
         for(int j=41;j>=0;j--)
         {
            //当次数匹配时打印输出
            if(intArray[0][i]==intArray[1][j])
            {
               System.out.println("随机数 "+(j+1)+" 出现 "+intArray[0][i]+" 次");
               intArray[1][j]=0;    //将找到的随机数对应的重复次数归零
               break;               //中断内循环，继续外循环
            }  
         }
      }
   }
}
