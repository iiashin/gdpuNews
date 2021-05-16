<template>
    <div id="login">
        <div class="header">
            <img src="https://news.gdpu.edu.cn/dfiles/11295/public/home/img/16sy/xh.jpg">
            <span>新闻管理系统</span>
        </div>
        <div class="container">
            <div class="left">
                <img src="../assets/library.jpg">
            </div>
            <div class="right">
                <div class="userInput">
                    <span>Login</span>
                    <br>
                    <input type="text" placeholder="username" v-model="name">
                    <br>
                    <input type="password" placeholder="password" v-model="password">
                    <br>
                    <el-button type="primary" round @click="isLogin">sign in</el-button>
                </div>
                <a href="http://localhost:8080/home">游客进入</a>
            </div>
        </div>
    </div>
</template>

<script>
    import {request} from "../util/request";

    export default {
        name: "Login",
        data(){
            return{
                name:'',
                password:''
            }
        },
        methods:{
            //登录功能
            isLogin(){
                let params={
                    name:this.name,
                    password:this.password
                }
                request('post','isLogin',params).then(
                    msg=>{
                        if(msg===1){
                            localStorage.setItem("userLogin",true)
                            this.$router.push('/')
                            // window.location.href = "http://localhost:8080/";
                        } else if(msg===2){
                            localStorage.setItem("adminLogin",true)
                            this.$router.push('/admin')
                            // window.location.href = "http://localhost:8080/admin";
                        }else{
                            alert('账号密码错误')
                            this.name='';
                            this.password=''
                        }
                    }
                )
            }
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #login{
        .header{
            padding: 10px;
            img{
                vertical-align: middle;
            }
            span{
                color: white;
                font-size: 36px;
                font-weight: 800;
                display: inline-block;
                vertical-align:middle;
            }
        }
        background-image: linear-gradient(to left top, white,white, #76b2f1);
        height: 100%;
        .container{
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            margin: auto;
            height: 500px;
            width: 900px;
            box-shadow: -1px -1px 5px black;
            display: flex;
            .left{
                width: 60%;
                height: 100%;
                img{
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                }
                h2{
                    /*position: absolute;*/
                    text-align: center;
                    /*top: 50%;*/


                }
            }
            .right{
                background-color: white;
                height: 100%;
                width: 40%;
                position: relative;
                .userInput{
                    width: 100%;
                    text-align: center;
                    position: absolute;
                    top: 50%;
                    transform: translateY(-50%);
                    span{
                        font-weight: bold;
                        font-family: Georgia;
                        font-size: 22px;
                    }
                    input{
                        outline: none;
                        margin: 15px 0;
                        height: 30px;
                        width: 70%;
                        border-top-width: 0;
                        border-left-width: 0;
                        border-right-width: 0;
                        border-bottom-width: 2px;
                        font-size: 15px;
                    }
                    input:focus{
                        border-bottom-color: #76b2f1;
                    }
                    button{
                        width: 70%;
                    }
                }
                a{
                    position: absolute;
                    bottom: 10px;
                    text-decoration: none;
                    right: 10px;
                }
            }
        }
    }
</style>