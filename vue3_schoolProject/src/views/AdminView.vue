<script setup lang="ts">
import { ref } from 'vue'
import type { Ref } from 'vue'
import { Document, Menu as IconMenu, Location, Setting } from '@element-plus/icons-vue'
import AdminHeader from '@/components/AdminHeader.vue';
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
// 先将系统管理员功能做完,完成相应组件后，再完成其他管理员的页面展示
// 本页面需要先获取管理员信息，才能确定如何展示后台(不同管理员，权限不同，页面展示不同)

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
</script>

<template>
  <AdminHeader></AdminHeader>
</template>

<!-- <div>
  <img src="/src/assets/github3.svg" alt="logo" />
  <span>git 商城 admin</span>
</div> -->
<style scoped>
</style>
