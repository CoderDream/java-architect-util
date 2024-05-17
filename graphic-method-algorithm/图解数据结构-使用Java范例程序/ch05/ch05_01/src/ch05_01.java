// 实现队列数据的存入和取出

import java.io.*;
public class ch05_01
{
	public static int front=-1,rear=-1,max=20;
	public static int val;
	public static char ch;
	public static int queue[]=new int[max];
	public static void main(String args[]) throws IOException
   {
	String strM;
	int M=0;
	BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	while(rear<max-1&& M!=3)
	{  
		System.out.print("[1]存入一个数值[2] 取出一个数值 [3]结束: ");
		strM=keyin.readLine();
		M=Integer.parseInt(strM);
		switch(M)
		  {
			case 1:
				System.out.print("\n[请输入数值]: ");
				strM=keyin.readLine();
				val=Integer.parseInt(strM);
				rear++;
				queue[rear]=val;
				break;
			case 2:
				if(rear>front)
				{  
					front++;
					System.out.print("\n[取出数值为]: ["+queue[front]+"]"+"\n");
					queue[front]=0;
				}
				else
				{  
					System.out.print("\n[队列已经空了]\n");
					break;
				}
				break;
			default:
				System.out.print("\n");
				break;
		  }
	}
	if(rear==max-1) System.out.print("[队列已经满了]\n");
	System.out.print("\n[当前队列中的数据]:");
	if (front>=rear)
	{
		System.out.print("没有\n");
		System.out.print("[队列已经空了]\n");
	}
	else
	{
		while (rear>front)
		{  
			front++;
			System.out.print("["+queue[front]+"]");
		}
		System.out.print("\n");
		}
   }
}
