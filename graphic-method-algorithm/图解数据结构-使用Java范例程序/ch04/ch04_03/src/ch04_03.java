// 用链表来实现堆栈

import java.io.*;

class Node //链表节点的声明
{
	int data;
	Node next;
	public Node(int data)
	{
		this.data=data;
		this.next=null;
	}
}

class StackByLink 
{
	public Node front;  //指向堆栈底部的指针
	public Node rear;   //指向堆栈顶端的指针
   //类方法：isEmpty()
   //判断堆栈如果为空堆栈，则front==null;
	public boolean isEmpty()
	{
		return front==null;
	}
   //类方法：output_of_Stack()
   //打印输出堆栈中的内容
	public void output_of_Stack()
	{
		Node current=front;
		while(current!=null)
		{
		System.out.print("["+current.data+"]");
		current=current.next;
		}
		System.out.println();
	}
   //类方法：insert()
   //把指定的数据压入堆栈顶端
	public void insert(int data)
	{
		Node newNode=new Node(data);
		if(this.isEmpty())
		{
			front=newNode;
			rear=newNode;
		}
		else
		{
			rear.next=newNode;
			rear=newNode;
		}
	}
   //类方法：pop()
   //从堆栈顶端弹出数据
	public void pop()
	{
		Node newNode;
		if(this.isEmpty())
		{
			System.out.print("===当前为空堆栈===\n");
			return;
		}
		newNode=front;
		if(newNode==rear) 
			{
			front=null;
			rear=null;
			System.out.print("===当前为空堆栈===\n");
			}
		else
		{
			while(newNode.next!=rear) 
				newNode=newNode.next;
			newNode.next=rear.next;
			rear=newNode;
		}

	}
}

class ch04_03
{
   public static void main(String args[]) throws IOException
   {
		BufferedReader buf;
		buf=new BufferedReader(new InputStreamReader(System.in));
		StackByLink stack_by_linkedlist =new StackByLink();
		int choice=0;
                while(true)
		{
			System.out.print("(0)结束 (1)把数据压入堆栈 (2)从堆栈弹出数据:");
			choice=Integer.parseInt(buf.readLine());		
			if(choice==2)
			{
				stack_by_linkedlist.pop();
				System.out.println("数据弹出后堆栈中的内容:");
				stack_by_linkedlist.output_of_Stack();
			}
			else if(choice==1)
			{
				System.out.print("请输入要压入堆栈的数据:");
				choice=Integer.parseInt(buf.readLine());
				stack_by_linkedlist.insert(choice);
				System.out.println("数据压入后堆栈中的内容:");
				stack_by_linkedlist.output_of_Stack();
			}
			else if(choice==0)
				break;
			else
			{
				System.out.println("输入错误！");
			}
		}
	}
}
