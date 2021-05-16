import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Admin from '../views/Admin'
import List from '../views/List'
import Detail from '../views/Detail'
import Login from '../views/Login'
import AddNews from '../views/AddNews'
Vue.use(VueRouter)

const routes = [
    //  默认路径
    {
        path:'/',
        redirect:'/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
    },
    {
        path: '/admin',
        name: 'Admin',
        component: Admin,
        meta:{
            adminLogin:true
        }
    },
    {
        path: '/list/:abbr',
        name: 'List',
        component: List
    },
    {
        path: '/list1/:key',
        name: 'List',
        component: List
    },
    {
        path: '/detail/:newsId',
        name: 'Detail',
        component: Detail
    },
    {
        path:'/addNews',
        name:'AddNews',
        component:AddNews,
        meta:{
            userLogin:true
        }
    },
    {
        path:'/login',
        name:'Login',
        component:Login
    },
    {path:'*',redirect:'/'}     //没有路由可匹配时，跳转默认页面
]
const originalPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
