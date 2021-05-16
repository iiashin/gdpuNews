<template>
    <div id="header">
        <!--当前登录情况-->
        <div class="line">
            <a @click.stop="loginOut">{{isLogin?"退出登录":"登录"}}</a>
            <span>当前:{{isLogin?"用户":"游客"}}</span>
        </div>
        <div class="headerTop">
            <div class="left">
                <img src="https://news.gdpu.edu.cn/dfiles/11295/public/home/img/16sy/xh.jpg">
                <span>新闻网</span>
            </div>
            <div class="right">
                <img src="../assets/wechat.png" class="wechat_icon" @click="dialogTableVisible = true">
                <!--弹出框-->
                <el-dialog title="WeChat" :visible.sync="dialogTableVisible" width="410px">
                    <img src="../assets/ashin.png">
                </el-dialog>
                <!--点击跳转发布界面-->
                <img src="../assets/add.png" class="add_icon" @click="$router.push('/addNews')" title="发布文章">
                <!--搜索触发方法-->
                <input type="text" v-model="key" @keyup.enter="searchArticle">
                <img src="../assets/search.png" class="search" @click="searchArticle">
            </div>
        </div>
        <div class="headerNav">
            <el-menu class="el-menu-demo" mode="horizontal">
                <el-menu-item>
                    <router-link to="/home">首页</router-link>
                </el-menu-item>
                <el-menu-item v-for="item in newsNav" :key="newsNav.id">
                    <router-link :to="`/list/${item.abbr}`" v-if="item!==null">{{item.typeName}}</router-link>  <!--展示新闻种类-->
                </el-menu-item>
            </el-menu>
        </div>
    </div>
</template>

<script>
    import {mapState,mapActions} from 'vuex'
    export default {
        data() {
            return {
                dialogTableVisible:false,
                isLogin:false,
                key:''   //搜索关键字
            };
        },
        computed:{
            //这里的...不是省略号了,是对象扩展符   +对象缩写
            ...mapState('home',[
                'newsNav'
            ])
        },
        methods: {
            //获取新闻种类
            ...mapActions('home', [
                'get_newsNav_data'
            ]),
            //用户登入或登出
            loginOut(){
                if(localStorage.getItem("userLogin"))
                    localStorage.setItem("userLogin",'')
                this.$router.push('/login')
            },
            //搜索关键字
            searchArticle(){
                this.$router.push(`/list1/${this.key}`)
            }
        },
        created(){
            this.get_newsNav_data();
            let flag=localStorage.getItem("userLogin")||'';   //获取是否是用户登入
            this.isLogin=flag;          //根据这个设置是否登入登出
        }

    }
</script>

<style scpoed lang="scss" type="text/scss" >
    #header{
        min-width:1300px;     /*限制页面缩小的最小宽度*/
        .line{
            width: 100%;
            background-color: #6b0c14;
            height: 30px;
            padding:5px 10%;
            position: relative;
            box-sizing: border-box;
            span,a{
                display:inline-block;
                color: white;
                font-size: 12px;
                margin-right: 10px;
            }
            span,a{
                float: right;
            }
            a{
                cursor: pointer;
            }
        }
        .headerTop{
            height: 136px;
            background-color:rgb(163,43,52);
            display: flex;
            justify-content:space-between;
            padding:0 10%;
            color: white;
            /*width: 100%;*/
            align-items:center;     //垂直居中
            box-sizing:border-box;
            .left span{
                font-size: 36px;
                font-weight: 800;
                display: inline-block;
                vertical-align:  middle;  ////定义行内元素的基线相对于该元素所在行的基线的垂直对齐。middle元素放在父元素的中部
            }
            .left img{
                vertical-align:  middle;   //定义行内元素的基线相对于该元素所在行的基线的垂直对齐。middle元素放在父元素的中部
            }
            .right input{
                height: 28px;
                width: 230px;
                border-radius: 10px;
            }
            .right {
                position: relative;
                .search{
                    position: absolute;
                    right:5px;
                    top: 15px;
                    height: 20px;
                }
                .wechat_icon{
                    height: 30px;
                    position: relative;
                    top: 10px;
                    right: 20px;
                }
                .add_icon{
                    height: 30px;
                    position: relative;
                    top: 10px;
                    right: 10px;
                }
                .user{
                    position: absolute;
                    right: 20px;
                    bottom: -40px;
                }
            }
        }
        .headerNav{
            font-size:16px;
            color: white !important;
            padding:0 10%;
            box-sizing:border-box;
            background-color: rgb(153,102,51) !important;
            ul,li{
                background-color: rgb(153,102,51) !important;
            }
            li:hover{
                background-color: rgb(153,0,0) !important;
            }
            .el-menu--horizontal>.el-menu-item{
                color: white !important;
                border-bottom: 2px solid;
                font-size: 16px;
                font-weight: bold;
            }
            a{
                text-decoration: none;
            }
        }

    }

</style>