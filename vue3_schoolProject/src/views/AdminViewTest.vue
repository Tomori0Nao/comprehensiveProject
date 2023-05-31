<template>
  <div class="layout">
    <el-container v-if="true" class="container">
      <el-aside class="aside">
        <div class="head">
          <div>
            <img src="/src/assets/github3.svg" alt="logo" />
            <span>git admin</span>
          </div>
        </div>
        <div class="line" />
        <el-menu background-color="#409EFF" text-color="#fff" active-text-color="#c6e2ff" :router="true">
          <el-sub-menu index="1">
            <template #title>
              <span>商品管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/Category">商品类型</el-menu-item>
              <el-menu-item index="/admin/Goods">商品信息</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <span>用户管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/User">用户信息</el-menu-item>
              <el-menu-item index="/admin/Order">订单</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>
              <span>管理员管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/AdminManage">普通管理员管理</el-menu-item>
              <el-menu-item index="/admin/ChangePasswd">修改密码</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container class="content">
        <div class="main">
          <router-view />
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

axios.defaults.withCredentials = true


// const router = useRouter()
// router.beforeEach(async (to, from) => {
//   console.log(window.localStorage.getItem('userAccount'))
//     // 如果不是 /login，判断是否有 token
//     if (!window.localStorage.getItem('adminAccount')&&to.name !== 'Login') {
//       // 如果没有，则跳至登录页面
//       return { name: 'Login' }
//     } else {
//       // 否则继续执行
//     }
//   }
// )
</script>

<style scoped>
.layout {
  min-height: 100vh;
  background-color: #ffffff;
}

.container {
  height: 100vh;
}

.aside {
  width: 200px !important;
  background-color: #409eff;
}

.head {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
}

.head>div {
  display: flex;
  align-items: center;
}

.head img {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}

.head span {
  font-size: 20px;
  color: #ffffff;
}

.line {
  border-top: 1px solid hsla(0, 0%, 100%, 0.05);
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}

.content {
  display: flex;
  flex-direction: column;
  max-height: 100vh;
  overflow: hidden;
}

.main {
  height: calc(100vh - 100px);
  overflow: auto;
  padding: 10px;
}

a {
  color: #409eff;
  text-decoration: none;
}</style>
