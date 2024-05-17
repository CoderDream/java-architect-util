// 建立二叉树

import java.io.*;
public    class ch06_01
{
public static void main(String args[]) throws IOException
   {
	    int i,level;
	    int data[]={6,3,5,9,7,8,4,2}; /*原始数组*/
	    int btree[]=new int[16];
	    for(i=0;i<16;i++) btree[i]=0;
	    System.out.print("原始数组的内容: \n");
	    for(i=0;i<8;i++)
	        System.out.print("["+data[i]+"] ");
	    System.out.println();
	    for(i=0;i<8;i++)             /*把原始数组中的值逐一对比*/
	    {
		    for(level=1;btree[level]!=0;)   /*比较树根及数组内的值*/
		    {
		        if(data[i]>btree[level])    /*如果数组内的值大于树根，则往右子树比较*/
				    level=level*2+1;
			    else           /*如果数组内的值小于或等于树根，则往左子树比较*/
				    level=level*2;
		     }             /*如果子树节点的值不为0，则再与数组内的值比较一次*/
		     btree[level]=data[i];   /*把数组值放入二叉树*/
        }
	     System.out.print("二叉树的内容：\n");
	     for (i=1;i<16;i++)
		    System.out.print("["+btree[i]+"] ");
	     System.out.print("\n");
    }
}
