<template>
  <el-card class="guest-container">
    <template #header>
      <div class="header">
        <el-input
          style="width: 200px; margin-right: 10px"
          placeholder="请输入用户账号"
          v-model="state.userAccount"
          @change="handleSearch"
          clearable
        />
        <el-button type="primary" :icon="Plus" @click="handleSolve">解除禁用</el-button>
        <el-button type="danger" :icon="Delete" @click="handleForbid">禁用账户</el-button>
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
    </el-table>
  </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElTable } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
import { reactive } from 'vue'
import { template } from 'lodash'
axios.defaults.withCredentials = true

interface UserInfo {
  userNakeName: string
  userAccount: string
  userAccountState: string
  rigisterDate: string
  isBaned: boolean
}
let tableData = ref<UserInfo[]>([])

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<UserInfo[]>([])

const state = reactive({
  loading: false,
  userAccount: ''
})
const path = 'http://localhost:8080'
const handleSolve = () => {
  if (multipleSelection.value.length == 0) {
    ElMessage.error('请选择项')
  } else {
    var userArray = []
    for (const iterator of multipleSelection.value) {
      userArray.push(iterator.userAccount)
    }
    axios({
      method: 'get',
      url: path + '/notForbidUser',
      params: {
        userAccountList: userArray
      }
    })
      .then((response) => {
        const respData = response.data
        console.log(respData)
        if (respData.code == 1) {
          ElMessage.error('该账号解除禁用')
          setTimeout(getUserList, 1000)
        }
      })
      .catch((error) => {
        console.log('error = ' + error)
      })
  }
}
const handleForbid = () => {
  if (multipleSelection.value.length == 0) {
    ElMessage.error('请选择项')
  } else {
    var userArray = []
    for (const iterator of multipleSelection.value) {
      userArray.push(iterator.userAccount)
    }

    axios({
      method: 'get',
      url: path + '/forbidUser',
      params: {
        userAccountList: userArray
      }
    })
      .then((response) => {
        const respData = response.data
        console.log(respData)
        if (respData.code == 1) {
          ElMessage.error('禁用该账号')
          setTimeout(getUserList, 1000)
        }
      })
      .catch((error) => {
        console.log('error = ' + error)
      })
  }
}
const handleSearch = () => {
  // 查询账号
  state.userAccount
  axios({
    method: 'get',
    url: path + '/searchUser',
    params: {
      userAccount: state.userAccount
    }
  })
    .then((response) => {
      const respData = response.data
      const resUserInfo = respData.data
      tableData.value = []
      let tem: UserInfo = {
        userNakeName: '',
        userAccount: '',
        userAccountState: '',
        rigisterDate: '',
        isBaned: false
      }
      tem.userAccount = resUserInfo
      tem.userNakeName = resUserInfo
      tem.rigisterDate = resUserInfo
      tem.isBaned = resUserInfo
      tableData.value.push(tem)
      console.log(respData)
    })
    .catch((error) => {
      console.log('error = ' + error)
    })
}
const handleSelectionChange = (val: UserInfo[]) => {
  multipleSelection.value = val
}
/**
 * 页面加载时请求用户信息
 */
const getUserList = () => {
  axios({
    method: 'get',
    url: path + '/getUsers',
    params: {}
  })
    .then((response) => {
      const respData = response.data
      const resUserList = respData.data
      tableData.value = []
      for (const iterator of resUserList) {
        let tem: UserInfo = {
          userNakeName: '',
          userAccount: '',
          userAccountState: '',
          rigisterDate: '',
          isBaned: false
        }
        tem.userNakeName = iterator.userNakeName
        tem.userAccount = iterator.userAccount
        tem.rigisterDate = iterator.registerDate
        tem.isBaned = iterator.isBaned
        tableData.value.push(tem)
      }
      console.log(respData)
    })
    .catch((error) => {
      console.log('error = ' + error)
    })
}
getUserList()
// axios({
//   method: 'get',
//   url: path + '/getUsers',
//   params: {}
// })
//   .then((response) => {
//     const respData = response.data
//     const resUserList = respData.data
//     for (const iterator of resUserList) {
//       let tem: UserInfo = {
//         userNakeName: '',
//         userAccount: '',
//         userAccountState: '',
//         rigisterDate: '',
//         isBaned: false
//       }
//       tem.userNakeName = iterator.userNakeName
//       tem.userAccount = iterator.userAccount
//       tem.rigisterDate = iterator.registerDate
//       tem.isBaned = iterator.isBaned
//       tableData.value.push(tem)
//     }
//     console.log(respData)
//   })
//   .catch((error) => {
//     console.log('error = ' + error)
//   })
</script>

<style scoped>
.routerLink {
  color: #409eff;
  text-decoration: none;
}
</style>
