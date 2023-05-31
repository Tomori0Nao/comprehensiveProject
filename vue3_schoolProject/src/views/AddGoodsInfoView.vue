<template>
  <div class="add">
    <el-card class="add-container">
      <el-form :model="goodsInfo" :rules="state.rules" ref="goodRef" label-width="100px" class="goodForm">
        <el-form-item required label="商品分类">
          <el-select v-model="state.category" class="m-2" placeholder="Select">
            <el-option v-for="item in categoryList" :key="item.categoryId" :label="item.categoryName"
              :value="item.categoryName" /></el-select>
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName">
          <el-input style="width: 300px" v-model="goodsInfo.goodsName" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" prop="goodsPrice">
          <el-input type="number" min="0" style="width: 300px" v-model="goodsInfo.goodsPrice"
            placeholder="请输入商品价格"></el-input>
        </el-form-item>
        <el-form-item label="商品进价" prop="goodsPurchasePrice">
          <el-input type="number" min="0" style="width: 300px" v-model="goodsInfo.goodsPurchasePrice"
            placeholder="请输入商品进价"></el-input>
        </el-form-item>
        <el-form-item label="商品库存" prop="goodsNumber">
          <el-input type="number" min="0" style="width: 300px" v-model="goodsInfo.goodsNumber"
            placeholder="请输入商品库存"></el-input>
        </el-form-item>
        <el-form-item label="品牌" prop="goodsBrand">
          <el-input min="0" style="width: 300px" v-model="goodsInfo.goodsBrand" placeholder="请输入商品品牌"></el-input>
        </el-form-item>
        <el-form-item label="商品重量" prop="goodsWeight">
          <el-input min="0" style="width: 300px" v-model="goodsInfo.goodsWeight" placeholder="请输入商品重量"></el-input>
          <span class="weight">(kg)</span>
        </el-form-item>
        <el-form-item label="商品产地" prop="goodsProducingArea">
          <el-input min="0" style="width: 300px" v-model="goodsInfo.goodsOrigin" placeholder="请输入商品产地"></el-input>
        </el-form-item>
        <!-- <el-form-item label="店铺编号" prop="storeNo">
          <el-input type="number" min="0" style="width: 300px" v-model="goodsInfo.storeNo"
            placeholder="请输入商品编号"></el-input>
        </el-form-item> -->
        <el-form-item label="会员减免" prop="vipDerate">
          <el-input type="number" min="0" style="width: 300px" v-model="goodsInfo.vipDerate"
            placeholder="请输入会员减免费用"></el-input>
        </el-form-item>
        <el-form-item label="进货日期" prop="goodsPurchaseDate">
          <el-date-picker v-model="goodsInfo.goodsPurchaseDate" type="date" placeholder="请选择进货日期" />
        </el-form-item>
        <el-form-item label="商品简介" prop="goodsSpecifiedInfo">
          <el-input style="width: 300px" type="textarea" v-model="goodsInfo.goodsSpecifiedInfo"
            placeholder="请输入商品简介(100字)"></el-input>
        </el-form-item>
        <el-form-item required label="商品图片" prop="goodsImageName">
          <el-upload class="avatar-uploader" :action="state" accept="jpg,jpeg,png" :show-file-list="false"
            :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon">
              <Plus />
            </el-icon>
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

axios.defaults.withCredentials = true

// ----商品信息
// 商品名称
// 商品图片名称
// 商品价格
// 店铺名称
// 商品数量
// 品牌
// 商品编号
// 商品重量
// 商品产地
// 商品类别
// 店铺编号
// 商品进价
// 会员减免费用
// 进货日期
// 商品特有信息
// 商品状态
interface GoodsInfo {
  goodsName: string
  goodsImageName: string
  goodsPrice: string
  storeName: string
  goodsNumber: number
  goodsBrand: string
  goodsNo: string
  goodsWeight: string
  goodsOrigin: string
  goodsType: string
  // storeNo: string
  goodsPurchasePrice: string
  vipDerate: string
  goodsPurchaseDate: string
  goodsSpecifiedInfo: string
  //goodsState: boolean
}
const goodsInfo = ref<GoodsInfo>({
  goodsName: '',
  goodsImageName: '',
  goodsPrice: '12.3',
  storeName: '',
  goodsNumber: 0,
  goodsBrand: '',
  goodsNo: '',
  goodsWeight: '50.36',
  goodsOrigin: '',
  goodsType: '',
  //storeNo: '',
  goodsPurchasePrice: '12.3',
  vipDerate: '12.36',
  goodsPurchaseDate: '0',
  goodsSpecifiedInfo: '',
  //goodsState: true
})
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
  rules: {
    goodsName: [{ required: 'true', message: '请填写商品名称', trigger: ['change'] }],
    goodsPrice: [{ required: 'true', message: '请填写商品价格', trigger: ['change'] }],
    goodsPurchasePrice: [{ required: 'true', message: '请填写商品进价', trigger: ['change'] }],
    goodsNumber: [{ required: 'true', message: '请填写商品库存', trigger: ['change'] }],
    goodsBrand: [{ required: 'true', message: '请填写商品品牌', trigger: ['change'] }],
    goodsWeight: [{ required: 'true', message: '请填写商品重量', trigger: ['change'] }],
    goodsProducingArea: [{ required: 'true', message: '请填写商品产地', trigger: ['change'] }],
    goodsType: [{ required: 'true', message: '请填写商品类型', trigger: ['change'] }],
    vipDerate: [{ required: 'true', message: '请填写会员减免', trigger: ['change'] }],
    goodsPurchaseDate: [{ required: 'true', message: '请填写进货日期', trigger: ['change'] }]
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
const path = 'http://localhost:8080'
const onSubmit = () => {
  axios({
    method: 'get',
    url: path + '/addGoods',
    params: {
      goodsName: '小熊饼干',
      goodsImageName: '小熊图片',
      goodsPrice: '12.3',
      storeName: '小熊饼干店铺',
      goodsNumber: 16,
      goodsBrand: '小熊零食',
      goodsNo: 'xiaoxiong',
      goodsWeight: '100',
      goodsOrigin: '北京市朝阳区',
      goodsType: '零食',
      //storeNo: '',
      goodsPurchasePrice: '5.69',
      vipDerate: '0.88',
      goodsPurchaseDate: '2023-12-23',
      goodsSpecialInfo: '好吃又美味',
    }
  }).then((response) => {
    const respData = response.data;
    console.log(respData);
    if (respData.code == 1) {
      //增加一个消息提示框：添加成功
      console.log(respData.msg);
    } else {
      //增加一个消息提示框：添加失败
      console.log(respData.msg);
    }
  }).catch((error) => {
    console.log("error = " + error);
  })
}
// const
const handleChangeCate = () => { }
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

.weight {
  padding-left: 20px;
}
</style>
