[@ConditionalOnProperty 注解](https://www.hxstrive.com/subject/spring_boot/481.htm)

在 Spring Boot 中，@ConditionalOnProperty 注解用于判断是否存在指定的属性，以及判断属性的值是否是我们期待的值。我们可以通过查看它的源码进一步了解：

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {
    // name 的别名
    String[] value() default {};
    // 指定属性的前缀，例如：acme.system.feature
    String prefix() default "";
    // 指定要验证属性的名称，如果你指定了前缀，则为“前缀.name”
    String[] name() default {};
    // 属性期望值的字符串表示形式。如果未指定，则该属性不得等于false。
    String havingValue() default "";
    // 指定如果未设置该属性，条件是否应该匹配。默认为false。
    boolean matchIfMissing() default false;
}
```

示例
本实例将在 application.properties 属性文件中定义属性，通过属性值是否为1来控制是否实例化 UserService 和 OrderService。

（1）创建 application.properties 文件，内容如下：

```properties

# 0-禁用；1-启动
hxstrive.service.user.enable=1
hxstrive.service.order.enable=0
```

访问
```shell
http://localhost:8086
```

返回：
```shell
userService=com.example.demo.service.UserService@70d7c311<br/>orderService=null
```