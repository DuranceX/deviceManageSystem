<template>
    <div class="login-Container">
        <el-form label-width="80px">
            <h2>设备管理系统</h2>
            <el-input type="text" v-model="name" placeholder="用户名"></el-input>
            <el-input type="password" v-model="password" placeholder="密码"></el-input>
            <el-button type="primary" @click="submit()">登录</el-button>
            <el-button @click="register()">注册</el-button>
        </el-form>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:"Login",
    data(){
        return{
            name:'',
            password:'',
        }
    },
    methods:{
        // submit(){
        //     if(!this.name){
        //         this.$alert("用户名不能为空")
        //     }else{
        //         checkLogin(this.name,this.password).then(data =>{
        //             this.data.status = data.status;
        //             this.data.obj = data.obj;
        //         });
        //         console.log(this.data.status);
        //         if(this.data.status === 200){
        //             this.$store.commit('login',this.data.obj.id,this.data.obj.username,true,this.data.obj.admin);
        //             this.$router.replace({
        //                 path:"/Home",
        //             });
        //         }
        //         else if(this.data.status === 500){
        //             this.$alert("用户名或密码错误");
        //             this.password = '';
        //         }
        //         else{
        //             this.$alert("登录失败");
        //         }
        //     }
        // },
        submit(){
            axios.post("/server/login","username=" + this.name + "&password=" + this.password)
            .then(res => {
                if(res.data.status === 200){
                    this.$store.commit('login',res.data.id,res.data.username,true,res.data.admin);
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