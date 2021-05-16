import axios from "axios"
import {request} from "../../util/request";
export default {
    namespaced: true,
    state: {
        newsList:''     //选中类别的新闻列表
    },
    mutations: {
        set_newsList_data(state,val){
            state.newsList=val;
        }
    },
    actions: {
        get_newsList_data({commit},data){
            if(data.abbr){
                request('get','selNewsByAbbr',{abbr:data.abbr}).then(
                    res=>{
                        commit('set_newsList_data',res)
                    }
                )
            }else{
                request('get','selNewsByAbbr',{key:data.key}).then(
                    res=>{
                        console.log(res)
                        commit('set_newsList_data',res)
                    }
                )
            }
        }
    },
    getters:{

    }
}