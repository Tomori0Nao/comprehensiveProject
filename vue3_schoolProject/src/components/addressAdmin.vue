<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'
import type { Ref } from 'vue'
import ChangeAddress from './ChangeAddress.vue'
import { reactive } from 'vue';
import {useEditAddressStore} from '../stores/EditAddress'
import { InfoFilled } from '@element-plus/icons-vue'


const editAddress = useEditAddressStore()
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
const addressList: Ref<Address[]> = ref([])
const state = reactive({
  addressNo: '',
  name: '',
  tel: '',
  shippingAddress: ''
})
const handleEdit = (index: number, row: Address) => {
  editAddress.addressNo = row.addressNo
  editAddress.consigneeName = row.consigneeName
  editAddress.consigneeTel = row.consigneeTel
  editAddress.address = row.address
  dialogFormVisible.value = true
  // console.log(index, row, 'edit', editAddres.value)
}
/**
 * 删除用户的收货地址
 * @param index 收货地址数组的下标
 * @param row 这一行的元素
 */
const handleDelete = (index: number, row: Address) => {
  // 先发送请求，收到后端发来的响应后，再删除数组中订单
  axios({
    method: 'get',
    url: path + '/deleteDeliveryAddress',
    params: {
      account: '333',
      addressNo: row.addressNo
      // addressNo: addressList.value[index].addressNo,
    }
  })
    .then((response) => {
      const respData = response.data
      console.log(respData)
      if (respData.code == 1) {
        console.log(index, row, 'delete')
        addressList.value.splice(index, 1)
        console.log(respData.msg)
      } else {
        console.log(respData.msg)
      }
    })
    .catch((error) => {})
}
const path = 'http://localhost:8080'
/**
 * 添加一个收货地址
 */
const onAddItem = () => {
  let tem: Address = {
    addressNo: '1000',
    consigneeName: 'hello',
    consigneeTel: '1008610086',
    address: '重庆市巴南区红光大道69号'
  }
  //////////////////////////////////////////////////////////////////
  /**
   * 添加一个收货地址，发起的请求
   */
  axios({
    method: 'get',
    url: path + '/addAddress',
    params: {
      consigneeName: '班县人',
      consigneeTel: '0371-5698-269',
      address: '重庆市渝中区幸福路110号',
      userAccount: '333'
    }
  })
    .then((response) => {
      const respData = response.data
      console.log('code =' + respData.code)
      if (respData.code == 1) {
        tem.consigneeName = respData.name
        tem = respData.data
        console.log(tem)

        addressList.value.push(tem)
      } else {
        //出错的情况可能是同一个收货地址多次添加
        console.log(respData.msg)
      }
    })
    .catch((error) => {
      console.log('error11 = ' + error)
    })
}
const handleCloseDialog = () => {
  addressList.value = []
  getAddressList()
  console.log('edit!!!!!');
  
  dialogFormVisible.value = false
}
const dialogFormVisible = ref(false)
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
const getAddressList = ()=>{
  axios({
  method: 'get',
  url: path + '/deliveryAddress',
  params: {
    account: '333'
  }
})
  .then((response) => {
    const respData= response.data
    const addresResList = respData.data 
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
      console.log('tem is ',tem)
    } 
    console.log(addressList,'addlist');
    
  })
  .catch((error) => {
    console.log('error111=' + error)
  })
}
getAddressList()
</script>

<template>
  <div>
    <el-table :data="addressList" style="width: 800px" max-height="600" stripe>
      <el-table-column fixed prop="consigneeName" label="收货人" width="150" />
      <el-table-column prop="consigneeTel" label="电话" width="150" />
      <el-table-column prop="address" label="收货地址" width="300" />
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="scope">
          <el-button size="small" link type="primary" @click="handleEdit(scope.$index, scope.row)"
            >Edit</el-button
          >
          <el-popconfirm
        width="220"
        confirm-button-text="是的"
        cancel-button-text="否"
        :icon="InfoFilled"
        icon-color="#626AEF"
        title="删除该收货地址吗?"
        @confirm="handleDelete(scope.$index, scope.row)"
      >
        <template #reference>
          <el-button size="small" type="primary" link 
            >Delete</el-button
          >
        </template>
      </el-popconfirm>
          
        </template>
      </el-table-column>
    </el-table>
    <el-button class="mt-4" style="width: 800px" @click="onAddItem">Add Item</el-button>
    <el-dialog v-model="dialogFormVisible" title="收货地址修改" center width="30%">
      <ChangeAddress @close-dialog="handleCloseDialog" ></ChangeAddress>
    </el-dialog>
  </div>
</template>

<style scoped></style>
