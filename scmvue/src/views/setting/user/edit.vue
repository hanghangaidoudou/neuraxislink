<template>
  <div class="app-container">

    <div class = "head_div" v-if="opertype === 'add'">新增用户信息</div>
    <div class = "head_div" v-if="opertype === 'modify'">编辑用户信息</div>
    <!-- 用户属性 -->
    <div class="filter-container">
      <el-form ref="editForm" 
      :model="userForm" 
      :rules="rules"
      labelPosition="right" 
      label-width="100px">
        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">        
            <div>
              <el-form-item label="代码" prop="userCode">
                <el-input v-model="userForm.userCode" 
                :disabled="opertype === 'modify'" 
                clearable 
                style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="名称" prop="userName">
                <el-input v-model="userForm.userName" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>&nbsp;</div>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="性别">
                <el-select v-model="userForm.sex" style="width: 250px;">
                  <el-option label="男" value="0" />
                  <el-option label="女" value="1" />
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="手机" prop="tel">
                <el-input v-model="userForm.tel" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="userForm.email" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="部门" prop="selectDept">
                <el-cascader
                v-model="userForm.selectDept"
                clearable
                placeholder="--选择--"
                :options="depts"
                @change="handleChange"
                style="width: 250px;"
                ></el-cascader>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="角色" prop="roles">
                <el-select v-model="userForm.roles" 
                placeholder="--选择--" 
                style="width: 250px;"
                >
                  <el-option
                    v-for="item in roles"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="初始密码">
                <el-input 
                v-model="userForm.password" 
                clearable style="width: 140px;" 
                :disabled="true"/>&nbsp;&nbsp;
                <el-button type="primary" 
                :disabled="opertype === 'add'"
                style="width: 100px;" 
                icon="el-icon-refresh" 
                size="medium" 
                @click="resetPassword()">重置</el-button>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>&nbsp;</div>
          </el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="在职状态">
                <el-select 
                  v-model="userForm.state" 
                  placeholder="--选择--"
                  style="width: 250px;"
                  >
                  <el-option
                    v-for="item in states"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="入职日期" prop="entryDate">
                <el-date-picker 
                value-format="yyyy-MM-dd" 
                v-model="userForm.entryDate" 
                type="date" 
                placeholder="--选择日期--" 
                style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
        </el-row>
        
        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="16">
            <div style="margin-left: 0px;">
              <el-form-item>

              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
        </el-row>
      </el-form>
    </div>
    <div class = "root_div">                
      <el-button type="primary" @click="opertype==='add'?addUser():updUser()">确认</el-button>&nbsp;&nbsp;
      <router-link :to="{ path:'/setting/user'}">
          <el-button type="primary">
            取消
          </el-button>
        </router-link>
    </div>
  </div>
</template>

<script>
import { getUserInfo } from '@/api/user'
import { addUserInfo } from '@/api/user'
import { updUserInfo } from '@/api/user'
import { getDataDicItem } from '@/api/tool'
import { getDeptSelInfo } from '@/api/tool'
import { validatePhone,isInteger,validateEMail,isNumberOrChar,checkMaxVal } from '@/utils/validate'
import { Form } from 'element-ui'

export default {
  filters: {
  },
  data() {
    return {
      userForm: {
        id : '',
        userCode: '',
        userName: '',
        password:'888888',
        sex: '0',
        tel: '',
        email: '',
        deptId: '',
        itemId: '',
        selectDept : [],
        roles: '',
        entryDate: '',
        state:'0'
      },
      // 表单验证
      rules: {
        userCode: [
          { required: true, message: '请输入代码', trigger: 'blur' },
          { validator:isInteger,message:"代码应该为数字类型！", trigger: 'blur'},
          { validator:checkMaxVal,message:"代码最大输入32766", trigger: 'blur'}
          
        ],
        userName: [
          { required: true, message: '请输入名称！', trigger: 'blur' },
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ],
        tel: [
          { required: true, message: '请输入手机！', trigger: 'blur' },
          { validator:validatePhone,message:"手机格式不正确！", trigger: 'blur'}
        ],
        email: [
          { required: true, message: '请输入邮箱！', trigger: 'blur' },
          { validator:validateEMail,message:"邮箱格式不正确！", trigger: 'blur'}
        ],
        entryDate: [
          { required: true, message: '请输入入职日期！', trigger: 'blur' }
        ],
        selectDept: [
            { type:'array',required: true, message: '请选择部门！', trigger: 'blur' }
        ],
        roles: [
          { required: true, message: '请选择角色！', trigger: 'blur' }
        ]
      },
      // add 追加 / modify 变更
      opertype : null,
      // 部门下拉框的值
      depts : null,
      // 部门下拉框选中的值
      checkedDept : [],
      // 状态下拉框的值
      states : null,
      // 角色
      roles: null
    }
  },
  created() {
    // 操作类型
    const opertype = this.$route.params && this.$route.params.opertype
    const id = this.$route.params && this.$route.params.id
    this.opertype = opertype
    this.userForm.id = id
    debugger
    // 初始化页面
    this.initPage()
  },
  methods: {
    // 级联下拉框赋值
    handleChange(checkedDept) {
      debugger
      if(checkedDept.length===1){
        this.userForm.deptId = checkedDept[0]
        this.userForm.selectDept[0]=checkedDept[0]
      }
      if(checkedDept.length===2){
        this.userForm.deptId = checkedDept[0]
        this.userForm.selectDept[0]=checkedDept[0]
        this.userForm.teamId = checkedDept[1]
        this.userForm.selectDept[1]=checkedDept[1]
      }
    },
    // 初始化画面
    initPage(){
      debugger
      const stateCode = 'ServiceStatus'
      const roleCode = 'Role'
      // 【状态】下拉框
      getDataDicItem(stateCode).then(response => {
        this.states = response.data
      }),
      // 【部门】下拉框
      getDeptSelInfo().then(response => {
        this.depts = response.data
      }),
      // 【角色】下拉框
      getDataDicItem(roleCode).then(response => {
        this.roles = response.data
      }),
      // 入职日期等于当前日期
      this.userForm.entryDate= new Date();
      // opertype == modify 时，查询数据重新赋值
      if(this.opertype === 'modify'){
        getUserInfo(this.userForm.id).then(response => {
          debugger
          this.userForm.userCode = response.data.userCode
          this.userForm.userName = response.data.userName
          this.userForm.sex = response.data.sex
          this.userForm.tel = response.data.tel
          this.userForm.email = response.data.email
          this.userForm.deptId = response.data.deptId
          this.checkedDept[0] = Number(response.data.deptId)
          this.userForm.selectDept[0] = Number(response.data.deptId)
          this.userForm.itemId = response.data.teamId
          this.checkedDept[1] = Number(response.data.teamId)
           this.userForm.selectDept[1] = Number(response.data.teamId)
          this.userForm.roles = response.data.roles
          this.userForm.entryDate = response.data.entryDate
          this.userForm.state = response.data.state
        })
        debugger
      }
    },
    // 添加新用户
    addUser(){
      this.$refs['editForm'].validate(res => {
        if (res) {
          addUserInfo(this.userForm).then(response => {
            if(response.code===0){
              this.$message({message:'添加成功！',type: 'success'})
              this.$router.push({path:'/setting/user'})
            }
          })
        }
      })
    },

    // 更新用户
    updUser(){
      debugger
      this.$refs['editForm'].validate(res => {
        if (res) {
          updUserInfo(this.userForm).then(response => {
            if(response.code===0){
              this.$message({message:'更新成功！',type: 'success'})
              this.$router.push({path:'/setting/user'})
            }
          })
        }
      })
    },

    resetPassword(){
      this.$message({message:'重置成功！',type: 'success'})
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
</style>