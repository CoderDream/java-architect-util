//�ݹ�����ʹ��
public class ch01_01  //������
{
   public static void main(String args[])
   {
      System.out.println("5!="+fac(5));
   }
   public static int fac(int n)
   {
      if(n==0) //�ݹ���ֹ������
         return 1;
      else
         return n*fac(n-1); //�ݹ����
   }
}