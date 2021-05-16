<template>
  <div id="home">
      <my-header/>
      <first/>
      <my-footer/>

  </div>
</template>
<script>
    // import { mapActions} from 'vuex'
    export default {
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1'
            };
        },
        components:{
            'my-header':()=>import('../components/myHeader.vue'),
            'first':()=>import('../components/first.vue'),
            'my-footer':()=>import('../components/myFooter.vue')
        },
        methods: {
            // ...mapActions('home', [
            //     'get_newsNav_data'
            // ])
        },
        created(){
            // this.get_newsNav_data();
        }
    }
</script>
<style>
    #home{
        /*position: relative;*/
    }
</style>
