# 第11章　组件和组合API

在前端应用程序开发中，如果所有的实例都写在一起，必然会导致代码又长又不好理解。组件就解决了这个问题，它是带有名字的可复用实例，不仅可以重复使用，还可以扩展。组件是Vue.js最核心的功能。组件可以将一些相似的业务逻辑进行封装，重用一些代码，从而达到简化代码的目的。另外，Vue.js 3.0新增了组合API，它是一组附加的、基于函数的API，允许灵活地组合组件逻辑。本章将重点学习组件和组合API的使用方法和技巧。

## 11.1　组件是什么

组件是Vue中的一个重要概念，它是一种抽象，是一个可以复用的Vue实例。它拥有独一无二的组件名称，可以扩展HTML元素，以组件名称的方式作为自定义的HTML标签。因为组件是可复用的Vue实例，所以它们与new Vue()接收相同的选项，例如data、computed、watch、methods以及生命周期钩子等。唯一的例外是el选项，这是只用于根实例特有的选项。

在大多数的系统网页中，网页都包含header、body、footer等部分，很多情况下，同一个系统中的多个页面，可能仅仅是页面中body部分显示的内容不同，因此，这里就可以将系统中重复出现的页面元素设计成一个个的组件，当需要使用到的时候，引用这个组件即可。

在为组件命名的时候，需要使用多个单词的组合，例如组件名称可以命名为：todo-item、todo-list。但Vue中的内置组件例外，不需要使用单词的组合，例如内置组件名称：App、<transition>和<component>。这样做可以避免自定义组件的名称与现有的Vue内置组件名称以及未来的HTML元素相冲突，因为所有的HTML元素的名称都是单个单词。

## 11.2　组件的注册

在Vue中创建一个新的组件之后，为了能在模板中使用，这些组件必须先进行注册以便Vue能够识别。在Vue中有两种组件的注册类型：全局注册和局部注册。

### 11.2.1　全局注册

全局注册组件使用应用程序实例的component()方法来注册组件。该方法有两个参数，第一个参数是组件的名称，第二个参数是函数对象或者选项对象。语法格式如下：

```js
app.component({string}name, {Function|Object} definition(optional))
```

因为组件最后会被解析成自定义的HTML代码，因此，可以直接在HTML中使用组件名称作为标签来使用。全局注册组件示例如下所示。

【例11.1】全局注册组件（源代码\ch11\11.1.html）。

```html
<div id="app">
    <!--使用my-component组件-->
    <my-component></my-component>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({});
    vm.component('my-component', {
        data() {
            return {
                message: "红罗袖里分明见"
            }
        },
        template: `
          <div><h2>{{ message }}</h2></div>`
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，按F12键打开控制台并切换到“Elements”选项，效果如图11-1所示。

![](assets\CB_3300020858_Figure-P167_73120.jpg)



图11-1　全局注册组件

从控制台中可以看到，自定义的组件已经被解析成了HTML元素。需要注意一个问题，当采用小驼峰（myCom）的方式命名组件时，在使用这个组件的时候，需要将大写字母改为小写字母，同时两个字母之间需要使用“-”进行连接，例如<my-com>。

### 11.2.2　局部注册

有些时候，注册的组件只想在一个Vue实例中使用，这时可以使用局部注册的方式注册组件。在Vue实例中，可以通过components选项注册仅在当前实例作用域下可用的组件。

【例11.2】局部注册组件（源代码\ch11\11.2.html）。

```html
<div id="app">
    库房还剩
    <button-counter></button-counter>
    台。
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    const MyComponent = {
        data() {
            return {
                num: 1000
            }
        },
        template:
                `
                  <button v-on:click="num--">
                  {{ num }}
                  </button>`
    }
    //创建一个应用程序实例
    const vm = Vue.createApp({
        components: {
            ButtonCounter: MyComponent
        }
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，单击按钮将会逐步递减数字，效果如图11-2所示。

![](assets\CB_3300020858_Figure-P168_73626.jpg)

图11-2　局部注册组件

## 11.3　使用prop向子组件传递数据

组件是当作自定义元素来使用的，而元素一般是有属性的，同样组件也可以有属性。在使用组件时，给元素设置属性，组件内部如何接受呢？首先需要在组件代码中注册一些自定义的属性，称为prop，这些prop是放在组件的props选项中定义的；之后，在使用组件时，就可以把这些prop的名字作为元素的属性名来使用，通过属性向组件传递数据，这些数据将作为组件实例的属性被使用。

### 11.3.1　prop基本用法

下面看一个示例，使用prop属性向子组件传递数据，这里传递“庭院深深深几许，云窗雾阁春迟。”，在子组件的props选项中接收prop属性，然后使用差值语法在模板中渲染prop属性。

【例11.3】使用prop属性向子组件传递数据（源代码\ch11\11.3.html）。

```html
<div id="app">
    <blog-content date-title="庭院深深深几许，云窗雾阁春迟。"></blog-content>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({});
    vm.component('blog-content', {
        props: ['dateTitle'],
        //date-title就像data定义的数据属性一样
        template: '<h3>{{ dateTitle }}</h3>',
        //在该组件中可以使用“this.dateTitle”这种形式调用prop属性
        created() {
            console.log(this.dateTitle);
        }
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，效果如图11-3所示。

![](assets\CB_3300020858_Figure-P169_74086.jpg)

图11-3　使用prop属性向子组件传递数据

> 提示：
>
> HTML中的attribute名是大小写不区分的，所以浏览器会把所有大写字符解释为小写字符，prop属性也适用这种规则。当使用DOM中的模板时，dateTitle（驼峰命名法）的prop名需要使用其等价的date-title（短横线分隔命名）命名。

上面示例中，使用prop属性向子组件传递了字符串值，还可以传递数字。这只是它的一个简单用法。通常情况下可以使用v-bind来传递动态的值，传递数组和对象时也需要使用v-bind指令。

修改上面示例，在Vue实例中定义title属性，以传递到子组件中去。示例代码如下。

【例11.4】传递title属性到子组件（源代码\ch11\11.4.html）。

```html
<div id="app">
    <blog-content v-bind:date-title="content"></blog-content>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        //该函数返回数据对象
        data() {
            return {
                content: "玉瘦檀轻无限恨，南楼羌管休吹。"
            }
        }
    });
    vm.component('blog-content', {
        props: ['dateTitle'],
        template: '<h3>{{ dateTitle }}</h3>',
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，效果如图11-4所示。

![](assets\CB_3300020858_Figure-P170_74534.jpg)

图11-4　传递title属性到子组件

在上面示例中，在Vue实例中向子组件中传递数据，通常情况下多用于组件向组件传递数据。下面示例创建了两个组件，在页面中渲染其中一个组件，而在这个组件中使用另外一个组件，并传递title属性。

【例11.5】组件之间传递数据（源代码\ch11\11.5.html）。

```html
<div id="app">
    <!--使用blog-content组件-->
    <blog-content></blog-content>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({});
    vm.component('blog-content', {
        // 使用blog-title组件，并传递content
        template: '<div><blog-title v-bind:date-title="title"></blog-title></div>',
        data: function () {
            return {
                title: "明朝准拟南轩望，洗出庐山万丈青。"
            }
        }
    });
    vm.component('blog-title', {
        props: ['dateTitle'],
        template: '<h3>{{ dateTitle }}</h3>',
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，效果如图11-5所示。

![](assets\CB_3300020858_Figure-P171_75067.jpg)

图11-5　组件之间传递数据

如果组件需要传递多个值，可以定义多个prop属性。

【例11.6】传递多个值（源代码\ch11\11.6.html）。

```html
<div id="app">
    <!--使用blog-content组件-->
    <blog-content></blog-content>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({});
    vm.component('blog-content', {
        // 使用blog-title组件，并传递content
        template: '<div><blog-title :name="name" :price="price" :num="num"></blog-title></div>',
        data: function () {
            return {
                name: "苹果",
                price: "6.88元",
                num: "2800公斤"
            }
        }
    });
    vm.component('blog-title', {
        props: ['name', 'price', 'num'],
        template: '<ul><li>{{name}}</li><li>{{price}}</li><li>{{num}}</li></ul> ',
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，效果如图11-6所示。

![](assets\CB_3300020858_Figure-P172_75669.jpg)

图11-6　传递多个值

从上面示例可以看到，代码以字符串数组形式列出多个prop属性：

```js
props: ['name','price','num']
```

但是，通常希望每个prop属性都有指定的值类型。这时，可以以对象形式列出prop，这些property的名称和值分别是prop各自的名称和类型，例如：

```html
props: {
	name: String,
	price: String,
	num: String
}
```

### 11.3.2　单向数据流

所有的prop属性传递数据都是单向的。父组件的prop属性的更新会向下流动到子组件中，但是反过来则不行。这样会防止从子组件意外变更父级组件的数据，从而导致应用的数据流向难以理解。

另外，每次父级组件发生变更时，子组件中所有的prop属性都将会刷新为最新的值。这意味着不应该在一个子组件内部改变prop属性。如果这样做，Vue会在浏览器的控制台中发出警告。

有两种情况可能需要改变组件的prop属性。第一种情况是定义一个prop属性，以方便父组件传递初始值，在子组件内将这个prop作为一个本地的prop数据来使用。遇到这种情况，解决办法是在本地的data选项中定义一个属性，然后将prop属性值作为其初始值，后续操作只访问这个data属性。示例代码如下：

```js
props: ['initDate'],
data: function() {
	return {
		title: this.initDate
	}
}
```

第二种情况是prop属性接收数据后需要转换后使用。这种情况可以使用计算属性来解决。示例代码如下：

```js
props: ['size'],
computed: {
    newSize: function() {
        return this.seze.trim().toLowerCase()
    }
}
```

后续的内容直接访问计算属性nowSize即可。

> 提示：
>
> 在JavaScript中对象和数组是通过引用传入的，所以对于一个数组或对象类型的prop属性来说，在子组件中改变这个对象或数组本身将会影响到父组件的状态。

### 11.3.3　prop验证

当开发一个可复用的组件时，父组件希望通过prop属性传递的数据类型符合要求。例如，组件定义一个prop属性是一个对象类型，结果父组件传递的是一个字符串的值，这明显不合适。因此，Vue.js提供了prop属性的验证规则，在定义props选项时，使用一个带验证需求的对象来代替之前使用的字符串数组（props:['name','price','city']）。代码说明如下：

```js
vm.component('my-component', {
    props: {
        // 基础的类型检查 (`null` 和 `undefined` 会通过任何类型验证)
        name: String,
        // 多个可能的类型
        price: [String, Number],
        // 必填的字符串
        city: {
            type: String,
            required: true
        },
        // 带有默认值的数字
        prop1: {
            type: Number,
            default: 100
        },
        // 带有默认值的对象
        prop2: {
            type: Object,
            // 对象或数组默认值必须从一个工厂函数获取
            default: function() {
                return { message: 'hello' }
            }
        },
        // 自定义验证函数
        prop3: {
            validator: function (value) {
                // 这个值必须匹配下列字符串中的一个
                return ['success', 'warning', 'danger'].indexOf(value) !== -1
            }
        }
        
    }
})
```

为组件的prop指定验证要求后，如果有一个需求没有被满足，则Vue会在浏览器控制台中发出警告。上面代码验证的type可以是下面原生构造函数中的一个：

```html
String
Number
Boolean
Array
Object
Date
Function
Symbol
```

另外，type还可以是一个自定义的构造函数，并且通过instanceof来进行检查确认。例如，给定下列现成的构造函数：

```js
function Person(firstName, lastName) {
    this.firstName = firstName
    this.lastName = lastName
}
```

可以通过以下代码验证name的值是否通过new Person创建的。

```vue
vm.component('blog-content', {
	props: {
		name: Person
	}
})
```

### 11.3.4　非prop的属性

在使用组件的时候，父组件可能会向子组件传入未定义prop的属性值，这样也是可以的。组件可以接收任意的属性，而这些外部设置的属性会被添加到子组件的根元素上。示例代码如下。

【例11.7】非prop的属性（源代码\ch11\11.7.html）。

```html
<style>
    .bg1 {
        background: #C1FFE4;
    }
    .bg2 {
        width: 120px;
    }
</style>
<div id="app">
    <!--使用blog-content组件-->
    <input-con class="bg2" type="text"></input-con>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({});
    vm.component('input-con', {
        template: '<input class="bg1">',
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，输入“九曲黄河万里沙”，打开控制台，效果如图11-7所示。

![](assets\CB_3300020858_Figure-P175_77485.jpg)

图11-7　非prop的属性

从上面示例可以看出，input-con组件没有定义任何prop，根元素是<input>，在DOM模板中使用<input-con>元素时设置了type属性，这个属性将被添加到input-con组件的根元素input上，渲染结果为<input type="text">。另外，在input-con组件的模板中还使用了class属性bg1，同时在DOM模板中也设置了class属性bg2，这种情况下，两个class属性的值会被合并，最终渲染的结果为<input class="bg1 bg2"type="text">。

要注意的是，只有class和style属性的值会合并，对于其他属性而言，从外部提供给组件的值会替换掉组件内容设置好的值。假设input-con组件的模板是<input type="text">，如果父组件传入type="password"，就会替换掉type="text"，最后渲染结果就变成了<input type="password">。

例如修改上面示例：

```html
<div id="app">
    <!--使用blog-content组件-->
    <input-con class="bg2" type="password"></input-con>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({});
    vm.component('input-con', {
        template: '<input class="bg1" type="text">',
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，然后输入“12345678”，可以发现input的类型为“password”，效果如图11-8所示。

![](assets\CB_3300020858_Figure-P176_77853.jpg)

图11-8　外部组件的值替换掉组件设置好的值

如果不希望组件的根元素继承外部设置的属性，可以在组件的选项中设置inheritAttrs:false。例如修改上面示例代码：

```html
vm.component('input-con', {
    template: '<input class="bg1" type="text">',
	inheritAttrs: false
});
```

再次运行项目，可以发现父组件传递的type="password"，子组件并没有继承。

## 11.4　子组件向父组件传递数据

前面介绍了父组件通过prop属性向子组件传递数据，那子组件如何向父组件传递数据呢？具体实现请看下面的讲解。

### 11.4.1　监听子组件事件

在Vue中可以通过自定义事件来实现。子组件使用$emit()方法触发事件，父组件使用v-on指令监听子组件的自定义事件。$emit()方法的语法形式如下：

```html
vm.$emit(myEvent, [...args])
```

其中myEvent是自定义的事件名称，args是附加参数，这些参数会传递给监听器的回调函数。如果要向父组件传递数据，可以通过第二个参数来传递。如下面示例代码所示。

【例11.8】子组件要向父组件传递数据（源代码\ch11\11.8.html）。

这里定义1个子组件，子组件的按钮接收到click事件后，调用$emit()方法触发一个自定义事件。在父组件中使用子组件时，可以使用v-on指令监听自定义的date事件。

```html
<div id="app">
    <parent></parent>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({});
    vm.component('child', {
        data: function () {
            return {
                info: {
                    name: "哈密瓜",
                    price: "8.66",
                    num: "2600公斤"
                }
            }
        },
        methods: {
            handleClick() {
                //调用实例的$emit()方法触发自定义事件greet，并传递info
                this.$emit("date", this.info)
            },
        },
        template: '<button v-on:click="handleClick">显示子组件的数据</button>'
    });
    vm.component('parent', {
        data: function () {
            return {
                name: '',
                price: '',
                num: '',
            }
        },
        methods: {
            // 接收子组件传递的数据
            childDate(info) {
                this.name = info.name;
                this.price = info.price;
                this.num = info.num;
            }
        },
        template: `
          <div>
          <child v-on:date="childDate"></child>
          <ul>
            <li>{{ name }}</li>
            <li>{{ price }}</li>
            <li>{{ num }}</li>
          </ul>
          </div>
        `
    });
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

在浏览器中运行程序，单击“显示子组件的数据”按钮，将显示子组件传递过来的数据，效果如图11-9所示。

![](assets\CB_3300020858_Figure-P178_78893.jpg)

图11-9　子组件要向父组件传递数据

### 11.4.2　将原生事件绑定到组件

在组件的根元素上可以直接监听一个原生事件，使用v-on指令时添加一个.native修饰符即可。例如：

```html
<base-input v-on:focus.native="onFocus"></base-input>
```

在有的情形下这很有用，不过在尝试监听一个类似<input>的非常特定的元素时，这并不是个好主意。例如<base-input>组件可能做了如下重构，所以根元素实际上是一个<label>元素：

```html
<label>
	{{ label }}
    <input v-bind="$attrs"
           v-bind:value="value"
           v-on:input="$emit('input', $event.target.value)"
           >
</label>
    
```

这时父组件的.native监听器将静默失败。它不会产生任何报错，但是onFocus处理函数不会如预期一般被调用。

为了解决这个问题，Vue提供了一个$listeners属性，它是一个对象，里面包含了作用在这个组件上的所有监听器。例如：

```html
{
	focus: function (event) { /* ... */ }
	input: function (event) { /* ... */ }
}
```

有了这个$listeners属性，就可以配合v-on="$listeners"，将所有的事件监听器指向这个组件的某个特定的子元素。对于那些需要v-model的元素（如input）来说，可以为这些监听器创建一个计算属性，例如下面代码中的inputListeners。

```js
vm.component('base-input', {
	inheritAttrs: false,
	props: ['label', 'value'],
	computed: {
        inputListeners: function() {
            var vm = this
            // `Object.assign` 将所有的对象合并为一个新对象
            return Object.assign({},
                                 // 我们从父级添加所有的监控器
                                 this.$listeners,
                                 // 然后我们添加自定义监控器，
                                 // 或覆写一些监听器的行为
                                 {
                					// 这里确保组件配合 `v-model` 的工作
                					input: function(event) {
                                        vm.$emit('input', event.target.value)
                                    }
	            				}
            )
        }
    },
    template: `
    	<label>
    		{{ label }}
    		<input 
    			v-bind="$attrs"
               	v-bind:value="value"
               	v-on:input="$emit('input', $event.target.value)"
               >
    	</label>
    `
})
```

现在<base-input>组件是一个完全透明的包裹器了，也就是说它可以完全像一个普通的<input>元素一样使用，所有跟它相同的属性和监听器都可以工作，不必再使用.native修饰符。

### 11.4.3　.sync修饰符

在有些情况下，可能需要对一个prop属性进行“双向绑定”。但是真正的双向绑定会带来维护上的问题，因为子组件可以变更父组件，且父组件和子组件都没有明显的变更来源。Vue.js推荐以update:myPropName模式触发事件来实现，示例代码如下所示。

【例11.9】设计购物的数量（源代码\ch11\11.9.html）。其中子组件代码如下：

```js
vm.component('child', {
    data: function () {
        return {
            count: this.value
        }
    },
    props: {
        value: {
            type: Number,
            default: 0
        }
    },
    methods: {
        handleClick() {
            this.$emit("update:value", ++this.count)
        },
    },
    template: `
      <div>
      <sapn>子组件：购买{{ value }}件</sapn>
      <button v-on:click="handleClick">增加</button>
      </div>
    `
});
```

在这个子组件中有一个prop属性value，在按钮的click事件处理器中，调用$emit()方法触发update:value事件，并将加1后的计数值作为事件的附加参数。

在父组件中，使用v-on指令监听update:value事件，这样就可以接收到子组件传来的数据，然后使用v-bind指令绑定子组件的prop属性value，就可以给子组件传递父组件的数据，这样就实现了双向数据绑定。示例代码如下：

```html
<div id="app">
    父组件：购买{{counter}}件
    <child v-bind:value="counter" v-on:update:value="counter=$event"></child>
</div>
<!--引入vue文件-->
<script src="https://unpkg.com/vue@next"></script>
<script>
    //创建一个应用程序实例
    const vm = Vue.createApp({
        data() {
            return {
                counter: 0
            }
        }
    });   
    //在指定的DOM元素上装载应用程序实例的根组件
    vm.mount('#app');
</script>
```

其中$event是自定义事件的附加参数。在浏览器中运行程序，单击6次“增加”按钮，可以看到父组件和子组件中购买数量是同步变化的，结果如图11-10所示。

![](assets\CB_3300020858_Figure-P180_80446.jpg)

图11-10　同步更新父组件和子组件的数据

为了方便起见，Vue为prop属性的“双向绑定”提供了一个缩写，即.sync修饰符，修改上面示例的<child>的代码：

```html
<child v-bind:value="counter" v-on:update:value="counter=$event"></child>
```

注意，带有.sync修饰符的v-bind不能和表达式一起使用，v-bind:title.sync="doc.title+'!'"是无效的。例如：

```html
v-bind:value.sync="doc.title+'!'"
```

上面代码是无效的，取而代之的是，只能提供你想要绑定的属性名，类似v-model。当用一个对象同时设置多个prop属性时，也可以将.sync修饰符和v-bind配合使用：

```html
<child v-bind.sync="doc"></child>
```

这样会把doc对象中的每一个属性都作为一个独立的prop传进去，然后各自添加用于更新的v-on监听器。

> 提示：
>
> 将v-bind.sync用在一个字面量的对象上，例如v-bind.sync="title:doc.title"，是无法正常工作的。

## 11.5　插槽

组件是当作自定义的HTML元素来使用的，其元素可以包括属性和内容，通过组件定义的prop来接收属性值，那组件的内容怎么实现呢？可以使用插槽（slot元素）来解决。

### 11.5.1　插槽基本用法

下面定义一个组件：

```html
vm.component('page', {
    template:`<div>
                <slot>Submit</slot>
              </div>
              `
});
```

在page组件中，div元素内容定义了slot元素，可以把它理解为占位符。在Vue实例中使用这个组件：

```html
<div id="app">
    <page>如今直上银河去，同到牵牛织女家。</page>
</div>
```

page元素给出了内容，在渲染组件时，这个内容会置换组件内部的<slot>元素。在浏览器中运行程序，渲染的结果如图11-11所示。

![](assets\CB_3300020858_Figure-P182_80649.jpg)

图11-11　插槽基本用法

如果page组件中没有slot元素，<page>元素中的内容将不会渲染到页面。

### 11.5.2　编译作用域

当想通过插槽向组件传递动态数据时，例如：

```html
<page>欢迎来到{{ name }}的官网</page>
```

代码中，name属性是在父组件作用域下解析的，而不是page组件的作用域。而在page组件中定义的属性，在父组件是访问不到的，这就是编译作用域。

作为一条规则记住：父组件模板里的所有内容都是在父级作用域中编译的；子组件模板里的所有内容都是在子作用域中编译的。

### 11.5.3　默认内容

有时为一个插槽设置默认内容是很有用的，它只会在没有提供内容的时候被渲染。例如在一个<submit-button>组件中：

```html
<button type="submit">
	<slot>Submit</slot>
</button>
```

如果希望这个<button>内绝大多数情况下都渲染文本“Submit”，可以将“Submit”作为默认内容，将它放在<slot>标签内：

```html

```

现在在一个父组件中使用<submit-button>，并且不提供任何插槽内容：

```html

```

默认内容“Submit”将会被渲染：

```html

```

但是如果提供内容：

```html
```

则这个提供的内容将会替换掉默认值Submit，渲染如下：

```html

```

【例11.10】设置插槽的默认内容（源代码\ch11\11.10.html）。

```html

```

在浏览器中运行程序，渲染的结果如图11-12所示。

[插图]

图11-12　设置插槽的默认内容

### 11.5.4　命名插槽

在组件开发中，有时需要使用多个插槽。例如对于一个带有如下模板的<page-layout>组件：

```html

```

对于这样的情况，<slot>元素有一个特殊的特性name，它用来命名插槽。因此可以定义多个名字不同的插槽，例如下面代码：

```html

```

一个不带name的<slot>元素，它有默认的名字“default”。在向命名插槽提供内容的时候，可以在一个<template>元素上使用v-slot指令，并以v-slot的参数的形式提供其名称：

```html

```

现在<template>元素中的所有内容都将会被传入相应的插槽。任何没有被包裹在带有v-slot的<template>中的内容都会被视为默认插槽的内容。然而，如果希望更明确一些，仍然可以在一个<template>中包裹默认命名插槽的内容：

```html

```

上面两种写法都会渲染出如下代码：

```html

```

【例11.11】命名插槽（源代码\ch11\11.11.html）。

```html

```

在浏览器中运行程序，效果如图11-13所示。

[插图]

图11-13　命名插槽

与v-on和v-bind一样，v-slot也有缩写，即把参数之前的所有内容（v-slot:）替换为字符#。例如下面代码：

```html

```

### 11.5.5　作用域插槽

在父级作用域下，在插槽的内容中是无法访问到子组件的数据属性的，但有时候需要在父级的插槽内容中访问子组件的属性，我们可以在子组件的<slot>元素上使用v-bind指令绑定一个prop属性。看下面的组件代码：

```html

```

这个按钮可以显示info对象中的任意一个，为了让父组件可以访问info对象，在<slot>元素上使用v-bind指令绑定一个values属性，称为插槽prop，这个prop不需要在props选项中声明。

在父级作用域下使用该组件时，可以给v-slot指令一个值来定义组件提供的插槽prop的名字。代码如下：

```html

```

因为<page-layout>组件内的插槽是默认插槽，所以这里使用其默认的名字default，然后给出一个名字slotProps，这个名字可以随便取，代表的是包含组件内所有插槽prop的一个对象，然后就可以在父组件中利用这个对象访问子组件的插槽prop，prop是绑定到info数据属性上的，所以可以进一步访问info的内容。示例代码如下。

【例11.12】访问插槽的内容（源代码\ch11\11.12.html）。

```html
```

在浏览器中运行程序，效果如图11-14所示。

[插图]

图11-14　命名插槽

### 11.5.6　解构插槽prop

作用域插槽的内部工作原理是将插槽内容传入到函数的单个参数里：

```html

```

这意味着v-slot的值实际上可以是任何能够作为函数定义中的参数的JavaScript表达式。所以在支持的环境下（单文件组件或现代浏览器），也可以使用ES6解构来传入具体的插槽prop，示例代码如下：

```html

```

这样可以使模板更简洁，尤其是在该插槽提供了多个prop的时候。它同样开启了prop重命名等其他可能，例如将verse重命名为poetry：[插图]甚至可以定义默认的内容，用于插槽prop是undefined的情形：

```html

```

【例11.13】解构插槽prop（源代码\ch11\11.13.html）。

```html

```

在浏览器中运行程序，效果如图11-15所示。

[插图]

图11-15　解构插槽prop

## 11.6　Vue.js 3.0的新变化1——组合API

通过创建Vue组件，可以将接口的可重复部分及其功能提取到可重用的代码段中，从而提高了应用程序的可维护性和灵活性。随着应用程序越来越复杂，拥有几百个组件的应用程序，仅仅依靠组件很难满足共享和重用代码的需求。

用组件的选项（data、computed、methods、watch）组织逻辑在大多数情况下都有效。然而，当组件变得更大时，逻辑关注点的列表也会增长。这样会致组件难以阅读和理解，尤其是对于那些一开始就没有编写这些组件的人来说。这种碎片化使得理解和维护复杂组件变得困难。选项的分离掩盖了潜在的逻辑问题。此外，在处理单个逻辑关注点时，用户必须不断地“跳转”相关代码的选项块。如何才能将同一个逻辑关注点相关的代码配置在一起？这正是组合API要解决的问题。

Vue 3.0中新增的组合API为用户组织组件代码提供了更大的灵活性。现在，可以将代码编写成函数，每个函数处理一个特定的功能，而不再需要按选项组织代码了。组合API还使得在组件之间甚至外部组件之间提取和重用逻辑变得更加简单。

组合API可以和TypeScript更好地集成，因为组合API是一套基于函数的API。同时，组合API也可以和现有的基于选项的API一起使用。不过需要特别注意的是，组合API会在选项（data、computed和methods）之前解析，所以组合API是无法访问这些选项中定义的属性的。

## 11.7　setup()函数

setup()函数是一个新的组件选项，它是组件内部使用组合API的入口点。新的setup组件选项在创建组件之前执行，一旦props被解析，并充当合成API的入口点。对于组件的生命周期钩子，setup()函数在beforeCreate钩子之前调用。

setup()是一个接受props和context的函数，而且接受的props对象是响应式的，在组件外部传入新的prop值时，props对象会更新，可以调用相应的方法监听该对象并对修改做出相应。其用法如下所示。

【例11.14】setup()函数（源代码\ch11\11.14.html）。

```html
```

在浏览器中运行程序，效果如图11-16所示。

[插图]

在浏览器中运行程序，效果如图11-16所示。[插图]图11-16　setup()函数

> 

注意：由于在执行setup()函数时尚未创建组件实例，因此在setup()函数中没有this。这意味着除了props之外，用户将无法访问组件中声明的任何属性——本地状态、计算属性或方法。

## 11.8　响应式API

Vue 3.0的核心功能主要是通过响应式API实现的，组合API将它们公开为独立的函数。

### 11.8.1　reactive()方法和watchEffect()方法

例如，下面代码中给出了Vue 3.0中的响应式对象的例子：

```html

```

Vue 3.0提供了一种创建响应式对象的方法reactive()，其内部就是利用Proxy API来实现的，特别是借助handler的set方法，可以实现双向数据绑定相关的逻辑，这相对于Vue 2.x中的Object.defineProperty()是很大的改变。

（1）Object.defineProperty()只能单一的监听已有属性的修改或者变化，无法检测到对象属性的新增或删除，而Proxy则可以轻松实现。

（2）Object.defineProperty()无法监听属性值是数组类型的变化，而Proxy则可以轻松实现。

例如，监听数组的变化：

```html

```

watchEffect()方法函数类似于Vue 2.x中的watch选项，该方法接受一个函数作为参数，会立即运行该函数，同时响应式地跟踪其依赖项，并在依赖项发生修改时重新运行该函数。

【例11.15】reactive()方法和watchEffect()方法（源代码\ch11\11.15.html）。

```html

```

在浏览器中运行程序，效果如图11-17所示。按F12键打开控制台并切换到“Console”选项，输入“state.count=1000”后按回车键，效果如图11-18所示。

[插图]

图11-17　初始状态

[插图]

图11-18　响应式对象的依赖跟踪



### 11.8.2　ref()方法

reactive()方法为一个JavaScript对象创建响应式代理。如果需要对一个原始值创建一个响应式代理对象，可以通过ref()方法来实现，该方法接受一个原始值，返回一个响应式和可变的响应式对象。ref()方法的用法如下所示。

【例11.16】ref()方法（源代码\ch11\11.16.html）。

```html

```

在浏览器中运行程序，按F12键打开控制台并切换到“Console”选项，输入“state.value=8888”后按回车键，效果如图11-19所示。这里需要修改state.value的值，而不是直接修改state对象。

[插图]

图11-19　使用ref()方法

### 11.8.3　readonly()方法

有时候仅仅需要跟踪相应对象，而不希望应用程序对该对象进行修改。此时可以通过readonly()方法为原始对象创建一个只读属性，从而防止该对象在注入的地方发生变化，这提供了程序的安全性。例如以下代码：

```html

```

### 11.8.4　computed()方法

computed()方法主要用于创建依赖于其他状态的计算属性，该方法接受一个getter函数，并为getter返回的值返回一个不可变的响应式对象。computed()方法的用法如下所示。











【例11.17】computed()方法（源代码\ch11\11.17.html）。[插图]在浏览器中运行程序，结果如图11-20所示。[插图]图11-20　computed()方法11.8.5　watch()方法watch()方法需要监听特定的数据源，并在单独的回调函数中应用。当被监听的数据源发生变化时，才会调用回调函数。例如下面的代码监听普通类型的对象：



[插图]watch()方法还可以监听响应式对象：[插图]对于Vue 2.x中，watch可以监听多个数据源，并且执行不同的函数。在Vue 3.0中同理也能实现相同的情景，通过多个watch来实现，但在Vue 2.x中，只能存在一个watch。例如Vue 3.0中监听多个数据源：[插图]对于Vue 3.0，监听器可以使用数组同时监听多个数据源。例如：[插图]11.9　Vue.js 3.0的新变化2——访问组件的方式在Vue.js 2.x中，如果需要访问组件实例的属性，可以直接访问组件的实例，如图11-21所示。



[插图]图11-21　Vue.js 2.x中访问组件属性的方式在Vue.js 3.0中，访问组件实例会通过组件代理对象，而不是直接访问组件实例，如图11-22所示。[插图]图11-22　Vue.js 3.0中访问组件属性的方式

## 11.10　综合案例——使用组件创建树状项目分类

本案例使用组件创建树状项目分类。其主要代码如下：

[插图]



在浏览器中运行程序，效果如图11-23所示。

[插图]

图11-23　树状项目分类

## 11.11　疑难解惑

疑问1：定义组件的名称可以用大写字母吗？

因为组件最后会被解析成自定义的HTML代码，而HTML并不区分元素和属性的大小写，所以浏览器会把所有大写字母解释为小写字母。例如组件注册的时候名称为Vum-counter，浏览器会解释为vum-counter，这就会导致找不到组件而出现错误，所以定义组件的名称不可以用大写字母。

疑问2：使用setup()函数需要注意什么？

使用setup()函数需要注意问题如下：

（1）由于在执行setup函数的时候，还没有执行Created生命周期方法，所以在setup()函数中，无法使用data和methods。

（2）Vue为了避免错误使用this，直接将setup函数中this修改成了undefined。

（3）setup()函数只能同步操作，而不能异步操作。
