// ʹ���ڽӾ�������ʾ����ͼ

import java.io.*;
public    class ch07_02
{
    public static void main(String args[]) throws IOException
    {  
	    int arr[][]=new int[5][5];  //��������arr
	    int i,j,tmpi,tmpj;     
	    int [][] data={{1,2},{2,1},{2,3},{2,4},{4,3}};  //ͼ���ߵ����ֵ���յ�ֵ
	    for (i=0;i<5;i++)           //�Ѿ�����Ϊ0
		    for (j=0;j<5;j++)
			    arr[i][j]=0;
	    for (i=0;i<5;i++)       //��ȡͼ������
		    for (j=0;j<5;j++)   //����arr����
		    {  
			    tmpi=data[i][0];     //tmpiΪ��ʼ����
			    tmpj=data[i][1];     //tmpjΪ��ֹ����
			    arr[tmpi][tmpj]=1;   //�бߵĵ�����1
		    }
	    System.out.print("����ͼ����\n");
	    for (i=1;i<5;i++)
	    {  
		    for (j=1;j<5;j++)
	            System.out.print("["+arr[i][j]+"] ");   //��ӡ��������
		    System.out.print("\n");
	    }
    }
}