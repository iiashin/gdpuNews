<template>
    <div id="footer">
        <img src="https://news.gdpu.edu.cn/images/footbg.png">
        <div class="msg">
            <p>广东药科大学党委宣传部 版权所有 Copyright © 2017 </p>
            <p> 制作单位：现代教育技术中心网络部  v1.0  粤ICP备05008853号</p>
        </div>
    </div>
</template>

<script>
    export default {
        name: ""
    }
</script>

<style scoped lang="scss" type="text/scss">
    #footer{
        height: 90px;
        background-color: #510000;
        margin-top: 15px;
        min-width:1300px;
        padding:10px 10%;
        box-sizing: border-box;  /*防止padding扩充了大小*/
        display: flex;
        /*justify-content: space-between;*/
        .msg{
            color: white;
            font-size: 10px;
            position: relative;
            margin-left: 40px;
        }
    }
</style>