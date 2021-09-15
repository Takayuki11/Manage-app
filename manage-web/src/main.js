import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from '@/plugins/vuetify'
// import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'

Vue.config.productionTip = false
// 以下の記述でthis.$axiosでどこでも使えるので他でimportする必要がなくなる
// Vue.prototype.$axios = axios

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
