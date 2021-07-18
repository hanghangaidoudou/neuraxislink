<template>
  <div class="app-container">
    <!-- add \ modify \ detail -->
    <div class = "head_div"><span>{{titleMap[operType]}}</span></div>
    <!-- 查询条件 -->
    <div class="filter-container">
      <el-form ref="editForm" 
      :model="proForm" 
      :rules="rules"
      labelPosition="right" 
      label-width="150px"
      :disabled="fromDistabled"
      >
        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="项目编号" prop="nbr">
                <el-input v-model="proForm.nbr" 
                clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="项目名称" prop="name">
                <el-input v-model="proForm.name" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="状态" prop="status">
                <el-select 
                  v-model="proForm.status" 
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
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="客户负责人" prop="cusCharge">
                <el-input v-model="proForm.cusCharge" 
                clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="客户公司" prop="cusCompany">
                <el-input v-model="proForm.cusCompany" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>&nbsp;</div>
          </el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="内部负责人" prop="internalCharge">
                <el-input v-model="proForm.internalCharge" 
                clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="内部工作量" prop="internalWorkload">
                <el-input v-model="proForm.internalWorkload" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="内部报价" prop="internalQuotation">
                <el-input v-model="proForm.internalQuotation" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="登记人" prop="registrar">
                <el-input v-model="proForm.registrar" clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="登记日" prop="registDate">
                 <el-date-picker 
                value-format="yyyy-MM-dd" 
                v-model="proForm.registDate" 
                type="date" style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6"><div>&nbsp;</div></el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="18">
            <div>
              <el-form-item label="项目背景" prop="background">
                <el-input
                  v-model="proForm.background"
                  :autosize="{minRows: 2, maxRows: 4}"
                  type="textarea"
                />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="18">
            <div>
              <el-form-item label="项目目标" prop="objectives">
                <el-input
                  v-model="proForm.objectives"
                  :autosize="{minRows: 2, maxRows: 4}"
                  type="textarea"
                />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="合同号" prop="contractNbr">
                <el-input v-model="proForm.contractNbr" 
                clearable style="width: 250px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="合同额（税前）" prop="contractAmount">
                <el-input v-model="proForm.contractAmount" style="width: 200px;"
                clearable />
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="合同额（税后7%）" prop="AfterTaxAmount">
                <el-input v-model="proForm.AfterTaxAmount" 
                clearable style="width: 200px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>

        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="6">
            <div>&nbsp;
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="回款额（税前）" prop="backAmount">
                <el-input v-model="proForm.backAmount" 
                clearable style="width: 200px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-form-item label="回款额（税后7%）" prop="AfterTaxBackAmount">
                <el-input v-model="proForm.AfterTaxBackAmount" 
                clearable style="width: 200px;"/>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>
        
        <el-row>
          <el-col :span="3"><div>&nbsp;</div></el-col>
          <el-col :span="18">
            <div style="margin-left: 0px;">
              <el-form-item>

              </el-form-item>
            </div>
          </el-col>
          <el-col :span="3"><div>&nbsp;</div></el-col>
        </el-row>
      </el-form>
    </div>
    <div class = "root_div">                
      <el-button type="primary" @click="opertype==='add'?addUser():updUser()">确认</el-button>&nbsp;&nbsp;
      <router-link :to="{ path:'/prepare/register'}">
        <el-button type="primary">
          取消
        </el-button>
      </router-link>
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
      // 设置整个表单不可用
      fromDisabled:null,
      operType: null,
      titleMap: {
        add: '项目登记',
        modify: '项目编辑',
        detail: '项目详细',
      },
      //项目状态
      states : null,

      proForm: {
        nbr: "2019-001",
        name: "2019.FTMS 新TOPSS系统改善",
        cusCharge: "弓箭",
        cusCompany: "一汽丰田销售有限公司",
        internalCharge: "张洪朋",
        internalWorkload: 10,
        internalQuotation: 4,
        status: "实施中",
        registrar:  "张震",
        registDate: "2019/01/01",
        background: "1、国家要求油脂类零件需要单独开票 \n2、虚拟EPC不是实物商品，需要单独开票 \n3、目前OPSS系统不能实现以上需求，因此单独做成小工具，根据油脂类和虚拟EP的C定义清单，将油脂类和虚拟EPC从OPSS导出的开票数据文件中单独拆出，实现油脂类、虚拟EPC、其他零件分开开票。",
        objectives: "能将要开票的数据按照油脂类、虚拟EPC和其他零件进行拆分，实现单独开票。 \n开完票的数据能正常回传OPSS，并能在TACT显示正确的财务余额。",
        contractNbr: "201905010053",
        contractAmount: 340000.00,
        AfterTaxAmount: 316200.00,
        backAmount: 0.00,
        AfterTaxBackAmount:0.00 
      },
      // 表单验证
      rules: {
        nbr: [
        ],
        name: [
        ],
        cusCharge: [
        ]
      }

    }
  },
  created() {
    const operType = this.$route.params && this.$route.params.operType
    this.operType = operType
    //查看详细时，设置表单不可编辑
    if(this.operType==='detail'){
        this.fromDistabled = 'disabled'
    }else{
      this.fromDistabled = null
    }
    const id = this.$route.params && this.$route.params.id
    this.initPage()
  },
 
  methods: {
    // 初始化画面
    initPage(){
      const ProjectStatus = 'ProjectStatus'
      // 【状态】下拉框
      getDataDicItem(ProjectStatus).then(response => {
        this.states = response.data
        console.log(`状态:` + this.states);
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