// �ҳ���ά���������洢��ֵ�е���Сֵ
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
                     {90,101,89}}};   //������ά���� 
      int min=num[0][0][0];  //����minΪnum����ĵ�һ��Ԫ�� 
    
      for(int i=0;i<2;i++)
         for(int j=0;j<3;j++)    
            for(int k=0;k<3;k++)   
               if(min>=num[i][j][k])
                  min=num[i][j][k];  //ʹ������ѭ���ҳ���Сֵ 
    
      System.out.println("��Сֵ= "+min+'\n');   
   }                                  
}