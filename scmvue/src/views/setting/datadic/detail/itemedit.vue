<template>
  <div class="app-container" >
  <div class = "head_div">数据明细管理</div>
    <!-- 标题 -->
    <div class="mb-2 div-title">
      |&nbsp;&nbsp;字典信息
    </div> 
    <!-- 数据明细信息 -->
    <div class ="mb-3 border-line-b-solid">
      <el-row class="div-row-font">
        <el-col :span="4">
          <div>
            <span>数据代码：</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span><b>{{dataDic.groupCode || '--'}}</b></span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span>数据名称：</span>
          </div>
          </el-col>
        <el-col :span="4">
          <div>
            <span><b>{{dataDic.groupName || '--' }}</b></span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span>数据描述：</span>
          </div>
        </el-col>
        <el-col :span="4">
          <div>
            <span><b>{{dataDic.groupName || '--' }}</b></span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <div class="mb-2 div-title" >
        |&nbsp;&nbsp;明细信息&nbsp;&nbsp;
        <el-button @click="add()" type="text" icon="el-icon-edit">添加明细</el-button>
      </div>
      <div class="mb-3 border-line-b-solid">
        <el-table
        :data="dataDic.items"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
        >
          <el-table-column type="index" ></el-table-column>>
          <el-table-column label="明细代码" prop="dataItemCode" />
          <el-table-column label="明细名称" prop="dataItemName" />
          <el-table-column label="明细描述" prop="dataItemDesc" />
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
        :model="dataDicItem"
        :rules="rules"
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="明细代码" prop="dataItemCode" >
          <el-input placeholder="代码" v-model="dataDicItem.dataItemCode" v-bind:disabled="this.dialogStatus=='update'"/>
        </el-form-item>
        <el-form-item label="明细名称" prop="dataItemName" >
          <el-input placeholder="名称" v-model="dataDicItem.dataItemName" />
        </el-form-item>
        <el-form-item label="明细描述" prop="dataItemDesc" >
          <el-input
              v-model="dataDicItem.dataItemDesc"
              :autosize="{minRows: 2, maxRows: 4}"
              type="textarea"
              placeholder="描述"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" >
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()" >确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getDataDic } from '@/api/datadic'
import { addDataDicItem } from '@/api/datadic'
import { updateDataDicItem } from '@/api/datadic'
import { delDataDicItem } from '@/api/datadic'
import { isNumberOrChar } from '@/utils/validate'
import { Form } from 'element-ui'

export default {
  filters: {
  },
  data() {
    return {
      dataDic:null,
      // 弹出框
      dialogVisible:false,
      dialogStatus: '',
      textMap: {
        update: '编辑明细项',
        create: '新增明细项'
      },
      dataDicItem: {
        dataDicId:'',
        id:'',
        dataItemCode:'',
        dataItemName:'',
        dataItemDesc:'',
        version:''
      },
      // 表单验证
      rules: {
        dataItemCode: [
          { required: true, message: '请输入明细代码！', trigger: 'blur' },
          { max:40, message: '最长输入40位字符', trigger: 'blur' },
          { validator:isNumberOrChar, trigger: 'blur'}
        ],
        dataItemName: [
          { required: true, message: '请输入明细名称！', trigger: 'blur' },
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ],
        dataItemDesc: [
          { max:255, message: '最长输入255位字符', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    // 初始查询数据
    const id = this.$route.params && this.$route.params.id
    this.getDataDic(id)
  },
  methods: {
    getDataDic(dataDicId) {
      getDataDic(dataDicId).then(response => {
        this.dataDic = response.data
      })
    },
    // 新增画面
    add(){
      this.resetDataDicItem()
      this.dialogStatus = 'create'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    resetDataDicItem(){
      this.dataDicItem.dataDicId = this.dataDic.id
      this.dataDicItem.id = ''
      this.dataDicItem.dataItemCode = ''
      this.dataDicItem.dataItemName = ''
      this.dataDicItem.dataItemDesc = ''
      this.dataDicItem.version = ''
    },
    // 修改画面
    mod(row){
      this.dialogStatus = 'update'
      this.dialogVisible = true
      this.dataDicItem.dataDicId = this.dataDic.id
      this.dataDicItem.id = row.id
      this.dataDicItem.dataItemCode = row.dataItemCode
      this.dataDicItem.dataItemName = row.dataItemName
      this.dataDicItem.dataItemDesc = row.dataItemDesc
      this.dataDicItem.version = row.version
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 删除数据
    del(dataDicItemId){
      delDataDicItem(dataDicItemId).then(response => {
        if(response.code===0){
          this.$message({message:'删除成功！',type: 'success'})
          // 重新查询数据
          this.getDataDic(this.dataDic.id)
        }
      })
    },
    // 新增数据
    createData(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          addDataDicItem(this.dataDicItem).then(response => {
            if(response.code===0){
              this.$message({message:'添加成功！',type: 'success'})
              // 重新查询数据
              this.getDataDic(this.dataDic.id)
            }
          })
        }
      })
    },
    // 更新数据
    updateData(){
      this.dialogVisible = false
      this.$refs['dataForm'].validate(res => {
        if (res) {
          updateDataDicItem(this.dataDicItem).then(response => {
            if(response.code===0){
              this.$message({message:'修改成功！',type: 'success'})
              // 重新查询数据
              this.getDataDic(this.dataDic.id)
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