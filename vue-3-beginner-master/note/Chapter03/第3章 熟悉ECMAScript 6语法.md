# 第 3 章 熟悉 ECMAScript 6 语法

ECMAScript 6（简称 ES6）目前基本成为业界标准，它的普及速度比 ES5 要快很多，主要原因是现代浏览器对 ES6 的支持相当迅速，尤其是 Chrome 和 Firefox 浏览器，已经支持 ES6 中绝大多数的特性。本章将重点学习 ES6 中常用的语法规则和精简压缩生产环境的 webpack。

## 3.1 ECMAScript 6 介绍

1995 年 12 月，Sun 公司与网景公司一起研发了 JavaScript。1996 年 3 月，网景公司发表了支持 JavaScript 的网景导航者（浏览器）2.0 说明。由于 JavaScript 作为网页的客户端脚本语言非常成功，微软于 1996 年 8 月引入了 Internet Explorer 3.0，该软件支持与 JavaScript 兼容的 Jscript。1996年 11 月，网景公司将 JavaScript 提交给欧洲计算机制造商协会（ECMA）进行标准化。ECMA-262的第一个版本于 1997 年 6 月被 ECMA 组织采纳，这也是 ECMAScript（简称 ES）的由来。

### 3.1.1 ES6 的前世今生

ECMAScript 是一种由 ECMA 国际（前身为欧洲计算机制造商协会）通过 ECMA-262 标准化的脚本程序设计语言，该语言在互联网上应用广泛，往往被称为 JavaScript 或 JScript，但实际上后两者是 ECMA-262 标准的实现和扩展。

迄今为止有 7 个 ECMA-262 版本发布，代表着一次次的 JavaScript 更新，具体的版本和详细更新内容如表 3-1 所示。

表3-1 ECMAScript版本更新

| <span style="display:inline-block;width: 40px">版本</span> | <span style="display:inline-block;width: 120px">发表日</span> | 与前版本的差异                                               |
| ---------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1                                                          | 1997 年 6 月                                                 | 首版                                                         |
| 2                                                          | 1998 年 6 月                                                 | 格式修正，以使得其形式与 ISO/IEC16262 国际标准一致           |
| 3                                                          | 1999 年 12 月                                                | 强大的正则表达式，更好的词法作用域链处理，新的控制指令，异常处理，错误定 义更加明确，数据输出的格式化及其他改变 |
| 4                                                          | 放弃                                                         | 由于语言的复杂性出现分歧，第 4 版本被放弃，其中的部分成为  第 5 版及 Harmony  的基础 |
| 5                                                          | 2009 年 12 月                                                | 新增“严格模式（strict mode）”。在该版本中提供更彻底的错误检查，以避免因语法不规范而导致的结构出错。澄清了许多第  3 版本中的模糊规范，增加了部分新  功能，比如 getters 及 setters，支持 JSON 以及在对象属性上更完整的反射 |
| 6                                                          | 2015 年 6 月                                                 | 多个新的概念和语言特性。ECMAScript Harmony 将会以 ECMAScript 6 发布 |
| 6.1                                                        | 2016 年 6 月                                                 | 多个新的概念和语言特性                                       |

ECMAScript 6 是对语言的重大更新，是自 2009 年 ES5 标准化以来语言的首次更新。有关 ES6

语言的完整规范，请参阅 ES6 标准。

### 3.1.2 为什么要使用 ES6

ES6 是一次重大的版本升级，与此同时，由于 ES6 秉承着最大化兼容已有代码的设计理念，过去编写的 JS 代码还能正常运行。事实上，许多浏览器已经支持部分 ES6 特性，并继续努力实现其余特性。这意味着，在一些已经实现部分特性的浏览器中，开发者符合标准的 JavaScript 代码已经可以正常运行，可以更加方便地实现很多复杂的操作，提高开发人员的工作效率。

以下是 ES6 排名前十位的最佳特性列表（排名不分先后）：

-   Default Parameters（默认参数）。

-   Template Literals（模板文本）。

-   Multi-line Strings（多行字符串）。

-   Destructuring Assignment（解构赋值）。

-   Enhanced Object Literals（增强的对象文本）。

-   Arrow Functions（箭头函数）。

-   Promises。

-   Block-Scoped Constructs Let and Const（块作用域构造 Let 和 Const）。

-   Classes（类）。

-   Modules（模块）。

## 3.2 块作用域构造let 和const

块级声明用于声明在指定块的作用域之外无法访问的变量。这里的块级作用域是指函数内部或者字符{}内的区域。

在 ES6 中，let 是一种新的变量声明方式。在函数作用域或全局作用域中，通过关键字 var 声明的变量，无论在哪里声明，都会被当成在当前作用域顶部声明的变量。

```js
function calculateTotalAmountES5(vip) {
    //使用 var 方式定义变量
    var amount = 0;
    if (vip) {
        //在此定义会覆盖初始化值 0
        var amount = 1;
        //在此定义会覆盖上一句的定义
        var amount = 100;
        //在此定义会覆盖上一句的定义
        var amount = 1000;
    }
    return amount;
}
console.log(calculateTotalAmountES5(true));//打印输出内容
```



以上代码结果将返回 1000，这是一个 bug。在 ES6 中，用 let 限制块级作用域，而 var 限制函数作用域。

```js
function calculateTotalAmountES6(vip) {
    //使用 var 方式定义变量
    var amount = 0;
    if (vip) {
        //使用 let 定义的局部变量
        let amount = 1;//第 1 个 let
        let amount = 100;//第 2 个 let
        let amount = 1000;//第 3 个 let
    }
    return amount;
}
console.log(calculateTotalAmountES6(true));
```



程序结果将会是 0，因为块作用域中有了 let，如果 amount=l，那么这个表达式将返回 1。本例是一个演示，这里有一堆变量，它们互不影响，因为它们属于不同的块级作用域。

JavaScript 中的 var 只能声明一个变量，这个变量可以保存任何数据类型的值。ES6 之前并没有定义声明常量的方式，ES6 标准中引入了新的关键字 const 来定义常量。

使用 const 定义常量后，常量将无法改变，const 常量的用法说明如下。

1. const 常量，只能一次赋值

   ```js
   const PI=3.14159; 
   PI=3.14; //报错 Assignment to constant variable. 
   ```

2. 对象常量

   对象的属性可以修改，对象的引用不能修改：

   ```js
   const obj={name:"kerr"}; 
   obj.name="tom"; 
   ```

3. 冻结对象

   防止修改对象的属性：

   ```js
   const obj=Object.freeze({name:"kerr"}); 
   obj.name="tomm";//报错，提示冻结对象不能再重新定义赋值 
   ```


## 3.3 模板字面量

ES6 引入了模板字面量（Template Literals），主要通过多行字符串和字符串占位符对字符串的操作进行了增强。

### 3.3.1 Multi-line Strings（多行字符串）

ES6 的多行字符串是一个非常实用的功能。在 ES5 中，我们只能使用以下方法来表示多行字符

串：

```js
// 多行字符串 
var roadPoem = "江南好，风景旧曾谙。" 
+"，日出江花红胜火，" 
+"春来江水绿如蓝。" 
+"能不忆江南？"; 
```

然而在 ES6 中，仅仅用反引号就可以解决问题了：

```js
//支持多行文本的字符串 
var roadPoem = ‘江南好，风景旧曾谙。 
日出江花红胜火， 
春来江水绿如蓝。 
能不忆江南？‘ 
```

### 3.3.2 字符串占位符

在其他语言中，使用模板和插入值是在字符串里输出变量的一种方式。在 ES5 中，开发者可以这样组合一个字符串：

```js
// ES6之前方式只能使用组合字符串方式 
var name = "Your name is " + first + " " + last + "."; 
var url = "http://localhost:3000/api/messages/" + id; 
```

在 ES6 中，占位符是使用语法\$ {NAME}的，并将包含的 NAME 变量或者表达式放在反引号中：

```js
//支持模板文本 
var name = ‘Your name is ${first} ${last}. ‘; 
var url = ‘http://localhost:3000/api/messages/${id}‘; 
```

## 3.4 默认参数和 rest 参数

JavaScript 定义默认参数的方式如下：

```js
//JavaScript原先的默认参数定义方式 
var link = function (height, color, url) { 
  var height = height || 50; 
  var color = color || " red";  
  var url = url || 'http://baidu.com'; 
} 
```

但在 ES6 中，可以直接把默认值放在函数声明中：

```js
//JavaScript新的默认参数定义方式 
var link = function(height=50, color=" red ", url='http://baidu.com') 
```

ES6 引入 rest 参数，用于获取函数的实参。不过 rest 参数不适合参数个数不确定的函数。

ES5 中获取函数的实参：

```js
function data() {  
  console.log(arguments); 
} 
data('li', 'ab', 'cc'); 
```

在 ES6 中，使用 rest 参数获取函数的实参：

```js
function data(...args) {  
  console.log(args); 
} 
data('苹果', '香蕉', '橘子'); 
```

rest 参数必须放到参数最后位置：

```js
function fn(a, b, ...args) { 
  console.log(a);  
  console.log(b);  
  console.log(args); 
} 
fn(100, 200, 300, 400, 500, 600); 
```

## 3.5 解构赋值

解构可能是一个比较难以掌握的概念。先从一个简单的赋值讲起，请看下面 ES5 代码，其中 house

和 mouse 是 key，同时 house 和 mouse 也是一个变量：

```js
var data = $('body').data() ; 
// data 拥有两个属性house 和 mouse 
house = data.house; 
mouse = data.mouse; 
// 在Node.js中使用ES5代码： 
var jsonMiddleware = require('body-parser').jsonMiddleware; 
var body = req.body; 
// body 两个属性 username 和 password 
username = body.username; 
password = body.password; 
```

在 ES6 中，可以使用以下语句来代替上面的 ES5 代码：

```js
var {house, mouse} = $('body').data(); 
var {jsonMiddleware} = require('body-parser'); 
var {username, password} = req.body; 
// 这个同样也适用于数组，是非常好的用法： 
var [coll, col2] = $('.column'), [linelz line2,line3z, line5]=file.split ('n'); 
```

## 3.6 展开运算符

展开运算符（spread operator）允许一个表达式在某处展开。展开运算符在多个参数（用于函数调用）或多个元素（用于数组字面量）或者多个变量（用于解构赋值）的地方可以使用。

1. 函数调用中使用展开运算符

   在 ES5 中可以使用 apply 方法来将一个数组展开成多个参数：

   ```js
   function test(a, b, c) { } 
   var args = [100, 200, 300]; 
   test.apply(null, args); 
   ```

   上面的代码中把 args 数组当作实参传递给了 a、b 和 c。在 ES6 中可以更加简洁地来传递数组参数：

   ```js
   function test(a,b,c) { } 
   var args = [100,200,300]; 
   test(...args); 
   ```

   这里使用展开运算符把 args 直接传递给 test()函数。

2. 数组字面量中使用展开运算符

   在 ES6 中，可以直接加一个数组并合并到另外一个数组当中：

   ```js
   var arr1=['a','b','c']; 
   var arr2=[...arr1,'d','e']; //['a','b','c','d','e'] 
   ```

   展开运算符也可以用在 push()函数中，可以不需要再使用 apply()函数来合并两个数组：

   ```js
   var arr1=['a','b','c']; 
   var arr2=['d','e']; 
   arr1.push(...arr2); //['a','b','c','d','e']
   ```

3. 用于解构赋值

   解构赋值也是 ES6 中新添加的一个特性，这个展开运算符可以用于部分情景：

   ```js
   let [arg1,arg2,...arg3] = [1, 2, 3, 4]; 
   arg1 //1 
   arg2 //2 
   arg3 //['3','4'] 
   ```

   展开运算符在解构赋值中的作用跟之前的作用看上去是相反的，它将多个数组项组合成了一个新数组。

   不过要注意，解构赋值中展开运算符只能用在最后。

   ```js
   let [arg1,...arg2,arg3] = [1, 2, 3, 4]; // 放在中间报错
   ```

4. 类数组对象变成数组

   展开运算符可以将一个类数组对象变成一个真正的数组对象：

   ```js
   var list=document.getElementsByTagName('div'); 
   var arr=[..list]; 
   ```

   list 是类数组对象，这里通过使用展开运算符使其变成了数组。

## 3.7 增强的对象文本

ES6 添加了一系列功能来增强对象文本，从而使得处理对象更加轻松。

1. 通过变量进行对象初始化

   在 ES5 中，对象的属性通常是由具有相同名称的变量创建的。例如：

   ```js
   var a = 100, b = 200, c = 300;  
   obj = {  
     a: a,  
     b: b,  
     c: c  
   }; 
   // obj.a = 100, obj.b = 200, obj.c = 300 
   ```

   在 ES6 中，简化如下：

   ```js
   const a = 100, b = 200, c = 300;  
   obj = {  
       a  
       b  
       c  
     }; 
   ```

2. 简化定义对象方法

   在 ES5 中，定义对象的方法需要 function 语句。例如：

   ```js
   var lib = { 
     sum: function(a, b) { return a + b; },  
     mult: function(a, b) { return a * b; } 
   }; 
   console.log( lib.sum(100, 200) ); // 300 
   console.log( lib.mult(100, 200) ); // 20000 
   ```

   在 ES6 中，定义对象的方法简化如下：

   ```js
   const lib = {  
     sum(a, b) { return a + b; },  
     mult(a, b) { return a * b; } 
   }; 
   console.log( lib.sum(100, 200) ); // 300 
   console.log( lib.mult(100, 200) ); // 20000 
   ```

   这里不能使用 ES6 箭头函数（=\>），因为该方法需要一个名称。如果直接命名每个方法，则可

   以使用=\>箭头函数。例如：

   ```js
   const lib = {  
     sum: (a, b) => a + b,  
     mult: (a, b) => a * b 
   }; 
   console.log( lib.sum(100, 200) ); // 300 
   console.log( lib.mult(100, 200) ); // 20000 
   ```

3. 动态属性键

   在 ES5 中，虽然可以在创建对象之后添加变量，但是不能使用变量作为键名称。例如：

   ```js
   var key1 = 'one', obj = { 
     two: 200, 
     three: 300  
   }; 
   obj[key1] = 100; 
   //表示obj.one = 100, obj.two = 200, obj.three = 300 
   ```

   通过在方括号\[\]内放置表达式，可以在 ES6 中动态分配对象键。例如：

   ```js
   const key1 = 'one',  
   obj = {  
       [key1]: 100, 
       two: 200, 
       three: 300  
   }; 
   //表示obj.one = 100, obj.two = 200, obj.three = 300 
   ```

4. 解构对象属性中的变量

   在 ES5 中，可以将对象的属性值提取到另一个变量中。例如：

   ```js
   var myObject = {  
       one: '洗衣机',  
       two: '冰箱', 
       three: '空调' 
   }; 
   var one = myObject.one, // '洗衣机' 
   two = myObject.two, // '冰箱' 
   three = myObject.three; // '空调' 
   ```

   在 ES6 中，通过解构可以创建与等效对象属性同名的变量。例如：

   ```js
   const myObject = { 
       one: '洗衣机', 
       two: '冰箱', 
       three: '空调' 
   }; 
   const { one, two, three } = myObject; 
   // 表示 one = '洗衣机', two = '冰箱', three = '空调' 
   ```

## 3.8 箭头函数

CoffeeScript 就是因为有丰富的箭头函数，所以让很多开发者所喜爱。在 ES6 中，也有丰富的箭头函数。比如，以前我们使用闭包，this 总是预期之外地产生改变，而箭头函数的好处在于，现在 this 可以按照你的预期使用了，身处箭头函数里面，this 还是原来的 this。

有了箭头函数，我们就不必像使用 that = this 或 self = this、\_this = this、.bind(this)那么麻烦了。例如，下面的代码使用 ES5 就不是很优雅：

```js
var _this = this; 
$('.btn').click(function(event){ 
  this.sendData(); 
})
```

在 ES6 中则不需要使用_this = this：

```js
$('.btn').click((event) =>{  
  this.sendData(); 
}) 
```

但并不是完全否定之前的方案，ES6 委员会决定，以前的 function 的传递方式也是一个很好的方案，所以它们仍然保留了以前的功能。

下面是另一个例子，通过 call 传递文本给 logUpperCase()函数，在 ES5 中：

```js
var logUpperCase = function() {  
  var _this = this;  
  this.string = this.string.toUpperCase();  
  return function () { 
    return console.log(_this.string); 
  }
} 
logUpperCase.call({ string: ' ES 6 rocks ' })(); 
// 而在ES6中并不需要用_this浪费时间 
var logUpperCase = function () { 
    this.string = this.string.toUpperCase(); 
    return () => console.log(this.string); 
} 
logUpperCase.call({
string: ' ES 6 rocks ' })(); 
```

> 注 意： 在ES6中，“=>”可以混合和匹配老的函数一起使用。当在一行代码中用了箭头函数后，它就变成了一个表达式，其将暗中返回单个语句的结果。如果结果超过一行，则需要明确使用return。 

在箭头函数中，对于单个参数，括号是可以省略的，但当参数超过一个时就需要使用括号了。在 ES5 代码中有明确的返回功能：

```js
var ids = ['5632953c4e345el45fdf2df8','563295464e345el45fdf2df9']; 
var messages = ids.map(function (value, index, list) { 
	return ' ID of ' + index + ' element is ' + value + ' '; 
}); 
```

在 ES6 中有更加严谨的版本，参数需要被包含在括号里并且是隐式地返回：

```js
var ids = ['5632953c4e345el45fdf2df8','563295464e345el45fdf2df9']; 
var messages = ids.map((value, index, list) => 'ID of ${index} element is $ {value} ') ;// 隐式返回 
```

## 3.9 Promise 实现

Promise 是一个有争议的话题，有人说我们不需要 Promise，仅仅使用异步、生成器、回调等就足够了，但是许多人尝试在写多个嵌套的回调函数时，基本上会在超过三层之后产生"回调地狱"。令人高兴的是，在 ES6 中有标准的 Promise 实现。

下面是使用 setTimeout()函数实现异步延迟加载函数：

```js
setTimeout(function (){  
  console.log('Yay!'); 
}, 1000); 
```

在 ES6 中，可以使用 Promise 重写，虽然在此实例中并不能减少大量的代码，甚至还多写了数行，但是逻辑却清晰了不少：

```js
var waitlOOO = new Promise((resolve, reject)=> ( 
  setTimeout(resolve, 1000); 
}).then(()=> (  
  console.log('Yay!'); 
}); 
```

## 3.10.Classes（类）

在之前的 JavaScript 版本中，类的创建和使用是令人非常头疼的一件事。不同于直接使用 class命名一个类的语言（在 JavaScript 中 class 关键字被保留，但是没有任何作用），因为没有官方的类功能，加上大量继承模型的出现（pseudo classical、classical\> functional 等），造成了 JavaScript 类使用的困难和不规范。

用 ES5 写一个类有很多种方法，这里就不介绍了，现在来看看如何使用 ES6 写一个类。ES6 不使用函数，而是使用原型来实现类，我们创建一个类 baseModel，并且在这个类中定义一个 constructor()和一个 getName()方法：

```js
class baseModel { 
  // class constructor, Node.js 5.6暂时不支持 options = {}, data =[]这样传参 
  constructor (options, data) {       
    this.name = 'Base'; 
    this.url = 'https://baidu.com';  
    this.data = data;  
    this.options = options; 
  } 
  //类的方法 
  getName () { 
    console.log('Class name: $(this.name}');
  } 
} 
```

## 3.11.Modules（模块）

众所周知，在 ES6 版之前 JavaScript 并不支持本地的模块，于是人们想出了 AMD、RequireJS、 CommonJS 及其他解决方法。如今 ES6 中可以用模块import 和export 操作了。在 ES5 中，可以在\<script\>中直接写可以运行的代码（简称 IIFE）或一些库，如 AMD。然而在 ES6 中，可以用 export 导入类。下面举个例子，在 ES5 中，module.js 有 port 变量和 getAccounts()方法：

```js
module.exports = {  
  port: 3000, 
  getAccounts: function() { 
  } 
} 
//在 ES5 中，main.js 需要依赖 require('module')导入 module.js： 
var service = require('module.js'); 
console.log(service.port); // 3000 
```

但在 ES6 中，将用 export 和 import 进行一个模块的引入和抛出。例如，以下是使用 ES6 写的module.js 文件库：

```js
export var port = 3000; 
export function getAccounts(url) { 
} 
```

如果用 ES6 将上述的 module.js 导入到文件 main.js 中，那么就变得非常简单了，只需使用 import{name} from \'my-module\' 语法即可，例如：

```js
import {port, getAccounts} from 'module'; 
console.log(port); // 3000 
```

或者可以在 main.js 中导入整个模块，并命名为：

```js
import * as service from 'module'; 
console.log(service.port); // 3000 
```

## 3.12.精简压缩生产环境的 webpack

> 随着网页功能越来越复杂，JavaScript 代码也随之越复杂。由于各种框架的使用，依赖的包也越来越多，这些复杂的内容要想让浏览器都能识别，就需要一些烦琐的操作，那么就可以借助 webpack将这些烦琐的操作简单化。
>

### 3.12.1 webpack 是什么

webpack 是一个开源的前端打包工具。当 webpack 处理应用程序时，它会构建一个依赖关系图，其中包含应用程序所需要的各个模块，然后将所有这些模块打包成一个或多个模组。webpack 可以通过终端或修改 webpack.config.js 文件来设定各项功能。

使用 webpack 前需要先安装 Node.js，webpack 其中一个特性是使用载入器将资源转化成模组，开发者可以自定义载入器的顺序、格式来适应需求。

简单来说，一款模块加载器兼打包工具，它能把各种资源，例如 JS（含 JSX）、Coffee、样式

（含 Less/Sass）、图片等都作为模块使用和处理。可以直接使用 require(XXX)的形式来引入各模块，即使它们可能需要经过编译（比如 JSX 和 Sass），但开发者无须在上面花费太多心血，因为 webpack有着各种健全的加载器（loader）在默默处理这些事情，这一点本书后续会提到。

webpack 的优点如下：

-   webpack 是以 CommonJS 的形式来书写脚本的，对 AMD/CMD 的支持也很全面，方便旧项目进行代码迁移。

-   能被模块化的不仅仅是 JavaScript，其他的静态资源同样也可以进行模块化。

-   开发便捷，能替代部分 Grunt/Gulp 的工作，如打包、压缩混淆、图片转 Base64 等。

-   扩展性强，插件机制完善。

### 3.12.2 配置一个完整项目的 webpack

新建项目文件夹webpack_test，这里需要 4 个相关的文件，分别作为 JavaScript 的入口文件app.js,存放需要调用方法的 bar.js 文件，引入导出生成的 JavaScript 文件 index.html，用于 webpack 打包文件配置的 webpack.config.js 文件。

在开始之前，请确保安装了 Node.js 的最新版本。使用 Node.js 最新的长期支持版本（Long Term Support，LTS）是理想的起步；使用旧版本可能会遇到各种问题，因为可能会缺少 webpack 功能，或者缺少相关 package 包。

在本地安装 webpack，本书使用的 webpack 版本为 webpack 3.6.0。

要安装最新版本或特定版本，请运行以下命令之一。如果读者是初学者，建议使用第 2 条命令安装和笔者相同的版本，以方便学习。

```sh
npm install save-dev webpack 
npm install save-dev webpack@<version> 
```

对于大多数项目，webpack 官方建议本地安装，这样可以使开发者在引入破坏式变更（breaking change）的依赖时，更容易分别升级项目。

通常，webpack 通过运行一个或多个 npm scripts，会在本地 node_modules 目录中查找安装的

webpack：

```js
"scripts": { 
  "start": "webpack -config webpack.config.js" 
} 
```

使用 npm 安装时也可以采用全局安装方式，使 webpack 在全局环境下可用。但是不推荐全局安装 webpack，因为会将我们项目中的 webpack 锁定到指定版本，并且在使用不同的 webpack 版本的项目中，可能会导致构建失败。

在项目中新建文件夹 app，作为 JavaScript 的代码存放处，新建 public 文件夹作为 index.html 的文件夹。

编写 app 文件夹中的两个 JavaScript 文件，为了测试 JavaScript 的 import 方式，这里需要编写两个 JavaScript 文件，分别是 app.js 和 bar.jso。

首先编写 bar.js 文件，让其在页面上弹出一个提示框，代码如下：

```js
export default function bar() { 
  // 弹岀提示 
  alert("This is Bar Function") 
} 
```

在 app.js 文件中需要引入上述 JS 文件，其代码如下：

```js
import bar from './bar '; 
bar(); 
```

在 public 文件夹中新建一个 index.html 文件，需要在该文件夹中引入 webpack 生成的 bundle.js

文件（后期生成，非自己创建），其代码如下：

```html
<html> 
  <head> 
  </head> 
  <body> 
    这里会调用bar中的方法，弹出弹窗 
    <script src='./bundle.js'></script> 
  </body> 
</html> 
```

接下来需要编辑 webpack 的配置文件即 webpack.config.js 文件，代码如下：

```js
module.exports = {
  entry: './app/app.js ', 
  output: { filename: 'bundle.js ' } 
} 
```

##  3.13 疑难解惑

### 疑问 1：ECMAScript 和 JavaScript 是什么关系？

> 要清楚这个问题，需要回顾历史。1996 年 11 月，JavaScript 的创造者 NetScape 公司，决定将JavaScript 提交给国际标准化组织 ECMA，希望这种语言能够成为国际标准。1997 年，ECMA 发布262 号标准文件（ECMA-262）的第一版，规定了浏览器脚本语言的标准，并将这种语言称为
>
> ECMAScript，这个版本就是 1.0 版。
>
> 该标准从一开始就是针对 JavaScript 语言制定的，但是之所以不叫 JavaScript，有两个原因：一是商标，Java 是 Sun 公司的商标，根据授权协议，只有 NetScape 公司可以合法地使用 JavaScript 这个名字，且 JavaScript 本身也已经被 NetScape 公司注册为商标；二是想体现这门语言的制定者是 ECMA，不是 NetScape，这样有利于保证这门语言的开放性和中立性。
>
> 因此，ECMAScript 和 JavaScript 的关系是，前者是后者的规格，后者是前者的一种实现（另外的 ECMAScript 方言还有 Jscript 和 ActionScript）。

### 疑问 2：普通函数和箭头函数的区别是什么？

> 普通函数是很早就提出的，而箭头函数是 ES6 提出的，它们两个在语法上不一样，并且它们 this的指向也不一样。对于普通函数内的 this 来说，如果没有绑定事件元素，this 指向的 window，或者在闭包中 this 指向的也是 window；如果函数绑定了事件，但并没有产生闭包，this 指向的是当前调用的事件对象。箭头函数内 this 指向是父作用域。
>
> 箭头函数不能使用 arguments，普通函数可以使用，arguments 是以集合的方式获取函数传递的参数。
>
> 箭头函数不能实例化为构造函数，而普通函数可以进行实例化。
