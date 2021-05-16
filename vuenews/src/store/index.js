import Vue from 'vue'
import Vuex from 'vuex'
import home from './home/index'
import list from './list/index'
import admin from './admin/index'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    home:home,
      list,
      admin
  },
  getters:{

  }
})
