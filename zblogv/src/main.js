import Vue from 'vue'
import App from './App'

import router from './router'
import store from './store'
import Storage from 'vue-ls'

import lodash from 'lodash'

import ElementUI from 'element-ui'
import '@/assets/theme/index.css'

import '@/assets/icon/iconfont.css'

import { formatTime } from "./utils/time";
import config from '@/defaultSettings'


Vue.config.productionTip = false

Vue.use(ElementUI)

Object.defineProperty(Vue.prototype, '$_', { value: lodash })


Vue.directive('title', function(el, binding) {
        document.title = el.dataset.title
    })
    // 格式话时间
Vue.filter('format', formatTime)
Vue.use(Storage, config.storageOptions)

new Vue({
    el: '#app',
    router,
    store,
    template: '<App/>',
    components: { App }
})