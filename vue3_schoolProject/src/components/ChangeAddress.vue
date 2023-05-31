<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'
import type { Ref } from 'vue'

const emit = defineEmits(['closeDialog'])

// ----收货地址信息
// 收货地址编号
// 收货人姓名
// 收货人电话
// 收货地址
interface Address {
  addressNo: string
  name: string
  tel: string
  shippingAddress: string
}
const address: Ref<Address> = ref({
  addressNo: 'addr3330',
  name: 'hello',
  tel: '1008610086',
  shippingAddress: '重庆市巴南区红光大道69号'
})
const path = 'http://localhost:8080'
// 应该在onSubmit函数中，提交修改的地址信息，
const onSubmit = () => {
  // axios
  axios({
    method: 'post',
    url: path + '/editDeliveryAddress',
    params: {
      addressNo: address.value.addressNo,
      userAccount: '333', //先添加一个账号进行测试
      consigneeName: address.value.name,
      consigneeTel: address.value.tel,
      address: address.value.shippingAddress
    }
  })
    .then((response) => {
      const respData = response.data
      console.log(respData)
      //这里收货地址修改完毕后应该再反馈到表格中
      if (respData.code == 1) {
        console.log('收货地址已经修改')
      } else {
      }
    })
    .catch((error) => {})
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
        <el-input v-model="address.name" />
      </el-form-item>
      <el-form-item label="手机号码" class="input">
        <el-input v-model="address.tel" />
      </el-form-item>
      <el-form-item label="收货地址" class="addressInput">
        <el-input v-model="address.shippingAddress" />
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
