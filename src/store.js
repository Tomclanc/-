import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
      isLoggedIn: false,
      username: '', // 在 state 中添加 username 字段
    };
  },
  mutations: {
    // 更新 mutation 以处理包含 isLoggedIn 和 username 的对象
    setLoginState(state, { isLoggedIn, username }) {
      state.isLoggedIn = isLoggedIn;
      state.username = username; // 确保这里正确设置了 username
    }
  },
  actions: {
    // 更新 action 以接收包含 isLoggedIn 和 username 的 payload
    login({ commit }, payload) {
      commit('setLoginState', { isLoggedIn: true, username: payload.username });
    },
    logout({ commit }) {
      // 登出时清除 username
      commit('setLoginState', { isLoggedIn: false, username: '' });
    }
  },
  getters: {
    // 已经有 isLoggedIn 和 username 的 getters
    isLoggedIn: state => state.isLoggedIn,
    username: state => state.username,
  }
});

export default store;
