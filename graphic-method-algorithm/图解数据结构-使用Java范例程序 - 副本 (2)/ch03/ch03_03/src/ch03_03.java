//ʵ�ֵ�����������½ڵ�Ĺ���
import java.io.*;

class Node 
{
	int data;
	Node next;
	public Node(int data)
	{
		this.data=data;
		this.next=null;
	}
}
class LinkedList
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
		System.out.print("["+current.data+"]");
		current=current.next;
		}
		System.out.println();
	}	
//������������
	public LinkedList Concatenate(LinkedList head1,LinkedList head2)
	{
		LinkedList ptr;
		ptr = head1;
		while(ptr.last.next != null)
			ptr.last = ptr.last.next;
		ptr.last.next = head2.first;
		return head1;
	}
//����ڵ�
	public void insert(Node ptr)
	{
		Node tmp;
		Node newNode;
		if(this.isEmpty())
		{
			first=ptr;
			last=ptr;
		}
		else
		{
			if(ptr.next==first)      //�����һ���ڵ�
			{
				ptr.next =first;
				first=ptr;
			}
			else
			{
				if(ptr.next==null)  //���뵽���һ���ڵ�
				{
					last.next=ptr;
					last=ptr;
				}
				else             //���뵽�м�ڵ�
				{
					newNode=first;
					tmp=first;
					while(ptr.next!=newNode.next) 
					{
						tmp=newNode;
						newNode=newNode.next;
					}
					tmp.next=ptr;
					ptr.next=newNode;
				}
			}
		}
	}
}

public class ch03_03
{
	public static void main(String args[]) throws IOException
	{
		LinkedList list1=new LinkedList();
		LinkedList list2=new LinkedList();
		Node node1=new Node(5);
		Node node2=new Node(6);
		list1.insert(node1);
		list1.insert(node2);
		Node node3=new Node(7);
		Node node4=new Node(8);
		list2.insert(node3);
		list2.insert(node4);
		list1.Concatenate(list1,list2);
		list1.print();
	}
}