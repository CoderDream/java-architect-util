// 实现环状队列数据的存入和取出

import java.io.*;
public    class ch05_03
{
    public static int front=-1,rear=-1,val;
    public static int queue[] =new int[5];
    public static void main(String args[]) throws IOException
    {  
	    String strM;
	    BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	    while(rear<5&&val!=-1)
	    {  
		    System.out.print("请输入一个值以存入队列，要取出值请输入0。(结束输入-1)：");
		    strM=keyin.readLine();
		    val=Integer.parseInt(strM);
		    if(val==0)
		    {  
			    if(front==rear)
			    {  
				    System.out.print("[队列已经空了]\n");
				    break;
			    }
			    front++;
			    if (front==5)
				    front=0;
			    System.out.print("取出队列值 ["+queue[front]+"]\n");
			    queue[front]=0;
		    }
		    else if(val!=-1&&rear<5)
		    {  
			    if(rear+1==front||rear==4&&front<=0)
			    {  
				    System.out.print("[队列已经满了]\n");
			    	break;
			    }
			    rear++;
			    if(rear==5)
				    rear=0;
			    queue[rear]=val;
		    }
	    }
	    System.out.print("\n队列剩余数据：\n");
	    if (front==rear)
		    System.out.print("队列已空!!\n");
	    else 
	    { 
		    while(front!=rear)
		    {  
			    front++;
			    if (front==5)
				    front=0;
			    System.out.print("["+queue[front]+"]");
			    queue[front]=0;
		    }
	    }
	    System.out.print("\n");
   }
}
