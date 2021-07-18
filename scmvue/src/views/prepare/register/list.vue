<template>
  <div class="app-container">
    <div class = "head_div">项目列表</div>
    <!-- 查询条件 -->
    <div class="filter-container">
      <el-input
        v-model="queryParam.projectNo"
        clearable
        placeholder="项目编号"
        style="width: 150px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp;
      <el-input
        v-model="queryParam.projectName"
        clearable
        placeholder="项目名称"
        style="width: 200px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp;
      <el-select 
        v-model="queryParam.projectStatus" 
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
      </el-select>&nbsp;&nbsp;&nbsp;&nbsp;
      <el-input
        v-model="queryParam.registerName"
        clearable
        placeholder="登记人"
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
      <!--
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-edit"
        @click="add()">添加</el-button>
        -->
      <router-link :to="{ name:'RegisterEdit',params:{operType:'add',id:''}}">
        <el-button type="primary" icon="el-icon-plus">
          添加
        </el-button>
      </router-link>
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
        style="width: 100%"
      >
        <el-table-column type="index" min-width="5%"></el-table-column>
        <el-table-column label="项目编号" min-width="10%" prop="projectNo" />
        <el-table-column label="项目名称" min-width="20%" prop="projectName" />
        <el-table-column label="内部负责人" min-width="10%" prop="PersonInCharge" />
        <el-table-column label="工作量" min-width="10%" prop="workload" />
        <el-table-column label="客户负责人" min-width="10%" prop="customCharge" />
        <el-table-column label="状态" min-width="10%" prop="projectStatus" />
        <el-table-column label="登记人" min-width="10%" prop="registrar" />
        <el-table-column label="操作" min-width="15%" >
          <template slot-scope="scope">
            <!--
          <el-button @click="mod(scope.row)" type="primary" icon="el-icon-edit" size="mini">编辑</el-button>
          -->
          <router-link :to="{ name:'RegisterEdit',params:{operType:'modify',id:scope.row.id}}">
            <el-button type="primary" size="mini" icon="el-icon-edit">
              修改
            </el-button>
          </router-link>
          <router-link :to="{ name:'RegisterEdit',params:{operType:'detail',id:scope.row.id}}">
            <el-button type="primary" size="mini" icon="el-icon-document">
              详细
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
import { getDataDics } from '@/api/datadic'
import { addDataDic } from '@/api/datadic'
import { updateDataDic } from '@/api/datadic'
import { delDataDic } from '@/api/datadic'
import { isNumberOrChar } from '@/utils/validate'
import { getDataDicItem } from '@/api/tool'
import { Form } from 'element-ui'

export default {
  filters: {
  },
  data() {
    return {
      listLoading: true,

      //项目状态
      states : null,
      // 查询条件
      queryParam: {
        pageNum: 1,
        pageSize: 5,
        projectNo:'',
        projectName:'',
        projectStatus:'',
        registerName:''
      },
      pageInfo : {
        total: 3,
        list: [
          {
            id: 1,
            projectNo: "2019-01",
            projectName: "2019.FTMS 新TOPSS系统改善",
            PersonInCharge: "张洪朋",
            workload: "10 人月",
            customCharge: "弓箭",
            projectStatus: "实施中",
            registrar: "张震"
          },
          {
            id: 2,
            projectNo: "2019-02",
            projectName: "2019.FTMS TACT新能源车电池溯源数据传输对应改善",
            PersonInCharge: "郭秋梅",
            workload: "40 人月",
            customCharge: "弓箭",
            projectStatus: "验收中",
            registrar: "董艳"
          }
          ,
          {
            id: 3,
            projectNo: "2019-03",
            projectName: "2019.FTMS PHEV静态数据传输对应改善",
            PersonInCharge: "郭秋梅",
            workload: "20 人月",
            customCharge: "弓箭",
            projectStatus: "验收中",
            registrar: "董艳"
          }
        ]
      },
      queryList: null,
      // 分页信息
      currentPage: 1,
      total : 0
    }
  },
  created() {
    this.initPage()
    this.query()
  },
 
  methods: {
    // 初始化画面
    initPage(){
      const ProjectStatus = 'ProjectStatus'
      // 【状态】下拉框
      getDataDicItem(ProjectStatus).then(response => {
        this.states = response.data
        console.log(`在职状态:` + this.states);
      })
    },
    // 查询数据
    query() {
      debugger
      this.listLoading = true
      this.total = 0
      /** 
      getDataDics(this.queryParam).then(response => {
        this.pageInfo = response.data
        this.queryList = this.pageInfo.list
        this.total = this.pageInfo.total
        this.listLoading = false
      })
      */
     this.queryList = this.pageInfo.list
     this.total = this.pageInfo.total
     this.listLoading = false
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