<script setup lang="ts">

// 先跳转到管理员登录页面
// 判断管理员类型后，再跳转到对应管理员页面

// ----管理员信息
// 管理员账号
// 管理员昵称
// 管理员类型
// 管理员账号是否被禁
// 管理员上次登录时间
// 管理员创建时间
interface adminInfo {
  adminAccount: string
  adminNakeMame: string
  adminType:string
  isBaned: boolean
  lastLoginTime: string
  createTime: string
}


</script>

<template>
  
  <!-- <div class="userInfo">
    <el-descriptions class="margin-top" title="管理员基本信息" :column="3" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员账号</div>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员昵称</div>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员类型</div>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员是否被禁用</div>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">上次登录时间</div>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员账号</div>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员账号</div>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">管理员账号</div>
        </template>
      </el-descriptions-item>
    </el-descriptions>
  </div> -->
</template>

<style scoped></style>
