import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <MallHeader></MallHeader>
    <el-card class="box-card">
      <div class="goodsContainer">
        <img :src="goodsInfo.goodsImageName" alt="" />
        <div class="goodsInfo">
          <p class="goodsName">
            <span>商品名: {{ goodsInfo.goodsName }}</span>
          </p>
          <p>价格: {{ goodsInfo.goodsPrice }}</p>
          <p>店铺名: {{ goodsInfo.storeName }}</p>
          <p>剩余商品数量: {{ goodsInfo.goodsNumber }}</p>
          <el-button size="small" @click.prevent="handleMinus" circle>
            <el-icon>
              <Minus />
            </el-icon>
          </el-button>
          <el-input v-model="orderNumber" @input="inputChanges" placeholder="1" class="numberInput" />
          <el-button size="small" type="danger" @click.prevent="handlePlus" circle>
            <el-icon>
              <Plus />
            </el-icon>
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
import axios from 'axios'
const fit: string = 'fill'
const picture: string =
  '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'

// ----商品信息
// 商品名称
// 商品价格
// 店铺名称
// 商品数量
// 品牌
// 商品编号
// 商品重量
// 商品产地
interface GoodsInfo {
  goodsName: string
  goodsImageName: string
  goodsPrice: number
  storeName: string
  goodsNumber: number
  goodsBrand: string
  goodsNo: string
  goodsWeight: string
  goodsProducingArea: string
}
const goodsInfo: GoodsInfo = {
  goodsName: '三星 s233',
  goodsImageName: picture,
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

/**
 * 请求商品详细信息
 *   {
    "code": 1,
    "msg": "sh001商品信息请求成功",
    "data": {
        "goodsNO": "sh001",                   商品编号
        "goodsPrice": "169.89",               商品价格
        "storeName": "store001",              商品店铺名字
        "goodsOrigin": "重庆市",              商品生产源地
        "goodsName": "金玉兰白酒",            商品名称
        "goodsNum": 100,                      商品数量
        "goodsWeight": "500g",                商品重量
        "goodsBrand": "金玉兰"                商品品牌
        "goodsImageName" "homeGoods-1"         商品图片名称
     }
}
 * 
 *
 */
const path = 'http://localhost:8080';
axios({
  method: 'get',
  url: path + '/goodsInfo',
  params: {
    goodsNo: 'sh001',
  }
}).then((response) => {
  const respData = response.data;
  var goods = respData.data;
  console.log(goods);
  //我这样赋值失败了
  //goodsInfo.goodsName = respData.goodsName,
  //goodsInfo.goodsPrice = respData.goodsName,
  // goodsInfo.storeName = respData.goodsName
  // goodsInfo.goodsNumber: 100,
  // goodsInfo.goodsBrand: '三星',
  // goodsInfo.goodsNo: '00001111',
  // goodsInfo.goodsWeight: '0.89kg',
  // goodsInfo.goodsProducingArea: '中国大陆'

}).catch((error) => {
  console.log('error = ' + error);

})

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
