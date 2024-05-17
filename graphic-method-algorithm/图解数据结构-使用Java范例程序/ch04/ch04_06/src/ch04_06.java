// 八皇后问题

import java.io.*;
class ch04_06
{
	static int TRUE=1, FALSE=0, EIGHT=8;
	static int[] queen=new int [EIGHT]; // 存放8个皇后的行位置					
	static int number=0; //// 计算共有几组解的总数
    //构造函数
	ch04_06()
	{
		number = 0 ;
	}
	//按Enter键函数
	public static void PressEnter()  
	{
		char tChar;
		System.out.print("\n\n");
		System.out.println("...按下Enter键继续...");
		try {
			tChar=(char)System.in.read();
		} catch(IOException e) {}
	}
    //确定皇后存放的位置
	public static void decide_position(int value)
	{
		int i=0;
		while ( i < EIGHT ) 
		{
		// 是否受到攻击的判断
			if ( attack(i, value) !=1) 
			{       
				queen[value] = i ; 
				if ( value == 7 )
					print_table() ; 
				else
					decide_position(value+1) ;  
			}
			i++ ;
		}
	}
	// 测试在(row,col)上的皇后是否遭受攻击
	// 若遭受攻击则返回值为1，否则返回0
	public static int attack(int row,int col)
	{
		int i=0, atk=FALSE ;
		int offset_row=0, offset_col=0 ;

		while ( (atk!=1) && i < col ) {
			offset_col = Math.abs(i - col) ;
			offset_row = Math.abs(queen[i] - row) ;
			// 判断两皇后是否在同一行或在同一对角线上
			if  ((queen[i] == row)||(offset_row == offset_col) )
				atk=TRUE ;
			i++ ;
		}
		return atk ;
	}

	// 输出所需要的结果
	public static void print_table()
	{
		int x=0, y=0;
		number+=1 ;		
		System.out.print("\n");
		System.out.print("八皇后问题的第"+number + "组解\n\t") ;
		for ( x = 0 ; x < EIGHT ; x++ ) {			
			for ( y =0 ; y< EIGHT ;y++ )
				if ( x == queen[y] )
					System.out.print("<*>") ;
				else
					System.out.print("<->") ;
			System.out.print("\n\t") ;	
		}      
		PressEnter();
	}
	public static void main (String args[])  		
	{
		ch04_06.decide_position(0) ;	
	}
}
