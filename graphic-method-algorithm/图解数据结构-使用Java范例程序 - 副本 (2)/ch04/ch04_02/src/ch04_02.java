//  ��ջӦ�á���ϴ���뷢�ƵĹ��� 
//		0~12  ÷��
//		13~25 ����
//		26~38 ����
//		39~51 ����

import java.io.*;
public    class ch04_02
{
static int top=-1;
public static void main(String args[]) throws IOException

{  
	int card[]=new int[52];
	int stack[]=new int[52];
	int i,j,k=0,test;
	char ascVal=5;
	int style;
	for (i=0;i<52;i++)
		card[i]=i;
	System.out.println("[ϴ����...���Ժ�!]");
	while(k<30)
	{
		for(i=0;i<51;i++)
		{
			for(j=i+1;j<52;j++)
			{
				if(((int)(Math.random()*5))==2)
				{
					test=card[i];//ϴ��
					card[i]=card[j];
					card[j]=test;  
				}
			}
				
		}
		k++;
	}
	i=0;
	while(i!=52)
	{
		push(stack,52,card[i]);		//��52����ѹ���ջ
		i++;
	}
	System.out.println("[��ʱ�뷢��]");
	System.out.println("[��ʾ���ҵ���]\n ����\t  ����\t   ����\t    �ϼ�");
	System.out.println("=================================");
	while (top >=0)
	{  
		style = stack[top]/13;	//�����ƵĻ�ɫ
		switch(style)			//�ƵĻ�ɫ��Ӧ����ĸ
		{
			case 0:				//÷��
				ascVal='C';
				break;
			case 1:				//����
				ascVal='D';
				break;
			case 2:				//����
				ascVal='H';
				break;
			case 3:				//����
				ascVal='S';
				break;
		}
		System.out.print("["+ascVal+(stack[top]%13+1)+"]");
				System.out.print('\t');
		if(top%4==0)
				System.out.println();
		top--;
	}
}
public static void push(int stack[],int MAX,int val)
  {
	if(top>=MAX-1)
		System.out.println("[��ջ�Ѿ�����]");
	else
	{
		top++;
		stack[top]=val;
	}
  }
public static int pop(int stack[])
  {
	if(top<0)
		System.out.println("[��ջ�Ѿ�����]");
	else
		top--;
	return stack[top];
  }
}