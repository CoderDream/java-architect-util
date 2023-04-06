import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import CatDialog from "./components/dialog.vue";
import Vue from 'vue'
Vue.component(CatDialog.name, CatDialog);

createApp(App).use(router).mount('#app')
