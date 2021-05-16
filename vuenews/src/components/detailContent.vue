<template>
    <div id="detailContent">
        <div class="content">
            <div class="left">
                <el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item v-if="passage[0]" :to="{ path:`/list/${passage[0].abbr}`}">{{passage[0].typeName}}</el-breadcrumb-item>
                    <el-breadcrumb-item>正文</el-breadcrumb-item>
                </el-breadcrumb>
                <!--v-if防止渲染时异步请求还没完成-->
                <div class="passage" v-if="passage.length!==0">
                    <h2>{{passage[0].title}}</h2>
                    <span>{{passage[0].day}}    {{passage[0].author}}   浏览量:{{passage[0].count}}</span>
                    <div class="pageContent" v-html="passage[0].content" ref="pageContent"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {request} from "../util/request";
    export default {
        data(){
            return{
                passage:''
            }
        },
        name: "",
        created(){
            //获取当前新闻信息
            request('get','newsServlet',{newsId:this.$route.params.newsId}).then(
                res=>{
                    this.passage=res
                }
            )
        },
        updated(){
            //获取dom元素替换图片
            if(this.passage[0].img_address)
                $('.pageContent img')[0].src=this.passage[0].img_address
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #detailContent {
        min-width: 1300px;
        .content {
            padding: 10px 10%;
            display: flex;
            justify-content: space-between;
            .left {
                border: 1px solid #a32b34;
                width: 73%;
                padding: 15px;
                position: relative;
                .el-breadcrumb{
                    border-bottom:2px solid #a32b34;
                    color: black;
                    padding-bottom: 3px;
                    font-size: 18px!important;
                }
                .passage{
                    h2,span{
                        text-align: center;
                    }
                    span{
                        display: block;
                        margin-bottom: 8px;
                        color: grey;

                    }
                    /*text-align: center;*/
                    width: 100%;
                    /deep/ img{
                        width: 100%;
                        height: auto;
                    }
                    .pageContent{
                        font-size: 18px;
                        font-family: 楷体;
                        color: rgb(51,51,51);
                    }
                }
            }
            .right{
                /deep/.el-card__header{
                    background-color: #a32b34 !important;
                    color: white;
                    font-weight: bold;
                }
                .text {
                    font-size: 14px;
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
                    width: 250px;
                    height: 260px;
                    margin-bottom: 20px;
                }
            }
        }
    }
</style>