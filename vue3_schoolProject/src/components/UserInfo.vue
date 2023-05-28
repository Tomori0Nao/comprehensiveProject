<script setup lang="ts">
import { ref } from 'vue'
import type { Ref } from 'vue'
import { useUserInfoStore } from '@/stores/userInfo'
import axios from 'axios';
const store = useUserInfoStore()
// ----用户信息
// 用户账号
// 用户电话
// 注册日期
// 上次登录时间
// 用户总消费额
// 用户余额
interface UserAccountInfo {
  userAccount: string
  userTel: string
  registerDate: string
  lastLoginTime: string
  userTotalConsumption: number
  userMoney: number
  userAvatar: string
}
const userAccountInfo: Ref<UserAccountInfo> = ref({
  userAccount: '1389478935',
  userTel: '100860086000',
  registerDate: '2023-5-24',
  lastLoginTime: '2023-5-24',
  userTotalConsumption: 6999,
  userMoney: 8888,
  userAvatar: '/src/assets/github3.svg'
})
/**
  后端拿到用户信息
  传送过来的属性： code和msg忽略
  {
    "code": 1,
    "msg": "查找到用户基本信息",
    "data": {
        "lastLoginTime": "2023-05-20T14:30:36",   上次登录时间
        "balance": "1300.36",                     余额
        "name": "小淘气",                         名字（昵称）
        "tel": "15369856230",                     电话号码
        "totalConsumption": "100,36",             总消费额
        "avatar": "user1",                        头像(的照片名字)
        "account": "111"                          账号
    }
}
*/
const path = 'http://localhost:8080'
axios({
  method: 'get',
  url: path + '/userInfo',
  params: {
    account: '111'
  }
}).then((response) => {
  var respData = response.data;
  console.log(respData);
}).catch((error) => {
  console.log(error);

})

////////////////////////////////////////////////////////////////////////////////////
const isChangeNakeName = ref(false)
const changeNakeName = () => {
  isChangeNakeName.value = true
  console.log(isChangeNakeName.value)
}
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
        <h3>helllll</h3>

      </el-descriptions-item>
    </el-descriptions>


  </div>
</template>

<style scoped></style>
