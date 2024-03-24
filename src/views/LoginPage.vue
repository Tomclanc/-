<template>
  <div class="login-container">
    <h2>登录</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <div>
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
      <button type="submit">登录</button>
    </form>
    <!-- 添加导航到注册页面的按钮或链接 -->
    <p>没有账户？<router-link to="/register">注册一个新账户</router-link></p>
  </div>
</template>


<script>
import axios from 'axios';
import { mapActions } from 'vuex';

export default {
  name: 'LoginPage',
  data() {
    return {
      username: '',
      password: '',
      errorMessage: '',
    };
  },
  methods: {
    ...mapActions(['login']), // 使用Vuex actions
    handleLogin() {
      axios.post('https://localhost:8443/login', {
        username: this.username,
        password: this.password,
      })
      .then(response => {
        // 假设后端返回的格式为 { message: '登录成功', username: '用户名' }
        if (response.data.message === '登录成功') {
          // 调用Vuex的login action更新状态
          this.login({ isLoggedIn: true, username: response.data.username });
          // 可能还需要跳转到主页或其他页面
          this.$router.push('/');
        } else {
          this.errorMessage = '登录失败，请检查您的用户名和密码。';
        }
      })
      .catch(error => {
        console.error('登录请求失败：', error);
        this.errorMessage = '登录过程中出现问题，请稍后再试。';
      });
    }
  },
};
</script>



<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.error-message {
  color: red;
  margin: 10px 0;
}

/* 密码框的样式 */
input[type="password"] {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  /* 添加更多样式来美化密码框，比如背景色、字体颜色等 */
}

/* 针对深色模式的密码框样式 */
@media (prefers-color-scheme: dark) {
  input[type="password"] {
    background-color: #333; /* 深色模式下的背景色 */
    color: #fff; /* 深色模式下的文字色 */
    border: 1px solid #555; /* 适合深色模式的边框色 */
  }
}

/* 密码框的样式 */
input[type="password"] {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-left: 35px; /* 向右移动密码框 */
  /* 添加其他样式 */
}
</style>

