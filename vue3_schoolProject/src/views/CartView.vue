import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <MallHeader></MallHeader>
    <el-divider content-position="left">共有{{ tableData.length }}件商品</el-divider>
    <el-table ref="multipleTableRef" :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" />
      <el-table-column label="商品" width="120">
        <template #default="scope">
          <el-avatar :src="scope.row.picture" :size="100" :fit="fit" shape="square" />
        </template>
      </el-table-column>
      <el-table-column property="goodsName" label="商品名" width="120" />
      <el-table-column property="storeName" label="店铺名" show-overflow-tooltip width="300" />
      <el-table-column property="goodsPrice" label="单价" show-overflow-tooltip />
      <el-table-column property="vipPrice" label="减免" show-overflow-tooltip />

      <el-table-column property="totalCost" label="总计" show-overflow-tooltip />

      <el-table-column label="数量">
        <template #default="scope">
          <el-button size="small" @click.prevent="handleMinus(scope.$index, scope.row)" circle>
            <el-icon>
              <Minus />
            </el-icon>
          </el-button>
          <el-input v-model="scope.row.purchaseNumber" placeholder="1" class="numberInput"
            @input="inputChanges(scope.row)" />

          <el-button size="small" type="danger" @click.prevent="handlePlus(scope.$index, scope.row)" circle>
            <el-icon>
              <Plus />
            </el-icon>
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" show-overflow-tooltip>
        <template #default="scope">
          <el-popconfirm width="220" confirm-button-text="是的" cancel-button-text="否" :icon="InfoFilled"
            icon-color="#626AEF" title="将此商品从购物车中删除吗?" @confirm="deleteGoods(scope.$index, scope.row)">
            <template #reference>
              <el-button type="danger" plain>删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px">
      <el-popconfirm width="220" confirm-button-text="是的" cancel-button-text="否" :icon="InfoFilled" icon-color="#626AEF"
        title="现在去结算吗?" @confirm="goToPay">
        <template #reference>
          <el-button>去结算</el-button>
        </template>
      </el-popconfirm>
      <el-popconfirm width="220" confirm-button-text="是的" cancel-button-text="否" :icon="InfoFilled" icon-color="#626AEF"
        title="将所有商品从购物车中删除吗?" @confirm="clearCart">
        <el-dialog v-model="dialogFormVisible" title="收货地址选择" center width="40%">
          <el-select v-model="addres" class="m-2 select" placeholder="Select">
            <el-option v-for="item in addressList" :key="item.addressNo" :label="item.address" :value="item.addressNo" />
          </el-select>
          <el-button type="primary" @click="onSubmit">提交</el-button>
          <el-button @click="onCancel">取消</el-button>
      </el-dialog>
      <!-- <el-popconfirm
        width="220"
        confirm-button-text="是的"
        cancel-button-text="否"
        :icon="InfoFilled"
        icon-color="#626AEF"
        title="将所有商品从购物车中删除吗?"
        @confirm="clearCart"
      >
>>>>>>> 4065a7ee36f5478b93ed7349f999f4a16b3d6c8e
        <template #reference>
          <el-button>清空购物车</el-button>
                </template>
              </el-popconfirm> -->
        <div class="sum">
          <img src="../assets/金额.svg" alt="" />
          {{ cartSum }}
        </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MallHeader from '../components/MallHeader.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { ElTable } from 'element-plus'
import { InfoFilled } from '@element-plus/icons-vue'
import { Minus, Plus } from '@element-plus/icons-vue'
import axios from 'axios'
import { fa } from 'element-plus/es/locale/index.js'
axios.defaults.withCredentials = true

const dialogFormVisible = ref(false)

const userAccount = window.localStorage.getItem('userAccount')
const fit: string = 'fill'
// ---- 商品信息
// 商品名
// 商品单价
// 店铺名
// 商品数量
// 总花费
// 商品图片
// 购买数量
// 会员减免
interface GoodsInfo {
  goodsNo: string,
  goodsName: string
  goodsPrice: number
  storeName: string
  goodsNumber: number
  totalCost: number
  picture: string
  purchaseNumber: number
  vipPrice: number
}
interface Address {
  addressNo: string
  consigneeName: string
  consigneeTel: string
  address: string
}
interface OrderSet {
  goodsNo: string
  goodsNumber: number
}
const orderList = ref<OrderSet[]>([])
const addressList: Ref<Address[]> = ref([])
const getOrderSet = () => {
  for (const iterator of multipleSelection.value) {
    let tem: OrderSet = {
      goodsNo: '',
      goodsNumber: 0,
    }
    tem.goodsNo = iterator.goodsNo
    tem.goodsNumber = iterator.goodsNumber
    orderList.value.push(tem)
  }
}
const addres = ref('')

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<GoodsInfo[]>([])
const cartSum: Ref<number> = ref(0)
const handleSelectionChange = (val: GoodsInfo[]) => {
  multipleSelection.value = val
  console.log(multipleSelection, 'hello')
  cartSum.value = 0
  for (const iterator of multipleSelection.value) {
    console.log('item is ', iterator)
    console.log('money is ', iterator.totalCost)
    cartSum.value += iterator.totalCost
  }
}

const onSubmit = () => {
  // 
  // 商品编号 商品数量 收货地址编号 
  dialogFormVisible.value = false
}
const onCancel = () => {
  dialogFormVisible.value = false

}

const handleMinus = (index: number, row: GoodsInfo) => {
  if (row.purchaseNumber < 2) {
  } else {
    row.purchaseNumber -= 1
    row.totalCost = row.totalCost - (row.goodsPrice - row.vipPrice)
    cartSum.value = 0
    for (const iterator of multipleSelection.value) {
      console.log('item is ', iterator)
      console.log('money is ', iterator.totalCost)
      cartSum.value += iterator.totalCost
    }
  }
}
const handlePlus = (index: number, row: GoodsInfo) => {
  if (row.purchaseNumber >= row.goodsNumber) {
  } else {
    row.purchaseNumber += 1
    row.totalCost = row.totalCost + (row.goodsPrice - row.vipPrice)
    cartSum.value = 0
    for (const iterator of multipleSelection.value) {
      console.log('item is ', iterator)
      console.log('money is ', iterator.totalCost)
      cartSum.value += iterator.totalCost
    }
  }
}
const inputChanges = (row: GoodsInfo) => {
  row.purchaseNumber = parseInt(row.purchaseNumber + '')
  console.log(row, 'row is ')
  if (row.purchaseNumber > row.goodsNumber) {
    row.purchaseNumber = row.goodsNumber
  } else if (row.purchaseNumber < 1) {
    row.purchaseNumber = 1
  }
  row.totalCost = row.purchaseNumber * (row.goodsPrice - row.vipPrice)
}
const deleteGoods = (index: number, row: GoodsInfo) => {
  console.log(index, row)
  axios({
    method: 'get',
    url: path + '/deleteCartGoods',
    params: {
      goodsNo: tableData.value[index].goodsNo,
    }
  }).then((response) => {
    const respData = response.data;
    console.log(respData);
  }).catch((error) => {
    console.log("error = " + error);

  })
  tableData.value.splice(index, 1)
  console.log(tableData.value)
}
const goToPay = () => {
  //
  axios({
    method: 'get',
    url: path + '/pay',
    params: {

    }
  }).then((response) => {
    const respData = response.data;
    console.log(respData);
  }).catch((error) => {
    console.log("error = " + error);

  })
  getOrderSet()
  getAddressList()
  // orderList 数组
  // goodsNo: string 
  // goodsNumber:number

  // address 地址
  // axios

  dialogFormVisible.value = true
  console.log(multipleSelection)
}
// const clearCart = () => {
//   tableData.value = []
// }
const tableData: Ref<GoodsInfo[]> = ref([])

////////////////////////////////////////////////
const path = 'http://localhost:8080'
axios({
  method: 'get',
  url: path + '/shoppingCart',
  params: {
    // account: '111'
    account: userAccount
  }
})
  .then((response) => {
    const imagesURLPrefix = 'http://localhost:8080/static/images/'
    const respData = response.data
    let cartGoodsList = respData.data
    for (const iterator of cartGoodsList) {
      let tem: GoodsInfo = {
        goodsNo: '',
        goodsName: '',
        goodsPrice: 0,
        storeName: '',
        goodsNumber: 0,
        totalCost: 0,
        picture: '',
        purchaseNumber: 0,
        vipPrice: 0
      }
      tem.goodsNo = iterator.cartGoodsNo
      tem.goodsName = iterator.cartGoodsName
      tem.goodsPrice = Number(iterator.cartGoodsPrice)
      tem.storeName = iterator.cartGoodsStoreName
      tem.goodsNumber = Number(iterator.maxNumOfSinglePurchase)
      tem.totalCost = Number(iterator.totalCost)
      tem.picture = imagesURLPrefix + iterator.cartGoodsImageName + '.png'
      tem.purchaseNumber = Number(iterator.cartGoodsNumber)
      tem.vipPrice = Number(iterator.vipDerate)
      tableData.value.push(tem)
    }
    ///////////////////////////////////////////////////////////////////////////////
    ///    收到的数据
    ///    cartGoodsName	"金玉兰白酒"
    //     cartGoodsImageName	"homeGoods-1"
    //     cartGoodsStoreName	"store001"
    //     cartGoodsPrice	"169.89"  商品标价
    //     totalCost	"56.36"   总共花费
    //     cartGoodsNumber	100  该商品购买的数目
    //     maxNumOfSinglePurchase	100   该商品单次购买的最大数目
    //     vipDerate	"10.36"   vip减免
    ////////////////////////////////////////////////////////////////////////////
    console.log(cartGoodsList, 'cartList')
  })
  .catch((error) => {
    console.log('error = ' + error)
  })
const getAddressList = () => {
  axios({
    method: 'get',
    url: path + '/deliveryAddress',
    params: {
      account: '111'
    }
  })
    .then((response) => {
      const respData = response.data
      const addresResList = respData.data
      addressList.value = []
      for (const iterator of addresResList) {
        let tem: Address = {
          addressNo: '',
          consigneeName: '',
          consigneeTel: '',
          address: ''
        }
        tem.addressNo = iterator.addressNo
        tem.consigneeName = iterator.consigneeName
        tem.consigneeTel = iterator.consigneeTel
        tem.address = iterator.address
        addressList.value.push(tem)
        console.log('tem is ', tem)
      }
      console.log(addressList, 'addlist')
    })
    .catch((error) => {
      console.log('error111=' + error)
    })
}
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
  /* width: 480px; */
  display: inline-block;
}

.select {
  width: 300px;
}
</style>
