<script lang="ts" setup>
import { ElMessage, ElMessageBox } from 'element-plus'
</script> 
<template>
    <div class="box">
        <div class="box-left">&nbsp;</div>
        <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
            <h3 class="login-title">欢迎注册</h3>
            <el-form-item label="电话号码" prop="username" label-width="80px">
                <el-input type="text" placeholder="请输入11位电话号码" v-model="form.tel" size="medium" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" placeholder="请输入密码" v-model="form.password" size="medium" />
            </el-form-item>
            <el-form-item label="确认密码" prop="password">
                <el-input type="password" placeholder="请再次输入密码" v-model="form.password1" size="medium" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" v-on:click="register">提交</el-button>
                <el-button type="success" v-on:click="resetForm" style="margin-left: 60px;">重新填写</el-button>
            </el-form-item>

        </el-form>



    </div>
</template>
<script lang="ts">
import axios from 'axios';
import { h, ref } from 'vue'
//没有输入账号或密码则提示
const open1 = () => {
    ElMessage({
        dangerouslyUseHTMLString: true,
        message: '<strong>电话号码或密码不能为空</strong>',
    })
}
// 没有输入验证码则提示
const open2 = () => {
    ElMessage({
        dangerouslyUseHTMLString: true,
        message: '<strong>请检查两次输入的密码</strong>',
    })
}
export default {
    name: "Content",
    data() {
        return {
            form: {
                tel: '',
                password: '',
                password1: ''
            },

            // 表单验证，需要在 el-form-item 元素中增加 prop 属性
            rules: {
                username: [
                    { required: true, message: '电话号码不可为空', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '密码不可为空', trigger: 'blur' }
                ],
                password1: [
                    { required: true, message: '密码不可为空', trigger: 'blur' }
                ]
            },
            // 对话框显示和隐藏
            dialogVisible: false
        }
    },
    methods: {
        register: function () {
            if (this.form.tel == '' || this.form.password == '' || this.form.password1 == '') {
                open1()
            } else if (this.form.tel.length != 11) {
                ElMessage({
                    dangerouslyUseHTMLString: true,
                    message: '<strong>请输入11位电话号码</strong>',
                })
            }
            else if (this.form.password1 != this.form.password) {
                open2()
            }
            else {
                axios({
                    method: 'POST',
                    url: 'http://localhost:8080/register',
                    params: {
                        tel: this.form.tel,
                        password: this.form.password
                    }
                }).then(
                    response => {
                        if (response.data != '') {
                            console.log("注册成功");
                            ElMessageBox({
                                title: 'Message',
                                message: h('p', null, [
                                    h('span', null, '注册成功 '),
                                    h('i', { style: 'color: teal' }, '账号是：' + response.data),
                                ]),
                            }),
                                this.$router.push('/login');
                        } else {
                            alert("同一个身份信息请勿重复注册");
                        }

                    }
                ).catch(error => {
                    console.log(error);
                })
            }
        },
        resetForm: function () {
            this.form.tel = '';
            this.form.password = '',
                this.form.password1 = ''
        }
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
    
    