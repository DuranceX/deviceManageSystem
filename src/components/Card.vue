<template>
    <el-card class="box-card">
        <el-skeleton :loading="loading" animated>
            <template slot="template">
                <el-row>
                    <el-col :span="10">
                        <el-skeleton-item
                            variant="image"
                            style="width: 160px; height: 160px;margin:0px"
                        />
                    </el-col>
                    <el-col :span="14">
                        <!--设备状态-->
                        <div style="padding:0 14px;margin: 16px 0 16px 10px; ">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                        <!--设备名-->
                        <div style="padding:0 14px;margin: 32px 0 32px 10px;">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                        <!--设备状态-->
                        <div style="padding:0 14px;margin: 16px 0 16px 10px;">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </template>
            <template>
                <el-row>
                    <el-col :span="10">
                        <img
                            src="../assets/default.png"
                            class="image"
                        />
                    </el-col>
                    <el-col :span="14">
                        <div style="padding:0 14px;margin: 16px 0 16px 10px;">
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">设备名称</h1>
                                <span>{{data.dname}}</span>
                            </div>
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">设备序列号</h1>
                                <span>{{ data.duid }}</span>
                            </div>
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">设备状态</h1>
                                <el-tag :type="tagType"> {{data.dstatus}}</el-tag>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </template>
        </el-skeleton>
    </el-card>
</template>

<script>
export default {
    name:"Card",
    props:['data'],
    data(){
        return {
            currentDate: '2021-06-01'
        }
    },
    computed:{
        tagType(){
            if(this.data.dstatus === '维修中')
                return 'danger';
            else if(this.data.dstatus === '转借中')
                return 'warning';
            else if(this.data.dstatus === '已报废')
                return 'info';
            return 'success';
        },
        loading(){
            return this.$store.state.deviceStore.loading;
        },
    }
}
</script>

<style lang="css">
    .image{
        height: 180px;
        width: 180px;
    }

    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
        margin: 0px 40px 20px 0px;
        float: left;
        height: 220px;
    }

    .el-skeleton__item{
        margin: 30px 0px;
    }

    .my-skeleton-item{
        display: flex; 
        align-items: center; 
        justify-items: space-between; 
        margin: 16px 0 16px 0; 
        height: 16px;
        margin: 30px 0px 35px 0px;
    }
</style>