// 二分查找法

import java.io.*;
public    class ch09_02
{
    public static void main(String args[]) throws IOException
    {  
	    int i,j,val=1,num;
	    int data[] =new int[50];
	    String strM;
	    BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	    for (i=0;i<50;i++)
	    {  
		    data[i]=val;
		    val+=((int)(Math.random()*100)%5+1);
	    }
	    while (true)
	    {  
		    num=0;
		    System.out.print("请输入查找键值(1-150)，输入-1结束：");
		    strM=keyin.readLine();
		    val=Integer.parseInt(strM);
		    if(val==-1)
			    break;
		    num=bin_search(data,val);
		    if(num==-1)
			    System.out.print("##### 没有找到["+val+"] #####\n");
		    else
			    System.out.print("在第 "+(num+1)+"个位置找到 ["+data[num]+"]\n");
	    }
	    System.out.print("数据内容：\n");
	    for(i=0;i<5;i++)
	    { 
		    for(j=0;j<10;j++)
			    System.out.print((i*10+j+1)+"-"+data[i*10+j]+" ");
		    System.out.print("\n");
	    }
	    System.out.print("\n");
    }

    public static int bin_search(int data[],int val)
    {  
	    int low,mid,high;
	    low=0;
	    high=49;
	    System.out.print("正在查找......\n");
	    while(low <= high && val !=-1)
	    {  
		    mid=(low+high)/2;
		    if(val<data[mid])
		    {  
			    System.out.print(val+" 介于位置 "+(low+1)+"["+data[low]+"]及中间值 "+(mid+1)+"["+data[mid]+"]，找左半边\n");
			    high=mid-1;	
		    }
		    else if(val>data[mid])
		    {  
			    System.out.print(val+" 介于中间值位置 "+(mid+1)+"["+data[mid]+"]及 "+(high+1)+"["+data[high]+"]，找右半边\n");
			    low=mid+1;   	
		    }
		    else
			    return mid;
	    }
	    return -1;
    }
}
