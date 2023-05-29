<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue'
import { reactive } from 'vue'
import type { Ref } from 'vue'

// ----收货地址信息
// 收货人姓名
// 收货人电话
// 收货地址
interface Address {
  addressNo: string
  consigneeName: string
  consigneeTel: string
  address: string
}
const addressList: Ref<Address[]> = ref([
  {
    addressNo: '1000',
    consigneeName: 'hello',
    consigneeTel: '1008610086',
    address: '重庆市巴南区红光大道69号'
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
const path = 'http://localhost:8080'
const onAddItem = () => {
  let tem: Address = {
    addressNo: '1000',
    consigneeName: 'hello',
    consigneeTel: '1008610086',
    address: '重庆市巴南区红光大道69号'
  }
  //////////////////////////////////////////////////////////////////
  /**
   * 添加一件商品，发起的求请求
   */
  axios({
    method: 'get',
    url: path + '/addAddress',
    params: {
      name: '班县人',
      tel: '0371-5698-269',
      address: '重庆市渝中区幸福路99号',
      account: '333'
    }
  }).then((response) => {
    const respData = response.data;
    console.log('code =' + respData.code);
    if (respData.code == 1) {
      tem.consigneeName = respData.name;
      tem = respData.data;
      addressList.value.push(tem)
    } else {
      console.log(respData.data);
    }
  }).catch((error) => {
    console.log('error11 = ' + error);

  })
}
/**
 * 请求用户的收货地址信息
 * 请求成后数据格式：
 * "data": [
        {
            "addressNo": "add000003",
            "userAccount": "222",
            "consigneeName": "关三庚",
            "consigneeTel": "663-5698-523",
            "address": "南天门仙人阁"
        },
 */
axios({
  method: 'get',
  url: path + '/deliveryAddress',
  params: {
    account: '333',
  }
}).then((response) => {
  const respData = response.data;
  console.log(respData);
}).catch((error) => {
  console.log("error111=" + error);
})
</script>

<template>
  <div>
    <el-table :data="addressList" style="width: 800px" max-height="600" stripe>
      <el-table-column fixed prop="consigneeName" label="收货人" width="150" />
      <el-table-column prop="consigneeTel" label="电话" width="150" />
      <el-table-column prop="address" label="收货地址" width="300" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <RouterLink to="/ChangeAddress">
            <el-button size="small" link type="primary" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
          </RouterLink>

          <el-button size="small" type="primary" link @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button class="mt-4" style="width: 800px" @click="onAddItem">Add Item</el-button>
  </div>
</template>

<style scoped></style>
