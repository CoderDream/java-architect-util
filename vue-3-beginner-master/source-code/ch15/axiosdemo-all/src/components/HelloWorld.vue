<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
import axios from "axios";
// 定义请求方法
function get1() {
  return axios.get('http://localhost:3004/stu');
}
function get2() {
  return axios.get('/api/data/user.json');
}

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  mounted() {
    this.add();
  },
  methods: {
    async add() {
      await axios.all([get1(), get2()]).then(axios.spread(function (get1, get2){
        console.log(get1);
        console.log(get2);
      }));
    }
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
