// 用链表实现二叉运算树

//节点类的声明
class TreeNode {
       int value;
       TreeNode left_Node;
       TreeNode right_Node;
       // TreeNode构造函数
       public TreeNode(int value) {
          this.value=value;
          this.left_Node=null;
          this.right_Node=null;
       }
    } 
//二叉搜索树类的声明
class Binary_Search_Tree {
   public TreeNode rootNode; //二叉树的根节点
   //构造函数：建立空的二叉搜索树
   public Binary_Search_Tree() { rootNode=null; }
   //构造函数：利用传入一个数组的参数来建立二叉树
   public Binary_Search_Tree(int[] data) {
      for(int i=0;i<data.length;i++) 
         Add_Node_To_Tree(data[i]);
   }
   //将指定的值加入到二叉树中适当的节点
   void Add_Node_To_Tree(int value) {
      TreeNode currentNode=rootNode;
      if(rootNode==null) { //建立树根
         rootNode=new TreeNode(value);
         return;
      }
      //建立二叉树
      while(true) {
         if (value<currentNode.value) { //符合这个判断表示此节点在左子树
            if(currentNode.left_Node==null) {
              currentNode.left_Node=new TreeNode(value);
              return;
            }
            else currentNode=currentNode.left_Node;
         }
         else { //符合这个判断表示此节点在右子树
            if(currentNode.right_Node==null) {
              currentNode.right_Node=new TreeNode(value);
              return;
            }
            else currentNode=currentNode.right_Node;
         }
       }
   }
}

class Expression_Tree extends Binary_Search_Tree{
   // 构造函数
   public Expression_Tree(char[] information, int index) {
      // create方法可以将二叉树的数组表示法转换成链表表示法
      rootNode = create(information, index);
   }
   // create方法的程序内容
   public TreeNode create(char[] sequence,int index) {
      TreeNode tempNode;            
      if ( index >= sequence.length )   // 作为递归调用的出口条件
         return null;
      else  { 
         tempNode = new TreeNode((int)sequence[index]);
         // 建立左子树
         tempNode.left_Node = create(sequence, 2*index);
         // 建立右子树
         tempNode.right_Node = create(sequence, 2*index+1);
         return tempNode;
      }
   }
  // preOrder(前序遍历)方法的程序内容
   public void preOrder(TreeNode node) {
      if ( node != null ) {
         System.out.print((char)node.value);
         preOrder(node.left_Node);  
         preOrder(node.right_Node); 
      }
   }
   // inOrder(中序遍历)方法的程序内容
   public void inOrder(TreeNode node) {
      if ( node != null ) {
         inOrder(node.left_Node);  
         System.out.print((char)node.value);
         inOrder(node.right_Node); 
      }
   }
   // postOrder(后序遍历)方法的程序内容
   public void postOrder(TreeNode node) {
      if ( node != null ) {
         postOrder(node.left_Node);  
         postOrder(node.right_Node); 
         System.out.print((char)node.value);
      }
   }
   // 判断表达式如何运算的方法声明之内容
   public int condition(char oprator, int num1, int num2) {
      switch ( oprator ) {
         case '*': return ( num1 * num2 ); // 乘法请返回num1 * num2
         case '/': return ( num1 / num2 ); // 除法请返回num1 / num2
         case '+': return ( num1 + num2 ); // 加法请返回num1 + num2
         case '-': return ( num1 - num2 ); // 减法请返回num1 - num2
         case '%': return ( num1 % num2 ); // 取余数法请返回num1 % num2
      }
      return -1;
   }
   // 传入根节点，用来计算此二叉运算树的值
   public int answer(TreeNode node) {
      int firstnumber = 0;       
      int secondnumber = 0;      
      // 递归调用的出口条件
      if ( node.left_Node == null && node.right_Node == null )
        // 将节点的值转换成数值后返回
        return Character.getNumericValue((char)node.value);
      else {
        firstnumber = answer(node.left_Node);     // 计算左子树表达式的值
        secondnumber = answer(node.right_Node); // 计算右子树表达式的值
        return condition((char)node.value, firstnumber, secondnumber);
      }
   }
 }
public class ch06_04 {
   public static void main(String[] args) {
      // 将二叉运算树以数组的方式来声明
      // 第一个表达式
      char[] information1 = {' ','+','*','%','6','3','9','5' };
      // 第二个表达式
      char[] information2 = {' ','+','+','+','*','%','/','*',
                            '1','2','3','2','6','3','2','2' };
      Expression_Tree exp1 = new Expression_Tree(information1, 1);
      System.out.println("====二叉运算树数值运算范例 1: ====");
      System.out.println("=================================");
      System.out.print("===转换成中序表达式===：");
      exp1.inOrder(exp1.rootNode);     
      System.out.print("\n===转换成前序表达式===：");
      exp1.preOrder(exp1.rootNode);    
      System.out.print("\n===转换成后序表达式===：");
      exp1.postOrder(exp1.rootNode);   
      // 计算二叉树表达式的运算结果
      System.out.print("\n此二叉运算树，经过计算后所得到的结果值：");
      System.out.println(exp1.answer(exp1.rootNode));
      // 建立第二棵二叉搜索树对象
      Expression_Tree exp2 = new Expression_Tree(information2, 1);
      System.out.println();
      System.out.println("====二叉运算树数值运算范例 2: ====");
      System.out.println("=================================");
      System.out.print("===转换成中序表达式===：");
      exp2.inOrder(exp2.rootNode);     
      System.out.print("\n===转换成前序表达式===：");
      exp2.preOrder(exp2.rootNode);    
      System.out.print("\n===转换成后序表达式===：");
      exp2.postOrder(exp2.rootNode);   
      // 计算二叉树表达式的运算结果
      System.out.print("\n此二叉运算树，经过计算后所得到的结果值：");
      System.out.println(exp2.answer(exp2.rootNode));

   }
}
