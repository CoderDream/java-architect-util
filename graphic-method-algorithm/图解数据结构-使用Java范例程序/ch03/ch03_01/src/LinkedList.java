class Node 
{
	int data;
	int np;
	String names;
	Node next;
	public Node(int data,String names,int np)
	{
		this.np=np;
		this.names=names;
		this.data=data;
		this.next=null;
	}
}
public class LinkedList 
{
	private Node first;
	private Node last;
	public boolean isEmpty()
	{
		return first==null;
	}
	public void print()
	{
		Node current=first;
		while(current!=null)
		{
			System.out.println("["+current.data+" "+current.names+" "+current.np+"]");
			current=current.next;
		}
		System.out.println();
	}
	public void insert(int data,String names,int np)
	{
		Node newNode=new Node(data,names,np);
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
