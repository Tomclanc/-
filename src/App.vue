<template>
  <div id="app">
    <div class="header">
      <img src="./assets/logo.png" alt="Logo" class="logo">
      <!-- 登录按钮，未登录时显示 -->
      <button v-if="!isLoggedIn && $route.name !== 'register'" @click="goToLogin" class="login-button">登录</button>
      <!-- 退出登录按钮，登录后显示 -->
      <button v-if="isLoggedIn" @click="logout" class="logout-button">退出登录</button>
    </div>
    <!-- 根据当前路由判断是否显示搜索框，不在登录或注册页面显示 -->
    <div class="search-container" v-if="$route.name !== 'login' && $route.name !== 'register'">
      <input type="text" v-model="searchQuery" @keyup.enter="search" :placeholder="$t('search')">
      <HelloWorld v-if="searchQuery" :searchQuery="searchQuery" />
    </div>
    <div v-if="isLoggedIn">
      <p>欢迎，{{ username }}，来到我们的应用！</p>
    </div>
    <router-view></router-view>
  </div>
</template>


<script>
import HelloWorld from './components/HelloWorld.vue';
import { mapGetters } from 'vuex';

export default {
  name: 'App',
  components: {
    HelloWorld,
  },
  data() {
    return {
      searchQuery: '',
    };
  },
  computed: {
    ...mapGetters(['isLoggedIn', 'username']),
  },
  methods: {
    search() {
      // 搜索逻辑
    },
    goToLogin() {
      this.$router.push({ name: 'login' });
    },
    logout() {
      this.$store.dispatch('logout').then(() => {
        this.$router.push({ name: 'login' }); // 跳转到登录页面
      });
    },
  }
};
</script>



<style>
.header {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px;
}

.login-button {
  position: absolute; /* 使用绝对定位 */
  top: 10px; /* 距顶部的距离，根据需要调整 */
  right: 10px; /* 距右侧的距离，根据需要调整 */
}

.logout-button {
  position: absolute; /* 使用绝对定位 */
  top: 15px; /* 距顶部的距离，根据需要调整 */
  right: 10px; /* 距右侧的距离，根据需要调整 */
}

.search-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start; /* 改为flex-start使内容顶部对齐 */
  height: 80vh; /* 减小高度让容器不再占满整个视窗高度 */
  padding-top: 5vh; /* 添加顶部内边距将内容向下推，从而相对于容器顶部向上 */
}

.logo {
  width: 180px;
  margin-top: 100px;
  margin-bottom: 10px;
}

input[type="text"] {
  width: 50%;
  padding: 15px;
  font-size: 18px;
  border-radius: 20px; /* 设置圆角的大小 */
  border: 1px solid #ccc; /* 可选：添加边框 */
}


/* 针对深色模式的搜索框样式 */
@media (prefers-color-scheme: dark) {
  input[type="text"] {
    background-color: #333; /* 深色模式下的背景色 */
    color: #fff; /* 深色模式下的文字色 */
    border: 1px solid #555; /* 适合深色模式的边框色 */
  }
}

</style>
