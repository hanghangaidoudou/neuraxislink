<template>
  <div class="app-container">
    <div class = "head_div">密码变更</div>
    <!-- 用户属性 -->
    <div class="filter-container">
      <el-form 
      ref="dataForm" 
      :model="pwdModel" 
      :rules="rules" 
      labelPosition="left" 
      label-width="100px">
        <el-row>
          <el-col :span="9">&nbsp;</el-col>
          <el-col :span="6">        
            <el-form-item label="原密码" prop="oldPassword">
              <el-input type="password" v-model="pwdModel.oldPassword" placeholder="请输入原密码" style="width: 200px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="9">&nbsp;</el-col>
        </el-row>
        <el-row>
          <el-col :span="9">&nbsp;</el-col>
          <el-col :span="6">        
            <el-form-item label="新密码" prop="newPassword">
              <el-input type="password" v-model="pwdModel.newPassword" placeholder="请输入新密码" style="width: 200px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="9">&nbsp;</el-col>
        </el-row>

        <el-row>
          <el-col :span="9">&nbsp;</el-col>
          <el-col :span="6">        
            <el-form-item label="重复新密码" prop="confirmPassword">
              <el-input type="password" v-model="pwdModel.confirmPassword" placeholder="请再次输入新密码" style="width: 200px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="9">&nbsp;</el-col>
        </el-row>
      </el-form>
    </div>
    <div class = "root_div">                
      <el-button type="primary" @click="updPwd()">确认</el-button>
    </div>
  </div>
</template>

<script>
import { isPassword } from '@/utils/validate'
import { updatePwd } from '@/api/user'
import { Form } from 'element-ui'
import { mapGetters } from 'vuex'
export default {
  filters: {
  },
  computed: {
    ...mapGetters([
      'userId',
      'userName',
      'userCode'
    ])
  },
  data() {
    let validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"))
      } else if (value !== this.pwdModel.newPassword) {
        callback(new Error("两次输入密码不一致!"))
      } else {
        callback()
      }
    }
    return {
      pwdModel: {
        userCode:'',
        oldPassword : '',
        newPassword : '',
        confirmPassword : '',
        type : 2
      },
      // 表单验证
      rules: {
        oldPassword: [
          { required: true, message: '请输入原密码！', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入新密码！', trigger: 'blur' },
          { max:16, message: '最长输入16位字符', trigger: 'blur' },
          { min:6, message: '最少输入6位字符', trigger: 'blur' },
          { validator:isPassword, trigger: 'blur'}
        ],
        confirmPassword: [
          { required: true, message: '请再次输入新密码！', trigger: 'blur' },
          { validator:validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  created() {
  },
  methods: {
   updPwd(){
    this.pwdModel.userCode = this.userCode;
      this.$refs['dataForm'].validate(res => {
        if (res) {
          updatePwd(this.pwdModel).then(response => {
            if(response.code===0){
              this.$message({message:'更新成功！',type: 'success'})
            }
          })
        }
      })
   }


  }
}
</script>

<style>
.head_div {
  padding-top: 0px;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 20px;
  font-size: 20px;
  font-weight: 700;
  color: #606266;
  font-family: Helvetica Neue;
  text-align:center;
}
.root_div {
  padding: 20px;
  text-align:center;
}
.el-col {
  text-align:center;
}
.el-form-item__label {
  text-align:center;
}
.el-form-item__error{
  position:center;
}
</style>