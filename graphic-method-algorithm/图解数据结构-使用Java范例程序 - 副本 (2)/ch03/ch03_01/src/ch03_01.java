// ������λѧ���ɼ��ĵ�������
// �ٱ���������������ÿһ���ڵ�����ӡ���ѧ���ĳɼ�

import java.io.*;

public class ch03_01 
{
   public static void main(String args[]) throws IOException
   {
	BufferedReader buf;
	buf=new BufferedReader(new InputStreamReader(System.in));
	int num;
	String name;
	int score;
	
	System.out.println("������5λѧ�������ݣ� ");
	LinkedList list=new LinkedList();
	for (int i=1;i<6;i++)
	{
		System.out.print("������ѧ�ţ� ");
		num=Integer.parseInt(buf.readLine());
		System.out.print("������������ ");
		name=buf.readLine();
		System.out.print("������ɼ��� ");
		score=Integer.parseInt(buf.readLine());
		list.insert(num,name,score);
		System.out.println("-------------");
	}
	System.out.println(" ѧ �� �� �� ");
	System.out.println(" ѧ��  ���� �ɼ� ===========");
	list.print();
    }
}