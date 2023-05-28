import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <MallHeader></MallHeader>
    <el-tabs tab-position="left" class="demo-tabs">
      <el-tab-pane label="我的订单"
        >我的订单
        <OrderList></OrderList>
      </el-tab-pane>

      <el-tab-pane label="账号管理"
        >账号管理
        <el-tabs type="border-card">
          <el-tab-pane label="基本信息">
            <UserInfo></UserInfo>
          </el-tab-pane>
          <el-tab-pane label="登录历史">登录历史</el-tab-pane>
        </el-tabs>
      </el-tab-pane>
      <el-tab-pane label="地址管理"><AddressAdmin></AddressAdmin></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup lang="ts">
import MallHeader from '../components/MallHeader.vue'
import UserInfo from '@/components/UserInfo.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { ElTable } from 'element-plus'
import axios from 'axios'
import OrderList from '@/components/OrderList.vue'
import AddressAdmin from '@/components/addressAdmin.vue'

// ----订单信息
// 用户账号
// 订单编号
// 收货地址
// 商品价格
// 商品名称
// 商品图片
// 店铺名称
interface OrderInfo {
  userAccount: string
  orderNo: string
  orderDate: string
  orderAddr: string
  goodsPrice: number
  goodsName: string
  goodsNum: number
  goodsPicture: string
  storeName: string
}
// ----用户信息
// 用户账号
// 用户电话
// 注册日期
// 上次登录时间
// 用户总消费额
// 用户余额
// 用户头像
interface UserAccountInfo {
  userAccount: string
  userTel: string
  registerDate: string
  lastLoginTime: string
  userTotalConsumption: number
  userMoney: number
  userAvatar: string
}

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<OrderInfo[]>([])
const fit: string = 'fill'

const handleSelectionChange = (val: OrderInfo[]) => {
  multipleSelection.value = val
}

const deleteGoods = (index: number, row: OrderInfo) => {
  console.log(index, row)
  orderInfoTableData.value.splice(index, 1)
  console.log(orderInfoTableData.value)
}

const orderInfoTableData: Ref<OrderInfo[]> = ref([
  {
    userAccount: '1389478935',
    orderNo: '147567814',
    orderDate: '2023-5-24',
    orderAddr: '重庆市巴南区红光大道',
    goodsPrice: 6999,
    goodsName: '三星s23',
    goodsNum: 2,
    goodsPicture: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png',
    storeName: 'git商城'
  }
])
const userAccountInfo: Ref<UserAccountInfo> = ref({
  userAccount: '1389478935',
  userTel: '100860086000',
  registerDate: '2023-5-24',
  lastLoginTime: '2023-5-24',
  userTotalConsumption: 6999,
  userMoney: 8888,
  userAvatar: '/src/assets/github3.svg'
})
/**
 * 请求订单数据
 */
const path = 'http://localhost:8080/'
axios({
  method: 'get'
})
  .then((response) => {})
  .catch((error) => {
    console.log('error = ' + error)
  })
</script>

<style scoped>
.container {
  padding-left: 10%;
  padding-right: 10%;
}

.numberInput {
  display: inline-block;
  width: 50px;
}

.sum {
  display: inline-block;
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
