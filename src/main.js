import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import {
  Form,
  Button,
  Input,
  MessageBox,
  Row,
  Col,
} from 'element-ui'

Vue.use(Form);
Vue.use(Button);
Vue.use(Input);
Vue.use(Row);
Vue.use(Col);
Vue.prototype.$alert = MessageBox.alert

Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
  router:router,
  store,
}).$mount('#app')
