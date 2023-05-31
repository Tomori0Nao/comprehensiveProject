import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <el-descriptions class="margin-top" title="订单详情" :column="3" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">订单编号</div>
        </template>
        {{ orderInfo.orderNo }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">下单时间</div>
        </template>
        {{ orderInfo.orderDate }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品编号</div>
        </template>
        {{ orderInfo.goodsNo }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品名称</div>
        </template>
        {{ orderInfo.goodsName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">商品数量</div>
        </template>
        {{ orderInfo.goodsNumber }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">收货地址</div>
        </template>
        {{ orderInfo.address }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">会员减免</div>
        </template>
        {{ orderInfo.vipPrice }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">实际付款</div>
        </template>
        {{ orderInfo.totalCost }}
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Minus, Plus } from '@element-plus/icons-vue'
import axios from 'axios'
const fit: string = 'fill'
const picture: string =
  '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'


//----订单信息
// 订单编号
// 下单时间
// 商品编号
// 商品名称
// 订单商品数量
// 收货地址
// 用户账号
// 商品图片
// 店铺名称
// 减免
// 实付款
interface OrderInfo {
  orderNo: string
  orderDate: string
  goodsNo: string
  goodsName: string
  goodsNumber: number
  address: string
  userAccount: string
  goodsPicture: string
  storeName: string
  vipPrice: number
  totalCost: number
}
const orderInfo = ref<OrderInfo>({
  orderNo: '',
  orderDate: '',
  goodsNo: '',
  goodsName: '',
  goodsNumber: 0,
  address: '',
  userAccount: '',
  goodsPicture: '',
  storeName: '',
  vipPrice: 0,
  totalCost: 0
})
let url = window.location.href
let para = url.split('?').at(1)?.split('=').at(1)
const path = 'http://localhost:8080'
axios({
  method: 'get',
  url: path + '/getOrderDetailInfo',
  params: {
    orderNo: para,
  }
}).then((response) => {
  const respData = response.data;
  console.log(respData);

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
