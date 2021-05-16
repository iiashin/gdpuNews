<template>
    <div id="Addmin-add-news">
        <form method="post" enctype="multipart/form-data" action="http://localhost:8976/gdpuNews/upLoadPic">
            新闻标题:
            <el-input class="UserInput" placeholder="请输入内容" v-model="title" clearable name="title" />
            <br>
            所属栏目:
            <template>
                <el-select class="UserInput" v-model="value" placeholder="请选择" name="type">
                    <el-option
                            v-for="item in newsNav"
                            :key="item.id"
                            :label="item.typeName"
                            :value="item.id">
                    </el-option>
                </el-select>
            </template>
            <br>
            新闻作者:
            <el-input class="UserInput" placeholder="请输入内容" v-model="author" clearable name="author" />
            <br>
            新闻图片:
            <input type="file" name="pic" ref="file" class="change" multiple="multiple"  />
            <input type="button"  value="上传" class="btn" @click="submitUpload" />
            <br>
            <input type=hidden name="content" ref="hidden">
            新闻内容:
            <div contenteditable="true" class="content" @input="getContent($event)" ref="content1"></div>
            <br>
            <input type="submit" value="提交" class="submit" />
        </form>
    </div>
</template>

<script>
    import Qs from 'qs'
    import {mapState,mapActions} from 'vuex'
    export default {
        name: "AdminAddNews",
        data() {
            return {
                content:'',
                pic:'',
                title: '' ,  //新闻标题
                value: '',  //所选内容
                author:'', //新闻作者
                // fileList:[]
            };
        },
        computed:{
            ...mapState('home',[
                'newsNav'    //获取新闻种类
            ])
        },
        methods: {
            //上传图片后显示功能
            submitUpload() {
                //createObjectURL
                this.pic=URL.createObjectURL(this.$refs.file.files[0])   //获取当前图片同时表示指定的blob对象，生命周期和创建它的窗口中的 document 绑定
                // this.fileList.push(this.pic)
                this.$refs.content1.innerHTML+=`<img  src="${this.pic}"></img>`

            },
            //输入框的值赋给hidden，hidden再给后台
            getContent(e){
                this.$refs.hidden.value=this.$refs.content1.innerHTML
            },
            ...mapActions('home', [
                'get_newsNav_data'
            ])
        },
        created(){
            //获取新闻种类，才能实现下拉种类选择
            this.get_newsNav_data();
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #Addmin-add-news{
        padding: 20px;
        /*width:980px ;*/
        width: 99%;
        box-shadow: 0px 2px 5px black;
        box-sizing: border-box;
        margin-top: 5px;
        /deep/.UserInput{
            width: 800px !important;
            margin-bottom: 10px;
        }
        .content{
            margin:10px 0px;
            vertical-align: top;
            display: inline-block;
            width: 800px;
            border: 1px solid grey;
            overflow:auto;
            height: 400px;
            /deep/img{
                /*display: inline-block;*/
                width: 620px;
                /*height: auto;*/
                /*background-color: red;*/
            }
        }
        .upload{
            width: 300px;
            border: 1px solid grey;
            height: 40px;
            background-color: red;
            .change{
                opacity: 0;
            }
        }
        .btn{
            margin-bottom: 10px;

        }
        .submit{
            height: 30px;
            width: 70px;
            color: white;
            background-color: #42b983;
            position: relative;
            margin-left: 70px;
        }
    }
</style>