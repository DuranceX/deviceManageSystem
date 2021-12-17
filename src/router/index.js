import Vue from 'vue'
import Router from 'vue-router'
import { MessageBox } from 'element-ui'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Register from '../components/Register.vue'
import Purchase from '../pages/Purchase.vue'
import Lease from '../pages/Lease.vue'
import Maintain from '../pages/Maintain.vue'
import Inventory from '../pages/Inventory.vue'
import Scrap from '../pages/Scrap.vue'
import UserManage from '../pages/UserManage.vue'

Vue.use(Router)

const router = new Router({
    routes:[
        {
            path:'/',
            name:'Login',
            component:Login,
            hidden:true
        },
        {
            path:'/Home',
            name:'Home',
            component:Home,
            children:[
                {
                    path:'/',
                    redirect:'/Purchase',
                    name:'主页'
                },
                {
                    path:'/Purchase',
                    name:'Purchase',
                    component:Purchase
                },
                {
                    path:'/Lease',
                    name:'Lease',
                    component:Lease
                },
                {
                    path:'/Maintain',
                    name:'Maintain',
                    component:Maintain
                },
                {
                    path:'/Inventory',
                    name:'Inventory',
                    component:Inventory
                },
                {
                    path:'/Scrap',
                    name:'Scrap',
                    component:Scrap
                },
                {
                    path:'/UserManage',
                    name:'UserManage',
                    component:UserManage
                },
            ]
        },
        {
            path:'/Register',
            name:"Register",
            component:Register,
        }
    ]
});

router.beforeEach((to,from,next)=>{
    if(to.name === 'Login' || to.name === 'Register'){
        next();
        return;
    }
    var name = window.sessionStorage.getItem("username");
    if(name === '' || !name){
        MessageBox.confirm("请先登录！",{
            callback:()=>{
                next({path:"/",query:{redirect:to.path}});
            }
        });
    }
    else{
        next();
    }
})

export default router;