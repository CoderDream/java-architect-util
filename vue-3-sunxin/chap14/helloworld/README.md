# helloworld

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







## 源代码

### Hello.vue

```vue
<template>
  <p>{{message}}</p>
</template>
<script>
  export default {
    data() {
      return {
        message: 'Hello, Vue.js'
      }
    }
  }
</script>
```



### App.vue

```vue
<template>
  <Hello />
</template>

<script>
import Hello from "@/components/Hello"

export default {
  name: 'App',
  components: {
    Hello
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
```







### 运行结果

 ![image-20230115131016048](assets\image-20230115131016048.png)



```
vue-router@4.0.13 requires a peer of vue@^3.2.0 but none is installed. 
```



### 安装vue-router@4



```vue
npm install vue-router@4 -s
```



![image-20230115174836524](assets\image-20230115174836524.png)

## 参考文档：

1. [Vue——组件命名报错 “Component name “XXX“ should always be multi-word”的解决方法](https://blog.csdn.net/helloyangkl/article/details/126885133)
1. [Vue Router入门（官网）](https://router.vuejs.org/zh/guide/#html)



# END
