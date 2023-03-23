<template>
    <!-- <keep-alive>是Vue的内置组件，能在组件切换过程中将状态保留在内存中，防止重复渲染DOM。-->
    <keep-alive>
        <router-view></router-view>
    </keep-alive>
</template>
