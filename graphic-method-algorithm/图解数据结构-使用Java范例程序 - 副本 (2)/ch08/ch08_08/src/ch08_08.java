// �������򷨴�С��������

import java.io.*;
import java.util.*;

public class ch08_08 extends Object
{
	int size;
	int data[]=new int[100];
	
	public static void main(String args[])
	{
		ch08_08 test = new ch08_08();
		
		System.out.print("�����������С(100����)��");
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			test.size=Integer.parseInt(br.readLine());
		}catch(Exception e){}
		
		test.inputarr ();
		System.out.print("�������ԭʼ�����ǣ�\n");
		test.showdata ();
		
		test.radix ();
	}
	
	void inputarr()
	{
		Random rand=new Random();
		int i;
		for (i=0;i<size;i++)
			data[i]=(Math.abs(rand.nextInt(999)))+1; //����dataֵ���Ϊ3λ��
	}
	
	void showdata()
	{  
		int i;
		for (i=0;i<size;i++)
			System.out.print(data[i]+" ");
		System.out.print("\n");
	}
	
	void radix()
	{  
		int i,j,k,n,m;
		for (n=1;n<=100;n=n*10)		//nΪ�������Ӹ�λ����ʼ����
		{
			//�����ݴ����飬[0~9λ��][���ݸ���]���������ݾ�Ϊ0
			int tmp[][]=new int[10][100];
			for (i=0;i<size;i++)		//�Ա���������
			{
				m=(data[i]/n)%10;  //mΪnλ����ֵ����36ȡʮλ��(36/10)%10=3 
				tmp[m][i]=data[i];  //��data[i]��ֵ�ݴ���tmp��
			}
			
			k=0;
			for (i=0;i<10;i++)
			{  
				for(j=0;j<size;j++)
				{					
					if(tmp[i][j] != 0)  //��һ��ʼ����tmp={0}���ʲ�Ϊ0�߼�Ϊ
					{
						//data�ݴ���tmp �е�ֵ����tmp�е�ֵ�Ż�data[ ]��
						data[k]=tmp[i][j];
						k++;
					}
				}
			}
			System.out.print("����"+n+"λ�������");
			showdata();
		}
	}
}