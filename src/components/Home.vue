<template>
    <div style="height:100%">
        <!-- <el-container>
            <el-header class="homeHeader">
                <div class="title">设备管理系统</div>
                <div>
                    <el-button icon="el-icon-bell" type="text" style="margin-right: 8px;color:white" size="normal"></el-button>
                    <el-dropdown class="userInfo">
                        <span class="el-dropdown-link">
                            {{username}}
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                            <el-dropdown-item command="setting">设置</el-dropdown-item>
                            <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu router unique-opened>
                        大师傅大师傅
                    </el-menu>
                </el-aside>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item>首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <router-view class="homeRouterView"/>
                </el-main>
            </el-container>
        </el-container> -->
        <el-container>
            <el-header>
                <div class="title" @click="backToHome()">设备管理系统</div>
                <div>
                    <el-button icon="el-icon-bell" type="text" style="margin-right: 8px;color:white" size="normal"></el-button>
                    <el-dropdown class="userInfo">
                        <span class="el-dropdown-link">
                            {{username}}
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                            <el-dropdown-item command="setting">设置</el-dropdown-item>
                            <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-col>
                        <el-menu
                        default-active="1"
                        class="el-menu-vertical-demo">
                            <el-menu-item index="1" @click="switchRoute('Purchase')">
                                <i class="el-icon-menu"></i>
                                <span slot="title">购买管理</span>
                            </el-menu-item>
                            <el-menu-item index="2" @click="switchRoute('Lease')">
                                <i class="el-icon-menu"></i>
                                <span slot="title">转借管理</span>
                            </el-menu-item>
                            <el-menu-item index="3" @click="switchRoute('Maintain')">
                                <i class="el-icon-document"></i>
                                <span slot="title">维修管理</span>
                            </el-menu-item>
                            <el-menu-item index="4" @click="switchRoute('Inventory')">
                                <i class="el-icon-setting"></i>
                                <span slot="title">库存管理</span>
                            </el-menu-item>
                            <el-menu-item index="5" @click="switchRoute('Scrap')">
                                <i class="el-icon-setting"></i>
                                <span slot="title">报废管理</span>
                            </el-menu-item>
                            <el-menu-item index="6" @click="switchRoute('UserManage')">
                                <i class="el-icon-setting"></i>
                                <span slot="title">用户管理</span>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-aside>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
    name:"Home",
    computed:{
        ...mapState('userStore',['username','userid',"admin"])
    },
    methods:{
        switchRoute(name){
            this.$router.push({
                name: name,
                query:{
                    admin:this.$store.state.admin,
                }
            })
        },
        backToHome(){
            this.$router.push({path:'/'});
        }
    }
}
</script>


<style lang="css">
    .homeRouterView {
        margin-top: 10px;
    }

    .el-header{
        background-color: #409eff;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 25px;
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
        text-align: center;
        line-height: 160px;
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
    }
</style>