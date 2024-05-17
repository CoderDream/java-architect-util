// 线索二叉树的建立与中序遍历

import java.io.*;
//线索二叉树中的节点声明
class ThreadNode {
       int value;
       int left_Thread;
       int right_Thread;
       ThreadNode left_Node;
       ThreadNode right_Node;
       // TreeNode构造函数
       public ThreadNode(int value) {
          this.value=value;
          this.left_Thread=0;
          this.right_Thread=0;
          this.left_Node=null;
          this.right_Node=null;
       }
} 
//线索二叉树类的声明
class Threaded_Binary_Tree{
   public ThreadNode rootNode; //线索二叉树的根节点

   //无传入参数的构造函数
   public Threaded_Binary_Tree() {
      rootNode=null;
   }

   //构造函数：建立线索二叉树，传入参数为一个数组
   //数组中的第一个数据是用来建立线索二叉树的树根节点
   public Threaded_Binary_Tree(int data[]) {
      for(int i=0;i<data.length;i++)
         Add_Node_To_Tree(data[i]);
   }
   //将指定的值加入到线索二叉树
   void Add_Node_To_Tree(int value) {
      ThreadNode newnode=new ThreadNode(value);
      ThreadNode current;
      ThreadNode parent;
      ThreadNode previous=new ThreadNode(value);
      int pos;
      //设置线索二叉树的开头节点
      if(rootNode==null) {
         rootNode=newnode;
         rootNode.left_Node=rootNode;
         rootNode.right_Node=null;
         rootNode.left_Thread=0;
         rootNode.right_Thread=1;
         return;
      }
      //设置开头节点所指的节点
      current=rootNode.right_Node;
      if(current==null){
         rootNode.right_Node=newnode;
         newnode.left_Node=rootNode;
         newnode.right_Node=rootNode;
         return ;
      }
      parent=rootNode; //父节点是开头节点
      pos=0; //设置二叉树中的行进方向
      while(current!=null) {
         if(current.value>value) {
            if(pos!=-1) {
               pos=-1;
               previous=parent;
            }
            parent=current;
            if(current.left_Thread==1)
               current=current.left_Node;
            else
               current=null;
         }
         else {
            if(pos!=1) {
               pos=1;
               previous=parent;
            }
            parent=current;
            if(current.right_Thread==1)
               current=current.right_Node;
            else
               current=null;
         }
      }
      if(parent.value>value) {
         parent.left_Thread=1;
         parent.left_Node=newnode;
         newnode.left_Node=previous;
         newnode.right_Node=parent;
      }
      else {
         parent.right_Thread=1;
         parent.right_Node=newnode;
         newnode.left_Node=parent;
         newnode.right_Node=previous;
      }
      return ;
   }
   //线索二叉树中序遍历
   void print() {
      ThreadNode tempNode;
      tempNode=rootNode;
      do {
         if(tempNode.right_Thread==0)
            tempNode=tempNode.right_Node;
         else
         {
            tempNode=tempNode.right_Node;
            while(tempNode.left_Thread!=0)
               tempNode=tempNode.left_Node;
         }
         if(tempNode!=rootNode)
            System.out.println("["+tempNode.value+"]");
      } while(tempNode!=rootNode);
   } 
}

public class ch06_07 {
   public static void main(String[] args) throws IOException {
      System.out.println("线索二叉树经建立后，以中序遍历有排序的效果");
      System.out.println("除了第一个数字作为线索二叉树的开头节点外");
      int[] data1={0,10,20,30,100,399,453,43,237,373,655};
      Threaded_Binary_Tree tree1=new Threaded_Binary_Tree(data1);
      System.out.println("====================================");
      System.out.println("范例 1 ");
      System.out.println("数字从小到大的排序结果为：");
      tree1.print();
      int[] data2={0,101,118,87,12,765,65};
      Threaded_Binary_Tree tree2=new Threaded_Binary_Tree(data2);
      System.out.println("====================================");
      System.out.println("范例 2 ");
      System.out.println("数字从小到大的排序结果为：");
      tree2.print();
   }
}
