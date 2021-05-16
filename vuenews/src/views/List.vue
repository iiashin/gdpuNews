<template>
    <div id="List">
        <my-header/>
        <menu-content/>
        <my-footer/>
    </div>
</template>
<script>
    import { mapActions} from 'vuex'
    export default {
        data() {
            return {
                abbr:''
            };
        },
        components:{
            'my-header':()=>import('../components/myHeader.vue'),
            'menu-content':()=>import('../components/menuContent.vue'),
            'my-footer':()=>import('../components/myFooter.vue')
        },
        methods: {
            ...mapActions('list', [
                'get_newsList_data'
            ])
        },
        watch:{
            //路由参数一变更改列表
            '$route.params.abbr':function(newVal,oldVal) {     //不能用箭头函数，指向了父级作用域(watch)
                if(newVal)
                    this.get_newsList_data({abbr:newVal,key:''});
            },
            '$route.params.key':function(newVal,oldVal) {     //不能用箭头函数，指向了父级作用域(watch)
                if(newVal)
                    this.get_newsList_data({abbr:'',key:newVal});
            },
        },
        created(){
            // console.log("create")
            this.get_newsList_data({abbr:this.$route.params.abbr||'',key:this.$route.params.key||''});
        }
    }
</script>
<style>
    #home{
        /*position: relative;*/
    }
</style>
