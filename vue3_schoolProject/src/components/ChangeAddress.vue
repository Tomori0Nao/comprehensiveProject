<script setup lang="ts">
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
const addressList: Ref<Address> = ref({
  addressNo: '1000',
  name: 'hello',
  tel: '1008610086',
  shippingAddress: '重庆市巴南区红光大道69号'
})

// 应该在onSubmit函数中，提交修改的地址信息，
const onSubmit = () => {
  // axios 
  
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
      <el-form :model="addressList" label-width="120px">
        <el-form-item label="收货人" class="input">
          <el-input v-model="addressList.name" />
        </el-form-item>
        <el-form-item label="手机号码" class="input">
          <el-input v-model="addressList.tel" />
        </el-form-item>
        <el-form-item label="收货地址" class="addressInput">
          <el-input v-model="addressList.shippingAddress" />
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
