import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios';

import 'leaflet/dist/leaflet.css';
import "./assets/style/style.scss";
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
//import { Icon } from 'leaflet';


Vue.config.productionTip = false

//Instancia de axios se configura con URL base del backend
const axiosInstance = axios.create({ 
  baseURL: 'http://localhost:3000'
})

//Para acceder a axios desde this.$http
Vue.prototype.$http = axiosInstance;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
