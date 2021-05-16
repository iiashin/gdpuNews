<template>
    <div id="AdminWriter">
        <div class="search">
            <label>账号:</label><input type="text" v-model="username" />
            <label>密码:</label><input type="text" v-model="password" />
            <button  @click="addUser">增加</button>
        </div>
        <div class="table">
            <el-table  v-if="tableData"
                       :data="tableData"
                       stripe
                       style="width: 100%">
                <el-table-column
                        prop="user_id"
                        label="用户id"
                        width="320">
                </el-table-column>
                <el-table-column
                        prop="user_name"
                        label="名称"
                        width="320">
                </el-table-column>
                <el-table-column
                        prop="password"
                        label="密码"
                        width="320">
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button  size="small" type="danger" @click="delUser(scope.row.user_id)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    import {mapState,mapActions} from 'vuex'
    import {request} from "../util/request";
    export default {
        name: "AdminWriter",
        data(){
            return{
                username:'',
                password:'',
                tableData: [],      //获取所有用户数据
                isChange:1      //数据改变
            }
        },
        computed:{
            ...mapState('admin',['user'])
        },
        created(){
            this.tableData=this.user
        },
        watch:{
            user:function (val) {
                this.tableData=val
            }
        },
        methods:{
            ...mapActions('admin',['get_user']),
            //添加用户
            addUser(){
                let params={
                    username:this.username,
                    password:this.password
                }
                request("get","addUser",params).then(
                    res=>{
                        if(res){
                            this.get_user()
                        }
                    }
                )
            },
            //删除用户
            delUser(id){
                request("get","delUser",{id:id}).then(
                    res=>{
                        if(res){
                            this.get_user()
                        }
                    }
                )
            }
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #AdminWriter {
        padding: 20px;
        width: 99%;
        height: 680px;
        box-shadow: 0px 2px 5px black;
        box-sizing: border-box;
        margin-top: 5px;
        position: relative;
        overflow: auto;
        /deep/ .search {
            font-size: 14px;
            input, select {
                margin-bottom: 10px;
                width: 225px;
                height: 22px;
                margin-right: 20px;
                /*border:1px solid #ccc;*/
                box-sizing: border-box;
            }
            label {
                display: inline-block;
                width: 50px;
            }
            button {
                background-color: #42b983;
                color: white;
                /*height: 25px;*/
                width: 54px;
                border-radius: 5px;
            }
        }
    }
</style>