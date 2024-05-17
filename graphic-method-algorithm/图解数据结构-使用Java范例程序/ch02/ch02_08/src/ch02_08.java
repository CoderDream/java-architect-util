// 数组的应用：上三角矩阵

class ch02_08
{
    private int[] arr;
    private int array_size;
    
    public ch02_08(int[][] array) 
    {
        array_size = array.length;
        arr = new int[array_size*(1+array_size)/2];
        
        int index = 0;
        for(int i = 0; i < array_size; i++) { 
            for(int j = 0; j < array_size; j++) { 
                if(array[i][j] != 0) 
                    arr[index++] = array[i][j]; 
            } 
        }
    }
    public int getValue(int i, int j) {
        int index = array_size*i - i*(i+1)/2 + j;
        return arr[index];
    }

    public static void main(String[] args) 
    {
        int array[][]= { 
                			{7, 8, 12, 21, 9}, 
               			 	{0, 5, 14,  17, 6}, 
              				{0, 0, 7, 23, 24}, 
                			{0, 0, 0,  32, 19}, 
                			{0, 0, 0,  0,  8}};        
  		ch02_08 Array_object = new ch02_08(array);
  		int i=0, j=0 ;
		System.out.println("==========================================") ;
		System.out.println("上三角形矩阵：");
		for ( i = 0 ; i < Array_object.array_size ; i++ ) 
		{
			for ( j = 0 ; j < Array_object.array_size ; j++ ) 
		        System.out.print("\t"+ array[i][j]);
		    System.out.println();    
		}
				System.out.println("==========================================") ;
		System.out.println("以一维数组的方式来表示：");
		System.out.print("\t"+"[");
		for ( i = 0 ; i < Array_object.array_size ; i++ ) 
		{
			for ( j = i ; j < Array_object.array_size ; j++ ) 
		        System.out.print(" "+Array_object.getValue(i, j));
		}
		System.out.print(" ]");
		System.out.println();    
    }
}
