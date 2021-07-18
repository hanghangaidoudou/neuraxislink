<template>
  <div class="app-container">
    <div class = "head_div">价格列表</div>
    <!-- 查询条件 -->
    <div class="filter-container">
          <el-select 
        v-model="queryParam.classa" 
        clearable 
        placeholder="类别"
        @clear="clearState"
        >
        <el-option
          v-for="item in this.optionsclass"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>&nbsp;&nbsp;&nbsp;&nbsp;
               <el-select 
        v-model="queryParam.serial" 
        clearable 
        placeholder="系列"
        @clear="clearState"
        >
        <el-option
          v-for="item in this.optionsserial"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>&nbsp;&nbsp;&nbsp;&nbsp;
      <el-input
        v-model="queryParam.productname"
        clearable
        placeholder="产品名称"
        style="width: 150px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />&nbsp;&nbsp; 
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="query()">查询</el-button>&nbsp;&nbsp;
 
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
        <el-table-column type="index" min-width="2%"></el-table-column>
        <el-table-column label="大类" min-width="20%" prop="classa" />
        <el-table-column label="系列" min-width="23%" prop="serial" />
        <el-table-column label="产品名称" min-width="42%" prop="productname" />

        <el-table-column label="提货价" min-width="24%" prop="price" />
 
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
import { getList } from '@/api/pricelist'
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
     optionsclass: [   {
          value: 'All',
          label: '全部'
        },{
       
          value: 'BI',
          label: 'BI'
        }, {
          value: 'IDEA',
          label: 'IDEA'
        }],
        optionsserial: [  
          
            {
          value: 'All',
          label: '全部'
        },{
          value: 'SenseNebula-G（渠道配单工具两卡机）',
          label: 'SenseNebula-G（渠道配单工具两卡机）'
        }, {
          value: 'SenseNebula-M',
          label: 'SenseNebula-M'
        },  {
          value: 'SenseDLC',
          label: 'SenseDLC'
        },  {
          value: '星云S系列智能摄像机软件',
          label: '星云S系列智能摄像机软件'
        },  {
          value: 'SensePass系列桌面底座',
          label: 'SensePass系列桌面底座'
        }, {
          value: 'SenseAike',
          label: 'SenseAike'
        }, {
          value: 'SenseLink',
          label: 'SenseLink'
        },{
          value: 'SenseKeeper',
          label: 'SenseKeeper'
        }, {
          value: 'SenseEngine',
          label: 'SenseEngine'
        }],
      //项目状态
      states : null,
      // 查询条件
      queryParam: {
        pageNum: 1,
        pageSize: 5,
        classa:'All',
        serial:'All',
        productname:''
     
      },
      pageInfo : {
        total: 3,
        list: [
        {
        "classa": "BI ",
        "cost": 3510,
        "mar": "300000000268",
        "model": "SID030-S",
        "price": 3896,
        "productname": "SenseID030，单屏版",
        "serial": "SenseID"
       },
       {
        "classa": "BI ",
        "cost": 4177,
        "mar": "300000000266",
        "model": "SID030-DS",
        "price": 4636,
        "productname": "SenseID030，双屏版，含OCR识别功能",
        "serial": "SenseID"
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
      this.listLoading = true
      this.total = 0
  
      getList(this.queryParam).then(response => {
        this.pageInfo = response.data
        this.queryList = this.pageInfo.list
        this.total = this.pageInfo.total
        this.listLoading = false
      })
        /** 
     this.queryList = this.pageInfo.list
     this.total = this.pageInfo.total
     this.listLoading = false  */
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