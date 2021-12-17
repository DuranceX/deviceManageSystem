<template>
    <div>
        <div class="tableTitle">
            用户权限管理
        </div>
        <Card v-for="r in recordList" :key="r.uid" :data="r"></Card>
        <br />
        <div style="float:left;height:80px;width:1440px"></div>
    </div>
</template>

<script>
import UserCard from '../components/UserCard.vue'
import {postRequest} from '../utils/api'

export default {
    name:'UserManage',
    data(){
        return {
            recordList:['1','2','3','4']
        }
    },
    mounted(){
        this.$nextTick(()=>{
            postRequest("/server/getUsers").then(res=>{
                this.recordList = [];
                this.$store.commit('deviceStore/changeStatus',false);
                for(var index in res.data){
                    var r = res.data[index];
                    this.recordList.push({
                        uid:r.uid,
                        username:r.username,
                        admin:r.admin,
                    });
                }
            }).catch(()=>{
                this.$store.commit('userStore/changeStatus',true);
                this.$alert("获取数据失败，请检查网络连接");
            })
        })
    },
    components:{Card:UserCard},
    beforeDestroy(){
        this.$store.commit('userStore/changeStatus',true);
    }
}
</script>

<style lang="css">
    .tableTitle{
        font-size: 22px;
        margin: 20px 0;
    }
</style>