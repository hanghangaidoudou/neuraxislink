<template>
  <div class="app-container">
    <div class = "head_div">部门信息管理</div>
    <!-- 查询条件 -->
    <div class="filter-container">
      <el-input
        v-model="queryParam.code"
        clearable
        placeholder="部门代码"
        style="width: 150px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp;
      <el-input
        v-model="queryParam.name"
        clearable
        placeholder="部门名称"
        style="width: 200px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp;
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="query()">查询</el-button>&nbsp;&nbsp;
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-plus"
        @click="add()">添加</el-button>
    </div>
    <br/>
    <!-- 查询列表 -->
    <div>
      <el-table
        v-loading="listLoading"
        :data="queryList"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column type="index" ></el-table-column>
        <el-table-column label="部门代码" prop="code" />
        <el-table-column label="部门名称" prop="name" />
        <el-table-column label="描述" prop="desc" />
        <el-table-column label="操作" >
          <template slot-scope="scope">
          <el-button @click="mod(scope.row)" type="primary" icon="el-icon-edit" size="mini">编辑</el-button>
          <router-link :to="{ name:'deptedit',params:{id:scope.row.id}}">
            <el-button type="primary" size="mini" icon="el-icon-document">
              详细
            </el-button>
          </router-link>
          <el-button @click="del(scope.row.id)" type="danger" size="mini">删除</el-button>
        </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <div v-if = "total > 0" >
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.queryParam.pageNum"
        :page-sizes="[5, 10, 20, 30, 40, 50]"
        :page-size="this.queryParam.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <el-dialog
      :title="textMap[dialogStatus]"
      :visible.sync="dialogVisible"
    >
      <div slot="title">
        <span>{{textMap[dialogStatus]}}</span>
      </div>
      <el-form
        ref="dataForm"
        :model="deptMode"
        :rules="rules"
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="代码" prop="code" >
          <el-input placeholder="代码" v-model="deptMode.code" v-bind:disabled="this.dialogStatus=='update'"/>
        </el-form-item>
        <el-form-item label="名称" prop="name" >
          <el-input placeholder="名称" v-model="deptMode.name" />
        </el-form-item>
        <el-form-item label=" 描述" prop="desc" >
          <el-input
              v-model="deptMode.desc"
              :autosize="{minRows: 2, maxRows: 4}"
              type="textarea"
              placeholder="描述"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" >
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?addDept():updateDept()" >确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getDepts } from '@/api/dept'
import { addDept } from '@/api/dept'
import { getDataDics } from '@/api/dept'
import { addDataDic } from '@/api/dept'
import { updateDept } from '@/api/dept'
import { delDept } from '@/api/dept'
import { isNumberOrChar } from '@/utils/validate'
import { Form } from 'element-ui'


export default {
  filters: {
  },
  data() {
    return {
      listLoading: true,
      dialogVisible:false,
      dialogStatus: '',
      textMap: {
        update: '编辑部门信息',
        create: '新增部门信息'
      },
      deptMode: {
        id:'',
        code:'',
        name:'',
        desc:'',
        version:''
      },
      // 表单验证
      rules: {
        code: [
          { required: true, message: '请输入部门代码！', trigger: 'blur' },
          { max: 40, message: '最长输入40位字符', trigger: 'blur' },
          { validator: isNumberOrChar, trigger: 'blur'}
        ],
        name: [
          { required: true, message: '请输入部门名称！', trigger: 'blur' },
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ],
        desc: [
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ]
      },
      // 查询条件
      queryParam: {
        pageNum: 1,
        pageSize: 5,
        code: '',
        name: ''
      },
      pageInfo : null,
      queryList: null,
      // 分页信息
      currentPage: 1,
      total : 0
    }
  },
  created() {
    this.query()
  },
  methods: {
    // 查询数据
    query() {
      debugger
      this.listLoading = true
      this.total = 0
      getDepts(this.queryParam).then(response => {
        this.pageInfo = response.data
        this.queryList = this.pageInfo.list
        this.total = this.pageInfo.total
        this.listLoading = false
      })
    },
        // 页面跳转
    handleSizeChange(val) {
      this.queryParam.pageSize = val
      console.log(`每页: ${val} 条`);
      this.query()
    },
    handleCurrentChange(val) {
      this.queryParam.pageNum = val
      console.log(`当前页: ${val}`);
      this.query()
    },
    // 新增画面
    resetDeptMode(){
      this.deptMode.code=''
      this.deptMode.name=''
      this.deptMode.desc = ''
    },
    add(){
      this.resetDeptMode()
      this.dialogStatus = 'create'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 修改画面
    mod(row){
      this.dialogStatus = 'update'
      this.dialogVisible = true
      this.deptMode.id = row.id
      this.deptMode.code = row.code
      this.deptMode.name = row.name
      this.deptMode.desc = row.desc
      this.deptMode.version = row.version
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 删除数据
    del(id){
      delDept(id).then(response => {
        if(response.code===0){
          this.$message({message:'删除成功！',type: 'success'})
          // 重新查询数据
          this.query()
        }
      })
    },
    // 新增部门信息
    addDept(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          addDept(this.deptMode).then(response => {
            if(response.code===0){
              this.$message({message:'添加成功！',type: 'success'})
              // 重新查询数据
              this.query()
            }
          })
        }
      })
    },
    // 更新数据
    updateDept(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          updateDept(this.deptMode).then(response => {
            if(response.code===0){
              this.$message({message:'修改成功！',type: 'success'})
              // 重新查询数据
              this.query()
            }
          })
        }
      })
    }
  }
}
</script>

<style lang="scss">

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

.link-type,
.link-type:focus {
  color: #337ab7;
  cursor: pointer;
  text-decoration:underline;
}
.app-container{
  .el-dialog{
    height:400px;
    width:700px;
    .el-dialog__header{
      padding: 15px;
      font-size: 18px;
      background-color: #dee3e8;
      color: #606266;
      font-family: Helvetica Neue;
    }
  }
}

</style>