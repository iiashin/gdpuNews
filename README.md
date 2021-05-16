# vuenews
仿校园新闻做的一个校园新闻系统 vue+elementUi+jdbc+servlet
后台代码需运行tomcat  java文件夹下
## Project setup
```
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
