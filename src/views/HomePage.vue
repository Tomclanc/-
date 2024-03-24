<template>
  <div class="home-container">
    <h1>欢迎来到我们的应用!</h1>
    <p v-if="isLoggedIn">这是主页，{{ username }}，你可以在这里找到各种信息和功能。</p>
    <p v-else>这是主页，你可以在这里找到各种信息和功能。</p>
    <!-- 你可以在这里添加更多的内容，比如链接到其他页面的导航、展示一些数据等 -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'HomePage',
  computed: {
    // 通过 Vuex 的 getters 来获取
    ...mapGetters(['isLoggedIn', 'username']),
  },
  mounted() {
    console.log("主页组件挂载：");
    console.log("当前登录状态（通过computed）：", this.isLoggedIn);
    console.log("当前用户名（通过computed）：", this.username);
  },
  watch: {
    // 监听 isLoggedIn 和 username 的变化
    isLoggedIn(newVal) {
      console.log("登录状态变化，新值为：", newVal);
    },
    username(newVal) {
      console.log("用户名变化，新值为：", newVal);
    }
  }
};
</script>

<style scoped>
.home-container {
  max-width: 600px;
  margin: auto;
  padding: 20px;
  text-align: center;
}
</style>
