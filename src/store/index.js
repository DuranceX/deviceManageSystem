import Vue from 'vue'
import Vuex from 'vuex'
import deviceStore from './deviceStore';
import userStore from './userStore';

Vue.use(Vuex);

export default new Vuex.Store({
    modules:{
        userStore:userStore,
        deviceStore:deviceStore,
    }
})