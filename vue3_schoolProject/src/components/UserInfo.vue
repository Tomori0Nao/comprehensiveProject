<script setup lang="ts">
import { ref } from 'vue'
import type { Ref } from 'vue'
import { useUserInfoStore } from '@/stores/userInfo'
const store = useUserInfoStore()
interface UserAccountInfo {
  userAccount: string
  userTel: string
  registerDate: string
  lastLoginTime: string
  userTotalConsumption: number
  userMoney: number
}
const userAccountInfo: Ref<UserAccountInfo> = ref({
  userAccount: '1389478935',
  userTel: '100860086000',
  registerDate: '2023-5-24',
  lastLoginTime: '2023-5-24',
  userTotalConsumption: 6999,
  userMoney: 8888
})
const isChangeNakeName = ref(false)
const changeNakeName = () => {
  isChangeNakeName.value = true
  console.log(isChangeNakeName.value)
}
const input = ref('1111')
</script>

<template>
  <div class="userInfo">
    
    <el-descriptions class="margin-top" title="用户基本信息" :column="3" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户账号</div>
        </template>
        {{ store.userAccount }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户昵称</div>
        </template>
        {{ store.userAccount }}
        <el-button type="danger" @click="changeNakeName" plain>修改</el-button>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户电话</div>
        </template>
        {{ store.userTel }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户总消费额</div>
        </template>
        {{ store.userTotalConsumption }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户余额</div>
        </template>
        {{ store.userMoney }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">上次登录时间</div>
        </template>
        {{ store.lastLoginTime }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">注册日期</div>
        </template>
        {{ store.registerDate }}
      </el-descriptions-item>
    </el-descriptions>
    <h3>helllll</h3>

  </div>
  

</template>

<style scoped></style>
