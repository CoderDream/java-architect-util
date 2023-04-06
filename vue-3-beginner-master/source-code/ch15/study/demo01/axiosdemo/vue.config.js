const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {//请求称号
        target: 'http://127.0.0.1:10086', //请求的接口
        changeOrigin: true,//允许跨域
        pathRewrite: {
          '^/api': '/'
        }
      }
    }
  }
})
