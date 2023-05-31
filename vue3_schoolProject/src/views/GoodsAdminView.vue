<template>
  <el-card class="good-container">
    <template #header>
      <div class="header">
        <el-button type="primary" :icon="Plus" @click="handleAdd">新增商品</el-button>
      </div>
    </template>
    <el-table :load="state.loading" :data="tableData" tooltip-effect="dark" style="width: 100%">
      <el-table-column prop="goodsNo" label="商品编号"> </el-table-column>
      <el-table-column prop="goodsName" label="商品名"> </el-table-column>
      <el-table-column prop="goodsBrand" label="品牌"> </el-table-column>
      <el-table-column label="商品图片" width="130px">
        <template #default="scope">
          <img style="width: 100px; height: 100px" :key="scope.row.goodsNo" :src="scope.row.goodsImageName" alt="商品主图" />
        </template>
      </el-table-column>
      <el-table-column prop="goodsNumber" label="商品库存"> </el-table-column>
      <el-table-column prop="goodsPrice" label="商品售价"> </el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="scope">
          <a style="cursor: pointer; margin-right: 10px" @click="handleEdit(scope.row.goodsId)">修改</a>
          <a style="cursor: pointer; margin-right: 10px" @click="handleDelete(scope.row.goodsId)">删除</a>
        </template>

      </el-table-column>
    </el-table>
    <!--总数超过一页，再展示分页器-->
    <el-pagination background layout="prev, pager, next" :total="state.total" :page-size="state.pageSize"
      :current-page="state.currentPage" @current-change="changePage" />
  </el-card>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
// import { getInnerRange } from '@vue/compiler-core';
import { useRouter } from 'vue-router'
axios.defaults.withCredentials = true

const router = useRouter()

//----商品信息
// 商品名称
// 商品图片
// 商品价格
// 商品品牌
// 商品数量
// 商品编号
//
interface GoodsInfo {
  goodsName: string
  goodsImageName: string
  goodsPrice: number
  storeName: string
  goodsNumber: number
  goodsBrand: string
  goodsNo: string
  goodsType: string
}
const tableData = ref<GoodsInfo[]>([
  // {
  //   goodsName: '三星s23',
  //   goodsImageName: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png',
  //   goodsPrice: 6999,
  //   storeName: 'git商城自营店',
  //   goodsNumber: 100,
  //   goodsNo: '318458',
  //   goodsBrand: '三星',
  //   goodsType: '手机'
  // }
])
const state = reactive({
  loading: false,
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 5 // 分页大小
})

const getGoodList = () => {
  state.loading = true
  // state.currentPage 为当前页码
  // axios
}
const handleAdd = () => {
  router.push({ path: '/admin/AddGoods' })
}
const handleEdit = (id: string) => {
  router.push({ path: '/admin/EditGoods', query: { id } })
}
const handleDelete = (id: string) => {
  // axios
  axios({
    method: 'get',
    url: path + '/deleteGoods',
    params: {
      goodsNo: '123'
    }
  }).then((response) => {
    const respData = response.data;
    console.log(respData);
  }).catch((error) => {
    console.log("error = " + error);

  })
}
const changePage = (val: number) => {
  state.currentPage = val
  getGoodList()
}
const handleStatus = (row: GoodsInfo, status: boolean) => {
  console.log(row)
  // axios
}
/**
 * 获取所有信息
 */
const path = 'http://localhost:8080'
axios({
  method: 'get',
  url: path + '/getAllGoods',
  params: {}
})
  .then((response) => {
    const imagesURLPrefix = 'http://localhost:8080/static/images/'
    tableData.value = []
    const respData = response.data
    const resGoodsList = respData.data
    for (const iterator of resGoodsList) {
      let tem: GoodsInfo = {
        goodsName: '',
        goodsImageName:
          '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png',
        goodsPrice: 0,
        storeName: '',
        goodsNumber: 0,
        goodsNo: '',
        goodsBrand: '',
        goodsType: ''
      }
      tem.goodsName = iterator.goodsName
      tem.goodsNo = iterator.goodsNo
      tem.goodsNumber = iterator.goodsNumber
      tem.goodsPrice = iterator.goodsPrice
      tem.storeName = iterator.storeName
      tem.goodsImageName = imagesURLPrefix + iterator.goodsImageName + '.png'
      tem.goodsType = iterator.goodsType
      tem.goodsBrand = iterator.goodsBrand
      tableData.value.push(tem)
    }
    console.log(respData)
  })
  .catch((error) => {
    console.log('error = ' + error)
  })
</script>

<style scoped>
.good-container {
  min-height: 100%;
}

.el-card.is-always-shadow {
  min-height: 100% !important;
}
</style>
