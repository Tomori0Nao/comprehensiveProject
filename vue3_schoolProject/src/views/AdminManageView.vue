<template>
  <el-card class="guest-container">
    <template #header>
      <div class="header">
        <el-input
          style="width: 200px; margin-right: 10px"
          placeholder="请输入管理员账号"
          v-model="state.adminAccount"
          @change="handleSearch"
          clearable
        />
        <el-button type="primary" :icon="Unlock" @click="handleSolve">解除禁用</el-button>
        <el-button type="danger" :icon="Delete" @click="handleForbid">禁用账户</el-button>
        <el-button type="primary" :icon="Plus" @click="handleAdd">增加账户</el-button>
      </div>
    </template>
    <el-table
      :load="state.loading"
      ref="multipleTableRef"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
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
          <el-button size="small" link type="primary" @click="handleEdit(scope.$index, scope.row)"
            >Edit</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="state.total"
      :page-size="state.pageSize"
      :current-page="state.currentPage"
      @current-change="changePage"
    />

    <!-- <el-button class="mt-4" style="width: 100%" @click="handleAdd">Add Item</el-button> -->
    <el-dialog v-model="dialogFormVisibleChange" title="管理员信息修改" center width="30%">
      <ChangeAdminInfo
        @close-dialog="handleCloseDialog"
        :admin-account="state.editAdminAccount"
      ></ChangeAdminInfo>
    </el-dialog>
    <el-dialog v-model="dialogFormVisibleAdd" title="添加管理员" center width="30%">
      <AddAdminInfo @close-dialog="handleCloseDialog"></AddAdminInfo>
    </el-dialog>
  </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElTable } from 'element-plus'
import { Plus, Delete, Unlock } from '@element-plus/icons-vue'
import axios from 'axios'
import { reactive } from 'vue'
import ChangeAdminInfo from '@/components/ChangeAdminInfo.vue'
import AddAdminInfo from '@/components/AddAdminInfo.vue'
import { useEditAdminStore } from '../stores/EditAdmin'

const editAdmin = useEditAdminStore()

interface AdminInfo {
  adminType: string
  adminNakeName: string
  adminAccount: string
  adminAccountState: string
  rigisterDate: string
  isBaned: boolean
}
const tableData = ref<AdminInfo[]>([])
/**
 * 渲染前，请求管理员信息
 */
const path = 'http://localhost:8080'
axios({
  method: 'get',
  url: path + '/getAdmins'
})
  .then((response) => {
    const respData = response.data
    const adminInfoList = respData.data
    for (const iterator of adminInfoList) {
      let tem: AdminInfo = {
        adminType: '',
        adminNakeName: '',
        adminAccount: '',
        adminAccountState: '',
        rigisterDate: '',
        isBaned: false
      }
      tem.adminType = iterator.adminType
      tem.adminNakeName = iterator.adminNakeName
      tem.adminAccount = iterator.adminAccount
      tem.rigisterDate = iterator.registerDate
      tem.isBaned = iterator.baned
      tableData.value.push(tem)
    }
    console.log(respData.data)
  })
  .catch((error) => {})
const dialogFormVisibleChange = ref(false)
const dialogFormVisibleAdd = ref(false)
const editAdminAccount = ref('')

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<AdminInfo[]>([])

const state = reactive({
  editAdminAccount: '',
  adminAccount: '',
  loading: false,
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 10, // 分页大小
  type: 'add', // 操作类型
  level: 1,
  parent_id: 0
})

/**
 * 这是解除禁用
 */
const handleSolve = () => {
  if (multipleSelection.value.length == 0) {
    ElMessage.error('请选择项')
  } else {
    //先将选中的管理员账号取出
    var addminAccountList = []
    for (var i = 0; i < multipleSelection.value.length; ++i) {
      addminAccountList.push(multipleSelection.value[i].adminAccount)
    }
    var array = ['admin003', 'admin002']
    //向后端发起请求，禁用管理员账号
    axios({
      method: 'get',
      url: path + '/notForbidAdmin',
      params: {
        //这里注意修改成选中的编号，我只是写了几个编号测试
        // adminNoList: addminAccountList
        adminNoList: addminAccountList
      }
    })
      .then((response) => {
        const respData = response.data
        console.log(respData.code)
        if (respData.code == 1) {
          ElMessage.error(respData.msg)
        } else {
          ElMessage.error('解除禁用管理员账号失败')
        }
      })
      .catch((error) => {})
    ElMessage.error('解除禁用该账号')
  }
}
const handleForbid = () => {
  if (multipleSelection.value.length == 0) {
    ElMessage.error('请选择项')
  } else {
    //先将选中的管理员账号取出
    var addminAccountList = []
    for (var i = 0; i < multipleSelection.value.length; ++i) {
      addminAccountList.push(multipleSelection.value[i].adminAccount)
    }
    // var array = ['admin003', 'admin002']
    //向后端发起请求，禁用管理员账号
    axios({
      method: 'get',
      url: path + '/forbidAdmin',
      params: {
        //这里注意修改成选中的编号，我只是写了几个编号测试
        adminNoList: addminAccountList
        // adminNoList: array
      }
    })
      .then((response) => {
        const respData = response.data
        console.log(respData.code)
        if (respData.code == 1) {
          ElMessage.error(respData.msg)
        } else {
          ElMessage.error('禁用管理员账号失败')
        }
      })
      .catch((error) => {})
    ElMessage.error('禁用该账号')

    // axios
  }
}
/**
 * 输入管理员账号查找管理员信息
 */
const handleSearch = () => {
  // 查询账号
  // state.userAccount
  // axios
  axios({
    method: 'get',
    url: path + '/searchAdmin',
    params: {
      adminAccount: state.adminAccount
      //我写一个固定值测试
      // adminAccount: 'admin002'
    }
  })
    .then((response) => {
      const respData = response.data
      //下面是查找的管理员信息
      console.log(respData)
      //查找到
      if (respData.code == 1) {
        console.log(respData.msg)
      }
      //没找到
      else {
        console.log(respData.msg)
      }
    })
    .catch((error) => {})
}
const handleSelectionChange = (val: AdminInfo[]) => {
  multipleSelection.value = val
}
const handleEdit = (index: number, row: AdminInfo) => {
  editAdmin.adminAccount = row.adminAccount
  editAdmin.adminNakeName = row.adminNakeName
  editAdmin.adminType = row.adminType
  editAdmin.isBaned = row.isBaned
  state.editAdminAccount = row.adminAccount
  console.log(state.editAdminAccount, 'selected!!!')
  dialogFormVisibleChange.value = true
  //请求已在ChangeAdminInfo中写
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
