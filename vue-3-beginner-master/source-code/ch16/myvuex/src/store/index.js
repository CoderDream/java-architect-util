import { createStore } from 'vuex'

export default createStore({
  state: {
      name:"洗衣机",
      price:8600
  },
  getters: {
      getterPrice(state){
         return state.price+=300
      }
  },
  mutations: {
      addPrice(state,obj){
           return state.price+=obj.num;
       },
       subPrice(state,obj){
           return state.price-=obj.num;
       }
  },
  actions: {
      addPriceasy(context){
            setTimeout(()=>{
                context.commit("addPrice",{
                num:100
             })
            },3000)
       },
      subPriceasy(context){
            setTimeout(()=>{
                context.commit("subPrice",{
                num:100
             })
            },3000)
       }
  },
  modules: {
  }
})
