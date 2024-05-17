// 顺序查找法

import java.io.*;
public    class ch09_01
{
    public static void main(String args[]) throws IOException
    {  
	    String strM;
	    BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	    int data[] =new int[100];
	    int i,j,find,val=0;
	    for (i=0;i<80;i++)
		    data[i]=(((int)(Math.random()*150))%150+1);
	    while (val!=-1)
	    {  
		    find=0;
		    System.out.print("请输入查找键值(1-150)，输入-1离开：");
		    strM=keyin.readLine();
		    val=Integer.parseInt(strM);
		    for (i=0;i<80;i++)
		    {  
			    if(data[i]==val)
			    {  
				    System.out.print("在第"+(i+1)+"个位置找到键值 ["+data[i]+"]\n");
				    find++;
			    }
		    }
		    if(find==0 && val !=-1)
			System.out.print("######没有找到 ["+val+"]######\n");
	    }
	    System.out.print("数据内容：\n");
	    for(i=0;i<10;i++)
	    { 
		    for(j=0;j<8;j++)
			    System.out.print(i*8+j+1+"["+data[i*8+j]+"]  ");
		    System.out.print("\n");
	    }
    }
}
