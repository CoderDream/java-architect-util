// 输入限制性双向队列的实现

import java.io.*;
class QueueNode               // 队列节点类
{
   int data;                    // 节点数据
   QueueNode next;            // 指向下一个节点
   //构造函数
   public QueueNode(int data) {
       this.data=data;
       next=null;
   }
};

class Linked_List_Queue {     //队列类
   public QueueNode front;  //队列的前端指针
   public QueueNode rear;  //队列的尾端指针

//构造函数
   public Linked_List_Queue() { front=null; rear=null; }

//方法enqueue：队列数据的存入                          
public boolean enqueue(int value) {
   QueueNode node= new QueueNode(value); //建立节点
  //检查是否为空队列
   if (rear==null)
      front=node;    //新建立的节点成为第1个节点
   else
      rear.next=node; //将节点加入到队列的尾端
   rear=node;       //将队列的尾端指针指向新加入的节点
   return true;
}

//方法dequeue：队列数据的取出
public int dequeue(int action) {
   int value;
   QueueNode tempNode,startNode;
   //从前端取出数据
   if (!(front==null) && action==1) {
     if(front==rear) rear=null;
     value=front.data; //将队列数据从前端取出
     front=front.next; //将队列的前端指针指向下一个
     return value; }
   //从尾端取出数据   
   else if(!(rear==null) && action==2) {
     startNode=front;  //先记下前端的指针值
     value=rear.data;  //取出当前尾端的数据
     //寻找最尾端节点的前一个节点
     tempNode=front;
     while (front.next!=rear && front.next!=null) { front=front.next;tempNode=front;}
     front=startNode;    //记录从尾端取出数据后的队列前端指针
     rear=tempNode;    //记录从尾端取出数据后的队列尾端指针
  //下一行程序是指当队列中仅剩下最后一个节点时，取出数据后便将front及rear指向null
     if ((front.next==null) || (rear.next==null)) { front=null;rear=null; }
     return value; }
   else return -1;
 }
} //队列类声明结束

public class ch05_04 {
// 主程序
   public static void main(String args[]) throws IOException {
   Linked_List_Queue queue =new Linked_List_Queue(); //创建队列对象
   int temp;
   System.out.println("用链表来实现双向队列");
   System.out.println("====================================");
   System.out.println("在双向队列前端加入第1个数据，此数据值为1");
   queue.enqueue(1);
   System.out.println("在双向队列前端加入第2个数据，此数据值为3");
   queue.enqueue(3);
   System.out.println("在双向队列前端加入第3个数据，此数据值为5");
   queue.enqueue(5);
   System.out.println("在双向队列前端加入第4个数据，此数据值为7");
   queue.enqueue(7);
   System.out.println("在双向队列前端加入第5个数据，此数据值为9");
   queue.enqueue(9);
   System.out.println("====================================");
   temp=queue.dequeue(1);
   System.out.println("从双向队列前端按序取出的数据值为："+temp);
   temp=queue.dequeue(2);
   System.out.println("从双向队列尾端按序取出的数据值为："+temp);
   temp=queue.dequeue(1);
   System.out.println("从双向队列前端按序取出的数据值为："+temp);
   temp=queue.dequeue(2);
   System.out.println("从双向队列尾端按序取出的数据值为："+temp);
   temp=queue.dequeue(1);
   System.out.println("从双向队列前端按序取出的数据值为："+temp);
   System.out.println();
  }
}
