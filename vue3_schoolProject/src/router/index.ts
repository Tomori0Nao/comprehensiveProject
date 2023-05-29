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
      path: '/cart',
      name: 'Cart',
      component: () => import('../views/CartView.vue')
    },
    {
      path: '/myInfo',
      name: 'MyInfo',
      component: () => import('../views/MyInfoView.vue')
    },
    {
      path: '/goodsInfo',
      name: 'goodsInfo',
      component: () => import('../views/GoodsInfoView.vue')
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/LogOut',
      name: 'LogOut',
      component: () => import('../views/LogOutView.vue')
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import('../views/Register.vue')
    },
    {
      path: '/UserInfoTest',
      name: 'UserInfoTest',
      component: () => import('../components/UserInfo.vue')
    },
    {
      path: '/Admin',
      // name: 'Admin',
      component: () => import('../views/AdminViewTest.vue'),
      children: [
        {
          path: 'Category',
          name: 'Category',
          component: () => import('../views/CategoryView.vue')
        }
      ]
    }
  ]
})

export default router
