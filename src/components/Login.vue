<template>
    <div>
        <div class="login-Container">
            <el-form label-width="80px">
                <h2>设备管理系统</h2>
                <el-input type="text" v-model="name" placeholder="用户名"></el-input>
                <el-input type="password" v-model="password" placeholder="密码"></el-input>
                <el-button type="primary" @click="submit()">登录</el-button>
                <el-button @click="register()">注册</el-button>
            </el-form>
        </div>
    </div>
</template>

<script>
import {postRequest} from '../utils/api'
export default {
    name:"Login",
    data(){
        return{
            name:'',
            password:'',
        }
    },
    methods:{
        //登录功能
        submit(){
            postRequest("/server/login",{username:this.name,password:this.password}).then(res => {
                if(res.data.status === 200){
                    let data = res.data.obj;
                    this.$store.commit('userStore/login',{id:data.id,username:data.username,admin:data.admin});
                    this.$router.replace({
                        path:"/Home",
                    });
                }
                else if(res.data.status === 500){
                    this.$alert("用户名或密码错误");
                    this.password = '';
                }
            }).catch(() =>{
                this.$alert("登录失败");
            });
        },
        //注册功能
        register(){
            this.$router.replace({
                path:"/Register",
            });
        }
    }
}
</script>

<style lang="css">

    .login-Container{
        background-clip: padding-box;
        margin: 360px auto;
        text-align: center;
        border-radius: 15px;
        width: 350px;
        padding: 55px;
        background-color: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 5px 5px 20px rgba(0,0,0,.06);
        overflow: hidden;
    }

    .el-input{
        margin-bottom: 20px;
    }

    h2{
        margin-bottom: 30px;
    }
</style>