<template>
  <el-card class="order-container">
    <template #header>
      <div class="header">
        <el-input style="width: 200px; margin-right: 10px" placeholder="请输入订单号" v-model="state.orderNo"
          @change="handleOption" clearable />
      </div>
    </template>
    <el-table :load="state.loading" :data="tableData" tooltip-effect="dark" style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="orderNo" label="订单号"> </el-table-column>
      <el-table-column prop="totalCost" label="订单总价"> </el-table-column>
      <el-table-column prop="userAccount" label="用户"> </el-table-column>
      <el-table-column prop="orderDate" label="创建时间"> </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <router-link
            class="routerLink"
            :to="{ path: '/admin/OrderDetail', query: { id: scope.row.orderNo } }"
            >订单详情</router-link
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="prev, pager, next" :total="state.total" :page-size="state.pageSize"
      :current-page="state.currentPage" @current-change="changePage" />
  </el-card>
</template>
<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { HomeFilled, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
axios.defaults.withCredentials = true

interface OrderInfo {
  orderNo: string
  orderDate: string
  totalCost: number
  userAccount: string
}
const multipleSelection = ref<OrderInfo[]>([])
const tableData = ref<OrderInfo[]>([
  {
    orderNo: 'order2',
    orderDate: '2023-4-4',
    totalCost: 6999,
    userAccount: '1235432'
  }
])
const state = reactive({
  loading: false,
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 10, // 分页大小
  orderNo: '' // 订单号
})
// 初始化获取订单列表
onMounted(() => {
  getOrderList()
})
// 获取列表方法
const getOrderList = () => {
  state.loading = true
  // axios
}
// 触发过滤项方法
const handleOption = () => {
  state.currentPage = 1
  getOrderList()
}
// checkbox 选择项
const handleSelectionChange = (val: OrderInfo[]) => {
  multipleSelection.value = val
}
// 翻页方法
const changePage = (val: number) => {
  state.currentPage = val
  getOrderList()
}
const path = 'http://localhost:8080'
axios({
  method: 'get',
  url: path + '/getOrders',
}).then((response) => {
  const respData = response.data;
  console.log(respData);

}).catch((error) => {
  console.log('error = ' + error);

})
</script>

<style scoped>
.routerLink {
  color: #409EFF;
  text-decoration: none;
}
</style>
