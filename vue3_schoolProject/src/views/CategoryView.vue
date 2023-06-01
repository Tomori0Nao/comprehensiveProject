<template>
  <el-card class="category-container">
    <template #header>
      <div class="header">
        <el-button type="primary" :icon="Plus" @click="handleAdd">增加</el-button>
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
      <el-table-column prop="categoryName" label="类型名称"> </el-table-column>
      <el-table-column label="操作" width="220">
        <template #default="scope">
          <a style="cursor: pointer; margin-right: 10px" @click="handleEdit(scope.row)">修改</a>
          <el-popconfirm
            title="确定删除吗？"
            confirmButtonText="确定"
            cancelButtonText="取消"
            @confirm="handleDeleteOne(scope.row)"
          >
            <template #reference>
              <a style="cursor: pointer">删除</a>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <AddCategory
      ref="addCate"
      :reload="getCategory"
      :type="state.type"
      @add-category="handleAddCategory"
      @edit-category="handleEditCategory"
    ></AddCategory>
  </el-card>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElTable } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import axios from 'axios'
import AddCategory from '@/components/AddCategory.vue'
axios.defaults.withCredentials = true

type AddCateType = InstanceType<typeof AddCategory>
interface CateoryInfo {
  categoryId: string
  categoryName: string
}
const tableData = ref<CateoryInfo[]>([
  // {
  //   categoryId: '14353245',
  //   categoryName: '手机',
  // }
])
const multipleSelection = ref<CateoryInfo[]>([])
const multipleTableRef = ref<InstanceType<typeof ElTable>>()

const addCate = ref<AddCateType>()
const state = reactive({
  loading: false,
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 10, // 分页大小
  type: 'add', // 操作类型
  level: 1,
  parent_id: 0
})
onMounted(() => {
  getCategory()
})

// 获取分类列表
const getCategory = () => {
  state.loading = true
  // axios
  axios({
    method: 'get',
    url: path + '/getAllGoodsType',
    params: {}
  })
    .then((response) => {
      const respData = response.data
      const resTypeList = respData.data
      tableData.value = []
      for (const iterator of resTypeList) {
        let tem: CateoryInfo = {
          categoryId: '',
          categoryName: ''
        }
        tem.categoryId = iterator.categoryNo
        tem.categoryName = iterator.categoryName
        tableData.value.push(tem)
      }
      console.log(respData)
    })
    .catch((error) => {
      console.log('error = ' + error)
    })
}
const changePage = (val: number) => {
  state.currentPage = val
  getCategory()
}

// 添加分类
const handleAdd = () => {
  state.type = 'add'
  addCate.value?.open()
  // setTimeout(getCategory,3000)
  // getCategory()
}
// 修改分类
const handleEdit = (row: CateoryInfo) => {
  state.type = 'edit'
  addCate.value?.open(row.categoryId, row.categoryName)
  // setTimeout(getCategory,3000)
  // getCategory()
}
// 选择项
const handleSelectionChange = (val: CateoryInfo[]) => {
  multipleSelection.value = val
}

const path = 'http://localhost:8080'

// 单个删除
const handleDeleteOne = (row: CateoryInfo) => {
  //   axios
  axios({
    method: 'get',
    url: path + '/deleteGoodsType',
    params: {
      categoryNo: row.categoryId
    }
  })
    .then((response) => {
      const respData = response.data
      console.log(respData)
      setTimeout(getCategory, 1000)
    })
    .catch((error) => {
      console.log('error = ' + error)
    })
}
const handleAddCategory = () => {
  setTimeout(getCategory, 1000)
  // getCategory()
}
const handleEditCategory = (categoryId: string, categoryName: string) => {
  console.log('edit submit!!!')
  // for (const iterator of tableData.value) {
  //   if (iterator.categoryId == categoryId) {
  //     iterator.categoryName = categoryName
  //   }
  // }
  setTimeout(getCategory, 1000)
  // getCategory()
}
getCategory()
</script>

<style></style>
