import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import axios from "axios"
// 挂载到vue原型链上
Vue.prototype.axios = axios;

import qs from 'qs'
//挂载到原型链
Vue.prototype.$qs = qs;



//引入jquery
import $ from "jquery";

import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)

import { Button,Menu,MenuItem,Submenu,Carousel, CarouselItem,Card,Breadcrumb,BreadcrumbItem,Pagination,MenuItemGroup,Tabs,TabPane,Upload,
         Input,Select,Option,Table,TableColumn, Dropdown, DropdownMenu, DropdownItem,Dialog} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.component(Button.name, Button);
Vue.use(Menu);
Vue.use(MenuItem);
Vue.use(Submenu);
Vue.use(Carousel);
Vue.use(CarouselItem);0
Vue.use(Card)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Pagination)
Vue.use(MenuItemGroup)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Upload)
Vue.use(Input)
Vue.use(Select)
Vue.use(Option)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Dialog)
// Vue.prototype.$alert = MessageBox.alert

Vue.config.productionTip = false

//路由跳转前需要判断
router.beforeEach(function (to,from,next) {
    if(to.meta.userLogin){    //当跳转的路由需要判断是不是登录
        if(window.localStorage.getItem('userLogin')){
            next()
        }else {
            alert("请先登入！")
            next('/login')
        }
    }else if(to.meta.adminLogin){
        if(window.localStorage.getItem('adminLogin')){
            next()
        }else {
            alert("请先登入！")
            next('/login')
        }
    } else {
        next()
    }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
