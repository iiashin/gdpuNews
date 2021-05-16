<template>
    <div id="AdminCheck">
        <!--引入子组件查看内容-->
        <Dialog :showDialog="showDialog" v-on:func="showDialog=false">
            <div v-if="tableData" v-html="selContent">
            </div>
        </Dialog>
        <div class="table">
            <el-table  v-if="tableData"
                       :data="tableData"
                       stripe
                       style="width: 100%">
                <el-table-column
                        prop="newsId"
                        label="新闻编号"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="title"
                        label="标题"
                        width="320">
                </el-table-column>
                <el-table-column
                        prop="typeName"
                        label="类型"
                        width="240">
                </el-table-column>
                <el-table-column
                        prop="author"
                        label="作者"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="day"
                        label="日期"
                        width="180">
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button  size="small" type="primary" @click="open(scope.row.content,scope.row.img_address)">查看</el-button>
                        <el-button  size="small" type="success" @click="toPublish(scope.row.newsId)">通过</el-button>
                        <el-button  size="small" type="danger" @click="failPublish(scope.row.newsId)">驳回</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    import {mapState,mapActions} from 'vuex'
    import Dialog from '../components/dialog.vue'
    import {request} from "../util/request";
    export default {
        name: "AdminCheck",
        components:{
            Dialog
        },
        computed:{
            ...mapState('admin',['reviewNewsList'])
        },
        data(){
            return{
                tableData:'',
                showDialog:false,  //是否显示自定义组件
                selContent:''    //当前选中的内容
            }
        },
        created(){
            this.tableData=this.reviewNewsList
        },
        methods:{
            ...mapActions('admin',['get_reviewNewsList','get_newsList']),
            //查看当前信息
            open(content,img_address) {
                this.showDialog=true
                this.selContent=content
                this.$nextTick(()=>{
                    if (img_address)
                     $('#dialog img')[0].src=img_address
                })
            },
            //审核通过
            toPublish(newsId){
                request("get","delReviewNews",{newsId:newsId,isPublish:1}).then(
                    res=>{
                        if(res){
                            this.get_reviewNewsList()
                            this.get_newsList()
                        }
                    }
                )
            },
            //驳回
            failPublish(newsId){
                request("get","delReviewNews",{newsId:newsId,isPublish:2}).then(
                    res=>{
                        if(res){
                            this.get_reviewNewsList()
                        }
                    }
                )
            }
        },
        watch:{
            reviewNewsList:function () {
                this.tableData=this.reviewNewsList
            }
        }

    }
</script>

<style scoped lang="scss" type="text/scss">
    #AdminCheck{
        padding: 20px;
        width: 99%;
        height: 680px;
        box-shadow: 0px 2px 5px black;
        box-sizing: border-box;
        margin-top: 5px;
        position: relative;
        overflow: auto;
        #dialog{
          /deep/img{
              width: 100%;
              height: auto;
          }
        }
    }
</style>