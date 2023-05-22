import type { RouterLink } from 'vue-router';

<template>
  <div class="container">
    <MallHeader></MallHeader>
    <el-divider content-position="left">共有{{ tableData.length }}件商品</el-divider>
    <el-table
      ref="multipleTableRef"
      :data="tableData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column label="Date" width="120">
        <template #default="scope">
          <el-avatar :src="scope.row.picture" :size="100" :fit="fit" shape="square" />
        </template>
      </el-table-column>
      <el-table-column property="name" label="商品名" width="120" />
      <el-table-column property="address" label="店铺名" show-overflow-tooltip width="300" />
      <el-table-column property="price" label="单价" show-overflow-tooltip />
      <el-table-column property="totalCost" label="总计" show-overflow-tooltip />

      <el-table-column label="数量">
        <template #default="scope">
          <el-button size="small" @click.prevent="handleMinus(scope.$index, scope.row)" circle
            >-1
          </el-button>
          <el-input v-model="scope.row.number" placeholder="1" class="numberInput" />
          <el-button
            size="small"
            type="danger"
            @click.prevent="handlePlus(scope.$index, scope.row)"
            circle
          >
            +1
          </el-button>
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
    <div style="margin-top: 20px">
      <el-button @click.prevent="goToPay">去结算</el-button>
      <el-button @click.prevent="clearCart">清空购物车</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import MallHeader from '../components/MallHeader.vue'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { ElTable } from 'element-plus'
const fit: string = 'fill'
interface User {
  date: string
  name: string
  address: string
  price: number
  number: number
  totalCost: number
  picture: string
}

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<User[]>([])
const toggleSelection = (rows?: User[]) => {
  if (rows) {
    rows.forEach((row) => {
      multipleTableRef.value!.toggleRowSelection(row, undefined)
    })
  } else {
    multipleTableRef.value!.clearSelection()
  }
}
const handleSelectionChange = (val: User[]) => {
  multipleSelection.value = val
}

const handleMinus = (index: number, row: User) => {
  // console.log(index, row)
  if (row.number < 1) {
  } else {
    row.number -= 1
    row.totalCost = row.totalCost - row.price
  }
}
const handlePlus = (index: number, row: User) => {
  // console.log(index, row)
  row.number += 1
  row.totalCost = row.totalCost + row.price
}
const deleteGoods = (index: number, row: User) => {
  console.log(index, row)
  // tableData.value.slice(index,1)
  tableData.value.splice(index, 1)
  console.log(tableData.value)
}
const goToPay = () => {
  console.log(multipleSelection)
}
const clearCart = () => {
  tableData.value = []
}
const tableData: Ref<User[]> = ref([
  {
    date: '三星 s23',
    name: '三星 s23',
    address: 'No. 189, Grove St, Los Angeles',
    price: 6999,
    number: 1,
    totalCost: 6999,
    picture: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'
  },
  {
    date: '魅族 20',
    name: '魅族 20',
    address: 'No. 189, Grove St, Los Angeles',
    price: 6999,
    number: 1,
    totalCost: 6999,
    picture: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'
  },
  {
    date: 'oppo reno 10',
    name: 'oppo reno 10',
    address: 'No. 189, Grove St, Los Angeles',
    price: 6999,
    number: 1,
    totalCost: 6999,
    picture: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'
  }
])
</script>

<style>
.container {
  padding-left: 10%;
  padding-right: 10%;
}
.numberInput {
  display: inline-block;
  width: 50px;
}
/* .store {
  width: 600px;
} */
</style>
