import { createApp } from 'vue'
import { router } from './router'
import store from './store'

// import { mitt } from 'mitt'
// import { axios } from './axios'

import App from './App.vue'

// const emitter = mitt();
const app = createApp(App)
// app.config.globalProperties.emitter = emitter;
// app.config.globalProperties.axios = axios;
// .use(store)
app
.use(router)
.use(store)
.mount('#app')