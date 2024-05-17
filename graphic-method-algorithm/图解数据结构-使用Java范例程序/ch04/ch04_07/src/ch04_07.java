// 将数学表达式由中序表示法转为后序表示法

import java.io.*;
import java.lang.String;
//中序转后序类的声明
class ch04_07 
{
	static int MAX=50;
	static char[] infix_q = new char[MAX];
    // 构造函数
	ch04_07 () 
	{
		int i=0;

		for (i=0; i<MAX; i++)
			infix_q[i]='\0';
	}    
// 运算符优先权的比较，若输入运算符小于堆栈中运算符，则返回值为1，否则为0

	public static int compare(char stack_o, char infix_o)
	{
		// 在中序表示法队列及暂存堆栈中，运算符的优先级表，其优先权值为INDEX/2
		char[] infix_priority = new char[9] ; 
		char[] stack_priority = new char[8] ;
		int index_s=0, index_i=0;

		infix_priority[0]='q';infix_priority[1]=')';
		infix_priority[2]='+';infix_priority[3]='-';
		infix_priority[4]='*';infix_priority[5]='/';
		infix_priority[6]='^';infix_priority[7]=' ';
		infix_priority[8]='(';      

		stack_priority[0]='q';stack_priority[1]='(';
		stack_priority[2]='+';stack_priority[3]='-';
		stack_priority[4]='*';stack_priority[5]='/';
		stack_priority[6]='^';stack_priority[7]=' ';

		while (stack_priority[index_s] != stack_o)
			index_s++;
		while (infix_priority[index_i] != infix_o)
			index_i++;
		return ((int)(index_s/2) >= (int)(index_i/2) ? 1 : 0);
	}
        //中序转前序的方法
	public static void infix_to_postfix()
	{
		new DataInputStream(System.in);
		int rear=0, top=0, flag=0,i=0;
		char[] stack_t = new char[MAX];  

		for (i=0; i<MAX; i++)
			stack_t[i]='\0';

		while (infix_q[rear] !='\n')  {		
			System.out.flush();
			try {
				infix_q[++rear] = (char)System.in.read();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		infix_q[rear-1] = 'q';  // 在队列加入q为结束符号
		System.out.print("\t后序表示法 : ");
		stack_t[top]  = 'q';  // 在堆栈加入#为结束符号
		for (flag = 0; flag <= rear; flag++) {		
			switch (infix_q[flag]) {
				// 输入为)，则输出堆栈内的运算符，直到堆栈内为(
				case ')':
					while(stack_t[top]!='(') 			
						System.out.print(stack_t[top--]);				
					top--;
					break;
				// 输入为q，则将堆栈内还未输出的运算符输出
				case 'q':
					while(stack_t[top]!='q')
						System.out.print(stack_t[top--]);
					break;
				// 输入为运算符，若小于TOP在堆栈中所指向的运算符，则将堆栈所指向的运算符输出
				// 若大于等于TOP在堆栈中所指向的运算符，则将输入的运算符放入堆栈
				case '(':
				case '^':
				case '*':
				case '/':
				case '+':
				case '-': 
					while (compare(stack_t[top], infix_q[flag])==1)                       
						System.out.print(stack_t[top--]);			
					stack_t[++top] = infix_q[flag];
					break;
				// 输入为操作数，则直接输出
				default :
					System.out.print(infix_q[flag]);
					break;
			}
		}
	}

	
        //主函数声明
	public static void main (String args[]) 		
	{ 
		new ch04_07();
		System.out.print("\t==========================================\n");
		System.out.print("\t本程序会将其转成后序表达式\n");
        System.out.print("\t请输入中序表达式\n");
        System.out.print("\t例如:(9+3)*8+7*6-12/4 \n");
        System.out.print("\t可以使用的运算符包括:^,*,+,-,/,(,)等 \n");
        System.out.print("\t==========================================\n");
        System.out.print("\t请开始输入中序表达式：");
		ch04_07.infix_to_postfix();
        System.out.print("\t==========================================\n");
	}
}
