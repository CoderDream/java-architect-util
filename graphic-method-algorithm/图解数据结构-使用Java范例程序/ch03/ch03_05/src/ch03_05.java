// 多项式相加

import java.io.*;

class Node 
{
	int coef;
	int exp;
	Node next;
	public Node(int coef,int exp)
	{
		this.coef=coef;
		this.exp=exp;
		this.next=null;
	}
}

class PolyLinkedList 
{
	public Node first;
	public Node last;
		
	public boolean isEmpty()
	{
		return first==null;
	}	

	public void create_link(int coef,int exp)
	{
		Node newNode=new Node(coef,exp);
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

	public void print_link()
	{
		Node current=first;
		while(current!=null)
		{
			if(current.exp==1 && current.coef!=0) // X^1时不显示指数
				System.out.print(current.coef+"X + ");
			else if(current.exp!=0 && current.coef!=0)
				System.out.print(current.coef+"X^"+current.exp+" + ");
			else if(current.coef!=0)             // X^0时不显示变量
				System.out.print(current.coef);
		current=current.next;
		}
		System.out.println();
	}
	
	public PolyLinkedList sum_link(PolyLinkedList b)
	{
	int sum[]=new int[10];
	int i=0,maxnumber;
	PolyLinkedList tempLinkedList=new PolyLinkedList();
	PolyLinkedList a=new PolyLinkedList();
	int tempexp[]=new int[10];
	Node ptr;
	a=this;
	ptr=b.first;
	while(a.first!=null)                      //判断多项式1
	   {  
		b.first=ptr;                        // 重复比较A和B的指数
		while(b.first!=null)
		{  
			if(a.first.exp==b.first.exp)        //指数相等，系数相加
			{  
				sum[i]=a.first.coef+b.first.coef;
				tempexp[i]=a.first.exp;
				a.first=a.first.next;
				b.first=b.first.next;
				i++;
			}
			else if(b.first.exp > a.first.exp)     //B指数较大，系数给C
			{  
				sum[i]=b.first.coef;
				tempexp[i]=b.first.exp;
				b.first=b.first.next;
				i++;
				
			}
			else if(a.first.exp > b.first.exp)      //A指数较大，系数给C
			{  
			        sum[i]=a.first.coef;
				tempexp[i]=a.first.exp; 
				a.first=a.first.next;
				i++;
			}
			} // end of inner while loop
		}	// end of outer while loop
		maxnumber=i-1;
		for (int j=0;j<maxnumber+1;j++) tempLinkedList.create_link(sum[j],maxnumber-j);
		return tempLinkedList;
	} // end of sum_link
} // end of class PolyLinkedList 


public class ch03_05 
{
   public static void main(String args[]) throws IOException
   {
	PolyLinkedList a=new PolyLinkedList();
	PolyLinkedList b=new PolyLinkedList();
	PolyLinkedList c=new PolyLinkedList();
	
	int data1[]={8,54,7,0,1,3,0,4,2};         //多项式A的系数
	int data2[]={-2,6,0,0,0,5,6,8,6,9};        //多项式B的系数
	System.out.print("原始多项式为：\nA=");

	for(int i=0;i<data1.length;i++)
		a.create_link(data1[i],data1.length-i-1); //建立多项式A，系数从3递减

	for(int i=0;i<data2.length;i++)
		b.create_link(data2[i],data2.length-i-1); //建立多项式B，系数从3递减

	a.print_link();                  //打印多项式A
	System.out.print("B=");
	b.print_link();                  //打印多项式B
	System.out.print("多项式相加的结果为：\nC=");
	c=a.sum_link(b);               //C为A、B多项式相加的结果
	c.print_link();                  //打印多项式C
	
   }
}
