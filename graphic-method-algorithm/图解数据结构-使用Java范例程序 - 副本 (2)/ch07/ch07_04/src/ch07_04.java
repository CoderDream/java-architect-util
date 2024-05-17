// ������������� (DFS) 

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

public class ch07_04
{	
	public static int run[]=new int[9];
	public static GraphLink Head[]=new GraphLink[9];		
	public static void dfs(int current)             //������ȱ����ӳ���
	{
		run[current]=1;
		System.out.print("["+current+"]");
		
		while((Head[current].first)!=null)
		{
			if(run[Head[current].first.x]==0) //���������δ�������ͽ���dfs�ĵݹ����
				dfs(Head[current].first.x);
			Head[current].first=Head[current].first.next;
		}
	}
	public static void main (String args[])
	{
		int Data[][] =		//ͼ�������������

			{ {1,2},{2,1},{1,3},{3,1},{2,4},{4,2},{2,5},{5,2},{3,6},{6,3},
		      {3,7},{7,3},{4,5},{5,4},{6,7},{7,6},{5,8},{8,5},{6,8},{8,6} };
		int DataNum;			
		int i,j;				
		System.out.println("ͼ���ڽ��������ݣ�"); //��ӡͼ���ڽ���������
		for ( i=1 ; i<9 ; i++ )			    //����8������
		{
			run[i]=0;			    //�������ж���Ϊ��δ������
			Head[i]=new GraphLink();	
			System.out.print("����"+i+"=>");
			for( j=0 ; j<20 ;j++)		    //20����
			{
				if(Data[j][0]==i)  //�����������ͷ��ȣ���Ѷ����������
				{
					DataNum = Data[j][1];
					Head[i].insert(DataNum);
				}
			}
			Head[i].print();                  //��ӡͼ���ڽ���������
		}		
		System.out.println("������ȱ������㣺");   //��ӡ������ȱ����Ķ���
		dfs(1);
		System.out.println("");
	}
}