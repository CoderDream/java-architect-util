<template>
  <div>
    <h1>商品名称：{{ name }}</h1>
    <h1>商品的最新价格：{{ price }}</h1>
    <button @click="handlerAdd()">涨价</button>
    <button @click="handlerSub()">降价</button>
    <button @click="handlerAddasy()">异步涨价(3秒后执行)</button>
    <button @click="handlerSubasy()">异步降价(3秒后执行)</button>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  computed: {
        name(){
            return this.$store.state.name
         },
        price(){
            return this.$store.state.price
         },
        getPrice(){
            return this.$store.getters.getterPrice
         }
     },
  methods: {
        handlerAdd(){
            this.$store.commit("addPrice",{
               num:100
            })
         },
        handlerSub(){
            this.$store.commit("subPrice",{
               num:100
            })
         },
        handlerAddasy(){
            this.$store.dispatch("addPriceasy")
         },
        handlerSubasy(){
            this.$store.dispatch("subPriceasy")
         },
     },
  }
</script>


