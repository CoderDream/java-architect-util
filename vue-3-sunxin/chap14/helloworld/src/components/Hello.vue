<template>
  <p>{{ message }}</p>
</template>
<script>

import axios from 'axios'
export default {
  data() {
    return {
      message: 'Hello, Vue.js'
    }
  },
  mounted() {
    axios.get("/xxx").then(res => {
      console.log(res);
    })
  }
}
</script>