import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

const app = createApp(App)

app.use(VueAxios, axios)
//然而只是这样全局并不能用;
app.provide('axios', app.config.globalProperties.axios)
//这句不写, 组件里无法注入全局axios, 也就无法使用

app.mount('#app')
