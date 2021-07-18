<template>
  <div class="app-container">
    <div class = "head_div">数据字典管理</div>
    <!-- 查询条件 -->
    <div class="filter-container">
      <el-input
        v-model="queryParam.groupCode"
        clearable
        placeholder="代码"
        style="width: 150px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp;
      <el-input
        v-model="queryParam.groupName"
        clearable
        placeholder="名称"
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
        <el-table-column label="代码" prop="groupCode" />
        <el-table-column label="名称" prop="groupName" />
        <el-table-column label="描述" prop="groupDesc" />
        <el-table-column label="操作" >
          <template slot-scope="scope">
          <el-button @click="mod(scope.row)" type="primary" icon="el-icon-edit" size="mini">编辑</el-button>
          <router-link :to="{ name:'itemedit',params:{id:scope.row.id}}">
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
        :model="dataDicMode"
        :rules="rules"
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="代码" prop="groupCode" >
          <el-input placeholder="代码" v-model="dataDicMode.groupCode" v-bind:disabled="this.dialogStatus=='update'"/>
        </el-form-item>
        <el-form-item label="名称" prop="groupName" >
          <el-input placeholder="名称" v-model="dataDicMode.groupName" />
        </el-form-item>
        <el-form-item label=" 描述" prop="groupDesc" >
          <el-input
              v-model="dataDicMode.groupDesc"
              :autosize="{minRows: 2, maxRows: 4}"
              type="textarea"
              placeholder="描述"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" >
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?addDataDic():updateDataDic()" >确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getDataDics } from '@/api/datadic'
import { addDataDic } from '@/api/datadic'
import { updateDataDic } from '@/api/datadic'
import { delDataDic } from '@/api/datadic'
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
        update: '编辑数据',
        create: '新增数据'
      },
      dataDicMode: {
        id:'',
        groupCode:'',
        groupName:'',
        groupDesc:'',
        version:''
      },
      // 表单验证
      rules: {
        groupCode: [
          { required: true, message: '请输入代码！', trigger: 'blur' },
          { max:40, message: '最长输入40位字符', trigger: 'blur' },
          { validator:isNumberOrChar, trigger: 'blur'}
        ],
        groupName: [
          { required: true, message: '请输入名称！', trigger: 'blur' },
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ],
        groupDesc: [
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ]
      },
      // 查询条件
      queryParam: {
        pageNum: 1,
        pageSize: 5,
        groupCode: '',
        groupName: ''
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
  /**
   * 
   * mounted: function () {
   *    Watermark.set("数据字典")
   * },
   */
  methods: {
    // 查询数据
    query() {
      debugger
      this.listLoading = true
      this.total = 0
      getDataDics(this.queryParam).then(response => {
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
    resetDataDicMode(){
      this.dataDicMode.groupCode=''
      this.dataDicMode.groupName=''
      this.dataDicMode.groupDesc = ''
    },
    add(){
      this.resetDataDicMode()
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
      this.dataDicMode.id = row.id
      this.dataDicMode.groupCode = row.groupCode
      this.dataDicMode.groupName = row.groupName
      this.dataDicMode.groupDesc = row.groupDesc
      this.dataDicMode.version = row.version
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 删除数据
    del(id){
      delDataDic(id).then(response => {
        if(response.code===0){
          this.$message({message:'删除成功！',type: 'success'})
          // 重新查询数据
          this.query()
        }
      })
    },
    // 新增数据
    addDataDic(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          addDataDic(this.dataDicMode).then(response => {
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
    updateDataDic(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          updateDataDic(this.dataDicMode).then(response => {
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