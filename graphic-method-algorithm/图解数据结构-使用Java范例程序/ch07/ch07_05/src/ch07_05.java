// 广度优先搜索法(BFS) 

class Node {
   int x;
   Node next;
   public Node(int x) {
      this.x=x;
      this.next=null;
   }
}
class GraphLink {
   public Node first;
   public Node last;
   public boolean isEmpty() {
      return first==null;
   }
   public void print() {
      Node current=first;
      while(current!=null) {
         System.out.print("["+current.x+"]");
	 current=current.next;
      }
      System.out.println();
   }
   public void insert(int x) {
      Node newNode=new Node(x);
      if(this.isEmpty()) {
         first=newNode;
	     last=newNode;
      }
      else {
	     last.next=newNode;
	     last=newNode;
      }
   }
}

public class ch07_05 {	
   public static int run[]=new int[9];  //用来记录各顶点是否遍历过
   public static GraphLink Head[]=new GraphLink[9];	
   public final static int MAXSIZE=10;   //定义队列的最大容量	
   static int[] queue= new int[MAXSIZE]; //队列数组的声明
   static int front=-1;  //指向队列的前端
   static int rear=-1;   //指向队列的后端
   //队列数据的存入
   public static void enqueue(int value) {
      if(rear>=MAXSIZE) return;
      rear++;
      queue[rear]=value;
   }
   //队列数据的取出
   public static int dequeue() {
      if(front==rear) return -1;
      front++;
      return queue[front];
   }
   //广度优先搜索法
   public static void bfs(int current) {
      Node tempnode;     //临时的节点指针
      enqueue(current);  //将第一个顶点存入队列
      run[current]=1;    //将遍历过的顶点设置为1
      System.out.print("["+current+"]"); //打印输出当前遍历的顶点
      while(front!=rear) { //判断当前是否为空队列
         current=dequeue(); //将顶点从队列中取出
         tempnode=Head[current].first; //先记录当前顶点的位置
         while(tempnode!=null) {
            if(run[tempnode.x]==0) {
               enqueue(tempnode.x);
               run[tempnode.x]=1; //记录已走访过
               System.out.print("["+tempnode.x+"]");
            }
	        tempnode=tempnode.next;
         }
      }
   }

   public static void main (String args[]) {
      int Data[][] =  //图边线数组的声明
                  { {1,2},{2,1},{1,3},{3,1},{2,4},{4,2},{2,5},{5,2},{3,6},{6,3},
                    {3,7},{7,3},{4,5},{5,4},{6,7},{7,6},{5,8},{8,5},{6,8},{8,6} };
      int DataNum;			
      int i,j;				
      
      System.out.println("图的邻接链表内容："); //打印输出图的邻接链表内容
      for( i=1 ; i<9 ; i++ ) { //共有8个顶点
	      run[i]=0; //设置所有顶点为尚未遍历过
	      Head[i]=new GraphLink();	
	      System.out.print("顶点"+i+"=>");
	      for( j=0 ; j<20 ;j++) {
	          if(Data[j][0]==i) { //如果起点和链表头相等，则把顶点加入链表
	              DataNum = Data[j][1];
	              Head[i].insert(DataNum);
	          }
	      }
	      Head[i].print();  //打印输出图的邻接链表内容
      }		
      System.out.println("广度优先遍历顶点："); //打印输出广度优先遍历的顶点
      bfs(1);
      System.out.println("");
   }
}
