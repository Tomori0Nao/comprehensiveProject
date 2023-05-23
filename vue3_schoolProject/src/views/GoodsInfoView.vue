import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <MallHeader></MallHeader>
    <el-card class="box-card">
      <div class="goodsContainer">
        <img :src="picture" alt="" />
        <div class="goodsInfo">
          <p class="goodsName">
            <span>商品名: {{ goodsInfo.goodsName }}</span>
          </p>
          <p>价格: {{ goodsInfo.goodsPrice }}</p>
          <p>店铺名: {{ goodsInfo.storeName }}</p>
          <p>剩余商品数量: {{ goodsInfo.goodsNumber }}</p>
          <el-button size="small" @click.prevent="handleMinus" circle>
            <el-icon><Minus /></el-icon>
          </el-button>
          <el-input
            v-model="orderNumber"
            @input="inputChanges"
            placeholder="1"
            class="numberInput"
          />
          <el-button size="small" type="danger" @click.prevent="handlePlus" circle>
            <el-icon><Plus /></el-icon>
          </el-button>
        </div>
        <div class="shopping">
          <el-button size="large" type="danger" @click.prevent="addToCart" plain>
            加入购物车
          </el-button>
        </div>
      </div>
    </el-card>

    <el-descriptions class="margin-top" title="商品介绍" :column="3" border>
      <!-- <template #extra>
      <el-button type="primary">Operation</el-button>
    </template> -->
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品名称</div>
        </template>
        {{ goodsInfo.goodsName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品价格</div>
        </template>
        {{ goodsInfo.goodsPrice }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">店铺名</div>
        </template>
        {{ goodsInfo.storeName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">品牌</div>
        </template>
        {{ goodsInfo.goodsBrand }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品编号</div>
        </template>
        {{ goodsInfo.goodsNo }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品毛重</div>
        </template>
        {{ goodsInfo.goodsWeight }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品产地</div>
        </template>
        {{ goodsInfo.goodsProducingArea }}
      </el-descriptions-item>
    </el-descriptions>
    <el-divider content-position="left">相似商品推荐</el-divider>
    <GoodsCol></GoodsCol>
  </div>
</template>

<script setup lang="ts">
import GoodsCol from '@/components/GoodsCol.vue'
import MallHeader from '../components/MallHeader.vue'
import { ref } from 'vue'
import { Minus, Plus } from '@element-plus/icons-vue'
const fit: string = 'fill'
const picture: string =
  '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'
interface GoodsInfo {
  goodsName: string
  goodsPrice: number
  storeName: string
  goodsNumber: number
  goodsBrand: string
  goodsNo: string
  goodsWeight: string
  goodsProducingArea: string
}
const goodsInfo: GoodsInfo = {
  goodsName: '三星 s23',
  goodsPrice: 6999,
  storeName: 'git商城自营店',
  goodsNumber: 100,
  goodsBrand: '三星',
  goodsNo: '00001111',
  goodsWeight: '0.89kg',
  goodsProducingArea: '中国大陆'
}
var orderNumber = ref(1)
const handleMinus = () => {
  console.log(orderNumber)
  if (orderNumber.value < 1) {
  } else {
    orderNumber.value -= 1
  }
}
const handlePlus = () => {
  console.log(orderNumber)

  if (orderNumber.value >= goodsInfo.goodsNumber) {
  } else {
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
.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}
</style>
