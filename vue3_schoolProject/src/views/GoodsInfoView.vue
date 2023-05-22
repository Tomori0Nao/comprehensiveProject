import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <MallHeader></MallHeader>
    <el-card class="box-card ">
      <div class="goodsContainer">
        <img :src="picture" alt="" />
        <div class="goodsInfo">
          <p class="goodsName">
            <span>商品名: {{ goodsInfo.goodsName }}</span>
          </p>
          <p>价格: {{ goodsInfo.goodsPrice }}</p>
          <p>店铺名: {{ goodsInfo.storeName }}</p>
          <p>剩余商品数量: {{ goodsInfo.goodsNumber }}</p>
          <el-button size="small" @click.prevent="handleMinus" circle>-1</el-button>
          <el-input v-model="orderNumber" @input="inputChanges" placeholder="1" class="numberInput" />
          <el-button size="small" type="danger" @click.prevent="handlePlus" circle> +1 </el-button>
        </div>
        <div class="shopping">
          <el-button  size="large" type="danger"  @click.prevent="addToCart" plain> 加入购物车 </el-button>
        </div>
      </div>
    </el-card>
    <el-divider content-position="left">相似商品推荐</el-divider>
    <GoodsCol></GoodsCol>
  </div>
</template>

<script setup lang="ts">
import GoodsCol from '@/components/GoodsCol.vue';
import MallHeader from '../components/MallHeader.vue'
import { ref } from "vue";
const fit: string = 'fill'
const picture: string =
  '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'
interface GoodsInfo {
  goodsName: string
  goodsPrice: number
  storeName: string
  goodsNumber: number
}
const goodsInfo: GoodsInfo = {
  goodsName: '三星 s23',
  goodsPrice: 6999,
  storeName: 'git商城自营店',
  goodsNumber: 100
}
var orderNumber = ref(1)
const handleMinus = () => {
  console.log(orderNumber)
  if (orderNumber.value < 1) {
    
  }else {
    orderNumber.value -= 1
  }
}
const handlePlus = () => {
  console.log(orderNumber)

  if (orderNumber.value >= goodsInfo.goodsNumber) {
    
  }else {
    orderNumber.value += 1
  }
}
const inputChanges = () => {
  console.log(orderNumber.value)
  if (orderNumber.value > goodsInfo.goodsNumber) {
    orderNumber.value = goodsInfo.goodsNumber
  }
}
const addToCart = () => {
  console.log('added to cart')
}
</script>

<style scoped>
.container {
  padding-left: 10%;
  padding-right: 10%;
}

.goodsInfo {
  padding-top: 12px;
  padding-right: 0px;
  padding-bottom: 0px;
  padding-left: 25px;
  display: inline-block;
}
.numberInput {
  display: inline-block;
  width: 50px;
}
.shopping {
  display: inline-block;
}
.goodsContainer {
  display: inline-block;
}
a {
  text-decoration: none;
}
span {
  text-decoration: none;

}
</style>
