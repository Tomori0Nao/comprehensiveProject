import type { RouterLink } from 'vue-router';
<script setup lang="ts">
import MallHeader from '../components/MallHeader.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { ElTable } from 'element-plus'

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
</script>

<template>
  <div >
    <el-table
      ref="multipleTableRef"
      :data="orderInfoTableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column label="商品" width="120">
        <template #default="scope">
          <el-avatar :src="scope.row.goodsPicture" :size="100" :fit="fit" shape="square" />
        </template>
      </el-table-column>
      <el-table-column property="goodsName" label="商品名" width="120" />
      <el-table-column property="orderAddr" label="收货地址" show-overflow-tooltip width="200" />
      <el-table-column property="storeName" label="店铺名" show-overflow-tooltip />
      <el-table-column property="goodsPrice" label="价格" show-overflow-tooltip />
      <el-table-column property="orderNo" label="订单编号" show-overflow-tooltip />
      <el-table-column label="数量">
        <template #default="scope">
          <span>{{ scope.row.goodsNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" show-overflow-tooltip>
        <template #default="scope">
          <el-button type="danger" @click.prevent="deleteGoods(scope.$index, scope.row)" plain
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped></style>
