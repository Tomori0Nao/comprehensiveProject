import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/test',
      name: 'test',
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/VIP',
      name: 'VIP',
      component: () => import('../views/VIPView.vue')
    },
    {
      path: '/Cart',
      name: 'Cart',
      component: () => import('../views/CartView.vue')
    },
    {
      path: '/MyInfo',
      name: 'MyInfo',
      component: () => import('../views/MyInfoView.vue')
    },
    {
      path: '/goodsInfo',
      name: 'goodsInfo',
      component: () => import('../views/GoodsInfoView.vue')
    },
    {
      path: '/Login',
      name: 'Login',
      component: () => import('../views/LoginView.vue')
    },
    {
      path: '/LogOut',
      name: 'LogOut',
      component: () => import('../views/LogOutView.vue')
    }
  ]
})

export default router
