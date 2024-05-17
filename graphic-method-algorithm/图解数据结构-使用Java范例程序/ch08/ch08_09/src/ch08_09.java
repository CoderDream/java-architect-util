// 直接合并排序法
// 数据文件名：data1.txt,data2.txt，
// 合并后的文件：data.txt

import java.io.*;
public class ch08_09
{	
	public static void main (String args[])throws Exception 
	{
		String filep="data.txt";
		String filep1="data1.txt";
		String filep2="data2.txt";
		File fp=new File(filep);	 //声明新文件主档指标 fp
		File fp1=new File(filep1);	//声明数据文件1的指针 fp1
		File fp2=new File(filep2);	//声明数据文件2的指针 fp2
		BufferedReader pfile=new BufferedReader(new FileReader(fp)); 
		BufferedReader pfile1=new BufferedReader(new FileReader(fp1)); 		
		BufferedReader pfile2=new BufferedReader(new FileReader(fp2)); 	
		if(!fp.exists())
			System.out.print("打开主文件失败\n");
		else if(!fp1.exists())        
			System.out.print("打开数据文件 1 失败\n"); //打开文件成功时，指针会返回FILE文件
		else if(!fp2.exists())		            //指针，打开失败则返回null值
			System.out.print("打开数据文件 2 失败\n");
		else
		{  
			System.out.print("正在对数据进行排序......\n");
	        merge(fp,fp1,fp2);
			System.out.print("数据处理完成！！！\n");
		}

		System.out.print("data1.txt数据内容为：\n");
		char str;
		int str1;
		while (true)
		{
			str1=pfile1.read();
			str=(char)str1;		
			if(str1==-1)
				 break;
			System.out.print("["+str+"]");	
		}
		System.out.print("\n");
		System.out.print("data2.txt数据内容为：\n");
		while (true)
		{  
			str1=pfile2.read();
			str=(char)str1;
			if(str1==-1)
				 break;
			System.out.print("["+str+"]");
		}
		System.out.print("\n");
		System.out.print("排序后data.txt数据内容为：\n");
		while (true)
		{
			str1=pfile.read();
			str=(char)str1;
			if(str1==-1)
				 break;
			System.out.print("["+str+"]");
		}
		System.out.print("\n");
		pfile.close();		//关闭文件
		pfile1.close();
		pfile2.close();
	}
	public static void merge(File p, File p1, File p2)throws Exception 
	{  
		char str1,str2;
		int n1,n2; //声明变量n1，n2，用于暂存数据文件data1和data2内的元素值
		BufferedWriter pfile=new BufferedWriter(new FileWriter(p)); 
		BufferedReader pfile1=new BufferedReader(new FileReader(p1)); 		
		BufferedReader pfile2=new BufferedReader(new FileReader(p2)); 	
		n1=pfile1.read();	
		n2=pfile2.read();
		while(n1!=-1 && n2!=-1)		//判断是否已到文件尾
		{  
			if (n1 <= n2)
			{  
				str1=(char)n1;
				pfile.write(str1); //如果n1比较小，则把n1存到fp中
				n1=pfile1.read();  //接着读下一项 n1 的数据
			}
			else
			{  
				str2=(char)n2;
				pfile.write(str2); //如果n2比较小，则把n2存到fp中
	            n2=pfile2.read();	//接着读下一项 n2的数据
			}
		}
		if(n2!=-1)		
		{ 
			while (true)
			{  
				if(n2==-1)
					 break;
				str2=(char)n2;
				pfile.write(str2);	
				n2=pfile2.read();		
			}
		}
		else if (n1!=-1)
		{ 
			while (true)
			{  
				if(n1==-1)
					 break;
				str1=(char)n1;
				pfile.write(str1);	
				n1=pfile1.read();		
			}
		}	
		pfile.close();
		pfile1.close();
		pfile2.close();
	}
}
