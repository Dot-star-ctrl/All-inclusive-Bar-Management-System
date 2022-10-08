import { createApp } from 'vue'
import App from './App.vue'
import '@/assets/css/tailwind.css'
import router from './router'
// import VueCompositionApi from '@vue/composition-api'
import {VuelidatePlugin} from '@vuelidate/core'
const app = createApp(App)

// app.use(VueCompositionApi);
app.use(VuelidatePlugin);
app.use(router);

app.mount('#app');


