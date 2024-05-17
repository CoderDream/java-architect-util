// 将两个学生成绩链表串联起来，
// 然后打印出串联后链表的内容

import java.util.*;
import java.io.*;

class ConcatStuLinkedList extends StuLinkedList
{
	public  StuLinkedList concat(StuLinkedList stulist)
	{
	this.last.next=stulist.first;
	this.last=stulist.last;
	
	return this;
	}
}


public class ch03_06
{
   public static void main(String args[]) throws IOException
   {
	Random rand=new Random();
	ConcatStuLinkedList list1 =new ConcatStuLinkedList();
	StuLinkedList list2=new StuLinkedList();
	int i,j,data[][]=new int[12][10];
	String name1[]=new String[] {"Allen","Scott","Marry","Jon","Mark","Ricky","Michael","Tom"};
	String name2[]=new String[] {"Lisa","Jasica","Hanson","Amy","Bob","Jack","John","Andy"};
	System.out.println("学号       成绩         学号        成绩         学号         成绩        学号        成绩\n ");
	for (i=0;i<8;i++)
		{
			data[i][0]=i+1;
			data[i][1]=(Math.abs(rand.nextInt(50)))+50;
			list1.insert(data[i][0],name1[i],data[i][1]);
		}
	for (i=0;i<2;i++)
		{
			for(j=0;j<4;j++)
			System.out.print("["+data[j+i*4][0]+"]  ["+data[j+i*4][1]+"]  ");
			System.out.println();
		}

	for (i=0;i<8;i++)
		{
			data[i][0]=i+9;
			data[i][1]=(Math.abs(rand.nextInt(50)))+50;
			list2.insert(data[i][0],name2[i],data[i][1]);
		}

	for (i=0;i<2;i++)
		{
			for(j=0;j<4;j++)
			System.out.print("["+data[j+i*4][0]+"]  ["+data[j+i*4][1]+"]  ");
			System.out.println();
		}

	list1.concat(list2);
	list1.print();
    }
}
