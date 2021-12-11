<template>
    <div>
        <div class="tableTitle">
            报废记录&nbsp;&nbsp;
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
                prop="sid"
                label="记录编号"
                :width="tableWidth/6">
            </el-table-column>
            <el-table-column
                prop="dname"
                label="设备名"
                :width="tableWidth/6">
            </el-table-column>
            <el-table-column
                prop="duid"
                label="序列号"
                :width="tableWidth/6">
            </el-table-column>
            <el-table-column
                prop="user"
                label="经办人"
                :width="tableWidth/6">
            </el-table-column>
            <el-table-column
                prop="sDate"
                label="报废时间"
                :width="tableWidth/6">
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                :width="tableWidth/6">
                <template slot-scope="scope">
                    <el-button @click="editRow(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="deleteRow(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog :title="isAdd?'添加报废记录':'修改报废记录'" :visible.sync="dialogFormVisible">
            <el-form :model="formData">
                <el-form-item label="设备名" :label-width="formLabelWidth">
                    <el-select v-model="formData.dname" placeholder="请选择">
                        <el-option
                            v-for="r in $store.state.deviceStore.deviceList"
                            :key="r.did"
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
                            v-show="r.dname === formData.dname"
                            :value="r.duid">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="经办人" :label-width="formLabelWidth">
                    <el-input disabled v-model="formData.user"></el-input>
                </el-form-item>
                <el-form-item label="报废日期" :label-width="formLabelWidth">
                    <el-date-picker
                        v-model="formData.sDate"
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
import {postRequest} from '../utils/api'
export default {
    name:'Scrap',
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
            postRequest('/server/scrap/getItems').then(res=>{
                this.loading = false;
                for(var index in res.data.obj){
                    var r = res.data.obj[index];
                    this.recordList.unshift({
                        sid:r.sid,
                        did:r.did,
                        uid:r.uid,
                        duid:r.duid,
                        dname:r.dname,
                        user:r.user,
                        sDate:r.sDate,
                    });
                }
            }).catch(()=>{
                this.loading = true;
                this.$alert("数据加载失败");
            })
        },
        editRow(row){
            this.isAdd = false;
            this.dialogFormVisible = true;
            this.formData = row;
        },
        deleteRow(row){
            console.log(row);
            //执行删除数据操作
            postRequest('/server/scrap/deleteItem',row.mid).then(res=>{
                if(res.data.status === 200){
                    this.$$alert("数据删除成功");
                    this.initData();
                }
                else if(res.data.status === 500){
                    this.$alert(res.data.msg);
                }
            }).catch(() =>{
                    this.$alert("数据更新失败");
            });
            //调用初始化操作重新读取设备数据
            this.initData();
        },
        updateData(){
            this.dialogFormVisible = false;
            console.log("newData",this.formData);
            // 接下来调用更新操作
            postRequest("/server/scrap/update",this.formData).then(res=>{
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
            this.initData();
        },

        //点击新增按钮，初始化表单数据
        addRecord(){
            this.formData = {};
            this.dialogFormVisible = true;
            this.formData = {did:'',duid:'',user:this.user,sDate:'',uid:this.uid};
            this.isAdd = true;
        },
        //点击表单中的确定按钮，添加数据
        addData(){
            for(var i=0;i<this.$store.state.deviceStore.deviceList.length;i++){
                if(this.$store.state.deviceStore.deviceList[i].duid === this.formData.duid)
                    this.formData.did = this.$store.state.deviceStore.deviceList[i].did;
            }
            this.dialogFormVisible = false;
            console.log(this.formData);

            postRequest("/server/scrap/add",this.formData).then(res=>{
                if(res.data.status === 200){
                    this.$alert("添加成功");
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