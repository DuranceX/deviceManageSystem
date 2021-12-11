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
                        <!--用户ID-->
                        <div style="padding:0 14px;margin: 16px 0 16px 10px; ">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                        <!--用户名-->
                        <div style="padding:0 14px;margin: 32px 0 32px 10px;">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                        <!--用户权限-->
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
                            src="../assets/user.png"
                            class="image"
                        />
                    </el-col>
                    <el-col :span="14">
                        <div style="padding:0 14px;margin: 16px 0 16px 10px;">
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">用户ID</h1>
                                <span>{{data.uid}}</span>
                            </div>
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">用户名</h1>
                                <span>{{ data.username }}</span>
                            </div>
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">管理员权限</h1>
                                <el-switch :disabled="!admin" v-model="data.admin" @change="changeAuth()"></el-switch>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </template>
        </el-skeleton>
    </el-card>
</template>

<script>
import { postRequest } from '../utils/api';

export default {
    name:"UserCard",
    props:['data'],
    computed:{
        loading(){
            return this.$store.state.deviceStore.loading;
        },
        admin(){
            if(window.sessionStorage.getItem('admin') === 'true')
                return true;
            return false;
        }
    },
    methods:{
        changeAuth(){
            this.$confirm('确认修改该用户权限吗','提示',{
                type:'warning'
            }).then(()=>{
                postRequest('/server/update',this.data).then(res=>{
                    if(res.data.status === 200){
                        this.$alert("修改权限成功");
                    }
                }).catch(()=>{
                    this.$alert("修改权限失败");
                })
            }).catch(()=>{
                this.data.admin = !this.data.admin;
            })
        }
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