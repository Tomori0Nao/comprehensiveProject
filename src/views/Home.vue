


<template>
  <!-- <span>test</span> -->
  <div class="container">
    <header class="HomeHeader">
      <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false">
        <el-menu-item index="0">
          <RouterLink to="/Home">
            <img src="../assets/github3.svg" alt="logo">
            <span class="Title">Git商城</span>
          </RouterLink>
        </el-menu-item>
        <el-menu-item index="1">
          <RouterLink to="/VIP">
            <img src="../assets/vip.svg" alt="logo">
          </RouterLink>
        </el-menu-item>
        <el-menu-item index="2">
          <RouterLink to="/Cart">
            <img src="../assets/购物车.svg" alt="logo">
          </RouterLink>
        </el-menu-item>
        <el-menu-item index="3">
          <RouterLink to="/MyInfo">
            <img src="../assets/我的.svg" alt="logo">
          </RouterLink>
        </el-menu-item>
        <el-menu-item index="4">
          <el-input class="w-50 m-2 search" size="large" placeholder="Please Input" :prefix-icon="Search" />
        </el-menu-item>
        <el-menu-item index="5">
          <div class="isLogin" v-if="notLogin">
            <RouterLink to="/Login">
              <img src="../assets/登录.svg" alt="logo">
            </RouterLink>
          </div>
        </el-menu-item>
        <el-menu-item index="6">
          <RouterLink to="/LoginOut" v-if="notLogin">
            <img src="../assets/退出登录.svg" alt="logo">
          </RouterLink>
        </el-menu-item>
      </el-menu>
    </header>
    <el-card :body-style="{ padding: '5px' }" style="width: 210px">
      <img src="../assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png" class="image" />
      <div class="bottom">
        <span>s23 2023</span>
        <el-button text class="button">$6999</el-button>
      </div>
  </el-card>
  <el-carousel :interval="4000" type="card" height="200px">
      <!-- <el-carousel-item v-for="item in 6" :key="item">
                                                                                                                                                              <h3 text="2xl" justify="center">{{ item }}</h3>
                                                                                                                                                              </el-carousel-item> -->
      <el-carousel-item :key="1">
        <img src="../assets/logo.svg" alt="">
        <!-- <img src="../assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png" alt=""> -->
      </el-carousel-item>
      <el-carousel-item :key="2">
        <img src="../assets/github3.svg" alt="">
      </el-carousel-item>
      <el-carousel-item :key="3">
        <img src="../assets/我的.svg" alt="">
      </el-carousel-item>
      <el-carousel-item :key="4">
        <img src="../assets/登录.svg" alt="">
      </el-carousel-item>
      <el-carousel-item :key="5">
        <img src="../assets/购物车.svg" alt="">
      </el-carousel-item>
      <el-carousel-item :key="6">
        <img src="../assets/退出登录.svg" alt="">
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script lang="ts">

import type { RouterLink } from 'vue-router';
import { Search } from '@element-plus/icons-vue'
import { onMounted, } from "vue";
export default {
  data() {
    return {
      notLogin: true,
    }
  },
  setup() {
    onMounted(() => {
      //进入首页应当检查用户是否登录

    })

    //组件挂载之前检查用户是否登录
  },

}

</script>

<style>
.container {
  padding-left: 10%;
  padding-right: 10%;
}

.HomeHeader {
  background-color: rgb(225, 117, 117);
}

.Title {
  font-size: large;
  color: rgb(204, 232, 255);
  line-height: 2.5;
}

a {
  text-decoration: none;
}

.search {
  margin-top: 5%;
}

.bottom {
  padding-left: 20%;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  /* line-height: 200px; */
  margin: 0;
  text-align: center;
}
</style>
  