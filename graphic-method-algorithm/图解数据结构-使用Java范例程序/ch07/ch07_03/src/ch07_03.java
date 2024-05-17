// 使用邻接链表来表示图(a) 

import java.io.*;

class Node 
{
	int x;
	Node next;
	public Node(int x)
	{
		this.x=x;
		this.next=null;
	}
}
class GraphLink
{
	public Node first;
	public Node last;
	public boolean isEmpty()
	{
		return first==null;
	}
	public void print()
	{
		Node current=first;
		while(current!=null)
		{
			System.out.print("["+current.x+"]");
			current=current.next;
			
		}
		System.out.println();
	}
	public void insert(int x)
	{
		Node newNode=new Node(x);
		if(this.isEmpty())
		{
			first=newNode;
			last=newNode;
		}
		else
		{
			last.next=newNode;
			last=newNode;
		}
	}
}
public class ch07_03
{	
	public static void main (String args[])throws IOException
	{
		int Data[][] =		//图的数组声明
   			          { {1,2},{2,1},{1,5},{5,1},{2,3},{3,2},{2,4},
		                {4,2},{3,4},{4,3},{3,5},{5,3},{4,5},{5,4} };
		int DataNum;			
		int i,j;				
		
		System.out.println("图(a)的邻接链表内容：");
		GraphLink Head[] = new GraphLink[6];		
		for ( i=1 ; i<6 ; i++ )
		{
			Head[i]=new GraphLink();
			System.out.print("顶点"+i+"=>");
			for( j=0 ; j<14 ;j++)
			{
				if(Data[j][0]==i)
				{
					DataNum = Data[j][1];
					Head[i].insert(DataNum);
				}
			}
			Head[i].print();
		}		
	}	
}
