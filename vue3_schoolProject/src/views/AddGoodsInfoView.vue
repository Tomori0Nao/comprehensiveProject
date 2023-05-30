<template>
  <div class="add">
    <el-card class="add-container">
      <el-form
        :model="state.goodForm"
        :rules="state.rules"
        ref="goodRef"
        label-width="100px"
        class="goodForm"
      >
        <el-form-item required label="商品分类">
          <el-select v-model="state.category" class="m-2" placeholder="Select">
            <el-option
              v-for="item in categoryList"
              :key="item.categoryId"
              :label="item.categoryName"
              :value="item.categoryName"
          /></el-select>
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input
            style="width: 300px"
            v-model="state.goodForm.goodsName"
            placeholder="请输入商品名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品价格" prop="originalPrice">
          <el-input
            type="number"
            min="0"
            style="width: 300px"
            v-model="state.goodForm.originalPrice"
            placeholder="请输入商品价格"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品售卖价" prop="sellingPrice">
          <el-input
            type="number"
            min="0"
            style="width: 300px"
            v-model="state.goodForm.sellingPrice"
            placeholder="请输入商品售价"
          ></el-input>
        </el-form-item>
        <el-form-item label="商品库存" prop="stockNum">
          <el-input
            type="number"
            min="0"
            style="width: 300px"
            v-model="state.goodForm.stockNum"
            placeholder="请输入商品库存"
          ></el-input>
        </el-form-item>
        <el-form-item label="上架状态" prop="goodsSellStatus">
          <el-radio-group v-model="state.goodForm.goodsSellStatus">
            <el-radio label="0">上架</el-radio>
            <el-radio label="1">下架</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item required label="商品主图" prop="goodsCoverImg">
          <el-upload
            class="avatar-uploader"
            :action="state"
            accept="jpg,jpeg,png"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">{{
            state.id ? '立即修改' : '立即创建'
          }}</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted, onBeforeUnmount, getCurrentInstance } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import type { UploadProps } from 'element-plus'
import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'

// ----商品信息
// 商品名称
// 商品价格
// 店铺名称
// 商品数量
// 品牌
// 商品编号
// 商品重量
// 商品产地
interface GoodsInfo {
  goodsName: string
  goodsImageName: string
  goodsPrice: number
  storeName: string
  goodsNumber: number
  goodsBrand: string
  goodsNo: string
  goodsWeight: string
  goodsProducingArea: string
}
const goodRef = ref(null)
const route = useRoute()
const router = useRouter()
const { id } = route.query
const imageUrl = ref('')
interface CateoryInfo {
  categoryId: string
  categoryName: string
  addTime: string
}
const categoryList = ref<CateoryInfo[]>([
  {
    categoryId: '135435',
    categoryName: '手机',
    addTime: '2023-4-4'
  }
])
const state = reactive({
  id: id,
  category: '手机',
  action: 'http://localhost:8080/uploadTest',
  defaultCate: '',
  goodForm: {
    goodsName: '',
    goodsIntro: '',
    originalPrice: '',
    sellingPrice: '',
    stockNum: '',
    goodsSellStatus: '0',
    goodsCoverImg: '',
    tag: ''
  },
  rules: {
    goodsName: [{ required: 'true', message: '请填写商品名称', trigger: ['change'] }],
    originalPrice: [{ required: 'true', message: '请填写商品价格', trigger: ['change'] }],
    sellingPrice: [{ required: 'true', message: '请填写商品售价', trigger: ['change'] }],
    stockNum: [{ required: 'true', message: '请填写商品库存', trigger: ['change'] }]
  },
  categoryId: ''
})
const handleAvatarSuccess: UploadProps['onSuccess'] = (response: any, uploadFile: any) => {
  imageUrl.value = URL.createObjectURL(uploadFile.raw!)
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
const onSubmit = () => {}
// const
const handleChangeCate = () => {}
</script>

<style scoped>
.add {
  display: flex;
}
.add-container {
  flex: 1;
  height: 100%;
}
.avatar-uploader {
  width: 100px;
  height: 100px;
  color: #ddd;
  font-size: 30px;
}
.avatar-uploader-icon {
  display: block;
  width: 100%;
  height: 100%;
  border: 1px solid #e9e9e9;
  padding: 32px 17px;
}
</style>
