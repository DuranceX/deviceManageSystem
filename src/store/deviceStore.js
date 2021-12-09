const deviceStore ={
    namespaced:true,
    actions:{},
    mutations:{
        changeStatus(state,value){
            state.loading = value;
        }
    },
    state:{
        loading:true,
    }
}

export default deviceStore;