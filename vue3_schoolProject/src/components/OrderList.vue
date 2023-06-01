import type { RouterLink } from 'vue-router';
<script setup lang="ts">
import MallHeader from '../components/MallHeader.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { ElTable } from 'element-plus'
import axios from 'axios'
axios.defaults.withCredentials = true

// ----订单信息
// 用户账号
// 订单编号
// 收货地址
// 商品价格
// 商品名称
// 商品数量
// 商品图片
// 店铺名称
// 减免
// 实付款
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
  vipPrice: number
  totalCost: number
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
const path = 'http://localhost:8080'
const deleteGoods = (index: number, row: OrderInfo) => {
  console.log(index, row)
  /**
   * 向后端请求删除操作
   */
  axios({
    method: 'get',
    url: path + '/deleteUserOrder',
    //account参数实际上不用传递，因为后端可以在session中获得
    //但是此处为了测试，暂时加上account属性，
    params: {
      //每个用户的每一个订单的编号都是唯一的，因此只需要发送订单编号即可
      //orderNo: orderInfoTableData.value[index].orderNo,
      orderNo: row.orderNo //先用一个写好的值测试
    }
  })
    .then((response) => {
      const respData = response.data

      if (respData.code == 1) {
        orderInfoTableData.value.splice(index, 1)
      } else {
        console.log(respData.msg)
      }
    })
    .catch((error) => {
      console.log('error = ' + error)
    })
  console.log(orderInfoTableData.value)
}

const orderInfoTableData: Ref<OrderInfo[]> = ref([
  // {
  //   userAccount: '1389478935',
  //   orderNo: '147567814',
  //   orderDate: '2023-5-24',
  //   orderAddr: '重庆市巴南区红光大道',
  //   goodsPrice: 6999,
  //   goodsName: '三星s23',
  //   goodsNum: 2,
  //   goodsPicture: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png',
  //   storeName: 'git商城',
  //   vipPrice: 10,
  //   totalCost: 2887
  // }
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
 * 加载页面时，将用户的订单信息加载出来
 *  "data": [
        {
            "userAccount": null,                     用户账号不需要
            "orderNo": "order1",                     订单编号
            "orderTime": "2023-12-14 15:30:06",      下单时间
            "deliveryAddress": "重庆市巴南社区",      下单时收货地址
            "orderPrice": "12.3",                     下单时价格
            "orderDerate": "1.33",                    下单时优惠额
            "actualPayment": "100.96",                实际总付款
            "goodsName": "牛别克手机",                商品名字
            "goodsNumber": 2,                        购买数量
            "goodsImageName": "homeGoods-1",         商品图片
            "storeName": "上酒乐乐公司"              商品所属的店铺名
        },
 */
const imagesURLPrefix = 'http://localhost:8080/static/images/'
axios({
  method: 'get',
  url: path + '/userOrder',
  //这个参数实际上不用传递，因为后端可以在session中获得
  //但是此处为了测试，暂时加上account属性，
  params: {
    account: '111'
  }
})
  .then((response) => {
    const respData = response.data
    console.log(respData)
    var orderList = respData.data
    orderInfoTableData.value = []
    for (const iterator of orderList) {
      let tem: OrderInfo = {
        userAccount: '',
        orderNo: '',
        orderDate: '',
        orderAddr: '',
        goodsPrice: 0,
        goodsName: '',
        goodsNum: 0,
        goodsPicture: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png',
        storeName: '',
        vipPrice: 0,
        totalCost: 0
      }
      tem.orderNo = iterator.orderNo
      tem.orderDate = iterator.orderTime
      tem.orderAddr = iterator.deliveryAddress
      tem.goodsPrice = iterator.orderPrice
      tem.goodsName = iterator.goodsName
      tem.goodsNum = iterator.goodsNumber
      tem.storeName = iterator.storeName
      tem.vipPrice = iterator.orderDerate
      tem.totalCost = iterator.actualPayment
      tem.goodsPicture = imagesURLPrefix + iterator.goodsImageName + '.png'
      orderInfoTableData.value.push(tem)
    }
  })
  .catch((error) => {
    console.log('error = ' + error)
  })
</script>

<template>
  <div>
    <el-table ref="multipleTableRef" :data="orderInfoTableData" style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column label="商品" width="120">
        <template #default="scope">
          <el-avatar :src="scope.row.goodsPicture" :size="100" :fit="fit" shape="square" />
        </template>
      </el-table-column>
      <el-table-column property="goodsName" label="商品名" width="120" />
      <el-table-column property="orderAddr" label="收货地址" show-overflow-tooltip width="200" />
      <el-table-column property="storeName" label="店铺名" show-overflow-tooltip />
      <el-table-column property="goodsPrice" label="单价" show-overflow-tooltip />
      <el-table-column property="vipPrice" label="减免" show-overflow-tooltip />
      <el-table-column property="orderNo" label="订单编号" show-overflow-tooltip />
      <el-table-column property="totalCost" label="实付款" show-overflow-tooltip />
      <el-table-column label="数量">
        <template #default="scope">
          <span>{{ scope.row.goodsNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" show-overflow-tooltip>
        <template #default="scope">
          <el-button type="danger" @click.prevent="deleteGoods(scope.$index, scope.row)" plain>删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped></style>
