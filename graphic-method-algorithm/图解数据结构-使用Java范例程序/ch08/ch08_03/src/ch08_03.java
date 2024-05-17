// 选择排序法

public class ch08_03 extends Object
{
	int data[]=new int[]{9,7,5,3,4,6};
	
	public static void main(String args[])
	{
		System.out.print("原始数据为：");
		ch08_03 test=new ch08_03();		
		test.showdata ();
		test.select ();
	}
	
	void showdata ()
	{
		int i;
		for (i=0;i<6;i++)
		{
			System.out.print(data[i]+" ");
		}
		System.out.print("\n");
	}
	
	void select ()
	{
		int i,j,tmp,k;
		for(i=0;i<5;i++)    	    //扫描5次
		{
			for(j=i+1;j<6;j++)  //由i+1比较起，比较5次
			{
				if(data[i]>data[j])  //比较第i和第j个元素
				{
				    tmp=data[i];
					data[i]=data[j];
					data[j]=tmp;	
				}
			}
			System.out.print("第"+(i+1)+"次排序结果：");
		    for (k=0;k<6;k++)
		    {
			    System.out.print(data[k]+" ");    //打印排序结果
		    }
		    System.out.print("\n");
	    }
	    System.out.print("\n");
	}
}
