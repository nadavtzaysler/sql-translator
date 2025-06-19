import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import HelpView from '../views/HelpView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/help',
    name: 'help',
    component: HelpView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
