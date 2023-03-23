# 第4章　熟悉Vue.js的语法

Vue.js使用了基于HTML的模板语法，允许开发者声明式地将DOM绑定至底层Vue实例的数据。所有Vue.js的模板都是合法的HTML，所以能被遵循规范的浏览器和HTML解析器解析。在底层的实现上，Vue将模板编译成虚拟DOM渲染函数。结合响应系统，Vue能够智能地计算出最少需要重新渲染的组件数量，并把DOM操作次数减到最少。本章将讲解Vue.js语法中数据绑定的语法和指令的使用。

## 4.1　创建应用程序实例

在一个使用Vue.js框架的页面应用程序中，最终都会创建一个应用程序的实例对象并挂载到指定DOM上。这个实例将提供应用程序上下文，应用程序实例装载的整个组件树将共享相同上下文。在Vue 3.0中，应用程序的实例创建语法规则如下：

```vue
Vue.createApp()
```

应用程序的实例充当了MVVM模式中的ViewModel。createAPP()是一个全局API，它接受一个根组件选项对象作为参数，该对象可以包含数据、方法、组件生命周期钩子等，然后返回应用程序实例本身。Vue 3.0引入createAPP()是为了解决Vue 2.x全局配置代理的一些问题。

创建了应用程序的实例后，可以调用实例的mount()方法，制定一个DOM元素，在该DOM元素上装载应用程序的根组件，这样这个DOM元素中的所有数据变化都会被Vue框架所监控，从而实现数据的双向绑定。

```vue
Vue.createApp().mount('#app');
```

【例4.1】创建应用程序实例（源代码\ch04\4.1.html）。

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>创建应用程序实例</title>
</head>
<body>
<div id="app">
    <!—简单的文本插值-->
    <h2>{{ message }}</h2>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                message: '萧萧梧叶送寒声，江上秋风动客情。',
            }
        }
        //在指定的DOM元素上装载应用程序实例的根组件
    }).mount('#app');
</script>
</body>
</html>
```

在组件选项对象中会有一个data()函数，Vue在创建组件实例时会调用该函数。data()函数返回一个数据对象，Vue会将这个对象包装到它的响应式系统中，即转化为一个代理对象，此代理使Vue能够在访问或修改属性时执行依赖项跟踪和改进通知，从而自动渲染DOM。数据对象的每一个属性都会被视为一个依赖项。

> 注意这里创建Vue实例后赋值给了变量vm，在实际开发中并不要求一定要将Vue实例赋值给某个变量。

在浏览器中运行程序4.1.html，结果如图4-1所示。

![](assets\CB_3300020858_Figure-P53_13788.jpg)

图4-1　创建应用程序实例

## 4.2　插值

应用程序实例创建完成后，就需要通过插值进行数据绑定。插值的方式有以下3种。

1. 文本插值

数据绑定最常见的形式就是使用Mustache语法（双大括号）的文本插值：

```html
<h2>{{ website}}</h2>
```

Mustache标签将会被替代为对应数据对象上message属性的值。无论何时，绑定的数据对象上message属性发生了改变，插值处的内容都会更新。

通过使用v-once指令，也能执行一次性地插值，当数据改变时，插值处的内容不会更新。但这会影响到该节点上的其他数据绑定：

```html
<span v-once>这个将不会改变：{{ message }}</span>
```

在浏览器中运行程序4.1.html，按F12键打开控制台并切换到“Elements”选项，可以查看渲染的结果，如图4-2所示。

![](assets\Figure-P54_13883.jpg)

图4-2　渲染文本

2. 原始HTML

Mustache语法会将数据解释为普通文本，而非HTML代码。为了输出真正的HTML，需要使用v-html指令。

> 注意：不能使用v-html来复合局部模板，因为Vue不是基于字符串的模板引擎。反之，对于用户界面（UI），组件更适合作为可重用和可组合的基本单位。

例如，想要输出一个a标签，首先需要在data属性中定义该标签，然后根据需要定义href属性值和标签内容，最后使用v-html绑定到对应的元素上。

【例4.2】输出真正的HTML（源代码\ch04\4.2.html）。

```html
<div id="app">
    <!—简单的文本插值-->
    <h2>{{ website}}</h2>
    <!—输出HTML代码-->
    <h2 v-html="website"></h2>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                website: '<a href="https://www.baidu.com">百度</a>'
            }
        }
        //在指定的DOM元素上装载应用程序实例的根组件
    }).mount('#app');
</script>
```

在浏览器中运行程序，按F12键打开控制台并切换到“Elements”选项，可以发现使用v-html指令的p标签输出了真正的a标签，当单击“百度”后，页面将跳转到对应的页面，效果如图4-3所示。

![](assets\Figure-P55_14352.jpg)

图4-3　输出真正的HTML

从结果可知，Mustache语法不能作用在HTML特性上，如果需要控制某个元素的属性，可以使用v-bind指令。注意站点上动态渲染的任意HTML可能会非常危险，因为它很容易导致XSS攻击。请只对可信内容使用HTML插值，绝不要对用户提供的内容使用插值。

3. 使用JavaScript表达式

在模板中，一直都只绑定简单的属性键值。但实际上，对于所有的数据绑定，Vue.js都提供了完全的JavaScript表达式支持。

```html
{{ number + 1 }}
{{ ok ? 'YES' : 'NO' }}
{{ message.split('').reverse().join('') }}
<div v-bind:id="'list-' + id"></div>
```

上面这些表达式会在所属Vue实例的数据作用域下作为JavaScript被解析。限制就是，每个绑定都只能包含单个表达式，所以下面的例子都不会生效。

```html
<!-- 这是语句，不是表达式 -->
{{ var a = 1 }}
<!-- 流控制器也不会生效，请使用三元表达式 -->
{{ if(ok) { return message } }}
```

【例4.3】使用JavaScript表达式（源代码\ch04\4.3.html）。

```html
<div id="app">
    <!--使用JavaScript表达式-->
    <h2>{{ message.toUpperCase()}}</h2>
    <p>苹果总共{{price * total}}元</p>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                message: 'apple',
                price: 5,
                total: 260
            }
        }
        //在指定的DOM元素上装载应用程序实例的根组件
    }).mount('#app');
</script>
```

在浏览器中运行程序，结果如图4-4所示。

![](assets\Figure-P56_15120.jpg)

图4-4　使用JavaScript表达式

## 4.3　方法选项

在Vue中，方法可以在实例的methods选项中定义。

### 4.3.1　使用方法

使用方法有两种方式，一种是使用插值{{}}方式，另一种是使用事件调用。

1. 使用插值方式下面通过一个字符串翻转的示例来看一下使用插值方式的方法。

   【例4.4】使用插值方式的方法（源代码\ch04\4.4.html）。

   在input中通过v-model指令双向绑定message，然后在methods选项中定义reversedMessage方法，让message的内容反转，然后使用插值语法渲染到页面。

   ```html
   <div id="app">
       输入内容：<input type="text" v-model="message"><br/>
       反转内容：{{reversedMessage()}}
   </div>
   <!--引入vue文件-->
   <script src="https://unpkg.com/vue@next"></script>
   <script>
       //创建一个应用程序实例
       const vm = Vue.createApp({
           //该函数返回数据对象
           data() {
               return {
                   message: ''
               }
           },
           //在选项对象的methods属性中定义方法
           methods: {
               reversedMessage: function () {
                   return this.message.split('').reverse().join('')
               }
           }
           //在指定的DOM元素上装载应用程序实例的根组件
       }).mount('#app');
   </script>
   ```

   在浏览器中运行程序，然后在文本框中输入“abcdefg”，可以看到下面会显示“gfedcba”反转后的内容，如图4-5所示。

   ![](assets\CB_3300020858_Figure-P57_15611.jpg)

   图4-5　使用插值方式的方法

2. 使用事件调用下面通过一个“单击按钮自增的数值”示例来看一下事件调用。

   【例4.5】事件调用方法（源代码\ch04\4.5.html）。

   首先在data()函数中定义num属性，然后在methods中定义add()方法，该方法每次调用num自增。在页面中首先使用插值渲染num的值，使用v-on指令绑定click事件，然后在事件中调用add()方法。

   ```html
   <div id="app">
       {{num}}
       <p>
           <button v-on:click="add()">增加</button>
       </p>
   </div>
   <!--引入vue文件-->
   <script src="https://unpkg.com/vue@next"></script>
   <script>
       //创建一个应用程序实例
       const vm = Vue.createApp({
           //该函数返回数据对象
           data() {
               return {
                   num: 1
               }
           },
           //在选项对象的methods属性中定义方法
           methods: {
               add: function () {
                   this.num += 1
               }
           }
           //在指定的DOM元素上装载应用程序实例的根组件
       }).mount('#app');
   </script>
   ```

   在浏览器中运行程序，多次单击“增加”按钮，可以发现每次单击num值自增1，结果如图4-6所示。

   ![[插图]](assets\CB_3300020858_Figure-P58_16074.jpg)

   图4-6　事件调用方法

### 4.3.2　传递参数

传递参数和正常的JavaScript传递参数的方法一样，分为如下两个步骤。

（1）在methods的方法中进行声明，例如给【例4.5】中的add方法加上一个参数a，声明如下：

```js
add:function(a){ }
```

（2）调用方法时直接传递参数，例如这里传递参数为2，在button上直接写：

```html
<button v-on:click="add(2)">增加</button>
```

下面我们修改【例4.5】的代码，每次单击按钮，让它自增2。

【例4.6】传递参数（源代码\ch04\4.6.html）。

```html
<div id="app">
    {{num}}
    <p>
        <button v-on:click="add(2)">增加</button>
    </p>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                num: 1
            }
        },
        //在选项对象的methods属性中定义方法
        methods: {
            add: function (a) {
                this.num += a
            }
        }
        //在指定的DOM元素上装载应用程序实例的根组件
    }).mount('#app');
</script>
```

在浏览器中运行程序，单击1次增加按钮，可以发现num值自增2，结果如图4-7所示。

![[插图]](assets\CB_3300020858_Figure-P59_16582.jpg)

图4-7　传递参数

### 4.3.3　方法之间调用

在Vue中，methods选项中的一个方法可以调用methods中的另外一个方法，其语法格式如下：

```js
this.$option.methods.+方法名
```

【例4.7】方法之间的调用（源代码\ch04\4.7.html）。

```html
<div id="app">
    {{content}}
    {{way2()}}
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                content: "古诗"
            }
        },
        //在选项对象的methods属性中定义方法
        methods: {
            way1: function () {
                alert("东风扇淑气， 水木荣春晖。");
            },
            way2: function () {
                this.$options.methods.way1();
            }
        }
        //在指定的DOM元素上装载应用程序实例的根组件
    }).mount('#app');
</script>
```

在浏览器中运行程序，结果如图4-8所示。

![](assets\CB_3300020858_Figure-P60_17116.jpg)

图4-8　方法之间调用

## 4.4　生命周期钩子函数

每个Vue实例在被创建时，都要经过一系列的初始化过程。例如，需要设置数据监听、编译模板、将实例挂载到DOM，并在数据变化时更新DOM等。同时，在这个过程中也会运行一些生命周期钩子的函数，这给了开发者在不同阶段添加自己代码的机会。

### 4.4.1　认识生命周期钩子函数

生命周期钩子函数说明如表4-1所示。

表4-1　生命周期钩子函数及说明

![](assets\CB_3300020858_Figure-T61_164912.jpg)

这些生命周期钩子函数与el和data类似，也是作为选项写入Vue实例中，并且钩子的this指向的是调用它的Vue实例。

> 提示：不要在钩子函数选项或回调上使用箭头函数，例如created:()=>console.log(this.a)或vm.$watch('a',newValue=>this.myMethod())。因为箭头函数并没有this，this会作为变量一直向上级词法作用域查找，直至找到为止，经常导致Uncaught TypeError:Cannot read property of undefined或Uncaught TypeError:this.myMethod is not a function之类的错误。

【例4.8】生命周期钩子函数（源代码\ch04\4.8.html）。

首先在页面加载完后触发beforeCreate、created、beforeMount、mounted，4秒修改msg的内容为“孤云还空山，众鸟各已归。”，并触发beforeUpdate和updated钩子函数。

```html
<div id="app">
    <p>{{ msg }}</p>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                msg: "白日照绿草， 落花散且飞。"
            }
        },
        //在实例初始化之后，数据观测(data observer)和event/watcher 事件配置之前被调用。
        beforeCreate: function () {
            console.log('beforeCreate');
        },
        /* 在实例创建完成后被立即调用。在这一步，实例已完成数据观测 (data observer)，属性和方法的运算，watch/event 事件回调。然而，挂载阶段还没开始，$el 属性目前不可见。 */
        created: function () {
            console.log('created');
        },
        //在挂载开始之前被调用：相关的渲染函数首次被调用
        beforeMount: function () {
            console.log('beforeMount');
        },
        //el 被新创建的 vm.$el 替换, 挂载成功
        mounted: function () {
            console.log('mounted');
        },
        //数据更新时调用
        beforeUpdate: function () {
            console.log('beforeUpdate');
        },
        //组件 DOM 已经更新, 组件更新完毕
        updated: function () {
            console.log('updated');
        }
    }).mount('#app');
    setTimeout(function () {
        vm.msg = "孤云还空山， 众鸟各已归。";
    }, 4000);
</script>
```

在浏览器中运行程序，按F12键打开控制台并切换到“Console”选项，页面渲染完成后，效果如图4-9所示。

![](assets\Figure-P62_18130.jpg)

图4-9　初始化页面效果

4秒后调用setTimeout()，修改msg的内容，又触发另外的钩子函数，效果如图4-10所示。

![](assets\Figure-P63_18135.jpg)

图4-10 4秒后效果

### 4.4.2　created和mouted

在使用Vue的过程中，经常需要对一些数据做初始化处理，常用的方法是在created与mounted钩子函数中处理。

created是在实例创建完成后立即调用。在这一步，实例已完成了数据观测、属性和方法的运算，以及watch/event事件回调。然而，挂载阶段还没开始，$el属性目前不可见。所以不能操作DOM元素，多用于初始化一些数据或方法。

mounted是在模板渲染成HTML后调用，通常是初始化页面完成后，再对HTML的DOM节点进行一些需要的操作。

【例4.9】created与mounted函数的应用（源代码\ch04\4.9.html）。

```html
<div id="app">
    <ul>
        <li id="b1"></li>
        <li id="b2"></li>
        <li id="b3"></li>
    </ul>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                name: '',
                city: '',
                price: 0
            }
        },
        //在选项对象的methods属性中定义方法
        methods: {
            way: function () {
                alert("使用created初始化方法")
            }
        },
        created: function () {
            // 初始化方法
            this.way();
            //初始化数据
            this.name = "苹果";
            this.city = "烟台市";
            this.price = "8.88元/公斤";
        },
        //对DOM的一些初始化操作
        mounted: function () {
            document.getElementById("b1").innerHTML = this.name;
            document.getElementById("b2").innerHTML = this.city;
            document.getElementById("b3").innerHTML = this.price;
        }
        //在指定的DOM元素上装载应用程序实例的根组件
    }).mount('#app');
</script>
```

在浏览器中运行程序，效果如图4-11所示，单击“确定”按钮，页面加载完成效果如图4-12所示。

![](assets\Figure-P64_18959.jpg)

图4-11　页面效果

![](assets\Figure-P64_18960.jpg)

图4-12　单击“确定”按钮后效果

## 4.5　指令

指令（Directives）是带有“v-”前缀的特殊特性。指令特性的值预期是单个JavaScript表达式（v-for是例外情况）。指令的职责是，当表达式的值改变时，将其产生的连带影响，响应式地作用于DOM。例如下面代码中，v-if指令将根据表达式布尔值（boole）的真假来插入或移除<p>元素。

```html
<p v-if="bool">现在你可以看到我了</p>
```

1. 参数

一些指令能够接收一个“参数”，在指令名称之后以英文冒号表示。例如，v-bind指令可以用于响应式地更新HTML特性：

```html
<a v-bind:href="url">...</a>
```

在这里href是参数，告知v-bind指令将该元素的href特性与表达式url的值绑定。v-on指令用于监听DOM事件，例如下面代码：

```html
<a v-on:click="doSomething">...</a>
```

其中参数click是监听的事件名，在后面章节中将会详细介绍v-on指令的具体用法。

2. 动态参数

从Vue 2.6.0版本开始，可以把方括号括起来的JavaScript表达式作为一个指令的参数：

```html
<a v-bind:[attributeName]="url">...</a>
```

这里的attributeName会被作为一个JavaScript表达式进行动态求值，求得的值将会作为最终的参数来使用。例如，在Vue实例的data选项有一个attributeName属性，其值为"href"，那么这个绑定等价于v-bind:href。同样地，可以使用动态参数为一个动态的事件名绑定处理函数：

```html
<a v-on:[eventName]="doSomething">...</a>
```

在这段代码中，当eventName的值为"click"时，v-on:[eventName]将等价于v-on:click。

下面看一个示例，其中用v-bind绑定动态参数attr，v-on绑定事件的动态参数things。

【例4.10】动态参数（源代码\ch04\4.10.html）。

```html
<div id="app">
    <p><a v-bind:[attr]="url">百度链接</a></p>
    <p>
        <button v-on:[things]="doSomething">单击事件</button>
    </p>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                attr: 'href',
                things: 'click',
                url: 'baidu.com'
            }
        },
        //在选项对象的methods属性中定义方法
        methods: {
            doSomething: function () {
                alert('触发了单击事件！')
            }
        }
        //在指定的DOM元素上装载应用程序实例的根组件
    }).mount('#app');
</script>
```

在浏览器中运行程序，在页面中单击“单击事件”按钮，弹出“触发了单击事件”，结果如图4-13所示。

![](assets\Figure-P66_19658.jpg)

图4-13　动态参数

对动态参数的值的约束：动态参数预期会求出一个字符串，异常情况下值为null。这个特殊的null值可以被显性地用于移除绑定。任何其他非字符串类型的值都将会触发一个警告。

动态参数表达式有一些语法约束，因为某些字符，如空格和引号，放在HTML属性名里是无效的。例如：

```html
<!-- 这会触发一个编译警告 -->
<a v-bind:['foo' + bar]="value">...</a>
```

所以不要使用带空格或引号的表达式，或用计算属性替代这种复杂表达式。在DOM中使用模板时，还需要避免使用大写字符来命名键名，因为浏览器会把属性名全部强制转为小写：

```html
<!-- 
在DOM中使用模板时这段代码会被转换为'v-bind:[someAttr]'。
除非在实例中有一个名为“someAttr”的property，否则代码不会工作。
-->
<a v-bind:[someAttr]="value">...</a>
```

3．事件修饰符

修饰符（modifier）是以半角句号“.”指明的特殊后缀，用于指出v-on应该以特殊方式绑定。例如，.prevent修饰符告诉v-on指令对于触发的事件调用event.preventDefault()：

```html
<form v-on:submit.prevent="onSubmit">...</form>
```

## 4.6　缩写

“v-”前缀作为一种视觉提示，用来识别模板中Vue特定的特性。在使用Vue.js为现有标签添加动态行时，“v-”前缀很有帮助。然而，对于一些频繁用到的指令来说，就会感到使用烦琐。同时，在构建由Vue管理所有模板的单页面应用程序（Single Page Application，SPA）时，“v-”前缀也就变得没那么重要了。因此，Vue为v-bind、v-on和v-slot这几个最常用的指令提供了特定缩写：

1．v-bind缩写

```html
<!-- 完整语法 -->
<a v-bind:href="url">...</a>
<!-- 缩写 -->
<a :href="url">...</a>
```

2．v-on缩写

```html
<!-- 完整语法 -->
<a v-on:click="doSomething">...</a>
<!-- 缩写 -->
<a :click="doSomething">...</a>
```

3．v-slot缩写

```html
<!-- 完整语法 -->
<slotOne v-slot:default></slotOne>
<!-- 缩写 -->
<slotOne #default></slotOne>
```

它们看起来可能与普通的HTML略有不同，但“:”、“@”和“#”对于特性名来说都是合法字符，在所有支持Vue的浏览器中都能被正确地解析，而且它们不会出现在最终渲染的标记中。

## 4.7　Vue.js 3.0的新变化——取消构造函数

Vue.js 2.x相比，Vue.js 3.0的构造函数已经发生了新变化。在Vue.js 2.x中，使用构造函数代码如下：

```js
const app = new Vue(options);
app.$mount("#app");
app.use();
```

这里新建一个构造函数Vue实例，并传入参数，然后挂载到app元素上。如果使用一些插件，可以使用Vue上的use()方法。而在Vue.js 3.0中，不存在构造函数了，而是应用对象的createApp()，代码如下：

```js
const app = createApp(app);
app.$mount("#app");
app.use();
```

如果使用一些插件，则用实例app上的use()方法。

## 4.8　疑难解惑

疑问1：命名动态参数时需要注意什么？

指令的参数可以是动态参数，例如以下代码：

```html
<a v-bind:[attribute]="url">百度官网</a>
```

这里的attribute会作为表达式进行动态求值，求得的值作为最终的参数来使用。这里记得要避免使用大写字母命名动态参数，因为浏览器会把元素的属性名全部转化为小写字母，最后会因为大小写问题而找不到最终的大写动态参数名称。

疑问2：data()函数的返回到数据保存到哪里了？

Vue在创建组件实例时，会把data()函数返回的数据对象保存到组件实例的$data属性中，同时为了方便访问，数据对象的任何顶层属性也直接通过组件实例公开。也就是访问数据属性时，vm.$data.message和vm.message的效果是一样的。



# END
