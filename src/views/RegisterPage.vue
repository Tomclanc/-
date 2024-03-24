<template>
    <div class="register-container">
      <h2>注册</h2>
      <form @submit.prevent="handleRegister">
        <div>
          <label for="username">用户名:</label>
          <input type="text" id="username" v-model="username" required>
        </div>
        <div>
          <label for="password">密码:</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
        <button type="submit">注册</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'RegisterPage',
    data() {
      return {
        username: '',
        password: '',
        errorMessage: '',
      };
    },
    methods: {
      handleRegister() {
        const userData = {
          username: this.username,
          password: this.password,
        };
        axios.post('https://localhost:8443/register', userData)
          .then(() => {
            alert('注册成功！');
            this.$router.push('/login'); // 假设你有一个登录页面
          })
          .catch(error => {
            console.error('注册请求失败：', error);
            this.errorMessage = error.response && error.response.data ? error.response.data : '注册过程中出现问题，请稍后再试。';
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .register-container {
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
  