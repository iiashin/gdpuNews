<template>
    <div id="homePage">
        <div class="content">
            <span style="display: inline-block;margin-top: 10px">当前日期:{{nowDate}}</span>
            <!--轮播图-->
            <div class="my-list-first">
                <div class="banner">
                    <el-carousel indicator-position="outside">
                        <el-carousel-item v-for="item in bannerMsg" :key="item.newsId">
                            <div class="title">{{ item.title }}</div>
                            <img :src="item.img_address">
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <!--第一个单独取出来-->
                <div class="first-item">
                    <el-card class="box-card" shadow="hover">
                        <div slot="header" class="clearfix">
                            <!--v-if,防止异步请求一开始取不到值-->
                            <span v-if="newsNav[0]">{{newsNav[0].typeName}}</span>
                            <el-button style="float: right; padding: 3px 0" type="text" @click="$router.push(`/list/${newsNav[0].abbr}`)">MORE</el-button>
                        </div>
                        <div v-for="(list,index) in articleMsg.filter((msg)=>{return msg.type_id===1}).slice(0,7)" :key="list.newsId" class="text item" @click="$router.push(`/detail/${list.newsId}`)">
                            {{list.title}}
                        </div>
                    </el-card>
                </div>
            </div>
            <!--剩余的，用flow浮动定位-->
            <div class="my-list-other" v-for="item in newsNav.slice(1)" :key="newsNav.id">
                <div class="my-list-item">
                    <el-card class="box-card" shadow="hover">
                        <div slot="header" class="clearfix">
                            <span>{{item.typeName}}</span>
                            <el-button style="float: right; padding: 3px 0;cursor: pointer" type="text" @click="$router.push(`/list/${item.abbr}`)">MORE</el-button>
                        </div>
                        <div v-for="list in articleMsg.filter((msg)=>{return msg.type_id===item.id}).slice(0,6)" :key="list.newsId" class="text item" @click="$router.push(`/detail/${list.newsId}`)">
                            {{list.title}}
                        </div>
                    </el-card>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import {request} from "../util/request.js"
    export default {
        name: "",
        data(){
            return{
                articleMsg:[],  //文章信息，主要拿标题
                bannerMsg:[],   //轮播图信息
                nowDate:''   //当前日期
            }
        },
        computed:{
            ...mapState('home',['newsNav'])   //获取新闻种类，用于显示种类列表
        },
        methods:{
          //获取日期
          getDate(){
              var myDate = new Date();
              let year=myDate.getFullYear();
              let month=myDate.getMonth()+1;
              let date=myDate.getDate();
              //获取数字对应的大写
              const map={
                  '0':'日',
                  '1':'一',
                  '2':'二',
                  '3':'三',
                  '4':'四',
                  '5':'五',
                  '6':'六'
              }
              let day1=map[myDate.getDay()];
              this.nowDate=`${year}年${month}月${date}日 星期${day1}`
          }
        },
        created(){
            //获取所有新闻信息，显示文章标题
            request('get','newsServlet').then(
                res=>{
                    this.articleMsg=res
                    this.bannerMsg=res.slice(0,4)
                    console.log(this.articleMsg)
                }
            )

        },
        mounted(){
            //显示当前日期
            this.getDate();
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #img{
        height:100px;
        width: 100px;
        display: inline-block;
    }
    #homePage{
        overflow: hidden;       //父元素防止塌陷
        min-width:1300px;
        .content{
            background-color:white;
            padding:0 10%;
            /deep/.el-card__header{
                background-color: #a32b34 !important;
                color: white;
                font-weight: bold;
            }
            .my-list-first{
                margin-top: 15px;
                display: flex;
                justify-content: space-between;
                .banner{
                    width: 60%;
                    /*height: 400px;*/
                    position: relative;
                    .el-carousel__item img {
                        width: 100%;
                        height: 100%;
                        object-fit: cover;
                    }

                    /*.el-carousel__container{*/
                        /*height: 400px !important;*/
                    /*}*/
                    .el-carousel__item:nth-child(2n) {
                        background-color: #99a9bf;
                    }
                    /deep/.el-carousel__container{
                        height: 350px!important;
                    }
                    .el-carousel__item:nth-child(2n+1) {
                        background-color: #d3dce6;
                    }
                    .title{
                        position: absolute;
                        bottom: 10px;
                        background-color: grey;
                        opacity: 0.75;
                        right: 0;
                        width: 60%;
                        height: 40px;
                        color: white;
                    }
                }
                .first-item{
                    /*float: left;*/
                    width: 35%;
                    height: 350px;
                    box-sizing: border-box;
                    background-color: antiquewhite;
                }
            }
            .my-list-other{
                width: 100%;
                .my-list-item{
                    float: left;
                    height: 284px;
                    width: 32%;
                    box-sizing: border-box;
                    margin-right: 13px;
                    margin-top: 15px;
                    background-color: antiquewhite;
                }
            }
        }

    }
    .box-card{
        width: 100% !important;
        height: 100%;
    }
    .text {
        font-size: 15px !important;
        cursor: pointer;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }
    .el-button--text{
        color: white;
        font-weight: bold;
    }
    .text{
        font-size: 18px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow:ellipsis;
    }
    .text:hover{
        background-color: #dfe9f1;
        color: #a32b34 !important;
    }

</style>