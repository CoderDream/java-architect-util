const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        port: 3000, // 端口
        proxy: {
            '/api': {//请求称号
                target: 'https://v0.yiketianqi.com/api?unescape=1&version=v9&appid=11956961&appsecret=2cU8kdgH&cityid=101200111', //请求的接口
                changeOrigin: true,//允许跨域
                pathRewrite: {
                    '^/api': '' // 调用时用 api 替代根路径
                }
            }
        }
    },
  lintOnSave: false // 关闭eslint 校验
})
