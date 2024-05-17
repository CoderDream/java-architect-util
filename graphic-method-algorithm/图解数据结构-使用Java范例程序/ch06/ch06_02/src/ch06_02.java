// 用链表来实现二叉树

import java.io.*;
//二叉树节点类的声明
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
//二叉树类的声明
class BinaryTree {
   public TreeNode rootNode; //二叉树的根节点
   //构造函数：利用传入一个数组的参数来建立二叉树
   public BinaryTree(int[] data) {
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
         if (value<currentNode.value) { //在左子树
            if(currentNode.left_Node==null) {
              currentNode.left_Node=new TreeNode(value);
              return;
            }
            else currentNode=currentNode.left_Node;
         }
         else { //在右子树
            if(currentNode.right_Node==null) {
              currentNode.right_Node=new TreeNode(value);
              return;
            }
            else currentNode=currentNode.right_Node;
         }
       }
   }
}
public class ch06_02 {   
   //主函数
   public static void main(String args[]) throws IOException {
      int ArraySize=10;
      int tempdata;
      int[] content=new int[ArraySize];
      BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
      System.out.println("请连续输入"+ArraySize+"个数据"); 
      for(int i=0;i<ArraySize;i++) {
      System.out.print("请输入第"+(i+1)+"个数据: ");       
      tempdata=Integer.parseInt(keyin.readLine());
      content[i]=tempdata;
      }
      new BinaryTree(content);
      System.out.println("===用链表方式建立二叉树，成功!!!===");
   }       
}
