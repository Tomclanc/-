<template>
  <div class="login-container">
    <h2>登录</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="credentials.username" required>
      </div>
      <div class="form-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="credentials.password" required>
      </div>
      <button type="submit">登录</button>
    </form>
  </div>
</template>

  
<script>
import axios from 'axios';
import { useRouter } from 'vue-router'; // 确保你使用的Vue版本支持Composition API
import { useStore } from 'vuex'; // 导入useStore


export default {
  name: 'LoginModal',
  setup() {
    const router = useRouter();
    const store = useStore(); // 使用useStore获取Vuex store的实例
    return { router, store }; // 将 store 也一并返回
  },
  data() {
    return {
      credentials: {
        username: '',
        password: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    login() {
      axios.post('https://localhost:8443/login', this.credentials)
        .then(response => {
          // 登录成功后，使用 store 更新状态
          this.store.dispatch('login', { username: response.data.username });
          localStorage.setItem('isLoggedIn', 'true'); // Optional: 如果你还想在localStorage中保持状态
          localStorage.setItem('username', response.data.username); // Optional
          this.router.push({ name: 'home' });
        })
        .catch(error => {
          console.error('登录请求失败', error);
          // 假设登录失败后端返回的格式为 {message: "失败原因"}
          this.errorMessage = error.response && error.response.data.message ? error.response.data.message : '登录请求失败，请稍后再试。';
          alert(this.errorMessage); // 使用alert在界面上显示错误消息
        });
    }
  }
}
</script>

  
<style scoped>
.login-container {
  /* 样式自定义 */
  max-width: 300px;
  margin: auto;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 5px;
}

input[type="text"],
input[type="password"] {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  padding: 10px;
  border: none;
  background-color: #007bff;
  color: white;
  cursor: pointer;
  border-radius: 5px;
}
</style>