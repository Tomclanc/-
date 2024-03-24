import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/global.css';
import './registerServiceWorker';
import i18n from './i18n';
import store from './store'; // 正确引入store

const app = createApp(App)
  .use(store)  // 注册Vuex store
  .use(router) // 注册Vue Router
  .use(i18n);  // 注册i18n

console.log("Vuex store是否成功配置？", store);

app.mount('#app');
