// ���ú�ŵ�����������ͬ������ʱ�����ƶ��Ĳ���

import java.io.*;
public    class ch04_04
{
public static void main(String args[]) throws IOException  
	{  
	int j;
	String str;
	BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	System.out.print("���������ӵ������� ");
	str=keyin.readLine();
	j=Integer.parseInt(str);
	hanoi(j,1, 2, 3);
	}
public static void hanoi(int n, int p1, int p2, int p3)
	{  
	if (n==1)
		System.out.println("���Ӵ� "+p1+" �Ƶ� "+p3);
	else
		{  
		hanoi(n-1, p1, p3, p2);
		System.out.println("���Ӵ� "+p1+" �Ƶ� "+p3);
		hanoi(n-1, p2, p1, p3);
		}
	}
}