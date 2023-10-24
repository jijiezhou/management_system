<template>
  <div style="height: 100vh; display: flex; align-items: center; justify-content: center; background-color: #0f9876">
    <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
      <div style="flex: 1">
        <img src="@/assets/login.png" alt="login" style="width: 100%">
      </div>

      <div style="flex: 1; display: flex; align-items: center; justify-content: center">
        <el-form :model="user" style="width: 80%">
          <div style="font-size: 20px; font-weight: bold; text-align: center; margin-bottom: 20px">Welcome to Management
            System
          </div>
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" size="medium" placeholder="please input username" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" size="medium" show-password placeholder="please input password"
                      v-model="user.password"></el-input>
          </el-form-item>

          <el-form-item prop="validCode">
            <div style="display: flex">
              <el-input prefix-icon="el-icon-circle-check" size="medium" style="flex: 1"></el-input>
              <div style="flex: 1">
                <valid-code></valid-code>
              </div>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 100%">Log In</el-button>
          </el-form-item>

          <div style="display: flex">
            <div style="flex: 1">Not Registered?<span style="color: #0f9876; cursor: pointer">Sign Up</span></div>
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
  name: "Login.vue",
  components: {
    ValidCode
  },
  data() {
    return {
      user: {
        username: '',
        password: ''
      }
    }
  },
  created() {

  },
  methods: {}
}
</script>


<style scoped>

</style>
