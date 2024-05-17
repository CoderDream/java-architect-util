// 使用邻接矩阵来表示有向图

import java.io.*;
public    class ch07_02
{
    public static void main(String args[]) throws IOException
    {  
	    int arr[][]=new int[5][5];  //声明矩阵arr
	    int i,j,tmpi,tmpj;     
	    int [][] data={{1,2},{2,1},{2,3},{2,4},{4,3}};  //图各边的起点值和终点值
	    for (i=0;i<5;i++)           //把矩阵清为0
		    for (j=0;j<5;j++)
			    arr[i][j]=0;
	    for (i=0;i<5;i++)       //读取图的数据
		    for (j=0;j<5;j++)   //填入arr矩阵
		    {  
			    tmpi=data[i][0];     //tmpi为起始顶点
			    tmpj=data[i][1];     //tmpj为终止顶点
			    arr[tmpi][tmpj]=1;   //有边的点填入1
		    }
	    System.out.print("有向图矩阵：\n");
	    for (i=1;i<5;i++)
	    {  
		    for (j=1;j<5;j++)
	            System.out.print("["+arr[i][j]+"] ");   //打印矩阵内容
		    System.out.print("\n");
	    }
    }
}
