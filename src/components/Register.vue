<template>
    <div>
        <svg viewBox="0 0 100 100" preserveAspectRatio="xMidYMid slice">
            <defs>
                <radialGradient id="Gradient1" cx="50%" cy="50%" fx="50%" fy="50%" r=".5">
                    <animate attributeName="fx" dur="34s" values="0%;10%;0%" repeatCount="indefinite" />
                    <stop offset="0%" stop-color="rgba(255, 0, 255, 1)" />
                    <stop offset="100%" stop-color="rgba(255, 0, 255, 0)" />
                </radialGradient>
                <radialGradient id="Gradient2" cx="50%" cy="50%" fx="10%" fy="50%" r=".5">
                    <animate attributeName="fx" dur="23.5s" values="0%;50%;0%" repeatCount="indefinite" />
                    <stop offset="0%" stop-color="rgba(255, 255, 0, 1)" />
                    <stop offset="100%" stop-color="rgba(255, 255, 0, 0)" />
                </radialGradient>
                <radialGradient id="Gradient3" cx="50%" cy="50%" fx="50%" fy="50%" r=".5">
                    <animate attributeName="fx" dur="23.5s" values="0%;10%;0%" repeatCount="indefinite" />
                    <stop offset="0%" stop-color="rgba(0, 255, 255, 1)" />
                    <stop offset="100%" stop-color="rgba(0, 255, 255, 0)" />
                </radialGradient>
            </defs>

            <rect x="0" y="0" width="100%" height="100%" fill="url(#Gradient1)">
                <animate attributeName="x" dur="20s" values="0%;25%;0%" repeatCount="indefinite" />
                <animate attributeName="y" dur="21s" values="0%;25%;0%" repeatCount="indefinite" />
                <animateTransform attributeName="transform" type="rotate" from="0 50 50" to="360 50 50" dur="17s" repeatCount="indefinite" />
            </rect>
            <rect x="0" y="0" width="100%" height="100%" fill="url(#Gradient2)">
                <animate attributeName="x" dur="23s" values="0%;-25%;0%" repeatCount="indefinite" />
                <animate attributeName="y" dur="24s" values="0%;-25%;0%" repeatCount="indefinite" />
                <animateTransform attributeName="transform" type="rotate" from="0 50 50" to="360 50 50" dur="18s" repeatCount="indefinite" />
            </rect>
            <rect x="0" y="0" width="100%" height="100%" fill="url(#Gradient3)">
                <animate attributeName="x" dur="25s" values="0%;-25%;0%" repeatCount="indefinite" />
                <animate attributeName="y" dur="26s" values="0%;25%;0%" repeatCount="indefinite" />
                <animateTransform attributeName="transform" type="rotate" from="360 50 50" to="0 50 50" dur="19s" repeatCount="indefinite" />
            </rect>
        </svg>
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
                                path:"/",
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

<style lang="css" scoped>

    .login-Container{
        background-clip: padding-box;
        text-align: center;
        border-radius: 15px;
        width: 350px;
        padding: 55px;
        background-color: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 5px 5px 20px rgba(0,0,0,.06);
        overflow: hidden;
        position: absolute;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }

    .el-input{
        margin-bottom: 20px;
    }

    h2{
        margin-bottom: 30px;
    }

    html {
        width: 100%;
        height: 100%;
        box-sizing: border-box;
        background: #000;
    }

    *,
    *:before,
    *:after {
        box-sizing: inherit;
    }
    body {
        width: 100%;
        height: 100%;
        font: 100px/100px "Helvetica Neue";
        font-weight: 900;
        color: white;
        -webkit-font-smoothing: antialiased;
        font-smoothing: antialiased;
        overflow: hidden;
    }

    svg {
        width: 100%;
        height: 100%;
    }
</style>