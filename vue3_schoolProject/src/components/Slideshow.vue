<script setup lang="ts">
</script>

<template>
  <div >
    <el-carousel :interval="4000" type="card" height="470px">
        <el-carousel-item :key="1">
          <img justify="center" src="../assets/picture_One (自定义).jpg" alt="" >
        </el-carousel-item>
        <el-carousel-item :key="2">
          <img justify="center" src="../assets/picture_two (自定义).jpg" alt="">
        </el-carousel-item>
        <el-carousel-item :key="3">
          <img justify="center" src="../assets/picture_three (自定义).png" alt="">
        </el-carousel-item>
        <el-carousel-item :key="4">
          <img justify="center" src="../assets/picture_four (自定义).jpg" alt="">
        </el-carousel-item>
      </el-carousel>
  </div>
</template>

<style scoped>

</style>
