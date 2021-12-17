<template>
    <div>
        <div class="tableTitle">
            转借记录&nbsp;&nbsp;
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
                prop="lid"
                label="记录编号"
                :width="tableWidth/7">
            </el-table-column>
            <el-table-column
                prop="dname"
                label="设备名"
                :width="tableWidth/7">
            </el-table-column>
            <el-table-column
                prop="duid"
                label="序列号"
                :width="tableWidth/7">
            </el-table-column>
            <el-table-column
                prop="user"
                label="经办人"
                :width="tableWidth/7">
            </el-table-column>
            <el-table-column
                prop="leaseDate"
                label="转借日期"
                :width="tableWidth/7">
            </el-table-column>
            <el-table-column
                prop="backDate"
                label="归还日期"
                :width="tableWidth/7">
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                :width="tableWidth/7">
                <template slot-scope="scope">
                    <el-button @click="editRow(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteRow(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog :title="isAdd?'添加转借信息':'修改转借信息'" :visible.sync="dialogFormVisible">
            <el-form :model="formData">
                <el-form-item label="设备名" :label-width="formLabelWidth">
                    <el-select v-model="formData.dname" placeholder="请选择">
                        <el-option
                            v-for="r in $store.state.deviceStore.deviceList"
                            :key="r.did"
                            v-show="r.dstatus === '运行中'"
                            :label="r.dname"
                            :value="r.dname">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="序列号" :label-width="formLabelWidth">
                    <el-select v-model="formData.duid" placeholder="请选择">
                        <el-option
                            v-for="r in $store.state.deviceStore.deviceList"
                            :key="r.did"
                            :label="r.duid"
                            v-show="r.dname === formData.dname && r.dstatus === '运行中'"
                            :value="r.duid">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="经办人" :label-width="formLabelWidth">
                    <el-input disabled v-model="formData.user"></el-input>
                </el-form-item>
                <el-form-item label="转借日期" :label-width="formLabelWidth">
                    <el-date-picker
                        v-model="formData.leaseDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择日期">
                        </el-date-picker>
                </el-form-item>
                <el-form-item label="归还日期" :label-width="formLabelWidth">
                    <el-date-picker
                        v-model="formData.backDate"
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
import {postRequest,paramsPostRequest} from '../utils/api'
export default {
    name:'Lease',
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
        user(){
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
            postRequest('/server/lease/getItems').then(res=>{
                this.loading = false;
                for(var index in res.data.obj){
                    var r = res.data.obj[index];
                    this.recordList.unshift({
                        lid:r.lid,
                        did:r.did,
                        uid:r.uid,
                        dname:r.dname,
                        duid:r.duid,
                        user:r.user,
                        leaseDate:r.leaseDate,
                        backDate:r.backDate,
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
            this.$confirm('此操作将删除该转借记录，确认删除吗','提示',{
                type:'warning'
            }).then(()=>{
                paramsPostRequest('/server/lease/delete',{id:row.lid}).then(res=>{
                    if(res.data.status === 200){
                        this.$alert("删除成功",'',{
                            callback:()=>{
                                this.initData();
                            }
                        });
                    }
                }).catch((err)=>{
                    console.log(err);
                    this.$alert("删除失败");
                })
            })
        },
        updateData(){
            this.dialogFormVisible = false;
            //接下来调用更新操作
            postRequest("/server/lease/update",this.formData).then(res=>{
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
            this.formData = {};
            this.dialogFormVisible = true;
            this.formData = {uid:this.uid,dname:'',duid:'',user:this.user,leaseDate:'',backDate:''};
            this.isAdd = true;
        },
        addData(){
            for(var i=0;i<this.$store.state.deviceStore.deviceList.length;i++){
                if(this.$store.state.deviceStore.deviceList[i].duid === this.formData.duid)
                    this.formData.did = this.$store.state.deviceStore.deviceList[i].did;
            }

            postRequest("/server/lease/add",this.formData).then(res=>{
                if(res.data.status === 200){
                    this.$alert("添加记录成功");
                    this.dialogFormVisible = false;
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