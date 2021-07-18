<template>
  <div class="app-container">

    <div class = "head_div">个人信息</div>
    <!-- 用户属性 -->
    <div class="filter-container">
      <el-form ref="editForm" :model="userForm" labelPosition="right" label-width="100px">
        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">        
            <div>
              <el-form-item label="代码" >
                <el-input v-model="userForm.userCode" 
                :disabled=true
                clearable 
                style="width: 250px;"/>
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
              <el-form-item label="性别">
                <el-select v-model="userForm.sex" style="width: 250px;">
                  <el-option label="男" value="0" />
                  <el-option label="女" value="1" />
                </el-select>
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
          <el-col :span="6"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="手机"  prop="tel">
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
              <el-form-item label="部门">
                <el-cascader
                v-model="userForm.selectDept"
                clearable
                placeholder="--选择--"
                :options="depts"
                @change="handleChange"
                style="width: 250px;"
                :disabled=true
                ></el-cascader>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="角色">
                <el-select v-model="userForm.roles" placeholder="--选择--" style="width: 250px;" :disabled=true>
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
              <el-form-item label="在职状态">
                <el-select 
                  v-model="userForm.state" 
                  clearable 
                  placeholder="--选择--"
                  style="width: 250px;"
                  :disabled=true
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
              <el-form-item label="入职日期">
                <el-date-picker value-format="yyyy-MM-dd" 
                v-model="userForm.entryDate" 
                type="date" 
                placeholder="--选择日期--" 
                clearable 
                style="width: 250px;"
                :disabled=true
                />
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
      <el-button type="primary" icon="el-icon-refresh" @click = "updUserInfo()">更新</el-button>
    </div>
  </div>
</template>

<script>
import { getUserInfo } from '@/api/user'
import { updUserInfoBySelf } from '@/api/user'
import { getDataDicItem } from '@/api/tool'
import { getDeptSelInfo } from '@/api/tool'
import { validatePhone,isInteger,validateEMail,isNumberOrChar,checkMaxVal } from '@/utils/validate'
import { mapGetters } from 'vuex'
import { Form } from 'element-ui'

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
        ]
      },
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
    
    debugger
    // 初始化页面
    this.initPage()
  },
  methods: {
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
      // 查询数据重新赋值
      this.userForm.id = this.userId;
      getUserInfo(this.userForm.id).then(response => {
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
    },
    // 更新用户
    updUserInfo(){
      this.$refs['editForm'].validate(res => {
        if (res) {
          updUserInfoBySelf(this.userForm).then(response => {
            if(response.code===0){
              this.$message({message:'更新成功！',type: 'success'})
              this.$router.push({path:'/setting/userinfo'})
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