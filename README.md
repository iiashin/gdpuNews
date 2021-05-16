# gupuNews
仿校园新闻做的一个校园新闻系统 vue+elementUi+jdbc+servlet   

[模仿界面](https://news.gdpu.edu.cn/)

1. gdpuNews 后台代码，需用idea运行，配置tomcat servlet+jdbc
2. vuenews  前端代码 vue+vuex+elementUi,以下解释前端代码运行

>想法是vue-cli开启一个端口，tomcat开启一个，通过跨域请求信息，存储在数据库中

## vuenews

### Project setup
```
cd vuenews
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

### 目录树
```js
├─babel.config.js
├─package-lock.json
├─package.json
├─README.md       
├─vue.config.js
├─src
|  ├─App.vue
|  ├─main.js             
|  ├─views
|  |   ├─AddNews.vue     //添加新闻界面
|  |   ├─Admin.vue       //管理员界面
|  |   ├─Detail.vue     //新闻详细界面
|  |   ├─Home.vue       //主界面
|  |   ├─List.vue      //新闻列表界面
|  |   └Login.vue      //登录界面
|  ├─util
|  |  └request.js    //封装axios
|  ├─store          //vuex
|  |   ├─index.js 
|  |   ├─list
|  |   |  └index.js
|  |   ├─home
|  |   |  └index.js
|  |   ├─admin
|  |   |   └index.js
|  ├─router
|  |   └index.js
|  ├─components       //主界面的各种组件页面
|  |     ├─AdminAddNews.vue   //添加新闻
|  |     ├─AdminCheck.vue     //审核新闻
|  |     ├─AdminHome.vue      //管理员主界面
|  |     ├─AdminPublished.vue  //已发新闻列表
|  |     ├─AdminWriter.vue    //新闻作者列表
|  |     ├─detailContent.vue    //详细新闻部分
|  |     ├─dialog.vue         //自写弹出框
|  |     ├─first.vue         //主界面页面
|  |     ├─menuContent.vue    //分类新闻页面
|  |     ├─myFooter.vue     //用户界面底部
|  |     └myHeader.vue     //用户界面头部
|  ├─assets              //静态文件
├─public
|   ├─favicon.ico
|   └index.html
```

#### 实现功能
1. 浏览阅读新闻
2. 根据关键字搜索新闻
3. 添加新闻
4. 管理员管理新闻
5. 管理员管理用户


#### 项目截图
![登录界面](https://github.com/iiashin/gdpuNews/blob/main/picture/login.png)
![用户主界面](https://github.com/iiashin/gdpuNews/blob/main/picture/home.png)
![详细界面](https://github.com/iiashin/gdpuNews/blob/main/picture/detail.png)
![管理员界面1](https://github.com/iiashin/gdpuNews/blob/main/picture/admin1.png)
![管理员界面2](https://github.com/iiashin/gdpuNews/blob/main/picture/admin.png)