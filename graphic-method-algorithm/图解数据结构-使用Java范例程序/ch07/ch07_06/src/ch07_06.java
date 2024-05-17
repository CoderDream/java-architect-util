// 最小成本生成树

public class ch07_06
{	
	public static int VERTS=6;
	public static int v[]=new int[VERTS+1];
	public static Node NewList = new Node();	
	public static int findmincost()
	{
		int minval=100;
		int retptr=0;
		int a=0;
		while(NewList.Next[a]!=-1)
		{
			if(NewList.val[a]<minval && NewList.find[a]==0)
			{
				minval=NewList.val[a];
				retptr=a;
			}
			a++;
		}
		NewList.find[retptr]=1;
		return retptr;
	}
	public static void mintree()
	{
		int i,result=0;
		int mceptr;
		int a=0;
		for(i=0;i<=VERTS;i++)
			v[i]=0;
		while(NewList.Next[a]!=-1)
		{
			mceptr=findmincost();
			v[NewList.from[mceptr]]++;
			v[NewList.to[mceptr]]++;
			if(v[NewList.from[mceptr]]>1 && v[NewList.to[mceptr]]>1)
			{
				v[NewList.from[mceptr]]--;
				v[NewList.to[mceptr]]--;
				result=1;
			}
			else
				result=0;
			if(result==0)
			{
				System.out.print("起始顶点["+NewList.from[mceptr]+"]  终止顶点[");
				System.out.print(NewList.to[mceptr]+"]  路径长度["+NewList.val[mceptr]+"]");
				System.out.println("");			
			}
			a++;
		}
	}
	public static void main (String args[])
	{
		int Data[][] =			/*图数组的声明*/
			{ {1,2,6},{1,6,12},{1,5,10},{2,3,3},{2,4,5},
		      {2,6,8},{3,4,7},{4,6,11},{4,5,9},{5,6,16} };
		int DataNum;
		int fromNum;
		int toNum;
		int findNum;
		int Header = 0;
		int FreeNode;	
		int i,j;
		System.out.println("建立图的链表：");
        /*打印图的邻接链表内容*/
		for ( i=0 ; i<10 ; i++ )
		{
			for( j=1 ; j<=VERTS ;j++)
			{
				if(Data[i][0]==j)
				{
					fromNum = Data[i][0];
					toNum = Data[i][1];
					DataNum = Data[i][2];
					findNum=0;
					FreeNode = NewList.FindFree();
					NewList.Create(Header,FreeNode,DataNum,fromNum,toNum,findNum);
				}
			}
		}				
		NewList.PrintList(Header);
		System.out.println("建立最小成本生成树");	
		mintree();
	}
}

class Node
{
	int MaxLength = 20;			// 定义链表的最大长度
	int from[] = new int[MaxLength];	
	int to[] = new int[MaxLength];	
	int find[] = new int[MaxLength];	
	int val[] = new int[MaxLength];	
	int Next[] = new int[MaxLength];	// 链表的下一个节点位置
	
	public Node ()				// Node构造函数
	{
		for ( int i = 0 ; i < MaxLength ; i++ )
			Next[i] = -2;		// -2表示未用节点
	}
	
// ---------------------------------------------------
// 搜索可用节点的位置
// ---------------------------------------------------	
	public int FindFree()
	{
		int	i;

		for ( i=0 ; i< MaxLength ; i++ )
			if ( Next[i] == -2 )
				break;
		return i;
	}

// ---------------------------------------------------
// 建立链表
// ---------------------------------------------------		
	public void Create(int Header,int FreeNode,int DataNum,int fromNum,int toNum,int findNum)
	{
		int Pointer;			// 现在的节点位置
	
		if ( Header == FreeNode )	// 新的链表
		{	
			val[Header] = DataNum;	// 设置数据编号
			from[Header]=fromNum;
			find[Header]=findNum;
			to[Header]=toNum;
			Next[Header] = -1;	// 下一个节点的位置，-1表示空节点
		}
		else
		{			
			Pointer = Header;	// 现在的节点为头节点							
			val[FreeNode] = DataNum;// 设置数据编号
			from[FreeNode]=fromNum;
			find[FreeNode]=findNum;
			to[FreeNode]=toNum;
		    // 设置数据名称
			Next[FreeNode] = -1;	// 下一个节点的位置，-1表示空节点
			// 寻找链表的尾端
			while ( Next[Pointer] != -1)
				Pointer = Next[Pointer];

			// 将新节点串连在原链表的尾端
			Next[Pointer] = FreeNode;	
		}
	}

// ---------------------------------------------------
// 打印输出链表的数据
// ---------------------------------------------------		
	public void PrintList(int Header)
	{
		int	Pointer;		
		Pointer = Header;
		while ( Pointer != -1 )
		{
			System.out.print("起始顶点["+from[Pointer]+"]  终止顶点[");
			System.out.print(to[Pointer]+"]  路径长度["+val[Pointer]+"]");
			System.out.println("");			
			Pointer = Next[Pointer];
		}
	}	
}
