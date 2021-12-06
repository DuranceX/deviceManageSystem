import Vue from 'vue'
import App from './App.vue'
import {Form,Button,Input,MessageBox} from 'element-ui'

Vue.use(Form);
Vue.use(Button);
Vue.use(Input);
Vue.prototype.$alert = MessageBox.alert

Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
}).$mount('#app')
