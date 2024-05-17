// 求出MxN矩阵的转置矩阵

import java.io.*;
public    class ch02_06
{
public static void main(String args[]) throws IOException

{
	int M,N,row,col;
	String strM;
	String strN;
	String tempstr;
	BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	System.out.println("[输入MxN矩阵的维度]");
	System.out.print("请输入维度M: ");
	strM=keyin.readLine();
	M=Integer.parseInt(strM);
	System.out.print("请输入维度N: ");
	strN=keyin.readLine();
	N=Integer.parseInt(strN);
	int arrA[][]=new int[M][N];
	int arrB[][]=new int[N][M];
	System.out.println("[请输入矩阵内容]");
	for(row=1;row<=M;row++)
	{
		for(col=1;col<=N;col++)
		{
			System.out.print("a"+row+col+"=");
			tempstr=keyin.readLine();
			arrA[row-1][col-1]=Integer.parseInt(tempstr);
		}
	}
	System.out.println("[输入矩阵内容为]\n");
	for(row=1;row<=M;row++)
	{
		for(col=1;col<=N;col++)
		{
			System.out.print(arrA[(row-1)][(col-1)]);
			System.out.print('\t');
		}
		System.out.println();
	}
	//进行矩阵转置的操作
	for(row=1;row<=N;row++)
		for(col=1;col<=M;col++)
			arrB[(row-1)][(col-1)]=arrA[(col-1)][(row-1)];

	System.out.println("[转置矩阵内容为]");
	for(row=1;row<=N;row++)
	{
		for(col=1;col<=M;col++)
		{
			System.out.print(arrB[(row-1)][(col-1)]);
			System.out.print('\t');
		}
		System.out.println();
	}
}
}
