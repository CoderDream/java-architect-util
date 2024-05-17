//  堆栈应用――洗牌与发牌的过程 
//		0~12  梅花
//		13~25 方块
//		26~38 红桃
//		39~51 黑桃

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
	System.out.println("[洗牌中...请稍候!]");
	while(k<30)
	{
		for(i=0;i<51;i++)
		{
			for(j=i+1;j<52;j++)
			{
				if(((int)(Math.random()*5))==2)
				{
					test=card[i];//洗牌
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
		push(stack,52,card[i]);		//将52张牌压入堆栈
		i++;
	}
	System.out.println("[逆时针发牌]");
	System.out.println("[显示各家的牌]\n 东家\t  北家\t   西家\t    南家");
	System.out.println("=================================");
	while (top >=0)
	{  
		style = stack[top]/13;	//计算牌的花色
		switch(style)			//牌的花色对应的字母
		{
			case 0:				//梅花
				ascVal='C';
				break;
			case 1:				//方块
				ascVal='D';
				break;
			case 2:				//红桃
				ascVal='H';
				break;
			case 3:				//黑桃
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
		System.out.println("[堆栈已经满了]");
	else
	{
		top++;
		stack[top]=val;
	}
  }
public static int pop(int stack[])
  {
	if(top<0)
		System.out.println("[堆栈已经空了]");
	else
		top--;
	return stack[top];
  }
}
