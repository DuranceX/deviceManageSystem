<template>
    <div>
        <div class="tableTitle">
            购买记录&nbsp;&nbsp;
            <el-button round plain type="primary" @click="addRecord()">+ 新增</el-button>
        </div>
        <el-table
            border
            :row-class-name="tableRowDeepColor"
            :data="recordList"
            ref="table"
            style="width: 100%"
            v-loading="loading">
            <el-table-column
                prop="pid"
                label="编号"
                :width="tableWidth/8">
            </el-table-column>
            <el-table-column
                prop="dname"
                label="设备名"
                :width="tableWidth/8">
            </el-table-column>
            <el-table-column
                prop="duid"
                label="序列号"
                :width="tableWidth/8">
            </el-table-column>
            <el-table-column
                prop="buyer"
                label="购买人"
                :width="tableWidth/8">
            </el-table-column>
            <el-table-column
                prop="amount"
                label="购买数量"
                :width="tableWidth/8">
            </el-table-column>
            <el-table-column
                prop="price"
                label="总价格"
                :width="tableWidth/8">
            </el-table-column>
            <el-table-column
                prop="pdate"
                label="购买日期"
                :width="tableWidth/8">
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                :width="tableWidth/8">
                <template slot-scope="scope">
                    <el-button @click="editRow(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteRow(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="修改购买信息" :visible.sync="dialogFormVisible">
            <el-form :model="formData">
                <el-form-item label="设备名" :label-width="formLabelWidth">
                    <el-input autocomplete="on" v-model="formData.dname"></el-input>
                </el-form-item>
                <el-form-item label="序列号" :label-width="formLabelWidth">
                    <el-input :disabled="!isAdd" ref="addUid" v-model="formData.duid"></el-input>
                    <el-button v-if="isAdd" type="text" @click="getUid()">生成序列号</el-button>
                </el-form-item>
                <el-form-item label="购买人" :label-width="formLabelWidth">
                    <el-input disabled v-model="formData.buyer"></el-input>
                </el-form-item>
                <el-form-item label="购买数量" :label-width="formLabelWidth">
                    <el-input v-model="formData.amount"></el-input>
                </el-form-item>
                <el-form-item label="总价格" :label-width="formLabelWidth">
                    <el-input v-model="formData.price"></el-input>
                </el-form-item>
                <el-form-item label="购买日期" :label-width="formLabelWidth">
                    <el-date-picker
                        v-model="formData.pdate"
                        type="date"
                        placeholder="选择日期">
                        </el-date-picker>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="isAdd ? addData() : updateData()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { nanoid } from 'nanoid';
import {postRequest} from '../utils/api'
export default {
    name:'Purchase',
    data(){
        return{
            recordList:[],
            loading:true,
            dialogFormVisible:false,
            formLabelWidth:'120px',
            formData:{},
            tableWidth:1630,
            isAdd:false,
        }
    },
    mounted(){
        this.initData();
        this.$nextTick(()=>{
            this.tableWidth=this.$refs.table.$el.offsetWidth;
        });
        window.onresize=()=>{
            this.tableWidth=this.$refs.table.$el.offsetWidth;
        }
    },
    methods:{
        tableRowDeepColor({rowIndex}){
            if(rowIndex%2 === 1)
                return 'deep-row';
            else{
                return '';
            }
        },
        initData(){
            postRequest('/server/purchase/getItems').then(res=>{
                this.loading = false;
                for(var index in res.data.obj){
                    var r = res.data.obj[index];
                    console.log(r);
                    this.recordList.unshift({
                        pid:r.pid,
                        dname:r.dname,
                        duid:r.duid,
                        buyer:r.buyer,
                        amount:r.amount,
                        price:r.price,
                        pdate:r.pdate,
                    });
                }
            }).catch(()=>{
                this.loading = true;
                this.$alert("数据加载失败");
            })
        },
        editRow(row){
            this.dialogFormVisible = true;
            this.formData = row;
        },
        deleteRow(row){
            console.log(row);
            //执行删除数据操作
            //调用初始化操作重新读取设备数据
        },
        updateData(){
            this.dialogFormVisible = false;
            console.log("newData",this.formData);
            //接下来调用更新操作
            postRequest("/server/purchase/update",this.formData).then(res=>{
                if(res.data.status === 200){
                    this.$$alert("数据更新成功");
                    this.initData();
                }
                else if(res.data.status === 500){
                    this.$alert("数据更新错误");
                }
            }).catch(() =>{
                    this.$alert("数据更新失败");
            });
            //调用初始化操作重新读取设备数据
        },
        addRecord(){
            let buyer = window.sessionStorage.getItem("username");
            this.dialogFormVisible = true;
            this.formData = {dname:'',duid:'',buyer:buyer,amount:'',price:'',pdate:''};
            this.isAdd = true;
        },
        getUid(){
            this.$refs.addUid.$el.childNodes[1].value = nanoid(8).toUpperCase();
        },
        addData(){
            this.formData.duid = this.$refs.addUid.$el.childNodes[1].value;
            this.dialogFormVisible = false;
            this.isAdd = false;
            postRequest("/server/purchase/add",this.formData).then(res=>{
                if(res.data.status === 200){
                    this.formData.duid = '';
                    this.initData();
                }
                else if(res.data.status === 500){
                    this.$alert("数据加载错误");
                }
            }).catch(() =>{
                this.$alert("数据加载失败");
            });
        }
    },
    
}
</script>

<style lang="css">
    .el-table .deep-row{
        background:#f6f6f6;
    }

    .tableTitle{
        font-size: 22px;
        margin: 20px 0;
    }
</style>