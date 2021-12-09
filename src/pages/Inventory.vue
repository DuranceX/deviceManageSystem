<template>
    <div>
        <div class="tableTitle">
            库存一览
        </div>
        <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
    </div>
</template>

<script>
import Card from '../components/Card.vue'
import {postRequest} from '../utils/api'

export default {
    name:'Purchase',
    data(){
        return {
            recordList:[]
        }
    },
    mounted(){
        postRequest("/server/device/getItems").then(res=>{
            this.$store.commit('deviceStore/changeStatus',false);
            for(var index in res.data.obj){
                var r = res.data.obj[index];
                this.recordList.unshift({
                    did:r.did,
                    dname:r.dname,
                    dstatus:r.dstatus,
                    duid:r.duid,
                });
            }
        }).catch(()=>{
            this.$store.commit('deviceStore/changeStatus',true);
            this.$alert("获取数据失败，请检查网络连接");
        })
    },
    components:{Card},
    beforeDestroy(){
        this.$store.commit('deviceStore/changeStatus',true);
    }
}
</script>

<style lang="css">
    .tableTitle{
        font-size: 22px;
        margin: 20px 0;
    }
</style>