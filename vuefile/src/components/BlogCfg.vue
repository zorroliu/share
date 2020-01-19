<template>
  <el-card style="width: 500px" v-loading="loading">
    <div>
      <div style="text-align: left">
        <el-form :model="emailValidateForm" label-position="top" ref="emailValidateForm"
                 style="color:#20a0ff;font-size: 14px;">
          <el-form-item
            prop="email"
            label="开启博客评论通知"
            :rules="[{type: 'email', message: '邮箱格式不对哦!'}]">
            <el-button type="primary" @click="submitForm('emailValidateForm')" size="mini">确定</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </el-card>
</template>
<script>
  export default{
    data(){
      return {
        emailValidateForm: {
          email: ''
        },
        loading: false
      }
    }
  }
</script>
