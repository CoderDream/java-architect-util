// ���MxN�����ת�þ���

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
	System.out.println("[����MxN�����ά��]");
	System.out.print("������ά��M: ");
	strM=keyin.readLine();
	M=Integer.parseInt(strM);
	System.out.print("������ά��N: ");
	strN=keyin.readLine();
	N=Integer.parseInt(strN);
	int arrA[][]=new int[M][N];
	int arrB[][]=new int[N][M];
	System.out.println("[�������������]");
	for(row=1;row<=M;row++)
	{
		for(col=1;col<=N;col++)
		{
			System.out.print("a"+row+col+"=");
			tempstr=keyin.readLine();
			arrA[row-1][col-1]=Integer.parseInt(tempstr);
		}
	}
	System.out.println("[�����������Ϊ]\n");
	for(row=1;row<=M;row++)
	{
		for(col=1;col<=N;col++)
		{
			System.out.print(arrA[(row-1)][(col-1)]);
			System.out.print('\t');
		}
		System.out.println();
	}
	//���о���ת�õĲ���
	for(row=1;row<=N;row++)
		for(col=1;col<=M;col++)
			arrB[(row-1)][(col-1)]=arrA[(col-1)][(row-1)];

	System.out.println("[ת�þ�������Ϊ]");
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