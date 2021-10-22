import { createApp } from 'vue'
// import { store } from './store'
import { router } from './router'
// import { mitt } from 'mitt'
// import { axios } from './axios'

import App from './App.vue'

// const emitter = mitt();
const app = createApp(App)

// app.config.globalProperties.emitter = emitter;

// app.config.globalProperties.axios = axios;

// app.use(store)
app.use(router)
.mount('#app')
