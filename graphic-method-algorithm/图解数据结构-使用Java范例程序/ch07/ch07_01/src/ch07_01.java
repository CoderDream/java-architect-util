// 无向图矩阵

import java.io.*;
public    class ch07_01
{
    public static void main(String args[]) throws IOException
    {
	    int [][] data={{1,2},{2,1},{1,5},{5,1},  //图各边的起点值和终点值
				       {2,3},{3,2},{2,4},{4,2},
					   {3,4},{4,3},{3,5},{5,3},
					   {4,5},{5,4}};
	    //声明矩阵arr
	    int arr[][] =new int[6][6];
	    int i,j,k,tmpi,tmpj;            
	
	    for (i=0;i<6;i++)          //把矩阵清为0
		    for (j=0;j<6;j++)
			    arr[i][j]=0;
	    for (i=0;i<14;i++)         //读取图的数据
		    for (j=0;j<6;j++)      //填入arr矩阵
			    for (k=0;k<6;k++)
			    {
				    tmpi=data[i][0];    //tmpi为起始顶点
				    tmpj=data[i][1];    //tmpj为终止顶点
				    arr[tmpi][tmpj]=1;  //有边的点填入1
			    }
	    System.out.print("无向图矩阵：\n");
	    for (i=1;i<6;i++)
	    {
		    for (j=1;j<6;j++)
	            System.out.print("["+arr[i][j]+"] ");   //打印矩阵内容
		    System.out.print("\n");
	    }
    }
}
