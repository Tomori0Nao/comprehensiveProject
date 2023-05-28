
import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <MallHeader></MallHeader>
    <SlideShow></SlideShow>
    <el-divider content-position="left">精选好物</el-divider>
    <GoodsCol ></GoodsCol>

  </div>
</template>
  
<script setup lang="ts">
import SlideShow from "../components/Slideshow.vue"
import GoodsCol from "../components/GoodsCol.vue"
import MallHeader from '../components/MallHeader.vue';
</script>

<style>
.container {
  padding-left: 10%;
  padding-right: 10%;
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
  margin: 0;
  text-align: center;
}
</style>
  