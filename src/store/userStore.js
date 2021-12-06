//用户相关的内容
const userStore = {
    namespace:true,
    actions:{},
    mutations:{
        login(state,id,username,logined,admin){
            state.username = username;
            state.userid = id;
            state.logined = logined;
            state.admin = admin;
        }
    },
    state:{
        username: '',
        logined:false,
        userid:'',
        admin:false,
    }
}

export default userStore;