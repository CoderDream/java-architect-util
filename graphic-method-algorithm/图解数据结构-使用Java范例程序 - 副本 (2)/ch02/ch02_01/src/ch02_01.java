// һά�����Ӧ�ã�������
class ch02_01
{
  public static void main(String args[])
  {
     final int MAX=300;
     //falseΪ������trueΪ������
     //��������û�и�����ֵ������Ĭ��ֵΪfalse
     boolean prime[]=new boolean[MAX];
     prime[0]=true;//0Ϊ������
     prime[1]=true;//1Ϊ������
     int num=2,i;
     //��1~MAX�в�����������һ���˵����Դ˷�ʽ�ҵ���������
     while(num<MAX)
     {
        if(!prime[num])
        {
            for(i=num+num;i<MAX;i+=num)
            {
               if(prime[i]) continue;
               prime[i]=true;//����Ϊtrue���������Ϊ������
            }
         }
         num++;
     }
     //��ӡ1~MAX�����������
     System.out.println("1��"+MAX+"�������������");
     for(i=2,num=0;i<MAX;i++)
     {
         if(!prime[i])
         {
           System.out.print(i+"\t"); 
           num++;
         }
     }
     System.out.println("\n��������= "+num+"��");
   }
}