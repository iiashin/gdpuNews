<template>
    <div id="dialog" v-show="showDialog">
            <div class="content">
                <div class="top">查看<button @click="handleSon">x</button></div>
                <div class="content1">
                    <slot></slot>
                </div>
            </div>
    </div>
</template>

<script>
    export default {
        name: "Dialog",
        props:{
            showDialog:{    //父组件传过来的属性
                type:Boolean,
                default:false
            }
        },
        methods:{
            handleSon(){
                this.$emit('func')  //点击事件，调用父组件的func函数
            }
        }
    }
</script>

<style scoped lang="scss" type="text/scss">
    #dialog{
        /*display: none;*/
        height: 92%;
        width: 95%;
        background-color:rgba(0,0,0,0.5);
        /*background-color: grey;*/
        position: absolute;
        z-index: 100;

        .content{
            padding: 5px;
            /*overflow: auto;*/
            height: 600px;
            width: 900px;
            background-color: white;
            border: 1px solid black;
            /*opacity: 100% !important;*/
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin: auto;
            position: absolute;
            /*position: relative;*/
            .content1{
                height: 90%;
                width: 100%;
                overflow: auto;
                margin-top: 50px;
            }
            .top{
                /*top: 0;*/
                /*max-width: 880px;*/
                position: absolute;
                padding-top: 5px;
                text-align: center;
                width: 99%;
                height: 40px;
                color: white;
                background-color: #db4254;
                box-sizing: border-box;
            }
            button{
                float: right;
                background-color: white;
            }
        }
    }
</style>