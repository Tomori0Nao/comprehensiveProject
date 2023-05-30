<template>
    <div class="rg_layout">
        <div class="rg_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_center">
            <div class="rg_form">
                <div style="margin: 50px 0;"></div>
                <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                    <!-- <el-form-item label="Email" prop="Email">
                        <el-col :span="12">
                            <el-input placeholder="请输入邮箱号" v-model="form.email"></el-input>
                        </el-col>
                        <el-col :span="9">
                            <el-button type="success" plain @click="sendEmail">发送邮件验证</el-button>
                        </el-col>
                    </el-form-item> -->
                    <el-form-item label="电话号码" prop="mobileNumber">
                        <el-col :span="15">
                            <el-input placeholder="请输入11位手机号" v-model="form.tel"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="用户昵称" prop="username">
                        <el-col :span="15">
                            <el-input placeholder="请输入用户昵称" v-model="form.username"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-col :span="15">
                            <el-input type="password" placeholder="请输入密码" v-model="form.password"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="确认密码">
                        <el-col :span="15">
                            <el-input type="password" placeholder="请再次输入密码" v-model="form.password1"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-col :span="5">
                            <el-radio v-model="form.radio" label="1">男</el-radio>
                        </el-col>
                        <el-col :span="3">
                            <el-radio v-model="form.radio" label="2">女</el-radio>
                        </el-col>
                    </el-form-item>

                    <!-- <el-form-item label="验证码">
                        <el-col :span="15">
                            <el-input type="text" placeholder="验证码将会发送到您的邮箱" v-model="form.text"
                                oninput="value=value.replace(/\D/g,'')" maxlength="6" show-word-limit>
                            </el-input>
                        </el-col>
                    </el-form-item> -->
                    <el-form-item>
                        <el-col :span="20">
                            <el-button type="primary" @click="onSubmit">立即注册</el-button>
                        </el-col>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <div class="rg_right">
            <p>已有账号?
                <el-link icon="el-icon-user-solid" type="primary" href="/login">立刻登陆</el-link>
            </p>
        </div>

    </div>
</template>
  
<script >
import axios from "axios";

export default {
    // mounted() {
    //     this.$store.state.yesOrNo = false
    // },
    name: "signUp",
    data: function () {
        return {
            form: {
                // email: '',
                tel: '',
                username: '',
                password: '',
                password1: '',
                radio: '1',
                date: '',
                text: ''
            },
            // rules: {
            //     Email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
            //     mobileNumber: [
            //         { required: true, message: "请输入电话号码", trigger: "blur" },
            //     ],
            //     username: [
            //         { required: true, message: "请输入昵称", trigger: "blur" },
            //     ]
            // },
            msg: ''
        }
    },
    methods: {
        login_asd() {
            this.$router.push('/login');
        },
        open1() {
            this.$message({
                showClose: true,
                message: this.msg,
                type: 'warning'
            });
        },
        open2() {
            this.$message({
                showClose: true,
                message: this.msg,
                type: 'success'
            });
        },
        open3() {
            this.$message({
                showClose: true,
                message: this.msg,
                type: 'error'
            });
        },
        // sendEmail() {
        //     //邮箱校验的正则表达式
        //     const emailRegExp = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        //     const urlPrefix = "http://localhost:8080";

        //     if (this.form.email == '') {
        //         this.msg = "请输入你的邮箱"
        //         this.open1();
        //         return;
        //     } else if (!emailRegExp.test(this.form.email)) {
        //         this.msg = "请输入正确的邮箱"
        //         this.open1();
        //         return;
        //     }
        //     //发送邮箱的路径
        //     axios.post(':8412/user/sendSignUpCode?email=' + _this.form.email,
        //     ).catch(function (error) {
        //         this.msg = "邮箱格式不正确！"
        //         this.open1()
        //     }).then(function (response) {
        //         if (response.data.code === 200) {
        //             this.msg = response.data.msg
        //             this.open2()
        //         } else {
        //             this.msg = response.data.msg
        //             this.open3()
        //         }
        //     })


        // },
        onSubmit() {
            //邮箱校验的正则表达式
            // const emailRegExp = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            const urlPrefix = "http://localhost:8080";
            {
                /*if (this.form.email == '') {
                    this.msg = "请输入你的邮箱"
                    this.open1();
                    return;
                } else if (!emailRegExp.test(this.form.email)) {
                    this.msg = "请输入正确的邮箱"
                    this.open1();
                    return;
                } else */if (this.form.tel == '') {
                    this.msg = "请输入你的电话"
                    this.open1();
                    return;
                } else if (this.form.tel.length != 11) {
                    this.msg = "请输入11位正确的电话号码"
                    this.open1();
                    return;
                } else if (this.form.username == '') {
                    this.msg = "请输入你的昵称"
                    this.open1();
                    return;
                } else if (this.form.password == '') {
                    this.msg = "请输入你的密码"
                    this.open1();
                    return;
                } else if (this.form.password != this.form.password1) {
                    this.msg = "两次密码输入不一致，请检查"
                    this.open1();
                    return;
                }
                //  else if (this.form.text == '') {
                //     this.msg = "请输入验证码"
                //     this.open1();
                //     return;
                // } 
                else {
                    console.log("恭喜通过层层考验");
                    axios({
                        method: "post",
                        url: urlPrefix + '/register',
                        params: {
                            //email: this.form.email,
                            tel: this.form.tel,
                            password: this.form.password,
                            username: this.form.username
                        }
                    }).catch((error) => {
                        this.msg = "注册失败"
                        this.open1()
                    }).then(response => {
                        console.log(response.data)
                        if (response.data.code === 1) {
                            console.log("注册成功");
                            this.$alert('<strong>账号：' + response.data.data + '</strong>', '注册成功', {
                                dangerouslyUseHTMLString: true
                            });
                            //this.$router.push('/login');
                        } else {
                            this.msg = response.data.msg
                            this.open3()
                        }
                    })
                }
            }
        }
    }
}
</script>
  
  
<style>
* {
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
}

body {
    /* background-image: url(https://img-blog.csdnimg.cn/76110abf7fb84ee28c50bfbfa7fa8e11.jpg); */
    background-repeat: no-repeat;
    background-size: 100%;
    background-position: 0px -50px;
}

.rg_layout {
    width: 900px;
    height: 500px;
    border: 5px solid #EEEEEE;
    background-color: white;
    opacity: 0.8;
    /*让div水平居中*/
    margin: auto;
    margin-top: 100px;
}

.rg_left {
    float: left;
    margin: 15px;
    width: 20%;
}

.rg_left>p:first-child {
    color: #FFD026;
    font-size: 20px;
}

.rg_left>p:last-child {
    color: #A6A6A6;
}

.rg_center {
    /*border: 1px solid red;*/
    float: left;
    width: 450px;
    /*margin: 15px;*/
}

.rg_right {
    float: right;
    margin: 15px;
}

.rg_right>p:first-child {
    font-size: 15px;
}

.rg_right p a {
    color: pink;
}
</style>
  
  