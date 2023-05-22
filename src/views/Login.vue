<script lang="ts" setup>
import { ElMessage } from 'element-plus'
</script>
<template>
  <div class="box">
    <div class="box-left">&nbsp;</div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">欢迎登录</h3>
      <el-form-item label="账号" prop="username" label-width="80px">
        <el-input type="text" placeholder="请输入账号" v-model="form.account" size="medium" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" placeholder="请输入密码" v-model="form.password" size="medium" />
      </el-form-item>
      <el-form-item label="验证码" prop="captcha">
        <el-col :span="10">
          <el-input type="text" placeholder="请输入验证码" v-model="form.captcha" size="medium" maxlength="4" />
        </el-col>

      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="toLogin">登录</el-button>
        <el-button type="success" v-on:click="toRegister" style="margin-left: 60px;">注册</el-button>
      </el-form-item>

    </el-form>



  </div>
</template>
<script lang="ts">
import axios from 'axios';

//没有输入账号或密码则提示
const open1 = () => {
  ElMessage({
    dangerouslyUseHTMLString: true,
    message: '<strong>请输入账号或密码</strong>',
  })
}
// 没有输入验证码则提示
const open2 = () => {
  ElMessage({
    dangerouslyUseHTMLString: true,
    message: '<strong>请正确地输入验证码</strong>',
  })
}
export default {
  name: "Content",
  data() {
    return {
      form: {
        account: '',
        password: '',
        captcha: ''
      },

      // 表单验证，需要在 el-form-item 元素中增加 prop 属性
      rules: {
        username: [
          { required: true, message: '账号不可为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不可为空', trigger: 'blur' }
        ],
        captcha: [
          { required: true, message: '验证码不可为空', trigger: 'blur' }
        ]
      },
      // 对话框显示和隐藏
      dialogVisible: false
    }
  },
  methods: {
    toLogin: function () {
      if (this.form.account == '' || this.form.password == '') {
        open1()
      } else if (this.form.captcha == '' || this.form.captcha != '1234') {
        open2()
      }
      //账号密码不空，且验证码输入正确
      else {
        axios({
          method: 'POST',
          url: 'http://localhost:8080/checkLogin',
          params: {
            account: this.form.account,
            password: this.form.password
          }
        }).then(
          response => {
            if (response.data == true) {
              console.log("登陆成功");
              window.sessionStorage.setItem("notLogin", JSON.stringify(response.data));
              this.$router.push('/test');
            } else {
              alert("请检查账号密码");
            }

          }
        ).catch(error => {
          console.log(error);

        })
      }
    },
    toRegister: function () {
      this.$router.push('/register');
    },
  }
}
</script>
  
<style>
.box {
  height: 650px;
}

.login-box {
  display: inline-block;
  border: 1px solid #DCDFE6;
  width: 350px;
  margin: 150px 300px 180px 800px;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>
    
    