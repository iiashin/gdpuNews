import axios from "axios"
import {request} from '../../util/request.js'
export default {
    namespaced: true,
    state: {
        newsNav:''   //新闻种类
    },
    mutations: {
        set_newsNav_data(state,val){
            state.newsNav=val;
        }
    },
    actions: {
        //获取新闻种类
        get_newsNav_data({commit}){
            request('get','typeServlet').then(res=>{
                    commit('set_newsNav_data',res)
                }
            )
        }
    },
    getters:{

    }
}