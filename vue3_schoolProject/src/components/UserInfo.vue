<script setup lang="ts">
import { ref } from 'vue'
import { reactive } from 'vue'
import type { Ref } from 'vue'
import { useUserInfoStore } from '@/stores/userInfo'
import axios from 'axios'
import { ElNotification } from 'element-plus'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import type { UploadProps } from 'element-plus'
axios.defaults.withCredentials = true

const store = useUserInfoStore()
const fit = ref('fill')
// ----用户信息
// 用户账号
// 用户昵称
// 用户电话
// 注册日期
// 上次登录时间
// 用户总消费额
// 用户余额
interface UserAccountInfo {
  userAccount: string
  userNakeName: string
  userTel: string
  registerDate: string
  lastLoginTime: string
  userTotalConsumption: number
  userMoney: number
  userAvatar: string
}
const userAccountInfo: Ref<UserAccountInfo> = ref({
  userAccount: store.userAccount,
  userNakeName: '',
  userTel: '',
  registerDate: '',
  lastLoginTime: '',
  userTotalConsumption: 0,
  userMoney: 0,
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
    account: store.userAccount
  }
})
  .then((response) => {
    var respData = response.data
    let resUserInfo = respData.data
    // userAccountInfo.value.lastLoginTime = resUserInfo.lastLoginTime
    userAccountInfo.value.registerDate = resUserInfo.registerDate
    userAccountInfo.value.userTel = resUserInfo.tel
    userAccountInfo.value.userTotalConsumption = resUserInfo.totalConsumption
    userAccountInfo.value.userMoney = resUserInfo.balance
    userAccountInfo.value.userNakeName = resUserInfo.name
    console.log(resUserInfo, 'userAccountInfo', store.userAccount, 'fsdaj', userAccountInfo)
  })
  .catch((error) => {
    console.log(error)
  })

////////////////////////////////////////////////////////////////////////////////////
const uploadPath = path + ''
const isChangeNakeName = ref(false)
const isChangeAvatar = ref(false)
const userNakeNameInput = ref('')
const headers = reactive({ 'Content-Type': 'multipart/form-data' })
const changeNakeName = () => {
  isChangeNakeName.value = true
  console.log(isChangeNakeName.value)
}
const changeAvatar = () => {
  isChangeAvatar.value = !isChangeAvatar.value
  console.log(isChangeAvatar.value)
}
/**
 * 处理修改昵称
 */
const onSubmit = () => {
  if (userNakeNameInput.value.length == 0) {
    ElNotification.success({
      title: 'Info',
      message: '用户昵称不能为空！！！',
      showClose: false
    })
    console.log('error!!!')
  } else {
    let tempName = userNakeNameInput.value
    console.log('submit!', tempName)
    console.log('原来的名字：' + store.userNakeName)
    axios({
      method: 'get',
      url: path + '/setUserName', //修改昵称的url
      params: {
        //account: userAccountInfo.value.userAccount,
        account: userAccountInfo.value.userAccount, //此处我先给一个账号做测试
        newName: tempName
      }
    })
      .then((response) => {
        const respData = response.data
        console.log(respData.msg)
        //code是1说明后端已经处理了昵称的修改
        //就将store.wuerNakeName修改为新的昵称
        if (respData.code == 1) {
          console.log(respData.msg)
          userAccountInfo.value.userNakeName = tempName
        } else {
          console.log(respData.msg)
        }
      })
      .catch((error) => {
        console.log('error = ' + error)
      })
  }
  isChangeNakeName.value = false
}
const onCancel = () => {
  isChangeNakeName.value = false
  console.log('cancel')
}
const imageUrl = ref('')

const handleAvatarSuccess: UploadProps['onSuccess'] = (response: any, uploadFile: any) => {
  imageUrl.value = URL.createObjectURL(uploadFile.raw!)
  isChangeAvatar.value = false
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile: any) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}
</script>

<template>
  <div class="userInfo">
    <el-descriptions class="margin-top" title="用户基本信息" :column="3" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户头像</div>
        </template>
        <div class="userAvatar">
          <el-avatar shape="square" :size="100" :fit="fit" src="/src/assets/github3.svg" />
          <el-button type="danger" @click="changeAvatar" plain>修改</el-button>
        </div>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户账号</div>
        </template>
        {{ userAccountInfo.userAccount }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户昵称</div>
        </template>
        {{ userAccountInfo.userNakeName }}
        <el-button type="danger" @click="changeNakeName" plain>修改</el-button>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户电话</div>
        </template>
        {{ userAccountInfo.userTel }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户总消费额</div>
        </template>
        {{ userAccountInfo.userTotalConsumption }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">用户余额</div>
        </template>
        {{ userAccountInfo.userMoney }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">上次登录时间</div>
        </template>
        {{ userAccountInfo.lastLoginTime }}
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">注册日期</div>
        </template>
        {{ userAccountInfo.registerDate }}
      </el-descriptions-item>
    </el-descriptions>
    <div class="changeNakeNameOrAvatar">
      <el-form :model="userNakeNameInput" label-width="120px" v-show="isChangeNakeName">
        <el-form-item label="修改昵称" class="nakeNameInput">
          <el-input v-model="userNakeNameInput" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确认</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
      <el-upload
        class="avatar-uploader"
        :action="uploadPath"
        method="post"
        :headers="headers"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
        v-show="isChangeAvatar"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon">
          <Plus />
        </el-icon>
      </el-upload>
    </div>
  </div>
</template>

<style scoped>
.changeNakeNameOrAvatar {
  margin-top: 30px;
}

.nakeNameInput {
  width: 30%;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
