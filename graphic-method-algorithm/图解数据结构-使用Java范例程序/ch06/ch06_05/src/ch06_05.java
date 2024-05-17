// 利用中序遍历进行排序

import java.io.*;
class TreeNode 
{
	int value;
	TreeNode left_Node;
	TreeNode right_Node;

	public TreeNode(int value)
	{
		this.value=value;
		this.left_Node=null;
		this.right_Node=null;
	}
}

class BinaryTree 
{
	public TreeNode rootNode;
	
	public void Add_Node_To_Tree(int value)
	{
		if (rootNode==null)
		{
			rootNode=new TreeNode(value);
			return;
		}
		TreeNode currentNode=rootNode;
		while(true)
		{
			if(value<currentNode.value)
			{
				if(currentNode.left_Node==null)
				{
					currentNode.left_Node=new TreeNode(value);
					return;
				}
				else
					currentNode=currentNode.left_Node;
			}
			else
			{
				if(currentNode.right_Node==null)
				{
					currentNode.right_Node=new TreeNode(value);
					return;
				}
				else
					currentNode=currentNode.right_Node;
			}
		}
	}
	public  void InOrder(TreeNode node)
	{
		if (node!=null)
		{
			InOrder(node.left_Node);
			System.out.print("["+node.value+"] ");
			InOrder(node.right_Node);
		}
	}

	public  void PreOrder(TreeNode node)
	{
		if (node!=null)
		{
			System.out.print("["+node.value+"] ");
			PreOrder(node.left_Node);
			PreOrder(node.right_Node);
		}
	}
	
	public  void PostOrder(TreeNode node)
	{
		if (node!=null)
		{
			PostOrder(node.left_Node);
			PostOrder(node.right_Node);
			System.out.print("["+node.value+"] ");
		}
	}
}
public    class ch06_05
{
public static void main(String args[]) throws IOException
   {
	  int value;
	  BinaryTree tree=new BinaryTree();
	  BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
	  System.out.print("请输入数据，结束请输入-1：\n");
	  while(true)
	  {
	      value=Integer.parseInt(keyin.readLine());
		  if(value==-1)
		      break;
		  tree.Add_Node_To_Tree(value);
	  }
	  System.out.print("====================: \n");
	  System.out.print("排序完成的结果：\n");
	  tree.InOrder(tree.rootNode);
	  System.out.print("\n");
   }	
}
