<template>
    <div class="login-Container">
        <h2>设备管理系统</h2>
        <el-form :model="form" label-width="80px">
            <el-input type="text" v-model="form.name" placeholder="用户名"></el-input>
            <el-input type="password" v-model="form.password" placeholder="密码"></el-input>
            <el-button type="primary" @click="submit()">登录</el-button>
            <el-button @click="register()">注册</el-button>
        </el-form>
    </div>
</template>

<script>
import {checkLogin} from '../utils/data'

export default {
    name:"Login",
    data(){
        return{
            form:{
                name:'',
                password:'',
            }
        }
    },
    methods:{
        submit(){
            if(!this.form.name){
                this.$alert("用户名不能为空")
            }else{
                checkLogin(this.form.name,this.form.password);
            }
        },
        register(){

        }
    }
}
</script>

<style lang="css">
    body{
        background: #ebedee;
    }   

    .login-Container{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 360px auto;
        width: 350px;
        padding: 35px 35px 35px 35px;
        background-color: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 5px 5px 20px rgba(0,0,0,.06);
    }

    .el-input{
        margin-bottom: 20px;
    }
</style>