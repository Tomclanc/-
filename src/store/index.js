import { createStore } from 'vuex';

export default createStore({
  state: {
    isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',
    username: localStorage.getItem('username') || ''
  },
  mutations: {
    setLoginState(state, { isLoggedIn, username }) {
      console.log('正在更新状态，用户名:', username); // 添加这行来确认用户名是否正确传入
      state.isLoggedIn = isLoggedIn;
      state.username = username;
      // 更新localStorage
      localStorage.setItem('isLoggedIn', isLoggedIn.toString());
      localStorage.setItem('username', username);
    }
  },
  actions: {
    login({ commit }, payload) {
      console.log('在action中接收到的用户名:', payload.username); // 确认action接收到的用户名
      console.log('登录操作收到的payload:', payload);
      commit('setLoginState', { isLoggedIn: true, username: payload.username });
    },
    logout({ commit }) {
      commit('setLoginState', { isLoggedIn: false, username: '' });
    }
  },
  getters: {
    isLoggedIn: state => state.isLoggedIn,
    username: state => state.username,
  }
});
