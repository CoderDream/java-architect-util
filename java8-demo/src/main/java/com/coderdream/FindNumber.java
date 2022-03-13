package com.coderdream;

/**
 * @author ：CoderDream
 * @date ：Created in 2021/10/31 14:39
 * @description：
 * @modified By：CoderDream
 * @version: $
 */
public class FindNumber {

    public static void main(String[] args) {
        int input = 1000;

//        System.out.println("守形数有：");
//        // 守形数
//        int n = 0;
//        int c = 0;
//        int i = 0;
//        for (i = 2; i < 1000; i++) {
//            n = i * i;
//            // 如果循环的数小于10，则守形数小于100，那守形数取余10后等于循环数
//            if( i < 10 && n % 10 == i) {
//                System.out.println(i);
//                c++;
//            }
//            else if (i < 100 && n % 100 == i) {
//                System.out.println(i);
//                c++;
//            }
//            else if (i < 1000 && n % 1000 == i) {
//                System.out.println(i);
//                c++;
//            }
//        }
//        System.out.println("共计： " +c);


        for (int i = 10; i < 1000; i++) {
            double d = Double.valueOf(i + "");
            int c = 1;
            do {
                c += 1;
                d = d / 10;
                System.out.println(d);
            } while (d < 1);
            System.out.println("值：" + i + " ；位数：" + c);
        }
    }


//    #include<stdio.h>
//    int main() {
//        int i,n,c;
//        c=0;
//        printf("守形数有:");
//        for(i=2; i<1000; i++) {
//            n=i*i;
//
//            if(i<10&&n%10==i) {
//                printf("%d ",i);
//                c++;
//            }
//
//            else if(i<100&&n%100==i) {
//                printf("%d ",i);
//                c++;
//            } else if(i<1000&&n%1000==i) {
//                printf("%d ",i);
//                c++;
//            }
//        }
//        printf("\n");
//        printf("共计%d个\n",c);
//        return 0;
//    }
}
