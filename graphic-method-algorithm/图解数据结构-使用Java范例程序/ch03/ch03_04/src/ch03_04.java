// 单向链表的反转功能
import java.util.*;
import java.io.*;

class ReverseStuLinkedList extends StuLinkedList
{
	public void reverse_print()
	{
	Node current=first;
	Node before=null;
	System.out.println("反转后的链表数据:");	
	while(current!=null)
	{
		last=before;
		before=current;
		current=current.next;
		before.next=last;
	}
	current=before;
		while(current!=null)
		{
		System.out.println("["+current.data+" "+current.names+" "+current.np+"]");
		current=current.next;
		}
		System.out.println();
	}
}


public class ch03_04
{
   public static void main(String args[]) throws IOException
   {
	Random rand=new Random();
	ReverseStuLinkedList list =new ReverseStuLinkedList();
	int i,j,data[][]=new int[12][10];
	String name[]=new String[] {"Allen","Scott","Marry","Jon","Mark","Ricky","Lisa","Jasica","Hanson","Amy","Bob","Jack"};
	System.out.println("学号         成绩         学号       成绩         学号        成绩          学号         成绩\n ");
	for (i=0;i<12;i++)
		{
			data[i][0]=i+1;
			data[i][1]=(Math.abs(rand.nextInt(50)))+50;
			list.insert(data[i][0],name[i],data[i][1]);
		}
	for (i=0;i<3;i++)
		{
			for(j=0;j<4;j++)
			System.out.print("["+data[j*3+i][0]+"]  ["+data[j*3+i][1]+"]  ");
			System.out.println();
		}
	list.reverse_print();
    }
}
