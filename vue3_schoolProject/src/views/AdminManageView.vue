<template>
  <el-card class="guest-container">
    <template #header>
      <div class="header">
        <el-input style="width: 200px; margin-right: 10px" placeholder="请输入管理员账号" v-model="state.adminAccount"
          @change="handleSearch" clearable />
        <el-button type="primary" :icon="Plus" @click="handleSolve">解除禁用</el-button>
        <el-button type="danger" :icon="Delete" @click="handleForbid">禁用账户</el-button>
        <el-button type="primary" :icon="Plus" @click="handleAdd">增加账户</el-button>
      </div>
    </template>
    <el-table :load="state.loading" ref="multipleTableRef" :data="tableData" tooltip-effect="dark" style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="adminType" label="类型"> </el-table-column>
      <el-table-column prop="adminNakeName" label="昵称"> </el-table-column>
      <el-table-column prop="adminAccount" label="账号"> </el-table-column>
      <el-table-column label="身份状态">
        <template #default="scope">
          <span :style="scope.row.isBaned == false ? 'color: green;' : 'color: red;'">
            {{ scope.row.isBaned == false ? '正常' : '禁用' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column prop="rigisterDate" label="注册时间"> </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" link type="primary" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        </template>
      </el-table-column>
      <!-- </template> -->
    </el-table>
    <el-pagination background layout="prev, pager, next" :total="state.total" :page-size="state.pageSize"
      :current-page="state.currentPage" @current-change="changePage" />
    <!-- <el-button class="mt-4" style="width: 100%" @click="handleAdd">Add Item</el-button> -->
    <el-dialog v-model="dialogFormVisibleChange" title="管理员信息修改" center width="30%">
      <ChangeAdminInfo @close-dialog="handleCloseDialog"></ChangeAdminInfo>
    </el-dialog>
    <el-dialog v-model="dialogFormVisibleAdd" title="添加管理员" center width="30%">
      <AddAdminInfo @close-dialog="handleCloseDialog"></AddAdminInfo>
    </el-dialog>
  </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElTable } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
import { reactive } from 'vue'
import ChangeAdminInfo from '@/components/ChangeAdminInfo.vue'
import AddAdminInfo from '@/components/AddAdminInfo.vue'

interface AdminInfo {
  adminType: string
  adminNakeName: string
  adminAccount: string
  adminAccountState: string
  rigisterDate: string
  isBaned: boolean
}
const tableData = ref<AdminInfo[]>([
  {
    adminType: '商品专员',
    adminNakeName: 'sjgdlkfh',
    adminAccount: '37483285',
    adminAccountState: '正常',
    rigisterDate: '2023-4-4',
    isBaned: false
  }
])
/**
 * 渲染前，请求管理员信息
 */
const path = "http://localhost:8080"
axios({
  method: 'get',
  url: path + '/getAdmins'
}).then((response) => {
  const respData = response.data;
  console.log(respData.data);

}).catch((error) => {

})
const dialogFormVisibleChange = ref(false)
const dialogFormVisibleAdd = ref(false)

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<AdminInfo[]>([])

const state = reactive({
  adminAccount: '',
  loading: false,
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 10, // 分页大小
  type: 'add', // 操作类型
  level: 1,
  parent_id: 0
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
  // state.userAccount
  // axios
}
const handleSelectionChange = (val: AdminInfo[]) => {
  multipleSelection.value = val
}
const handleEdit = (index: number, row: AdminInfo) => {
  dialogFormVisibleChange.value = true
  axios({

  })
}
const handleCloseDialog = () => {
  dialogFormVisibleChange.value = false
  dialogFormVisibleAdd.value = false
}
const handleAdd = () => {

  dialogFormVisibleAdd.value = true
}
const changePage = (val: number) => {
  state.currentPage = val
  getAdminList()
}
const getAdminList = () => {
  // axios
  // 根据state.currentPage来获取管理员信息列表
}
</script>

<style scoped></style>
