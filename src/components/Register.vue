<template>
    <div>
        <div class="login-Container">
            <el-form label-width="80px">
                <h2>设备管理系统——注册</h2>
                <el-input type="text" v-model="name" placeholder="用户名"></el-input>
                <el-input type="password" v-model="password" placeholder="密码"></el-input>
                <el-button type="primary" @click="register()">注册</el-button>
                <el-button @click="submit()">登录</el-button>
            </el-form>
        </div>
    </div>
</template>

<script>
import {postRequest} from '../utils/api'
export default {
    name:"Register",
    data(){
        return{
            name:'',
            password:'',
        }
    },
    methods:{
        //跳转到登录界面
        submit(){
            this.$router.replace({
                path:"/",
            });
        },
        //注册功能
        register(){
            postRequest("/server/reg",{username:this.name,password:this.password}).then(res=>{
                console.log(res);
                if(res.data.status === 200){
                    this.$store.commit('userStore/login',{username:this.name,admin:this.admin});
                    this.$alert("注册成功",{
                        callback:()=>{
                            this.$router.replace({
                                path:"/Home",
                            });
                        }
                    });
                    
                }
                else if(res.data.status === 500){
                    this.$alert(res.data.msg);
                    this.password = '';
                    this.username = '';
                }
            }).catch(()=>{
                this.$alert("注册失败");
            });
        }
    }
}
</script>

<style lang="css">
    body{
        background: #ebedee;
    }   

    .login-Container{
        background-clip: padding-box;
        margin: 360px auto;
        height: 100%;
        text-align: center;
        border-radius: 15px;
        width: 350px;
        padding: 35px 35px 35px 35px;
        background-color: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 5px 5px 20px rgba(0,0,0,.06);
        overflow: hidden;
    }

    .el-input{
        margin-bottom: 20px;
    }
</style>