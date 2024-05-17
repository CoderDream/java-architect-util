// 深度优先搜索法 (DFS) 

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
	public static void dfs(int current)             //深度优先遍历子程序
	{
		run[current]=1;
		System.out.print("["+current+"]");
		
		while((Head[current].first)!=null)
		{
			if(run[Head[current].first.x]==0) //如果顶点尚未遍历，就进行dfs的递归调用
				dfs(Head[current].first.x);
			Head[current].first=Head[current].first.next;
		}
	}
	public static void main (String args[])
	{
		int Data[][] =		//图边线数组的声明

			{ {1,2},{2,1},{1,3},{3,1},{2,4},{4,2},{2,5},{5,2},{3,6},{6,3},
		      {3,7},{7,3},{4,5},{5,4},{6,7},{7,6},{5,8},{8,5},{6,8},{8,6} };
		int DataNum;			
		int i,j;				
		System.out.println("图的邻接链表内容："); //打印图的邻接链表内容
		for ( i=1 ; i<9 ; i++ )			    //共有8个顶点
		{
			run[i]=0;			    //设置所有顶点为尚未遍历过
			Head[i]=new GraphLink();	
			System.out.print("顶点"+i+"=>");
			for( j=0 ; j<20 ;j++)		    //20条边
			{
				if(Data[j][0]==i)  //如果起点和链表头相等，则把顶点加入链表
				{
					DataNum = Data[j][1];
					Head[i].insert(DataNum);
				}
			}
			Head[i].print();                  //打印图的邻接链表内容
		}		
		System.out.println("深度优先遍历顶点：");   //打印深度优先遍历的顶点
		dfs(1);
		System.out.println("");
	}
}
