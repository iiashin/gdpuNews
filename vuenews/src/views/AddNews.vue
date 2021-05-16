<template>
    <div id="addnews">
        <my-header/>
        <div class="content">
            <admin-add-news/>
        </div>
        <my-footer/>
    </div>
</template>

<script>
    export default {
        name: "AddNews",
        components:{
            'my-header':()=>import('../components/myHeader.vue'),
            'admin-add-news':()=>import('../components/AdminAddNews.vue'),
            'my-footer':()=>import('../components/myFooter.vue')
        },
    }
</script>

<style scoped lang="scss" type="text/scss">
    #addnews{
        .content{
            padding: 0 10%;
            /deep/#Addmin-add-news{
                /*margin-top: 20px;*/
                /*padding: 10px 10%;*/
                box-shadow: none;
                border: 1px solid #a32b34;
            }
            .submit{
                background-color: #a32b34;
            }
        }
    }
</style>