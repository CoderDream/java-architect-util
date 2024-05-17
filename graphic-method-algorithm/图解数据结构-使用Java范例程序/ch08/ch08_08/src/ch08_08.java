// 基数排序法从小到大排序

import java.io.*;
import java.util.*;

public class ch08_08 extends Object
{
	int size;
	int data[]=new int[100];
	
	public static void main(String args[])
	{
		ch08_08 test = new ch08_08();
		
		System.out.print("请输入数组大小(100以下)：");
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			test.size=Integer.parseInt(br.readLine());
		}catch(Exception e){}
		
		test.inputarr ();
		System.out.print("您输入的原始数据是：\n");
		test.showdata ();
		
		test.radix ();
	}
	
	void inputarr()
	{
		Random rand=new Random();
		int i;
		for (i=0;i<size;i++)
			data[i]=(Math.abs(rand.nextInt(999)))+1; //设置data值最大为3位数
	}
	
	void showdata()
	{  
		int i;
		for (i=0;i<size;i++)
			System.out.print(data[i]+" ");
		System.out.print("\n");
	}
	
	void radix()
	{  
		int i,j,k,n,m;
		for (n=1;n<=100;n=n*10)		//n为基数，从个位数开始排序
		{
			//设置暂存数组，[0~9位数][数据个数]，所有内容均为0
			int tmp[][]=new int[10][100];
			for (i=0;i<size;i++)		//对比所有数据
			{
				m=(data[i]/n)%10;  //m为n位数的值，如36取十位数(36/10)%10=3 
				tmp[m][i]=data[i];  //把data[i]的值暂存于tmp中
			}
			
			k=0;
			for (i=0;i<10;i++)
			{  
				for(j=0;j<size;j++)
				{					
					if(tmp[i][j] != 0)  //因一开始设置tmp={0}，故不为0者即为
					{
						//data暂存在tmp 中的值，把tmp中的值放回data[ ]中
						data[k]=tmp[i][j];
						k++;
					}
				}
			}
			System.out.print("经过"+n+"位数排序后：");
			showdata();
		}
	}
}
