import type { RouterLink } from 'vue-router';

<template>
  <header class="HomeHeader">
    <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false">
      <el-menu-item index="0">
        <RouterLink to="/">
          <img src="../assets/github3.svg" alt="logo" />
          <span class="Title">Git商城</span>
        </RouterLink>
      </el-menu-item>
      <el-menu-item index="1">
        <RouterLink to="/VIP">
          <img src="../assets/vip.svg" alt="logo" />
        </RouterLink>
      </el-menu-item>
      <el-menu-item index="2">
        <RouterLink to="/Cart">
          <img src="../assets/购物车.svg" alt="logo" />
        </RouterLink>
      </el-menu-item>
      <el-menu-item index="3">
        <RouterLink to="/MyInfo">
          <img src="../assets/我的.svg" alt="logo" />
        </RouterLink>
      </el-menu-item>
      <el-menu-item index="4">
        <el-input
          class="w-50 m-2 search"
          size="large"
          placeholder="Please Input"
          :prefix-icon="Search"
        />
      </el-menu-item>
      <el-menu-item index="5">
        <RouterLink to="/Login">
          <img src="../assets/登录.svg" alt="logo" />
        </RouterLink>
      </el-menu-item>
      <el-menu-item index="6">
        <RouterLink to="/LogOut">
          <img src="../assets/退出登录.svg" alt="logo" />
        </RouterLink>
      </el-menu-item>
    </el-menu>
  </header>
</template>

<script setup lang="ts">
import { Search } from '@element-plus/icons-vue'
import axios from 'axios'
axios.defaults.withCredentials = true
</script>

<style>
.Title {
  font-size: large;
  color: rgb(204, 232, 255);
  line-height: 2.5;
}
a {
  text-decoration: none;
}
</style>
