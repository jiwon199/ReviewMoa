import Vue from 'vue'
import Vuex from 'vuex'
import reviewStore from "@/store/modules/reviewStore";
import memberStore from "@/store/modules/memberStore";

// 데이터 유지를 위해 import
import createPersistedState from 'vuex-persistedstate';
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  
  modules: {reviewStore, memberStore},
  plugins: [ 
    createPersistedState({
      paths: ["reviewStore","memberStore"]
    })
  ],
})
