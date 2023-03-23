// movie路由
export default {
    path:'/movie',
    //按需载入的方式
    component:()=>import('../../views/Movie'),
    // 二级路由，使用children进行配置
    children:[
        {
            path:'city',
            component:()=>import('../../components/City')
        },
        {
            path:'nowPlaying',
            component:()=>import('../../components/NowPlaying')
        },
        {
            path:'comingSoon',
            component:()=>import('../../components/ComingSoon')
        },
        {
            path:'search',
            component:()=>import('../../components/Search')
        },
        // 重定向：当路径为/movie时，重定向到/movie/nowPlaying路径
        {
            path:'/movie',
            redirect:'/movie/nowPlaying'
        }
    ]
}