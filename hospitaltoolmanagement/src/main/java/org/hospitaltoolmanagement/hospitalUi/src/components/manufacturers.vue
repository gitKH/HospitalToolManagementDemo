<template>
  <div>
    <table class="table table-striped table-dark table-bordered">
      <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Options</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td></td>
        <td>
          <button class="btn btn-primary" v-on:click="openmodal('newToolManufacturerModal')">Add new</button>
        </td>
      </tr>
      <tr v-for="(toolManufacturer, index) in toolManufacturers">
        <td>
          {{toolManufacturer.toolManufacturerName}}
        </td>
        <td>
          <span class="fa fa-trash  font font__delete" v-on:click="deleteToolManifacturer(toolManufacturer)"></span>
          <span class="fa fa-pencil font font__edit" v-on:click="edit(toolManufacturer)"></span>
        </td>
      </tr>
      </tbody>
    </table>




    <div id="editModal" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <span class="close" v-on:click="closemodal('editModal')">&times;</span>
        <p>
          Edit Tool Manufacturer
        </p>
        <form @submit.prevent="updatetoolManufacturer">
          <div class="form-group">
            <label>Tool toolManufacturer Name</label>
            <input type="text" class="form-control" v-model="editToolManufacturer.toolManufacturerName">
          </div>
          <div class="alert alert-danger" v-show="editerror">
            {{editerror}}
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>




    <div id="newToolManufacturerModal" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <span class="close" v-on:click="closemodal('newToolManufacturerModal')">&times;</span>
        <p>
          Edit Clinic
        </p>
        <form @submit.prevent="saveNewToolManifacturer">
          <div class="form-group">
            <label>New Tool Manufacturer</label>
            <input type="text"  class="form-control" v-model="newToolManufacturer.toolManufacturerName">
          </div>
          <div class="alert alert-danger" v-show="editerror">
            {{editerror}}
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>

  </div>
</template>

<script>
  import axios from 'axios';
  import config from '../server.config';
  export default {

    name: 'manufaturers',

    data(){
      return{
        toolManufacturers :[],
        editToolManufacturer : {
          toolManufacturerName:"",
          toolSet:[]
        },
        newToolManufacturer: {
          toolManufacturerName:"",
          toolSet:[]
        },
        editerror :''
      }
    },
    methods:{
      deleteToolManifacturer(toolManufacturer){
        axios.post(config.backendip+"/toolmanufacturer/"+toolManufacturer.toolManufacturerId+"/deleteById").then(res=>{
          this.getAllToolManifacturers();
        }).catch(err=>{
          alert(err.response.data.errorMessage);
        });
      },
      edit(toolManifacturer){
        this.editToolManufacturer = JSON.parse(JSON.stringify(toolManifacturer));
        this.openmodal("editModal");
      },
      updatetoolManufacturer(){
        axios.post(config.backendip+"/toolmanufacturer/newToolManufacturer",this.editToolManufacturer).then(res=>{
          this.getAllToolManifacturers();
          this.closemodal("editModal");
        }).catch(err=>{
          this.editerror = err.response.data[0].errorMessage;
          setTimeout(()=>{
            this.editerror = '';
          },2000);
          this.getAllToolManifacturers();
        });
      },
      saveNewToolManifacturer(){
        axios.post(config.backendip+"/toolmanufacturer/newToolManufacturer",this.newToolManufacturer).then(res=>{
          this.getAllToolManifacturers();
          this.closemodal("newToolManufacturerModal");
        }).catch(err=>{
          this.editerror = err.response.data[0].errorMessage;
          setTimeout(()=>{
            this.editerror = '';
          },2000);
          this.getAllToolManifacturers();
        });
      },
      openmodal(id){
        document.querySelector("#"+id).style.display = "block";
      },
      closemodal(id){
        document.querySelector("#"+id).style.display = "none";
      },
      getAllToolManifacturers(){
        axios.get(config.backendip+"/toolmanufacturer/getAll").then(res=>{
          this.toolManufacturers = res.data;
        });
      }

    }
    ,
    created:function(){
      this.getAllToolManifacturers();
    }

  }
</script>


