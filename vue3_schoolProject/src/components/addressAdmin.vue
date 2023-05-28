<script setup lang="ts">
import { ref } from 'vue'
import { reactive } from 'vue'
import type { Ref } from 'vue'

// ----收货地址信息
// 收货人姓名
// 收货人电话
// 收货地址
interface Address {
  addressNo: string
  name: string
  tel: string
  shippingAddress: string
}
const addressList: Ref<Address[]> = ref([
  {
    addressNo: '1000',
    name: 'hello',
    tel: '1008610086',
    shippingAddress: '重庆市巴南区红光大道69号'
  }
])
const handleEdit = (index: number, row: Address) => {
  console.log(index, row, 'edit')
}
const handleDelete = (index: number, row: Address) => {
  // 先发送请求，收到后端发来的响应后，再删除数组中订单
  
  console.log(index, row, 'delete')
  addressList.value.splice(index, 1)
}
const onAddItem = () => {
  let tem: Address = {
    addressNo: '1000',
    name: 'hello',
    tel: '1008610086',
    shippingAddress: '重庆市巴南区红光大道69号'
  }
  addressList.value.push(tem)
}
</script>

<template>
  <div>
    <el-table :data="addressList" style="width: 800px" max-height="600" stripe>
      <el-table-column fixed prop="name" label="收货人" width="150" />
      <el-table-column prop="tel" label="电话" width="150" />
      <el-table-column prop="shippingAddress" label="收货地址" width="300" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <RouterLink to="/ChangeAddress">
            <el-button size="small" link type="primary" @click="handleEdit(scope.$index, scope.row)"
              >Edit</el-button
            >
          </RouterLink>

          <el-button size="small" type="primary" link @click="handleDelete(scope.$index, scope.row)"
            >Delete</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-button class="mt-4" style="width: 800px" @click="onAddItem">Add Item</el-button>
  </div>
</template>

<style scoped></style>
