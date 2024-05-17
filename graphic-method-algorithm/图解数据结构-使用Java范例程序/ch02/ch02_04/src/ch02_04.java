// 程序目的：两个矩阵相加的运算

import java.io.*;
public    class ch02_04
{
public static void MatrixAdd(int arrA[][],int arrB[][],int arrC[][],int dimX,int dimY)
{
	int row,col;
	if(dimX<=0||dimY<=0)
	{
		System.out.println("矩阵维数必须大于0");
		return;
	}
	for(row=1;row<=dimX;row++)
		{
		for(col=1;col<=dimY;col++)
			{
			arrC[(row-1)][(col-1)]=arrA[(row-1)][(col-1)]+arrB[(row-1)][(col-1)];
			}
		}
}
public static void main(String args[]) throws IOException

{
	int i;
	int j;
	final int ROWS = 3;
	final int COLS =3;
	int [][] A= {{1,3,5},
						{7,9,11},
						{13,15,17}};
	int [][] B= {{9,8,7},
						{6,5,4},
						{3,2,1}};
	int [][] C= new int[ROWS][COLS];
		System.out.println("[矩阵A的各个元素]");  //输出矩阵A的内容
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		System.out.print(A[i][j]+" \t");
		System.out.println();
	}
	System.out.println("[矩阵B的各个元素]");	  //输出矩阵B的内容
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		System.out.print(B[i][j]+" \t");
		System.out.println();
	}
	MatrixAdd(A,B,C,3,3);
	System.out.println("[显示矩阵A和矩阵B相加的结果]");	//输出A+B的结果
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		System.out.print(C[i][j]+" \t");
		System.out.println();
	}
}
}
