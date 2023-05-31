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
        },
        {
          path: 'Goods',
          name: 'Goods',
          component: () => import('../views/GoodsAdminView.vue')
        },
        {
          path: 'User',
          name: 'User',
          component: () => import('../views/UserAdminView.vue')
        },
        {
          path: 'Order',
          name: 'Order',
          component: () => import('../views/OrderAdminView.vue')
        },
        {
          path: 'AdminManage',
          name: 'AdminManage',
          component: () => import('../views/AdminManageView.vue')
        },
        {
          path: 'AddGoods',
          name: 'AddGoods',
          component: () => import('../views/AddGoodsInfoView.vue')
        },
        {
          path: 'OrderDetail',
          name: 'OrderDetail',
          component: () => import('../views/OrderDetailView.vue')
        },
        {
          path: 'EditGoods',
          name: 'EditGoods',
          component: () => import('../views/EditGoodsInfoView.vue')
        },
      ]
    }
  ]
})

export default router
