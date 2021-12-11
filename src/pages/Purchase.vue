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

        <el-dialog :title="isAdd ? '添加购买信息':'修改购买信息'" :visible.sync="dialogFormVisible">
            <el-form :model="formData">
                <el-form-item label="设备名" :label-width="formLabelWidth">
                    <el-input autocomplete="on" v-model="formData.dname"></el-input>
                </el-form-item>
                <el-form-item label="序列号" v-show="!isAdd" :label-width="formLabelWidth">
                    <el-input disabled v-model="formData.duid"></el-input>
                    <!-- <el-button v-if="isAdd" type="text" @click="getUid()">生成序列号</el-button> -->
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
                        value-format="yyyy-MM-dd"
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
import {postRequest,paramsPostRequest} from '../utils/api'
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
    computed:{
        uid(){
            return window.sessionStorage.getItem('userid');
        },
        buyer(){
            return window.sessionStorage.getItem("username");
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
            this.recordList = [];
            postRequest('/server/purchase/getItems').then(res=>{
                this.loading = false;
                for(var index in res.data.obj){
                    var r = res.data.obj[index];
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
            this.isAdd = false;
        },
        deleteRow(row){
            this.$confirm('此操作将一同删除设备，确认删除吗','提示',{
                type:'warning'
            }).then(()=>{
                paramsPostRequest('/server/purchase/delete',{id:row.pid}).then(res=>{
                    if(res.data.status === 200){
                        this.$alert("删除成功",'',{
                            callback:()=>{
                                this.initData();
                            }
                        });
                        
                    }
                }).catch(()=>{
                    this.$alert("删除失败");
                })
            })
        },
        updateData(){
            this.dialogFormVisible = false;
            //接下来调用更新操作
            postRequest("/server/purchase/update",this.formData).then(res=>{
                if(res.data.status === 200){
                    this.$alert("数据更新成功");
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
            this.dialogFormVisible = true;
            this.formData = {dname:'',duid:'',buyer:this.buyer,amount:'',price:'',pdate:'',uid:this.uid};
            this.isAdd = true;
        },
        // getUid(){
        //     this.$refs.addUid.$el.childNodes[1].value = nanoid(8).toUpperCase();
        // },
        addData(){
            this.dialogFormVisible = false;
            this.isAdd = false;

            //生成多个序列号
            let str = '';
            for(var i=0;i<this.formData.amount;i++)
            {
                let s = nanoid(8).toUpperCase();
                str = str + s + ",";
            }
            str = str.substring(0,str.length-1);
            this.formData.duid = str;

            postRequest("/server/purchase/add",this.formData).then(res=>{
                if(res.data.status === 200){
                    this.$alert("数据添加成功");
                    this.initData();
                }
                else if(res.data.status === 500){
                    this.$alert("数据加载错误");
                }
            }).catch(() =>{
                this.$alert("数据加载失败，请检查网络连接");
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