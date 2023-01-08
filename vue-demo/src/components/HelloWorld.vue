<!--
跟 Vue 说 Hello World！
-->

<template>
  <div class="hello">
    <h3>学习模板语法</h3>
    <h1>{{ message }}</h1>
    <div>{{ rawHtml }}</div>
    <div v-html="rawHtml"></div>
    <div v-bind:id="dynamicId"></div>
    <!-- 省略v-bind -->
    <div :id="dynamicId"></div>
    <p>{{ num + 10 }}</p>
    <p>{{ flag ? 'YES' : 'NO ' }}</p>
    <p v-if="flagMonkey">我是孙猴子</p>
    <p v-else>你是傻猴子</p>
    <br>

    <p v-show="flag">你真的是孙猴子吗？</p>
  </div>

  <div>
    <ul v-if="newsList.length">
      <li v-for="item of newsList" :key="item.id">
        {{ item.title }}
      </li>
    </ul>
  </div>
  <p>06—事件处理</p>
  <div>
    <button v-on:click="counter += 1">点击1：counter = {{ counter }}</button>
  </div>
  <div>
    <button @click="counter += 1">点击2：counter = {{ counter }}</button>
  </div>
  <div>
    <button @click="clickHandle">按钮</button>
  </div>
  <div>
    <p>{{ message }}</p>
  </div>
  <div>
    <button @click="say('hi')">say hi</button>
    <button @click="say('what')">say what</button>
  </div>
  <hr>
  <div>
    <ul v-if="names.length">
      <li @click="clickItemHandle(item)" v-for="(item, index) of names" :key="index">
        {{ item }}
      </li>
    </ul>
  </div>

  <div class="hello">
    <input type="text" v-model="username">
    <input type="text" v-model="password">
    <p>{{ username }}</p>
    <button @click="clickGetUserName()">获取用户名</button>
  </div>
</template>

<script>
export default {
  // innerHTML innerText
  name: 'HelloWorld',
  data() {
    return {
      message: '消息通知',
      rawHtml: "<a href='http://cn.bing.com'>必应中文</a>",
      dynamicId: 10001,
      num: 20,
      flag: true,
      flagMonkey: false,
      newsList: [
        {
          id: 1001,
          title: "今日新闻1"
        },
        {
          id: 1002,
          title: "今日新闻2"
        },
        {
          id: 1003,
          title: "今日新闻3"
        }
      ],
      counter: 1,
      names: ["iwen", "ime", "frank"],
      username: "",
      password: ""
    }
  },
  methods: {
    clickHandle(event) {
      //  console.log("哈哈哈");
      // 在事件中，读取data中的属性，是需要通过 this. 属性
      this.message = "消息被撤回了"
      // `event` 是原生的 DOM event
      if(event) {
        alert(event.target.tagName);
        console.log(event);
        event.target.innerHTML = "点击之后";
      }
    },
    say(data) {
      console.log(data);
    },
    clickItemHandle(item) {
      console.log(item);
    },
    clickGetUserName() {
      console.log(this.username);
    }
  }
}
</script>