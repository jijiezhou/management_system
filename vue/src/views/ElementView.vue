<template>
  <div>
<!--    <el-row>-->
<!--      <el-col :span="12">-->
<!--        <div style="width: 100%; height: 300px; background-color: aqua;"></div>-->
<!--      </el-col>-->
<!--      <el-col :span="12">-->
<!--        <div style="width: 100%; height: 300px; background-color: chocolate;"></div>-->
<!--      </el-col>-->
<!--    </el-row>-->

      <el-row>
        <el-col>
          <el-input style="width: 200px" v-model="value"></el-input>
          <el-autocomplete style="width: 300px" placeholder="please input, and I will guess" :fetch-suggestions="querySearch" :trigger-on-focus="false" v-model="value1"></el-autocomplete>
        </el-col>
      </el-row>

    <el-row>
      <el-col>
        <el-select v-model="select1" @change="changeSelect">
          <el-option v-for="item in fruits" :key="item.id" :label="item.name" :value="item.id">
          </el-option>
        </el-select>

        <el-select v-model="select2" @change="changeSelectUser" filterable>
          <el-option v-for="item in users" :key="item.card" :label="item.name" :value="item.id">
          </el-option>
        </el-select>
      </el-col>
    </el-row>

    <el-radio-group v-model="radio" @change="changeRadio">
      <el-radio label="man"></el-radio>
      <el-radio label="woman"></el-radio>
    </el-radio-group>

    <el-date-picker v-model="date" type="date" placeholder="choose date" value-format="yyyy-MM-dd" @change="changeDate"></el-date-picker>
    <el-date-picker v-model="datetime" type="datetime" placeholder="choose date and time" value-format="yyyy-MM-dd HH:mm:ss" @change="changeDateTime"></el-date-picker>
    <div>
      <el-date-picker
          v-model="daterange"
          type="daterange"
          range-separator="to"
          start-placeholder="start date"
          end-placeholder="end date"
          @change="changeDateRange"
          value-format="yyyy-MM-dd"
          align="right">
      </el-date-picker>
    </div>

    <el-row style="margin: 100px 0">
      <el-table :data="tableData" border :header-cell-style="{background: 'aliceblue', fontSize: '16px'}">
        <el-table-column prop="id" label="id" align="center"></el-table-column>
        <el-table-column prop="name" label="name" align="center"></el-table-column>
        <el-table-column prop="age" label="age" align="center"></el-table-column>
        <el-table-column prop="address" label="address" align="center"></el-table-column>
        <el-table-column label="operate">
          <template v-slot="scope">
            <el-button type="primary" @click="edit(scope.row)">operate</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>
<script>
  export default{
    name: 'Element',
    data(){
      return {
        value: '',
        value1: '',
        coffees: [{value: '1starbuck'},{value: '2ruixin'},{value: '3kudi'}],
        select1: '',
        select2: '',
        fruits: [
          {name: 'apple', id: 1},
          {name: 'orange', id: 2},
          {name: 'pineapple', id: 3},
          {name: 'jijiez', id: 4}
        ],
        users: [
          {name: 'gege', card: '123456789'},
          {name: 'xiaogege', card: '987654321'},
          {name: 'dagege', card: '34858734918734'},
        ],
        radio: '',
        date: '',
        datetime: '',
        daterange: '',
        tableData: [
          {name: 'jijiez', address: '303 Smith Level', id: 1, age: '21'},
          {name: 'jijiez', address: '303 Smith Level', id: 1, age: '21'},
          {name: 'jijiez', address: '303 Smith Level', id: 1, age: '21'},
        ]
      }
    },
    methods: {
      querySearch(query, cb){
        let res = query ? this.coffees.filter(v => v.value.includes(query)) : this.coffees;
        cb(res);
      },
      changeSelect(){
        console.log(this.select1);
      },
      changeSelectUser(){
        console.log(this.select2);
      },
      changeRadio(){
        console.log(this.radio);
      },
      changeDate(){
        console.log(this.date);
      },
      changeDateTime(){
        console.log(this.datetime);
      },
      changeDateRange(){
        console.log(this.daterange);
      },
      edit(row){
        //console.log(row.name);
        //this.$message.success(row.name);
        //this.$message.warning(row.name);
        this.$confirm('please select', 'notification', {
          type: 'warning'
        }).then(res => {
          this.$message.success('you click confirm');
        }).catch(() => {
          this.$message.warning('you click cancel');
        })
      }
    }
  }
</script>
