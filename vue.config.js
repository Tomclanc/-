const fs = require('fs'); // 引入文件系统模块

const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8083,
    https: {
      key: fs.readFileSync('src/private-key.pem'),
      cert: fs.readFileSync('src/certificate.crt'),
      // 如果有CA证书，也可以使用ca属性指定
      // ca: fs.readFileSync('path/to/ca.pem'),
    },
  },
  pages: {
    index: {
      entry: 'src/main.js',
      template: 'public/index.html',
      filename: 'index.html',
      title: '图书馆管理系统',
    }
  }
});
