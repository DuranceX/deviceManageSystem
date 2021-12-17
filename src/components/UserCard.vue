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
                        <div style="padding:0 0 0 14px;margin: 16px 0 16px 10px; ">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                        <!--用户名-->
                        <div style="padding:0 0 0 14px;margin: 32px 0 32px 10px;">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                        <!--用户权限-->
                        <div style="padding:0 0 0 14px;margin: 16px 0 16px 10px;">
                            <div class="my-skeleton-item">
                                <el-skeleton-item variant="h1" style="margin-right: 16px;width: 40%;" />
                                <el-skeleton-item variant="h1" />
                            </div>
                        </div>
                        <!--用户密码-->
                        <div style="padding:0 0 0 14px;margin: 32px 0 32px 10px;">
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
                        <div style="padding:0 0 0 14px;margin: 16px 0 16px 10px;">
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">用户ID</h1>
                                <span>{{data.uid}}</span>
                            </div>
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">用户名</h1>
                                <span>
                                    {{ data.username }}
                                    <el-button 
                                        icon="el-icon-edit" 
                                        type="text"
                                        :disabled="!admin" 
                                        style="padding: 5px 5px;"
                                        @click="changeUserName()"
                                    ></el-button>
                                </span>
                            </div>
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">管理员权限</h1>
                                <el-switch :disabled="!admin" v-model="data.admin" @change="changeAuth()"></el-switch>
                            </div>
                            <div class="my-skeleton-item">
                                <h1 style="margin-right: 16px;width: 50%;">修改密码</h1>
                                <el-button 
                                    round 
                                    plain
                                    icon="el-icon-edit" 
                                    type="primary"
                                    :disabled="!admin" 
                                    style="padding: 5px 20px;"
                                    @click="callPswDialog()"
                                ></el-button>
                            </div>
                        </div>
                    </el-col>
                </el-row>
                <el-button 
                    type="text" 
                    :disabled="!admin" 
                    style="color: red;font-size: 16pt;position: relative;top: -210px;left: 430px;" 
                    icon="el-icon-close" 
                    @click="deleteUser()"
                ></el-button>
            </template>
        </el-skeleton>

        <el-dialog
            title="提示"
            :visible.sync="pswDialogVisible"
            width="30%">
                请输入新密码<br />
                <el-input type="password" v-model="newPsw"></el-input>
                请确认新密码<br />
                <el-input type="password" v-model="reNewPsw"></el-input><br />
                <span v-show="!(newPsw === reNewPsw)" style="color:red">两次密码输入不一致</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="pswDialogVisible = false">取 消</el-button>
                    <el-button type="primary" :disabled="!(newPsw === reNewPsw)" @click="changePsw()">确 定</el-button>
                </span>
        </el-dialog>
    </el-card>
</template>

<script>
import { postRequest,paramsPostRequest } from '../utils/api';

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
    data(){
        return {
            newPsw:'',
            reNewPsw:'',
            pswDialogVisible:false,
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
        },
        callPswDialog(){
            this.pswDialogVisible = true;
        },
        changePsw(){
            paramsPostRequest('/server/updateUserPwd',{username:this.data.username,password:this.newPsw}).then(res=>{
                if(res.data.status === 200){
                    this.$alert("成功修改用户" + this.data.username + "的密码");
                    this.pswDialogVisible = false;
                }
                else{
                    this.$alert("密码修改失败");
                }
            }).catch((err)=>{
                console.log(err);
                this.$alert("服务器响应超时，请检查网络连接");
            })
        },
        changeUserName(){
            this.$prompt('请输入新用户名','提示',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
            }).then(({value})=>{
                let newData = {};
                newData.uid = this.data.uid;
                newData.username = value;
                newData.admin = this.data.admin;
                console.log(newData);
                postRequest('/server/update',newData).then(res=>{
                    if(res.data.status === 200)
                    {
                        this.$alert("修改成功",'',{
                            callback:()=>{
                                location.reload();
                            }
                        });
                    }
                }).catch(()=>{
                    this.$alert("修改失败");
                })
            })
        },
        deleteUser(){
            this.$confirm('此操作将删除该用户，确认删除吗','提示',{
                type:'warning'
            }).then(()=>{
                paramsPostRequest('/server/delete',{id:this.data.uid}).then(res=>{
                    if(res.data.status === 200){
                        this.$alert("成功删除用户");
                        location.reload();
                    }
                    else if(res.data.status === 500){
                        this.$alert(res.data.msg);
                    }
                }).catch(() =>{
                        this.$alert("删除失败");
                });
            })
        }
    }
}
</script>

<style lang="css" scoped>
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
        margin: 20px 0px 25px 0px !important; 
    }
</style>