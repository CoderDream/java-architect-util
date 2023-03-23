<template>
  <div class="hello">
    <h2>{{ city }}</h2>
    <h4>今天：{{ date }} {{ week }}</h4>
    <h4>{{ message }}</h4>
    <ul>
      <li v-for="item in obj">
        <div>
          <h3>{{ item.date }}</h3>
          <h3>{{ item.week }}</h3>
          <img :src="get(item.wea_img)" alt="">
          <h3>{{ item.wea }}</h3>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'HelloWorld',
  data() {
    return {
      city: "",
      obj: [],
      date: "",
      week: "",
      message: ""
    }
  },
  props: {
    msg: String
  },
  mounted() {
    // this.add();
  },
  methods: {
    //定义get方法，拼接图片的路径
    get(sky) {
      return "durian/" + sky + ".png"
    },
    async add(that) {
      await axios.get('/api').then((response) => {
        if (response) {
          console.log(response);
          //处理数据
          that.city = response.data.city;
          that.obj = response.data.data;
          that.date = response.data.data[0].date;
          that.week = response.data.data[0].week;
          that.message = response.data.data[0].air_tips;
        } else {
          // alert('数据获取失败);'
        }
      })
    }
  },
  created() {
    this.get();  //页面开始加载时调用get方法
    var that = this;
    this.add(that);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
