<template>
  <el-card class="guest-container">
    <template #header>
      <div class="header">
        <el-input style="width: 200px; margin-right: 10px" placeholder="请输入订单号" v-model="state.userAccount"
          @change="handleSearch" clearable />
        <el-button type="primary" :icon="Plus" @click="handleSolve">解除禁用</el-button>
        <el-button type="danger" :icon="Delete" @click="handleForbid">禁用账户</el-button>
      </div>
    </template>
    <el-table :load="state.loading" ref="multipleTableRef" :data="tableData" tooltip-effect="dark" style="width: 100%"
      @selection-change="handleSelectionChange">
      <!-- <template #column> -->
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="userNakeName" label="昵称"> </el-table-column>
      <el-table-column prop="userAccount" label="账号"> </el-table-column>
      <el-table-column label="身份状态">
        <template #default="scope">
          <span :style="scope.row.isBaned == false ? 'color: green;' : 'color: red;'">
            {{ scope.row.isBaned == false ? '正常' : '禁用' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="rigisterDate" label="注册时间"> </el-table-column>
      <!-- <el-table-column label="操作">
        <template #default="scope">
          <router-link
            class="routerLink"
            :to="{ path: '/admin/User/User_detail', query: { id: scope.row.orderId } }"
            >修改</router-link
          >
        </template>
      </el-table-column> -->
      <!-- </template> -->
    </el-table>
  </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElTable } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
import { reactive } from 'vue'

interface UserInfo {
  userNakeName: string
  userAccount: string
  userAccountState: string
  rigisterDate: string
  isBaned: boolean
}
let tableData = ref<UserInfo[]>([
  {
    userNakeName: 'sjgdlkfh',
    userAccount: '37483285',
    userAccountState: '正常',
    rigisterDate: '2023-4-4',
    isBaned: false
  }
])


const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<UserInfo[]>([])

const state = reactive({
  loading: false,
  userAccount: ''
})
const handleSolve = () => {
  if (multipleSelection.value.length == 0) {
    ElMessage.error('请选择项')
  } else {
    // axios
    ElMessage.error('该账号解除禁用')
  }
}
const handleForbid = () => {
  if (multipleSelection.value.length == 0) {
    ElMessage.error('请选择项')
  } else {
    ElMessage.error('禁用该账号')

    // axios
  }
}
const handleSearch = () => {
  // 查询账号
  state.userAccount
  // axios
}
const handleSelectionChange = (val: UserInfo[]) => {
  multipleSelection.value = val
}
</script>

<style scoped>
.routerLink {
  color: #409eff;
  text-decoration: none;
}
</style>
