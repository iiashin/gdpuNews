<template>
    <div id="AdminPublished">
        <Dialog :showDialog="showDialog" v-on:func="showDialog=false">
            <!--在dialog里的样式-->
            <div v-if="tableData" v-html="selContent"></div>
        </Dialog>
        <div class="search">
            <label>编号:</label><input type="text" v-model="id" />
            <label>标题:</label><input type="text" v-model="title" />
            <br>
            <label>类型:</label>
            <select v-model="type">
                <option :value="item.id" v-for="item in newsNav">{{item.typeName}}</option>
            </select>
            <label>作者:</label><input type="text" v-model="author"/>
            <button @click="handleSearch">搜索</button>
        </div>
        <div class="table">
            <el-table  v-if="tableData"
                    :data="data"
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
                        prop="count"
                        label="阅读量"
                        width="140">
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button  type="primary" size="small"  @click="open(scope.row.content,scope.row.img_address)">查看</el-button>
                        <el-button type="danger" size="small" @click="delNews(scope.row.newsId)" >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="block">
            <el-pagination
            layout="prev, pager, next"
            :total="tableData.length"
            :page-size="8"
            @current-change="handleCurrentChange">
            </el-pagination>
        </div>
    </div>
</template>
<script>
    import Dialog from '../components/dialog.vue'
    import {mapState,mapActions} from 'vuex'
    import {request} from "../util/request";
    export default {
        components:{Dialog},
        computed:{
            ...mapState('home',['newsNav']),
            ...mapState('admin',['newsList'])
        },
        data() {
            return {
                tableData: [],      //获取所有数据
                data:[],            //筛选出每页数据
                id:'',              //新闻id
                title:'',           //题目
                author:'',          //作者
                type:'',             //新闻类型
                showDialog:false,  //是否显示自定义组件
                selContent:'',    //当前选中的内容
                valData:1   //当前页数
            }
        },
        methods:{
            ...mapActions('home',['get_newsNav_data']),
            ...mapActions('admin',['get_newsList']),
            //跳转页数
            handleCurrentChange(val) {
                this.valData=val
                console.log(this.valData)
                this.data=this.tableData.slice((val-1)*8,val*8)
            },
            //搜索新闻
            handleSearch(){
                let params={
                    id:this.id,
                    title:this.title,
                    type_id:this.type,
                    author:this.author
                }
                request("get","searchMsg",params).then(
                    res=>{
                        //搜索后重新获取页面的值
                        this.tableData=res
                        //当前页面为第一页
                        this.data=this.tableData.slice(0,8)
                    }
                )
            },
            //查看新闻,按钮同时把内容和图片传进来
            open(content,img_address) {
                this.showDialog=true   //显示自定义组件
                this.selContent=content
                this.$nextTick(()=>{
                    if(img_address)
                        $('#dialog img')[0].src=img_address
                })
            },
            //删除新闻
            delNews(id){
                request("get","delNews",{newsId:id}).then(
                    res=>{
                        if(res){
                            this.get_newsList()
                            //当前页数，删除新闻后还是在当前页数
                            this.handleCurrentChange(this.valData)
                        }
                    }
                )
            }
        },
        watch:{
            //当newsList改变，增加或者删除新闻，就会重新获取一次
            newsList:function (val) {
                this.tableData=val
                this.data=this.tableData.slice((this.valData-1)*8,this.valData*8)   //跳转当前页数
            }
        },
        created(){
            //在admin.vue已经create中初始化值，此时是获取
            this.tableData=this.newsList
            this.data=this.newsList.slice(0,8)
            this.get_newsNav_data()
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #AdminPublished{
        height: 700px;
        padding: 20px;
        margin-top: 5px;
        width: 99%;
        box-shadow: 0px 2px 5px black;
        box-sizing: border-box;
        position: relative;
        #dialog{
            font-size: 18px !important;
            font-family: 楷体 !important;
            color: rgb(51,51,51) !important;
            /deep/img{
                width: 100%;
                height: auto;
            }
        }
        /deep/.search{
            font-size: 14px;
            input,select{
                margin-bottom: 10px;
                width: 225px;
                height: 22px;
                margin-right: 20px;
                /*border:1px solid #ccc;*/
                box-sizing: border-box;
            }
            label{
                display: inline-block;
                width: 50px;
            }
            button{
                background-color: #db4254;
                color: white;
                /*height: 25px;*/
                width: 54px;
                border-radius: 5px;
            }
        }
        .table{

        }
        .block{
            position: relative;
            height: 20px;
            padding-top: 5px;
            .el-pagination{
                display: inline-block;
                position: absolute;
                left: 50%;
                transform: translateX(-50%);
            }
        }
    }
</style>