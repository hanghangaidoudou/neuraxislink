<template>
  <div class="app-container">
    <div class = "head_div">用户信息管理</div>
    <!-- 查询条件 -->
    <div class="filter-container">
      <el-input
        v-model="queryParam.userCode"
        clearable
        placeholder="代码"
        style="width: 150px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp;
      <el-input
        v-model="queryParam.userName"
        clearable
        placeholder="名称"
        style="width: 200px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp;
      <el-cascader
        v-model="checkedDept"
        clearable
        placeholder="部门"
        :options="depts"
        @change="handleChange"
        ></el-cascader>&nbsp;&nbsp;
      <el-select 
        v-model="queryParam.state" 
        clearable 
        placeholder="状态"
        @clear="clearState"
        >
        <el-option
          v-for="item in this.states"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>&nbsp;&nbsp;
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="query()">查询</el-button>
        <!--
<router-link :to="{ path: 'register', query: { plan: 'private' }}">Register</router-link>

        -->
        <router-link :to="{ name:'UserEditAdd',params:{opertype:'add',id:null}}">
          <el-button type="primary" icon="el-icon-plus">
            添加
          </el-button>
        </router-link>
    </div>
    <br/>
    <div>
    <!-- 查询列表 -->
      <el-table
        v-loading="listLoading"
        :data="queryList"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column type="index" ></el-table-column>
        <el-table-column label="代码" prop="userCode" />
        <el-table-column label="名称" prop="userName" />
        <el-table-column label="手机" prop="tel" />
        <el-table-column label="部门" prop="deptName" />
        <el-table-column label="小组" prop="teamName" />
        <el-table-column label="状态" prop="state" />
        <el-table-column label="入职时间" prop="entryDate" />
        <el-table-column label="操作" >
          <template slot-scope="scope">

            <router-link :to="{ name:'UserEditMod',params:{opertype:'modify',id:scope.row.id}}">
              <el-button type="primary" icon="el-icon-edit" size="mini">
                编辑
              </el-button>
            </router-link>

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
  </div>
</template>

<script>
import { getUserInfos } from '@/api/user'
import { getDataDicItem } from '@/api/tool'
import { getDeptSelInfo } from '@/api/tool'
import { Form } from 'element-ui'

export default {
  filters: {
  },
  data() {
    return {
      listLoading: true,
      // 查询条件
      queryParam: {
        pageNum: 1,
        pageSize: 5,
        userCode:'',
        userName:'',
        deptId:'',
        teamId:'',
        state : '0'
      },
      // 查询结果
      pageInfo : null,
      queryList: null,
      // 分页信息
      currentPage: 1,
      total : 0,
      // 部门下拉框的值
      depts : null,
      // 部门下拉框选中的值
      checkedDept:[],
      // 状态下拉框的值
      states : null
    }
  },
  created() {
    // 初始化页面项目
    this.initPage()
    // 查询数据
    this.query()
  },
  methods: {
    // 级联下拉框赋值
    handleChange(checkedDept) {
      if(checkedDept.length===1){
        this.queryParam.deptId = checkedDept[0]
      }
      if(checkedDept.length===2){
        this.queryParam.deptId = checkedDept[0]
        this.queryParam.teamId = checkedDept[1]
      }
    },
    // 清空【状态】下拉框
    clearState(){
      this.queryParam.state = ''
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
    // 初始化画面
    initPage(){
      const groupCode = 'ServiceStatus'
      // 【状态】下拉框
      getDataDicItem(groupCode).then(response => {
        this.states = response.data
        console.log(`在职状态:` + this.states);
      }),
      // 【部门】下拉框
      getDeptSelInfo().then(response => {
        this.depts = response.data
        console.log(`部门:` + this.depts);
      })
    },
    // 查询数据
    query() {
      this.listLoading = true
      this.total = 0
      getUserInfos(this.queryParam).then(response => {
        this.pageInfo = response.data
        this.queryList = this.pageInfo.list
        this.total = this.pageInfo.total
        this.listLoading = false
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

.link-type,
.link-type:focus {
  color: #337ab7;
  cursor: pointer;
  text-decoration:underline;
}
</style>