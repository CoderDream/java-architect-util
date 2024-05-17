// 一维数组的应用：求质数
class ch02_01
{
  public static void main(String args[])
  {
     final int MAX=300;
     //false为质数，true为非质数
     //声明后若没有给定初值，则其默认值为false
     boolean prime[]=new boolean[MAX];
     prime[0]=true;//0为非质数
     prime[1]=true;//1为非质数
     int num=2,i;
     //将1~MAX中不是质数者逐一过滤掉，以此方式找到所有质数
     while(num<MAX)
     {
        if(!prime[num])
        {
            for(i=num+num;i<MAX;i+=num)
            {
               if(prime[i]) continue;
               prime[i]=true;//设置为true，代表此数为非质数
            }
         }
         num++;
     }
     //打印1~MAX间的所有质数
     System.out.println("1到"+MAX+"间的所有质数：");
     for(i=2,num=0;i<MAX;i++)
     {
         if(!prime[i])
         {
           System.out.print(i+"\t"); 
           num++;
         }
     }
     System.out.println("\n质数总数= "+num+"个");
   }
}
