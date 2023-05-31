<template>
  <el-card class="category-container">
    <template #header>
      <div class="header">
        <el-button type="primary" :icon="Plus" @click="handleAdd">增加</el-button>
        <el-popconfirm
          title="确定删除吗？"
          confirmButtonText="确定"
          cancelButtonText="取消"
          @confirm="handleDelete"
        >
          <template #reference>
            <el-button type="danger" :icon="Delete">批量删除</el-button>
          </template>
        </el-popconfirm>
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
      <el-table-column prop="addTime" label="添加时间" width="200"> </el-table-column>
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
    <!--总数超过一页，再展示分页器-->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="state.total"
      :page-size="state.pageSize"
      :current-page="state.currentPage"
      @current-change="changePage"
    />
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

type AddCateType = InstanceType<typeof AddCategory>
interface CateoryInfo {
  categoryId: string
  categoryName: string
  addTime: string
}
const tableData = ref<CateoryInfo[]>([
  {
    categoryId: '14353245',
    categoryName: '手机',
    addTime: '2033-4-23'
  }
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
}
const changePage = (val: number) => {
  state.currentPage = val
  getCategory()
}

// 添加分类
const handleAdd = () => {
  state.type = 'add'
  addCate.value?.open()
}
// 修改分类
const handleEdit = (row: CateoryInfo) => {
  state.type = 'edit'
  addCate.value?.open(row.categoryId, row.categoryName)
}
// 选择项
const handleSelectionChange = (val: CateoryInfo[]) => {
  multipleSelection.value = val
}
// 批量删除
const handleDelete = () => {
  if (!multipleSelection.value.length) {
    ElMessage.error('请选择项')
    return
  }
  // multipleSelection.value 为待删除的数组
  //   axios
}
// 单个删除
const handleDeleteOne = (row: CateoryInfo) => {
  //   axios
}
const handleAddCategory = () => {}
const handleEditCategory = (categoryId: string,categoryName:string) => {
  console.log("edit submit!!!")
  for (const iterator of tableData.value) {
    if (iterator.categoryId == categoryId) {
      iterator.categoryName = categoryName
      // console.log(iterator,'find it !!!')
    }
  }
  // tableData.value[index].categoryName = categoryName
}
</script>

<style></style>
