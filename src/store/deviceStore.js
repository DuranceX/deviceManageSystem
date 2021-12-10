const deviceStore ={
    namespaced:true,
    actions:{},
    mutations:{
        changeStatus(state,value){
            state.loading = value;
        },
        addDevice(state,r){
            state.deviceList.unshift({
                did:r.did,
                dname:r.dname,
                dstatus:r.dstatus,
                duid:r.duid,
            })
        },
        clearList(state){
            state.deviceList = [];
        }
    },
    state:{
        loading:true,
        deviceList:[],
    }
}

export default deviceStore;