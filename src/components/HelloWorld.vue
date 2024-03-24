<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <!-- 显示搜索结果 -->
    <ul v-if="books.length > 0">
      <li v-for="book in books" :key="book.id">
        {{ book.title }} {{ $t('author') }}: {{ book.author }} ({{ $t('isbn') }}: {{ book.isbn }}) - {{ $t('quantity') }}: {{ book.quantity }}
        <!-- 添加借书和还书按钮 -->
        <button @click="borrowBook(book.id)">借书</button>
        <button @click="returnBook(book.id)">还书</button>
      </li>
    </ul>
  </div>
</template>


<script>
import { mapGetters } from 'vuex';
import axios from 'axios';

export default {
  name: 'HelloWorld',
  props: {
    msg: String,
    searchQuery: String,
  },
  data() {
    return {
      books: [],
    };
  },
  computed: {
    // 使用 mapGetters 引入 isLoggedIn 和 username
    ...mapGetters(['isLoggedIn', 'username']),
  },
  methods: {
    searchBooks(query) {
      if (!query) {
        this.books = [];
        return;
      }
      const baseURL = `${window.location.protocol}//${window.location.hostname}:8443`;
      axios.get(`${baseURL}/books/search?query=${query}`)
        .then(response => {
          this.books = response.data;
        })
        .catch(error => {
          console.error("There was an error during the search:", error);
          this.books = [];
        });
    },
    borrowBook(bookId) {
      if (!this.isLoggedIn) {
        alert('请先登录。');
        this.$router.push('/login'); 
        return;
      }
 

      const borrowData = {
        bookId: bookId,
        username: this.username, // 使用 Vuex 中的用户名
      };

      axios.post('https://localhost:8443/borrowRecords/borrow', borrowData)
        .then(() => {
          alert('借书成功');
          // 更新书籍的显示状态或重新搜索以显示最新数据
          this.searchBooks(this.searchQuery);
        })
        .catch(error => {
          console.error("借书过程中发生错误:", error);
          alert('借书失败，详情请查看控制台日志。');
        });
    },
    returnBook(bookId) {
      // const username = localStorage.getItem('username'); // 直接使用用户名
      if (!this.isLoggedIn) { // 使用 Vuex 状态进行判断
        alert('请先登录。');
        this.$router.push('/login');
        return;
      }
      
      const returnData = {
        bookId: bookId,
        username: this.username, // 使用 Vuex 中的用户名
      };
      axios.post('https://localhost:8443/borrowRecords/return', returnData)
        .then(() => {
          alert('还书成功');
          // 更新书籍的显示状态或重新搜索以显示最新数据
          this.searchBooks(this.searchQuery);
        })
        .catch(error => {
          console.error("还书过程中发生错误:", error);
          alert('还书失败');
        });
    }
  },
  watch: {
    searchQuery(newVal, oldVal) {
      if (newVal !== oldVal) {
        this.searchBooks(newVal);
      }
    },
  },
  mounted() {
    if (this.searchQuery) {
      this.searchBooks(this.searchQuery);
    }
  }
}
</script>


<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>