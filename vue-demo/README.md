# vue-demo

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).



### 模板语法

```
https://www.yuque.com/russell-qqjgt/rfbdgd/treztt

```





# 模板语法

通过标记, 我们可以在html中插入变量.

 #### {{}} 

变量分隔符, 标记变量. 注意这种语法只能使用在html标签中间.

```vue
<div id="#app">
  <h1>{{html}}</h1>
 	<!-- 生成: <h1>小象</h1>  -->
</div>

<script>
Vue.createApp({
  data(){
    return {html: `小象`}
  }
}).mount('#app');    
</script>
```

而且还可以是js表达式

```js
<div id="#app">
  <h1>{{list[1]}}</h1>
  <!-- 生成: <h1>orange</h1> -->
  
  <h1>{{price*num}}</h1>
  <!-- 生成: <h1>90</h1> -->
</div>

<script>
Vue.createApp({
    data(){
    	return {
        list:['apple','orange'],
        price:10,
        num:9,
      }
    }
}).mount('#app');  
</script>
```

### 指令 

vue封装了常用dom操作, 通过在元素上标记"v-"开头的属性来使用, 这些属性就叫做指令.

#### v-bind 

绑定vue变量和标签属性. 这里注意下"style"和"class"的特殊用法即可.

```vue
<div id="#app">
  <h1 v-bind:align="align">hi vue</h1>
  <!-- 生成: <h1 align="center">hi vue</h1> -->
  
  <h1 v-bind:style="{color:color}">hi vue</h1>
  <!-- 生成: <h1 style="color:#f10;">hi vue</h1> -->
  
  <h1 v-bind:class="className">hi vue</h1>
  <!-- 生成: <h1 class="red big">hi vue</h1> -->
  
  <h1 v-bind:class="className2">hi vue</h1>
  <!-- 生成: <h1 class="red">hi vue</h1> -->
</div>

<script>
Vue.createApp({
    data:function{
    	return {
        align:'center',
        color:'#f10',
        className:['red','big'],
        className2:{red:true,big:false},
     	}
    }
}).mount('#app');  
</script>
```



#### 缩写 

v-bind:可以缩写成:.

```vue
<h1 v-bind:align="align">hi vue</h1>
<!-- 等于 -->
<h1 :align="align">hi vue</h1>
```



### 监听事件

#### v-on 

事件绑定. 语法为: v-on:事件名.

```vue
<div id="#app">
  <h1 v-on:click="onClick">hi vue</h1>
</div>

<script>
Vue.createApp({
    methods:{
    	onClick(){
      	alert(1);
      }
    }
}).mount('#app');  
</script>
```

#### 缩写 

v-on:可以缩写为@.

```vue
<h1 v-on:click="onClick">hi vue</h1>
<!-- 等于 -->
<h1 @click="onClick">hi vue</h1>
```

![image-20221229172129997](assets\image-20221229172129997.png)



![image-20221229172215578](assets\image-20221229172215578.png)

![image-20221229172259473](assets\image-20221229172259473.png)









#### v-if 

控制是否渲染对应元素.

```vue
<div id="#app">
  <h1 v-if="isShow">hi vue</h1>
</div>

<script>
Vue.createApp({
    data(){
    	return {
        isShow: true,
      }
    }
}).mount('#app');
</script>
```

配套的还有"v-else-if"和"v-else":

```vue
<h1 v-if="isShow">hi vue</h1>
<h1 v-else>你好</h1>
```

#### v-show 

通过控制元素的样式"display:none"来控制显示隐藏.

```vue
<div id="#app">
  <h1 v-show="isShow">hi vue</h1>
</div>

<script>
Vue.createApp({
    data(){
    	return {
        isShow: false,
      }
    }
}).mount('#app');
</script>
```

#### 运行结果

```vue
<h1 style="display:none;">hi vue</h1>
```



![image-20221229151020187](assets\image-20221229151020187.png)



#### v-for 

循环, 注意被循环的元素需要标记key属性, key的值要保证唯一, 初期学习可以让key的值等于循环索引(index).

#### 循环数组 

```vue
<div id="#app">
  <h1 v-for="(item,index) in list" :key="index">{{index}} - {{item}}</h1>
</div>

<script>
Vue.createApp({
    data(){
    	return {
        list:['苹果','柿子','香蕉']
      }
    }
}).mount('#app');
</script>
```

#### 运行结果

```
  <h1>0 - 苹果</h1>
  <h1>1 - 柿子</h1>
  <h1>2 - 香蕉</h1>
```



```
遇到Elements in iteration expect to have 'v-bind:key' directives.' 这个错误
https://www.cnblogs.com/h2zZhou/p/9639984.html

```

 ![image-20221229152552347](assets\image-20221229152552347.png)

 #### 循环对象 

```vue
<div id="#app">
  <h1 v-for="(value, key) in object" :key="key">{{key}} - {{value}}</h1>
</div>

<script>
Vue.createApp({
    data(){
    	return {
        object:{a:1,b:2,c:3}
      }
    }
}).mount('#app');
</script>
```

#### 运行结果

```
  <h1>a - 1</h1>
  <h1>b - 2</h1>
  <h1>c - 3</h1>
```

  

#### 循环数字 

做测试数据时候很有用.

```vue
 <h1 v-for="n in 5" :key="n">{{n}}</h1>
```

#### 运行结果

```
  <h1>1</h1>
  <h1>2</h1>
  <h1>3</h1>
  <h1>4</h1>
  <h1>5</h1>
```

注意: 第一位数字是1.

#### v-text 

填充元素内的文字.

```vue
<div id="#app">
  <h1 v-text="text"></h1> 
</div>

<script>
Vue.createApp({
    data(){
    	return {
        text: `大象`
      }
    }
}).mount('#app');
</script>
```

#### 运行结果

```
<h1>大象</h1>
```

#### v-html 

可以填充任意字符串, "v-text"的内容会转义, 比如"v-text"的内容不能是html标签, "v-htm"l可以

```vue
<div id="#app">
  <h1 v-html="html"></h1> 
</div>

<script>
Vue.createApp({
    data(){
    	return {
        html: '<small>小象</small>'
      }
    }
}).mount('#app');
</script>
```

#### 运行结果

```
<h1><small>小象</small></h1>
```



####  其他指令 

下面几个系统指令暂时不展开讲, 后面会随着课程的深入针对性的带出.

●[v-model](https://v3.cn.vuejs.org/api/directives.html#v-model)

●[v-slot](https://v3.cn.vuejs.org/api/directives.html#v-slot)

●[v-pre](https://v3.cn.vuejs.org/api/directives.html#v-pre)

●[v-cloak](https://v3.cn.vuejs.org/api/directives.html#v-cloak)

●[v-once](https://v3.cn.vuejs.org/api/directives.html#v-once)

●[v-is](https://v3.cn.vuejs.org/api/directives.html#v-is)

 #### 

#### <template>

空标签, 并不会渲染到DOM中, 一般用来包围元素进行批量操作.

```vue
<template v-if="isShow">
  <h1>呐喊</h1>
  <p>鲁迅<p>
</template>

<script>
Vue.createApp({
    data(){
    	return {
        isShow: true
      }
    }
}).mount('#app');
</script>
```

