<template>
    <div style="height:100%">
        <el-container>
            <el-header>
                <button class="title" @click="backToHome()">设备管理系统</button>
                <div>
                    <el-button icon="el-icon-user" type="text" style="margin-right: 8px;color:white" size="normal"></el-button>
                    <el-dropdown @command="handleCommand">
                        <span class="el-dropdown-link">
                            {{username}}
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="userinfo">修改用户名</el-dropdown-item>
                            <el-dropdown-item command="changePsw">修改密码</el-dropdown-item>
                            <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-col>
                        <el-menu
                        default-active="-1"
                        class="el-menu-vertical-demo">
                            <el-menu-item index="1" @click="switchRoute('Purchase')">
                                <i class="el-icon-menu"></i>
                                <span>购买管理</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="switchRoute('Lease')">
                                <i class="el-icon-s-promotion"></i>
                                <span>转借管理</span>
                            </el-menu-item>
                            <el-menu-item index="3" @click="switchRoute('Maintain')">
                                <i class="el-icon-s-order"></i>
                                <span>维修管理</span>
                            </el-menu-item>
                            <el-menu-item index="4" @click="switchRoute('Inventory')">
                                <i class="el-icon-s-home"></i>
                                <span>库存管理</span>
                            </el-menu-item>
                            <el-menu-item index="5" @click="switchRoute('Scrap')">
                                <i class="el-icon-s-release"></i>
                                <span>报废管理</span>
                            </el-menu-item>
                            <el-menu-item index="6" @click="switchRoute('UserManage')">
                                <i class="el-icon-user-solid"></i>
                                <span>用户管理</span>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>

        <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%">
                请输入新密码<br />
                <el-input type="password" v-model="newPsw"></el-input>
                请确认新密码<br />
                <el-input type="password" v-model="reNewPsw"></el-input><br />
                <span v-show="!(newPsw === reNewPsw)" style="color:red">两次密码输入不一致</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" :disabled="!(newPsw === reNewPsw)" @click="changePsw()">确 定</el-button>
                </span>
        </el-dialog>
    </div>
</template>

<script>
import { postRequest,paramsPostRequest } from '../utils/api';

export default {
    name:"Home",
    data(){
        return {
            dialogVisible:false,
            newPsw:'',
            reNewPsw:''
        }
    },
    computed:{
        username(){
            return window.sessionStorage.getItem('username');
        },
        admin(){
            if(window.sessionStorage.getItem('admin'))
                return true;
            return false;
        }
    },
    methods:{
        switchRoute(name){
            if(this.$route.name !== name){
                this.$router.push({
                    name: name,
                })
            }
        },
        backToHome(){
            if(this.$route.name !== 'Home')
                this.$router.push({path:'/Home'});
        },
        handleCommand(command) {
            if(command === 'logout'){
                this.$store.commit('userStore/logout');
                this.$router.replace({path:"/"});
            }
            else if(command === 'userinfo'){
                this.$prompt('请输入新用户名','提示',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                }).then(({value})=>{
                    let data = {};
                    data.uid = window.sessionStorage.getItem("userid");
                    data.username = value;
                    data.admin = window.sessionStorage.getItem("admin");
                    postRequest('/server/update',data).then(res=>{
                        if(res.data.status === 200)
                        {
                            window.sessionStorage.setItem("username",value);
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
            }
            else if(command === 'changePsw'){
                this.dialogVisible = true;
            }
        },
        changePsw(){
            paramsPostRequest('/server/updateUserPwd',{username:this.username,password:this.newPsw}).then(res=>{
                if(res.data.status === 200){
                    this.$store.commit('userStore/logout');
                    this.$alert("密码修改成功，请重新登录",'',{
                        callback:()=>{
                            this.$router.replace({path:"/"});
                        }
                    });

                }
                else{
                    this.$alert("密码修改失败");
                }
            }).catch((err)=>{
                console.log(err);
                this.$alert("服务器响应超时，请检查网络连接");
            })
        }
    },
}
</script>


<style lang="css">
    
    html,body{
        height: 100%;
    }

    .title{
        background-color: rgba(0,0,0,0);
        border: none;
        cursor: pointer;
    }

    .homeRouterView {
        margin-top: 10px;
    }

    .el-header{
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 45px;
        box-sizing: border-box;
    }

    .el-header .title {
        font-size: 20px;
        color: #ffffff
    }

    .el-header .el-dropdown{
        color: white;
        font-size: 16px;
    }

    .el-header, .el-footer {
        background-color: #409eff;
        color: white;
        text-align: start;
        line-height: 60px;
    }
    
    .el-aside {
        background-color: #fff;
        color: #333;
        text-align: center;
        line-height: 200px;
        height:100%;
        border-right: solid 4px #e6e6e6;
    }

    .el-menu-item{
        font-size: 18px;
        padding: 0 30px;
        margin: 10px 0;
        height: auto;
    }

    .el-menu-item:focus, .el-menu-item:hover{
        background-color: #deefff;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;
    }
    
    .el-container{
        height: 100%;
    }

    body > .el-container {
        margin-bottom: 40px;
    }
    
    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }
    
    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .el-dropdown-link img {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }

    .el-dropdown-link {
        display: flex;
        align-items: center;
        cursor: pointer;
    }
</style>