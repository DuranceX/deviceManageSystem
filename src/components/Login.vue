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
                <h2>设备管理系统</h2>
                <el-input type="text" v-model="name" placeholder="用户名"></el-input>
                <el-input type="password" v-model="password" placeholder="密码"></el-input>
                <el-button type="primary" @click="submit()" v-loading.fullscreen.lock="fullscreenLoading">登录</el-button>
                <el-button @click="register()">注册</el-button>
                <el-button @click="test()">测试</el-button>
            </el-form>
        </div>
    </div>
</template>

<script>
import {postRequest,getRequest} from '../utils/api'
import {nanoid} from 'nanoid'
export default {
    name:"Login",
    data(){
        return{
            name:'',
            password:'',
            fullscreenLoading:false,
        }
    },
    methods:{
        //登录功能
        submit(){
            this.fullscreenLoading = true;
            postRequest("/server/login",{username:this.name,password:this.password}).then(res => {
                if(res.data.status === 200){
                    this.fullscreenLoading = false;
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
        },
        test(){
            this.fullscreenLoading = true;
            getRequest("/server/login?username=admin&password=admin").then(res=>{
               if(res.data.status === 200){
                    this.fullscreenLoading = false;
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
        }
    },
    mounted(){
        console.log(nanoid(8).toUpperCase());
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
        background-color: rgba(255, 255, 255, 0.863);
        border: 1px solid #eaeaea;
        box-shadow: 5px 5px 20px rgba(0,0,0,.06);
        overflow: hidden;
        position: absolute;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }

    .login-Container::before{
        filter: blur(20px);
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