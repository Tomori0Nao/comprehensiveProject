<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue'
import type { Ref } from 'vue'
axios.defaults.withCredentials = true
const emit = defineEmits(['closeDialog'])
// 父组件传入的用户账号
const props = defineProps(['adminAccount'])
// ----普通管理员信息
// 管理员账号
// 管理员昵称
// 管理员密码
// 管理员类型
interface AdminInfo {
  adminAccount: string
  adminNakeName: string
  adminPassword: string
  adminType: string
}
const adminAccount = props.adminAccount
const adminInfo: Ref<AdminInfo> = ref({
  adminAccount: adminAccount,
  adminNakeName: '',
  adminPassword: '',
  adminType: ''
})
const options = [
  {
    value: '商品专员',
    label: '商品专员'
  },
  {
    value: '营销经理',
    label: '营销经理'
  }
]
const path = 'http://localhost:8080'
axios({
  method: 'get',
  url: path + '/getAdminSimpleInfo',
  params: {
    adminAccount: adminAccount,    //父组件传递的管理员账号
  }
}).then((response) => {
  const respData = response.data;
  const resAdminInfo =respData.data
  adminInfo.value.adminNakeName = resAdminInfo.adminNakeName
  adminInfo.value.adminPassword = resAdminInfo.adminPassword
  adminInfo.value.adminType = resAdminInfo.adminType
  console.log(respData);

}).catch((error) => {
  console.log(error);

})
// 应该在onSubmit函数中，提交修改的地址信息，
const onSubmit = () => {
  // axios
  axios({
    method: 'post',
    url: path + '/updateAdminInfo',
    params: {
      adminAccount: adminInfo.value.adminAccount,
      adminNakeName: adminInfo.value.adminNakeName,
      adminPassword: adminInfo.value.adminPassword,
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
        <el-input v-model="adminInfo.adminPassword" />
      </el-form-item>
      <el-form-item label="管理员类型">
        <el-select v-model="adminInfo.adminType" class="m-2" placeholder="Select">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
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
