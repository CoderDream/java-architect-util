// 传统冒泡排序法

public class ch08_01 extends Object
{
	public static void main(String args[])
	{
		int i,j,tmp;
		int data[]={6,5,9,7,2,8};	//原始数据
		
		System.out.println("冒泡排序法：");
		System.out.print("原始数据为：");
		for(i=0;i<6;i++)
		{
			System.out.print(data[i]+" ");
		}
		System.out.print("\n");
		
		for (i=5;i>0;i--)		//扫描次数
		{
			for (j=0;j<i;j++)   	//比较、交换次数
			{
				// 比较相邻两数，如第一个数较大则交换
				if (data[j]>data[j+1])
				{
				    tmp=data[j];
				    data[j]=data[j+1];
				    data[j+1]=tmp;
			   }
		    }
		
		    //把各次扫描后的结果打印输出
		    System.out.print("第"+(6-i)+"次排序后的结果是："); 
		    for (j=0;j<6;j++)
		    {
			    System.out.print(data[j]+" ");
		    }		
		    System.out.print("\n");
		}
		
		System.out.print("排序后结果为：");
		for (i=0;i<6;i++)
		{
			System.out.print(data[i]+" ");
		}
		System.out.print("\n");
	}
}
