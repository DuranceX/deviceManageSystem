import Vue from 'vue'
import vuex from 'vuex'
import userStore from './userStore';

Vue.use(vuex);

export default new vuex.Store({
    modules:{
        userStore:userStore,
    }
})