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
          <button class="btn btn-primary" v-on:click="openmodal('newToolCategoryModal')">Add new</button>
        </td>
      </tr>
      <tr v-for="(toolCategory, index) in toolCategories">
        <td>
          {{toolCategory.toolCategoryName}}
        </td>
        <td>
          <span class="fa fa-trash  font font__delete" v-on:click="deleteToolCategory(toolCategory)"></span>
          <span class="fa fa-pencil font font__edit" v-on:click="edit(toolCategory)"></span>
        </td>
      </tr>
      </tbody>
    </table>




    <div id="editModal" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <span class="close" v-on:click="closemodal('editModal')">&times;</span>
        <p>
          Edit Tool Category
        </p>
        <form @submit.prevent="updateToolCategory">
          <div class="form-group">
            <label>Tool Category Name</label>
            <input type="text" class="form-control" v-model="editToolCategory.toolCategoryName">
          </div>
          <div class="alert alert-danger" v-show="editerror">
            {{editerror}}
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>




    <div id="newToolCategoryModal" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <span class="close" v-on:click="closemodal('newToolCategoryModal')">&times;</span>
        <p>
          Edit Clinic
        </p>
        <form @submit.prevent="saveNewToolCategory">
          <div class="form-group">
            <label>New Tool Category </label>
            <input type="text"  class="form-control" v-model="newToolCategory.toolCategoryName">
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

    name: 'categories',

    data(){
      return{
        toolCategories :[],
        editToolCategory : {
          toolCategoryName:""
        },
        newToolCategory: {
          toolCategoryName:"",
          toolSet:[]
        },
        editerror :''
      }
    },
    methods:{
      deleteToolCategory(toolCategory){
        axios.post(config.backendip+"/toolcategory/"+toolCategory.toolCategoryId+"/deleteById").then(res=>{
          this.getAllToolCategories();
        }).catch(err=>{
          alert(err.response.data.errorMessage);
        });
      },
      edit(toolCategory){
        this.editToolCategory = JSON.parse(JSON.stringify(toolCategory));
        this.openmodal("editModal");
      },
      updateToolCategory(){
        axios.post(config.backendip+"/toolcategory/newToolCategory",this.editToolCategory).then(res=>{
          this.getAllToolCategories();
          this.closemodal("editModal");
        }).catch(err=>{
          this.editerror = err.response.data[0].errorMessage;
          setTimeout(()=>{
            this.editerror = '';
          },2000);
          this.getAllToolCategories();
        });
      },
      saveNewToolCategory(){
        axios.post(config.backendip+"/toolcategory/newToolCategory",this.newToolCategory).then(res=>{
          this.getAllToolCategories();
          this.closemodal("newToolCategoryModal");
        }).catch(err=>{
          this.editerror = err.response.data[0].errorMessage;
          setTimeout(()=>{
            this.editerror = '';
          },2000);
          this.getAllToolCategories();
        });
      },
      openmodal(id){
        document.querySelector("#"+id).style.display = "block";
      },
      closemodal(id){
        document.querySelector("#"+id).style.display = "none";
      },
      getAllToolCategories(){
        axios.get(config.backendip+"/toolcategory/getAll").then(res=>{
          this.toolCategories = res.data;
        });
      }

    }
    ,
    created:function(){
      this.getAllToolCategories();
    }

  }
</script>


