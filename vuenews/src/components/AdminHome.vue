<template>
    <div id="AdminHome">
        <h2>管理员界面</h2>
        <div class="count">
            <div class="box box1">
                <img src="../assets/box1.png">
                <div>
                    浏览量<div>{{MsgCount}}</div>
                </div>
            </div>
            <div class="box box2">
                <img src="../assets/box2.png">
                <div>
                    待审核<div>{{reviewNewsList.length}}</div>
                </div>
            </div>
            <div class="box box3">
                <img src="../assets/box3.png">
                <div>
                    已发布<div>{{newsList.length}}</div>
                </div>
            </div>
            <div class="box box4">
                <img src="../assets/box4.png">
                <div>
                    用户数<div>{{user.length}}</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    // import {mapState} from 'vuex'
    import {mapState,mapGetters} from 'vuex'
    export default {
        name: "AdminHome",
        computed: {
            ...mapState('admin', ['user','newsList','reviewNewsList']),
            ...mapGetters('admin',['MsgCount'])   //获取所有count总数
        },
        data(){
            return{
            }
        }
    }
</script>

<style scpoed lang="scss" type="text/scss">
    #AdminHome{
        padding: 20px;
        .count{
            display: flex;
            justify-content: space-between;
            .box{
                height: 150px;
                width: 300px;
                margin-right: 10px;
                color: white;
                display: flex;
                justify-content: space-between;
                box-sizing: border-box;
                padding: 20px;
                align-items: center;
                img{
                    height: 40px;
                }
                div{
                    text-align: right;
                }
            }
            .box1{
                background: rgb(85, 200, 202);
            }
            .box2{
                background-color: rgb(247, 184, 127);;
            }
            .box3{
                background: rgb(54, 163, 247);
            }
            .box4{
                background: rgb(52, 191, 163);
            }
        }
    }
</style>