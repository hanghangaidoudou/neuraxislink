<template>
  <div class="app-container" >
  <div class = "head_div">部门团队管理</div>
    <!-- 标题 -->
    <div class="mb-2 div-title">
      |&nbsp;&nbsp;部门团队管理
    </div> 
    <!-- 部门信息 -->
    <div class ="mb-3 border-line-b-solid">
      <el-row class="div-row-font">
        <el-col :span="4">
          <div>
            <span>部门代码：</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span><b>{{dept.code || '--'}}</b></span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span>部门名称：</span>
          </div>
          </el-col>
        <el-col :span="4">
          <div>
            <span><b>{{dept.name || '--' }}</b></span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span>部门描述：</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span><b>{{dept.desc || '--' }}</b></span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <div class="mb-2 div-title" >
        |&nbsp;&nbsp;团队信息&nbsp;&nbsp;
        <el-button @click="add()" type="text" icon="el-icon-edit">添加团队信息</el-button>
      </div>
      <div class="mb-3 border-line-b-solid">
        <el-table
        :data="dept.teams"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
        >
          <el-table-column type="index" ></el-table-column>>
          <el-table-column label="团队代码" prop="code" />
          <el-table-column label="团队名称" prop="name" />
          <el-table-column label="团队描述" prop="desc" />
          <el-table-column label="操作" >
            <template slot-scope="scope">
            <el-button @click="mod(scope.row)" type="primary" size="mini" icon="el-icon-edit">编辑</el-button>
            <el-button @click="del(scope.row.id)" type="danger" size="mini">删除</el-button>
          </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

    <el-dialog
      :title="textMap[dialogStatus]"
      :visible.sync="dialogVisible"
    >
      <el-form
        ref="dataForm"
        :model="team"
        :rules="rules"
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="团队代码" prop="teamCode" >
          <el-input placeholder="代码" v-model="team.teamCode" v-bind:disabled="this.dialogStatus=='update'"/>
        </el-form-item>
        <el-form-item label="团队名称" prop="teamName" >
          <el-input placeholder="名称" v-model="team.teamName" />
        </el-form-item>
        <el-form-item label="团队描述" prop="teamDesc" >
          <el-input
              v-model="team.teamDesc"
              :autosize="{minRows: 2, maxRows: 4}"
              type="textarea"
              placeholder="描述"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" >
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createTeam():updateTeam()" >确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getDept } from '@/api/dept'
import { addTeam } from '@/api/dept'
import { updateTeam } from '@/api/dept'
import { delTeam } from '@/api/dept'
import { isNumberOrChar } from '@/utils/validate'
import { Form } from 'element-ui'

export default {
  filters: {
  },
  data() {
    return {
      dept:null,
      // 弹出框
      dialogVisible:false,
      dialogStatus: '',
      textMap: {
        update: '编辑团队信息',
        create: '新增团队信息'
      },
      team: {
        deptId:'',
        id:'',
        teamCode:'',
        teamName:'',
        teamDesc:'',
        version:''
      },
      // 表单验证
      rules: {
        teamCode: [
          { required: true, message: '请输入团队代码！', trigger: 'blur' },
          { max:40, message: '最长输入40位字符', trigger: 'blur' },
          { validator:isNumberOrChar, trigger: 'blur'}
        ],
        teamName: [
          { required: true, message: '请输入团队名称！', trigger: 'blur' },
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ],
        teamDesc: [
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    // 初始查询数据
    const id = this.$route.params && this.$route.params.id
    this.getDept(id)
  },
  methods: {
    getDept(deptId) {
      getDept(deptId).then(response => {
        this.dept = response.data
      })
    },
    // 新增画面
    add(){
      this.resetTeam()
      this.dialogStatus = 'create'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    resetTeam(){
      this.team.deptId = this.dept.id
      this.team.id = ''
      this.team.teamCode = ''
      this.team.teamName = ''
      this.team.teamDesc = ''
      this.team.version = ''
    },
    // 修改画面
    mod(row){
      this.dialogStatus = 'update'
      this.dialogVisible = true
      this.team.teamId = this.dept.id
      this.team.id = row.id
      this.team.teamCode = row.code
      this.team.teamName = row.name
      this.team.teamDesc = row.desc
      this.team.version = row.version
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 删除数据
    del(teamId){
      delTeam(teamId).then(response => {
        if(response.code===0){
          this.$message({message:'删除成功！',type: 'success'})
          // 重新查询数据
          this.getDept(this.dept.id)
        }
      })
    },
    // 新增数据
    createTeam(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          addTeam(this.team).then(response => {
            if(response.code===0){
              this.$message({message:'添加成功！',type: 'success'})
              // 重新查询数据
              this.getDept(this.dept.id)
            }
          })
        }
      })
    },
    // 更新数据
    updateTeam(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          updateTeam(this.team).then(response => {
            if(response.code===0){
              this.$message({message:'修改成功！',type: 'success'})
              // 重新查询数据
              this.getDept(this.dept.id)
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

  .mb-2{margin-bottom:.5rem!important}
  .mb-3{margin-bottom:1rem!important}
  .border-line-b-solid {
    /* 用于底边边边框线样式--分割线 */
    border-bottom: 1px solid #e4e4e4;
  }

  .div-title{
    /* background: rgb(118, 127, 138); */
    font-size: 16px;
    text-align: left;
    font-weight: 600;
    color:rgb(134, 133, 133) !important;
  }
  
  .div-row-font{
    font-size: 14px;
    color:rgb(134, 133, 133) !important;
  }
</style>