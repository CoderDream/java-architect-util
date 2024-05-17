// ������ģ���ջ

import java.io.*; 

class StackByArray { //������ģ���ջ��������
   private int[] stack; //��������������
   private int top;  //ָ���ջ���˵�ָ��
   //StackByArray�๹�캯��
   public StackByArray(int stack_size) {
      stack=new int[stack_size]; //��������
      top=-1;
   }
   //�෽����push
   //��ָ��������ѹ���ջ����
   public boolean push(int data) {
      if (top>=stack.length) { //�ж϶�ջ���˵�ָ���Ƿ���������С
         System.out.println("��ջ����,�޷���ѹ��");
         return false;
      }
      else {
         stack[++top]=data; //������ѹ���ջ
         return true;
      }
   }
   //�෽����empty
   //�ж϶�ջ�Ƿ�Ϊ�ն�ջ�����򷵻�true�������򷵻�false.
   public boolean empty() {
      if (top==-1) return true;
      else         return false;
   }
   //�෽����pop
   //�Ӷ�ջ��������
   public int pop() {
      if(empty()) //�ж϶�ջ�Ƿ�Ϊ�յģ�������򷵻�-1ֵ
        return -1;
      else
        return stack[top--]; //�Ƚ����ݵ������ٽ���ջָ��������
   }
}
//���������
public class ch04_01 {
   public static void main(String args[]) throws IOException {
      BufferedReader buf;
      int value;
      StackByArray stack =new StackByArray(10);
      buf=new BufferedReader(
                   new InputStreamReader(System.in));
      System.out.println("�밴������10�����ݣ�");
      for (int i=0;i<10;i++) {
         value=Integer.parseInt(buf.readLine());
         stack.push(value);
      }
      System.out.println("=============================");
      while (!stack.empty()) //����ջ����½���Ӷ��˵���
         System.out.println("��ջ������˳��Ϊ:"+stack.pop());
   }
}