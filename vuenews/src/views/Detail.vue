<template>
    <div id="detail">
        <my-header/>
        <detail-content/>
        <my-footer/>
    </div>
</template>

<script>
    export default {
        name: "Detail",
        components:{
            'my-header':()=>import('../components/myHeader.vue'),
            'detail-content':()=>import('../components/detailContent.vue'),
            'my-footer':()=>import('../components/myFooter.vue')
        },
    }
</script>

<style scoped>

</style>