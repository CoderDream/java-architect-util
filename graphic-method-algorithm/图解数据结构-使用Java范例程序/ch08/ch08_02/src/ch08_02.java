// 改进的冒泡排序法

public class ch08_02 extends Object
{
	int data[]=new int[]{4,6,2,7,8,9};	//原始数据

	public static void main(String args[])
	{		
		System.out.print("改进的冒泡排序法\n原始数据为：");
		ch08_02 test=new ch08_02();
		test.showdata();
		test.bubble();
	}
	
	public void showdata ()     //使用循环打印数据
    {
	    int i;
	    for (i=0;i<6;i++)
	    {
		    System.out.print(data[i]+" ");
	    }
	    System.out.print("\n");
    }

    public void bubble ()
    {
	    int i,j,tmp,flag;    	
	    for(i=5;i>=0;i--)
	    {
		    flag=0;           //flag用来判断是否执行交换的操作
		    for (j=0;j<i;j++)
		    {
			    if (data[j+1]<data[j])
			    {
				    tmp=data[j];
				    data[j]=data[j+1];
				    data[j+1]=tmp;
				    flag++;    //如果执行过交换，则flag不为0
			    }
		    }
		    if (flag==0)
		    {
			    break;
		    }
			
			//当执行完一次扫描就判断是否执行过交换操作，如果没有交换过数据，
			//则表示此时数组已完成排序，故可直接跳出循环
			
			System.out.print("第"+(6-i)+"次排序：");
			for (j=0;j<6;j++)
			{
				System.out.print(data[j]+" ");
			}
			System.out.print("\n");
		}
		
	    System.out.print("排序后结果为：");
	    showdata ();
	}
}
