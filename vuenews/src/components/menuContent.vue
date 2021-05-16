<template>
    <div id="memuContent">
        <div class="content">
            <div class="left">
                <el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item v-if="newsList[0]">{{newsList[0].typeName}}</el-breadcrumb-item>
                </el-breadcrumb>
                <!--当获取新闻数大于0时，即不是查询新闻-->
                <ul v-if="newsList.length>0">
                    <li v-for="list in newsList" :key="list.newsId" @click="$router.push(`/detail/${list.newsId}`)">{{list.title}}   {{list.day.slice(0,10)}}</li>
                </ul>
                <div v-else-if="$route.params.key&&newsList.length===0">找不到和您查询的相符的新闻</div>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        name: "",
        computed:{
            ...mapState('list',[
                'newsList'
            ])
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #memuContent{
        min-width:1300px;
        .content{
            padding:10px 10%;
            display: flex;
            justify-content: space-between;
            .left{
                border: 1px solid #a32b34;
                width: 73%;
                padding: 15px;
                position: relative;
                min-height: 450px;
                .el-breadcrumb{
                    border-bottom:2px solid #a32b34;
                    color: black;
                    padding-bottom: 3px;
                    font-size: 18px!important;
                }
                ul{
                    li:before{     //加个伪类元素使点变色
                        content: '.';
                        color: #a32b34;
                        font-size: 30px;
                    }
                    li{
                        font-size: 16px;
                        list-style: none;
                        cursor:pointer
                    }
                    li:hover{
                        background-color: #dfe9f1;
                    }
                }
                .el-pagination{
                    position: absolute;
                    bottom: 0px;
                    left: 50%;
                    transform: translateX(-50%);

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