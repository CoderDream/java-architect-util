package designpattern.proxy;

/**
* @description 代理模式：算术运算
* @author xindaqi
* @since 2021-02-15 12:23:01
*/
public interface IArithmeticOperation {

    /**
    * description: 加法
    * @since 2021-02-15 12:26:35
    * @param a: 参数
    * @param b: 参数
    * @return 两数之和
    */
    float addition(float a, float b);

    /**
    * description: 减法
    * @since 2021-02-15 12:27:52
    * @param a: 参数
    * @param b: 参数
    * @return 两数之差
    */
    float substraction(float a, float b);
    
}