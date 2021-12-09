<template>
    <div>
        <div class="tableTitle">
            库存一览
        </div>
        <!-- <el-row :gutter="20">
            <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="1">
                <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="1">
                <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
            </el-col>
            <el-col  :xs="24" :sm="12" :md="12" :lg="8" :xl="1">
                <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="8" :xs="24" :sm="12" :md="12" :lg="8" :xl="1">
                <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
            </el-col>
            <el-col :span="8" :xs="24" :sm="12" :md="12" :lg="8" :xl="1">
                <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
            </el-col>
            <el-col :span="8" :xs="24" :sm="12" :md="12" :lg="8" :xl="1">
                <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
            </el-col>
        </el-row> -->
        <Card v-for="r in recordList" :key="r.did" :data="r"></Card>
    </div>
</template>

<script>
import Card from '../components/Card.vue'
export default {
    name:'Purchase',
    data(){
        return {
            recordList:[
                {did:'001',dname:'空调',dstatus:"运行中",duid:'TXJUCWR3'},
                {did:'002',dname:'车床',dstatus:"维修中",duid:'SVGDTJJO'},
                {did:'003',dname:'电视',dstatus:"转借中",duid:'TBFCHWR3'},
                {did:'004',dname:'电话',dstatus:"已报废",duid:'VLMNFDVO'},
            ]
        }
    },
    mounted(){
        
    },
    components:{Card}
}
</script>

<style lang="css">
    .tableTitle{
        font-size: 22px;
        margin: 20px 0;
    }
</style>