<template>
  <el-card class="good-container">
    <template #header>
      <div class="header">
        <el-button type="primary" :icon="Plus" @click="handleAdd">新增商品</el-button>
      </div>
    </template>
    <el-table
      :load="state.loading"
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <el-table-column prop="goodsNo" label="商品编号"> </el-table-column>
      <el-table-column prop="goodsName" label="商品名"> </el-table-column>
      <el-table-column prop="goodsBrand" label="品牌"> </el-table-column>
      <el-table-column label="商品图片" width="150px">
        <template #default="scope">
          <img
            style="width: 100px; height: 100px"
            :key="scope.row.goodsNo"
            :src="scope.row.goodsImageName"
            alt="商品主图"
          />
        </template>
      </el-table-column>
      <el-table-column prop="goodsNumber" label="商品库存"> </el-table-column>
      <el-table-column prop="goodsPrice" label="商品售价"> </el-table-column>
      <el-table-column label="上架状态">
        <template #default="scope">
          <span style="color: green" v-if="0 == 0">销售中</span>
          <span style="color: red" v-else>已下架</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="100">
        <template #default="scope">
          <a style="cursor: pointer; margin-right: 10px" @click="handleEdit(scope.row.goodsId)"
            >修改</a
          >
          <a
            style="cursor: pointer; margin-right: 10px"
            v-if="scope.row.goodsSellStatus == 0"
            @click="handleStatus(scope.row, true)"
            >下架</a
          >
          <a
            style="cursor: pointer; margin-right: 10px"
            v-else
            @click="handleStatus(scope.row, false)"
            >上架</a
          >
        </template>
      </el-table-column>
    </el-table>
    <!--总数超过一页，再展示分页器-->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="state.total"
      :page-size="state.pageSize"
      :current-page="state.currentPage"
      @current-change="changePage"
    />
  </el-card>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
// import { getInnerRange } from '@vue/compiler-core';
  import { useRouter } from 'vue-router'

  const router = useRouter()

//----商品信息
// 商品名称
// 商品图片
// 商品价格
// 商品品牌
// 商品数量
// 商品编号
// 商品状态（下架/销售中）
//
interface GoodsInfo {
  goodsName: string
  goodsImageName: string
  goodsPrice: number
  storeName: string
  goodsNumber: number
  goodsBrand: string
  goodsNo: string
  goodsState: string
}
const tableData = ref<GoodsInfo[]>([{
    goodsName: '三星s23',
    goodsImageName: '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png',
    goodsPrice: 6999,
    storeName: 'git商城自营店',
    goodsNumber: 100,
    goodsNo: '318458',
    goodsBrand: '三星',
    goodsState: '上架'
}])
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
  console.log('Add Goods!!!')
  router.push({ path: '/admin/AddGoods' })
}
const handleEdit = (id: string) => {
    console.log('Edit goods!!!')
  router.push({ path: '/admin/EditGoods', query: { id } })
}
const changePage = (val: number) => {
  state.currentPage = val
  getGoodList()
}
const handleStatus = (row: GoodsInfo, status: boolean) => {
    console.log(row)
  // axios
}
</script>

<style scoped>
.good-container {
  min-height: 100%;
}
.el-card.is-always-shadow {
  min-height: 100% !important;
}
</style>
