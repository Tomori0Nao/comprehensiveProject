<template>
  <el-dialog :title="props.type == 'add' ? '添加分类' : '修改分类'" v-model="state.visible" width="400px">
    <el-form :model="state.ruleForm" :rules="state.rules" ref="formRef" label-width="150px" class="good-form">
      <el-form-item label="商品类型名称" prop="name">
        <el-input type="text" v-model="state.ruleForm.name"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="state.visible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import type { FormInstance } from 'element-plus'
axios.defaults.withCredentials = true

const props = defineProps({
  type: String, // 用于判断是添加还是编辑
  reload: Function // 添加或修改完后，刷新列表页
})
interface CateoryInfo {
  categoryId: string
  categoryName: string
  addTime: string
}
const emit = defineEmits<{
  (e: 'AddCategory'): void
  (e: 'EditCategory', categoryId: string, categoryName: string): void
}>()

const formRef = ref<FormInstance>()
const state = reactive({
  visible: false,
  ruleForm: {
    name: '',
    rank: ''
  },
  rules: {
    name: [{ required: 'true', message: '名称不能为空', trigger: ['change'] }],
    rank: [{ required: 'true', message: '编号不能为空', trigger: ['change'] }]
  },
  id: ''
})
const path = "http://localhost:8080"
// 获取详情
const getDetail = (id: string) => {
  axios({
    method: 'get',
    url: path + '/getCategoryName',
    params: {
      categoryNo: ''
    }
  }).then((response) => {
    const respData = response.data;
    console.log(respData);
  }).catch((error) => {
    console.log("error = " + error);

  })
}
// 开启弹窗
const open = (id?: string, categoryName?: string) => {
  state.visible = true
  if (id) {
    state.id = id
    // 如果是有 id 传入，证明是修改模式
    if (categoryName) {
      state.ruleForm.name = categoryName
    }
    // getDetail(id)
  } else {
    // 否则为新增模式
    state.ruleForm = {
      name: '',
      rank: ''
    }
  }
}
// 关闭弹窗
const close = () => {
  state.visible = false
}
const submitForm = () => {
  formRef.value.validate((valid: boolean) => {
    if (valid) {
      if (props.type == 'add') {
        // 添加商品种类
        //   axios
        axios({
          method: 'get',
          url: path + '/addGoodsType',
          params: {
            categoryName: '武器'
          }
        }).then((response) => {
          const respData = response.data;
          if (respData.code == 1) {
            console.log(respData.msg);
          } else {
            console.log(respData.msg);
          }
          console.log(respData);
        }).catch((error) => {
          console.log("error = " + error);

        })
        emit('AddCategory')
        state.visible = false
      } else {
        // 修改商品种类
        //   axios
        axios({
          method: 'get',
          url: path + '/updateGoodsType',
          params: {
            categoryNo: 'category002',
            categoryName: '手机666'
          }
        }).then((response) => {
          const respData = response.data;
          if (respData.code == 1) {
            console.log(respData.msg);
          } else {
            console.log(respData.msg);
          }
          console.log(respData);
        }).catch((error) => {
          console.log("error = " + error);

        })
        emit('EditCategory', state.id, state.ruleForm.name)
        state.visible = false
      }
    }
  })
}
defineExpose({ open, close })
</script>
