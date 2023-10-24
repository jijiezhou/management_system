<template>
  <div style="height: 100vh; display: flex; align-items: center; justify-content: center; background-color: #0f9876">
    <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
      <div style="flex: 1">
        <img src="@/assets/register.png" alt="login" style="width: 100%">
      </div>

      <div style="flex: 1; display: flex; align-items: center; justify-content: center">
        <el-form :model="user" :rules="rules" style="width: 80%" ref="loginRef">
          <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 20px">Welcome to Management
            System
          </div>
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" size="medium" placeholder="please input username"
                      v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="please input password"
                      v-model="user.password"></el-input>
          </el-form-item>

          <el-form-item prop="code">
            <div style="display: flex">
              <el-input prefix-icon="el-icon-circle-check" size="medium" style="flex: 1" v-model="user.code" placeholder="pls input code"></el-input>
              <div style="flex: 1; height: 36px">
                <valid-code @update:value="getCode"/>
              </div>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="login">Log In</el-button>
          </el-form-item>

          <div style="display: flex">
            <div style="flex: 1">Not Registered?<span
                style="color: #0f9876; cursor: pointer; margin-left: 4px">Sign Up</span></div>
            <div style="flex: 1; text-align: right"><span style="color: #0f9876; cursor: pointer">Forget Password</span>
            </div>
          </div>

        </el-form>

      </div>
    </div>
  </div>
</template>

<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  name: "Register",
  components: {
    ValidCode
  },
  data() {
    //Verification authentication
    const validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('please enter password'));
      } else if (value.toLowerCase() != this.code) {
        callback(new Error('verification code incorrect'))
      } else {
        //pass
        callback();
      }
    };

    return {
      code: '',   //ValidCode sent
      user: {
        username: '',
        password: '',
        code: ''  //user input, need to compare with Validcode
      },
      rules: {
        username: [
          {required: true, message: 'please enter username', trigger: 'blur'},
        ],
        password: [
          {required: true, message: 'please enter password', trigger: 'blur'},
        ],
        code: [
          {validator: validateCode, trigger: 'blur'}
        ],
      }
    }
  },
  created() {

  },
  methods: {
    login() {
      this.$refs['loginRef'].validate((valid) => {
        if (valid) {
          //after check valid then send request
          this.$request.post("/login", this.user).then(res => {
            if (res.code == 200){
              this.$router.push("/")
              this.$message.success('Log In Success')
              localStorage.setItem("system_user", JSON.stringify(res.data)) //store user
            }else{
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    getCode(code) {
      this.code = code.toLowerCase();
    }
  }
}
</script>


<style scoped>

</style>
