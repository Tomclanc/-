import { createI18n } from 'vue-i18n';

const messages = {
  'en': {
    search: 'Search',
    author: 'Author',
    title: 'Title',
    isbn: 'ISBN',
    quantity: 'Quantity',
    libraryManagementSystem: 'Library Management System',
    // 添加其他美式英语翻译
  },
  'zh-CN': {
    search: '搜索',
    author: '作者',
    title: '书名',
    isbn: '编号',
    quantity: '数量',
    libraryManagementSystem: '图书馆管理系统',
    // 添加其他简体中文翻译
  },
  'ja': {
    search: '検索',
    author: '著者',
    title: 'タイトル',
    isbn: 'ISBN',
    quantity: '数量',
    libraryManagementSystem: '図書館管理システム',
    // 添加其他日语翻译
  },
  // 添加其他语言的翻译...
};

let userPreferredLang = navigator.language || navigator.userLanguage;

// 更精确地处理简体中文和其他语言匹配
if (userPreferredLang.startsWith('zh')) {
  userPreferredLang = 'zh-CN';
} else if (!Object.keys(messages).includes(userPreferredLang)) {
  userPreferredLang = 'zh-CN'; // 设置默认语言为简体中文
}

const i18n = createI18n({
  locale: userPreferredLang, // 使用检测到的语言或默认语言
  fallbackLocale: 'zh-CN', // 如果找不到对应的翻译，则回退到简体中文
  messages,
});

export default i18n;
