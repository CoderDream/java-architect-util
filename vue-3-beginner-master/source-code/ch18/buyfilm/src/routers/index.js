import Vue from 'vue'
import Router from 'vue-router'
import movieRoter from './movie'
import cinemaRoter from './cinema'
import mineRoter from './mine'
// ./router 代表 touter 文件夹里的 index文件，其它文件要加名字。
Vue.use(Router)
// export defalut 是输出，相当于把接口暴露在外部，共所有文件来调用。
export default new Router({
    // mode可选参数：
    // hash: 默认为hash, 如果使用hash的话，页面的地址就会加上 # 号就会比较不好看，如我们的地址变成如下：http://localhost:8080/#/
    // history: 我们使用history的话，那么访问页面的时候就和平常一样，不带井号的；
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        movieRoter,
        cinemaRoter,
        mineRoter,
        // 重定向：当上面路由都不匹配的情况下，跳转到movie页面
        {
            path:'/*',
            redirect:'/movie'
        }
    ]
})
