<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'
import type { Ref } from 'vue'
import { useAddAddressStore } from '../stores/EditAdmin'

const addAddress = useAddAddressStore()

const emit = defineEmits(['closeDialog'])

// ----收货地址信息
// 收货地址编号
// 收货人姓名
// 收货人电话
// 收货地址
interface Address {
  addressNo: string
  consigneeName: string
  consigneeTel: string
  address: string
}
const address: Ref<Address> = ref({
  addressNo: '',
  consigneeName: '',
  consigneeTel: '',
  address: ''
})
const path = 'http://localhost:8080'
// 应该在onSubmit函数中，提交修改的地址信息，
const onSubmit = () => {
  console.log(address, 'address')
  let tem: Address = {
    addressNo: '',
    consigneeName: '',
    consigneeTel: '',
    address: ''
  }
  // axios
  axios({
    method: 'get',
    url: path + '/addAddress',
    params: {
      consigneeName: address.value.consigneeName,
      consigneeTel: address.value.consigneeTel,
      address: address.value.address,
      userAccount: '111'
    }
  })
    .then((response) => {
      const respData = response.data
      console.log('code =' + respData.code)
      if (respData.code == 1) {
      } else {
        //出错的情况可能是同一个收货地址多次添加
        console.log(respData.msg)
      }
    })
    .catch((error) => {
      console.log('error11 = ' + error)
    })
  console.log('submit!')
  emit('closeDialog')
}
const onCancel = () => {
  console.log('cancel!')
  emit('closeDialog')
}
</script>

<template>
  <div class="container">
    <el-form :model="address" label-width="120px">
      <el-form-item label="收货人" class="input">
        <el-input v-model="address.consigneeName" />
      </el-form-item>
      <el-form-item label="手机号码" class="input">
        <el-input v-model="address.consigneeTel" />
      </el-form-item>
      <el-form-item label="收货地址" class="addressInput">
        <el-input v-model="address.address" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.container {
  padding-left: 10%;
  padding-right: 10%;
}

.input {
  width: 90%;
}

.addressInput {
  width: 90%;
}
</style>
