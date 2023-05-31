<script setup lang="ts">
import { ref } from 'vue'
import type { Ref } from 'vue'
import axios from 'axios'
axios.defaults.withCredentials = true

// 先将系统管理员功能做完,完成相应组件后，再完成其他管理员的页面展示

// 管理员页面
// ----管理员信息
// 管理员账号
// 管理员昵称
// 管理员类型
// 管理员账号是否被禁
// 管理员上次登录时间
// 管理员账号创建时间
interface AdminInfo {
  adminAccount: string
  adminNakeMame: string
  adminType: string
  isBaned: boolean
  lastLoginTime: string
  createTime: string
}
const adminInfo: Ref<AdminInfo> = ref({
  adminAccount: '123543532',
  adminNakeMame: 'hello World',
  adminType: '系统管理员',
  isBaned: false,
  lastLoginTime: '2023-5-27',
  createTime: '2023-5-15'
})
</script>

<template>
  <div class="adminInfo">
    <el-descriptions class="margin-top" title="管理员基本信息" :column="3" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员账号</div>
        </template>
        {{ adminInfo.adminAccount }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员昵称</div>
        </template>
        {{ adminInfo.adminNakeMame }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员类型</div>
        </template>
        {{ adminInfo.adminType }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员是否被禁用</div>
        </template>
        {{ adminInfo.isBaned }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">上次登录时间</div>
        </template>
        {{ adminInfo.lastLoginTime }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">账号创建时间</div>
        </template>
        {{ adminInfo.createTime }}
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<style scoped></style>
