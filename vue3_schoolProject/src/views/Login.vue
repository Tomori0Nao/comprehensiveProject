<template>
  <div>
    <div class="b1">
      <h2>欢迎登录</h2>
    </div>
    <el-form :model="formLogin" :rules="rules" ref="ruleForm" label-width="0px" class="login-bok">
      <el-col :span="16" style="margin-left: 60px; margin-top: 30px;">
        <el-form-item prop="account">
          <el-input v-model="formLogin.account" placeholder="请输入账号">
            <i slot="prepend" class="el-icon-s-custom"></i>
          </el-input>
        </el-form-item></el-col>

      <el-col :span="16" style="margin-left: 60px;">
        <el-form-item prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="formLogin.password">
            <i slot="prepend" class="el-icon-lock"></i>
          </el-input>
        </el-form-item>
      </el-col>
      <el-col :span="16" style="margin-left: 60px;">
        <el-form-item prop="captcha">
          <el-row :span="16">
            <el-col :span="13">
              <el-input v-model="formLogin.captcha" auto-complete="off" placeholder="请输入验证码" size=""></el-input>
            </el-col>
            <el-col :span="5">
              <div class="login-code" @click="refreshCode">
                <!--验证码组件-->
                <s-identify :identifyCode="identifyCode"></s-identify>
              </div>
            </el-col>
          </el-row>
        </el-form-item>
      </el-col>
      <el-form-item>
        <div class="login-btn-box">
          <el-button class="login-btn" type="primary" @click="toLogin"
            style="margin-left: 10px; width: 35%">登录</el-button>
          <el-button class="register-btn" type="primary" @click="toRegister"
            style="margin-left: 20px; width: 35%">注册</el-button>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import axios from 'axios'
import SIdentify from "../components/SIdentify.vue"
/////////////////////////////////////////////////////////////
///////////////////
//下面这行代码一定要加上，这样才能保证浏览器与服务器的会话是同一个会话sessionId才是固定不变的
axios.defaults.withCredentials = true
////////////////////////////////////////////////////////////
export default {
  name: "Login",
  components: { SIdentify },
  data() {
    return {
      formLogin: {
        account: "",
        password: "",
        captcha: "",
      },
      identifyCodes: "1234567890abcdefjhijklinopqrsduvwxyz", //随机串内容
      identifyCode: "",
      // 校验
      rules: {
        account: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],

        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        captcha: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      },
    };
  },
  mounted() {
    // 初始化验证码
    this.identifyCode = "";
    this.makeCode(this.identifyCodes, 4);
  },
  methods: {
    toLogin() {
      if (this.formLogin.account == '' || this.formLogin.password == '') {
        this.$message.error("请填写账号和密码");
      } else if (this.formLogin.captcha == '') {
        this.$message.error("请填写验证码");
      } else if (
        this.formLogin.captcha.toLowerCase() != this.identifyCode.toLowerCase()
      ) {
        console.log(this.formLogin.captcha);
        console.log(this.identifyCode.toLowerCase());
        this.$message.error("请填写正确验证码");
        this.refreshCode();
      } //账号密码不空，且验证码输入正确
      else {
        axios({
          method: 'POST',
          url: 'http://localhost:8080/checkLogin',
          params: {
            account: this.formLogin.account,
            password: this.formLogin.password
          }
        })
          .then((response) => {
            if (response.data == true) {
              console.log('登陆成功')
              window.sessionStorage.setItem('notLogin', JSON.stringify(response.data))
              this.$router.push('/test')
            } else {
              alert('请检查账号密码')
            }
          })
          .catch((error) => {
            console.log(error)
          })
      }
    },
    toRegister() {
      this.$router.push('/register')
    },
    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode +=
          this.identifyCodes[this.randomNum(0, this.identifyCodes.length)];
      }
    },
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },

    submitForm() {
      //this.$router.push("/space");
      sessionStorage.setItem("isLogin", true);

    },
  },
};
</script>

<style>
.b1 {
  margin-top: 50px;
  text-align: center;
}

.login-bok {
  width: 30%;
  margin: 50px auto;
  border: 1px solid #dcdfe6;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 30px #dcdfe6;
}

.login-bok .login-btn-box {
  margin: auto;
  /* background-color: pink; */
}
</style>



