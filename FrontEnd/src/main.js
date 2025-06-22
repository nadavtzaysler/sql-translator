import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faArrowRight, faRepeat, faCopy, faBolt } from '@fortawesome/free-solid-svg-icons'
import Toast from 'vue-toastification'
import 'vue-toastification/dist/index.css'

library.add(faRepeat, faArrowRight, faCopy, faBolt)

const app = createApp(App)

const options = {
}
app.use(Toast, options)
app.component('FontAwesomeIcon', FontAwesomeIcon)
app.use(store)
app.use(router)

app.mount('#app')
