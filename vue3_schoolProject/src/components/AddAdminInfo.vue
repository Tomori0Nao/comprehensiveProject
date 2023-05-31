<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue'
import type { Ref } from 'vue'

const emit = defineEmits(['closeDialog'])

// ----普通管理员信息
// 管理员账号
// 管理员昵称
// 管理员密码
// 管理员类型
interface AdminInfo {
  adminAccount: string
  adminNakeName: string
  adminPasswd: string
  adminType: string
}
const adminInfo = ref<AdminInfo>({
  adminAccount: '',
  adminNakeName: '',
  adminPasswd: '',
  adminType: ''

})
const options = [
  {
    value: '商品专员',
    label: '商品专员',
  },
  {
    value: '营销经理',
    label: '营销经理',
  },
]
const path = 'http://localhost:8080'
// 应该在onSubmit函数中，提交修改的地址信息，
const onSubmit = () => {
  // axios 
  axios({
    method: 'post',
    url: path + '/addAdmin',
    params: {
      adminNakeName: adminInfo.value.adminNakeName,
      adminPassword: adminInfo.value.adminPasswd,
      adminType: adminInfo.value.adminType,
    }
  }).then((response) => {
    const respData = response.data;
    console.log(respData);

  }).catch((error) => {

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
    <el-form :model="adminInfo" label-width="120px">
      <el-form-item label="管理员昵称" class="input">
        <el-input v-model="adminInfo.adminNakeName" />
      </el-form-item>
      <el-form-item label="管理员密码" class="input">
        <el-input v-model="adminInfo.adminPasswd" />
      </el-form-item>
      <el-form-item label="管理员类型">
        <el-select v-model="adminInfo.adminType" class="m-2" placeholder="Select">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="onCancel()">取消</el-button>
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
