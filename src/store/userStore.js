//用户相关的内容
const userStore = {
    namespaced:true,
    actions:{},
    mutations:{
        login(state,user){
            state.username = user.username;
            state.userid ? user.id : '';
            state.admin = user.admin;
            window.sessionStorage.setItem("username",user.username);
            window.sessionStorage.setItem("admin",user.admin);
        },
        logout(state){
            state.username = '';
            state.admin=false
            window.sessionStorage.setItem("username",'');
            window.sessionStorage.setItem("admin",false);
        }
    },
    state:{
        username: '',
        userid:'',
        admin:false,
    }
}

export default userStore;