// 线性探测法

import java.io.*;
import java.util.*;
public    class ch09_04 extends Object
{
    final static int INDEXBOX=10;    //哈希表最大元素
    final static int MAXNUM=7;      //最大的数据个数
    public static void main(String args[]) throws IOException
    {
	    int i;
	    int index[]=new int[INDEXBOX];
	    int data[]=new int[MAXNUM];
	    Random rand=new Random();
	    System.out.print("原始数组值：\n");
	    for(i=0;i<MAXNUM;i++)       //起始数据值
		data[i]=(Math.abs(rand.nextInt(20)))+1;
	    for(i=0;i<INDEXBOX;i++)     //清除哈希表
		    index[i]=-1;
	    print_data(data,MAXNUM);    //打印输出起始数据
	    System.out.print("哈希表内容：\n");
	    for(i=0;i<MAXNUM;i++)       //建立哈希表
	    {  
		    creat_table(data[i],index);
		    System.out.print("  "+data[i]+" =>");  //打印单个元素的哈希表位置
		    print_data(index,INDEXBOX);
	    }
	    System.out.print("完成的哈希表：\n");     
	    print_data(index,INDEXBOX);  //打印输出最后完成的结果
       }
    public static void print_data(int data[],int max)  //打印输出数组子程序
    {  
	    int i;
	    System.out.print("\t");
	    for(i=0;i<max;i++)
		    System.out.print("["+data[i]+"] ");
	    System.out.print("\n");
    }
    public static void creat_table(int num,int index[])  //建立哈希表子程序
    {  
	    int tmp;
	    tmp=num%INDEXBOX;    //哈希函数=数据%INDEXBOX
	    while(true)
	    {  
	        if(index[tmp]==-1)      //如果数据对应的位置是空的
		    {  
		        index[tmp]=num;     //则直接存入数据
			    break;
		    }
		    else
		        tmp=(tmp+1)%INDEXBOX;    //否则往后找位置存放
        } 
    }
}
