<template>
    <div>
        <div class="tableTitle">
            库存一览
        </div>
        <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
        <br />
        <div style="float:left;height:80px;width:1440px"></div>
    </div>
</template>

<script>
import Card from '../components/Card.vue'
import {postRequest} from '../utils/api'

export default {
    name:'Purchase',
    data(){
        return {
            recordList:['1','2','3','4','5','6','7','8','9','10','11','12']
        }
    },
    mounted(){
        this.$nextTick(()=>{
            postRequest("/server/device/getItems").then(res=>{
                this.recordList = [];
                this.$store.commit('deviceStore/changeStatus',false);
                this.$store.commit('deviceStore/clearList');
                for(var index in res.data.obj){
                    var r = res.data.obj[index];
                    this.$store.commit('deviceStore/addDevice',{
                        did:r.did,
                        dname:r.dname,
                        dstatus:r.dstatus,
                        duid:r.duid,
                    });
                }
                this.recordList = this.$store.state.deviceStore.deviceList;
                //排序，运行中在前，已报废在最后
                this.recordList.sort((a,b)=>{
                    return a.dstatus>b.dstatus ? -1:1;
                })
            }).catch(()=>{
                this.$store.commit('deviceStore/changeStatus',true);
                this.$alert("获取数据失败，请检查网络连接");
            })
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