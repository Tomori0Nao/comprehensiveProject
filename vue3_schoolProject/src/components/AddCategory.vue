<template>
    <el-dialog
      :title="props.type == 'add' ? '添加分类' : '修改分类'"
      v-model="state.visible"
      width="400px"
    >
      <el-form :model="state.ruleForm" :rules="state.rules" ref="formRef" label-width="100px" class="good-form">
        <el-form-item label="商品名称" prop="name">
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
  
  const props = defineProps({
    type: String, // 用于判断是添加还是编辑
    reload: Function // 添加或修改完后，刷新列表页
  })
  
  const formRef = ref<FormInstance>()
  const state = reactive({
    visible: false,
    ruleForm: {
      name: '',
      rank: ''
    },
    rules: {
      name: [
        { required: 'true', message: '名称不能为空', trigger: ['change'] }
      ],
      rank: [
        { required: 'true', message: '编号不能为空', trigger: ['change'] }
      ]
    },
    id: ''
  })
  // 获取详情
  const getDetail = (id:string) => {
    // axios
    
  }
  // 开启弹窗
  const open = (id?:string) => {
    state.visible = true
    if (id) {
      state.id = id
      // 如果是有 id 传入，证明是修改模式
      getDetail(id)
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
    formRef.value.validate((valid:boolean) => {
      if (valid) {
        if (props.type == 'add') {
          // 添加方法
        //   axios
        state.visible = false
        } else {
          // 修改方法
        //   axios
        state.visible = false
        }
      }
    })
  }
  defineExpose({ open, close })
  </script>