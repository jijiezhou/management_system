<template>
  <div style="height: 100vh; display: flex; align-items: center; justify-content: center; background-color: #669fef">
    <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
      <div style="flex: 1">
        <img src="@/assets/register.png" alt="login" style="width: 100%">
      </div>

      <div style="flex: 1; display: flex; align-items: center; justify-content: center">
        <el-form :model="user" :rules="rules" style="width: 80%" ref="registerRef">
          <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 20px">Welcome to Register Page
          </div>
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" size="medium" placeholder="please input username"
                      v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="please input password"
                      v-model="user.confirmPass"></el-input>
          </el-form-item>

          <el-form-item prop="confirmPass">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="please confirm password"
                      v-model="user.password"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="info" style="width: 100%" @click="register">Sign Up</el-button>
          </el-form-item>

          <div style="display: flex">
            <div style="flex: 1">Have Account?<span
                style="color: #6E77F2; cursor: pointer; margin-left: 4px" @click="$router.push('/login')">Log In</span></div>
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
  data() {
    //Verification authentication
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('please confirm password'));
      } else if (confirmPass.toLowerCase() != this.user.password) {
        callback(new Error('please make sure password consistent'))
      } else {
        //pass
        callback();
      }
    };

    return {
      user: {
        username: '',
        password: '',
        confirmPass: ''  //must be same as password
      },
      rules: {
        username: [
          {required: true, message: 'please enter username', trigger: 'blur'},
        ],
        password: [
          {required: true, message: 'please enter password', trigger: 'blur'},
        ],
        confirmPass: [
          {validator: validatePassword, trigger: 'blur'}
        ],
      }
    }
  },
  created() {

  },
  methods: {
    register() {
      this.$refs['registerRef'].validate((valid) => {
        if (valid) {
          //after check valid then send request
          this.$request.post("/register", this.user).then(res => {
            if (res.code == 200){
              this.$router.push("/login")
              this.$message.success('Sign Up Success')
            }else{
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
  }
}
</script>


<style scoped>

</style>
