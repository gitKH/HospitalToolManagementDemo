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
                  <button class="btn btn-primary" v-on:click="openmodal('newClinicModal')">Add new</button>
                </td>
              </tr>
              <tr v-for="(clinic, index) in clinics">
                <td>
                    {{clinic.hospitalClinicName}}
                </td>
                <td>
                  <span class="fa fa-trash  font font__delete" v-on:click="deleteClinic(index,clinic)"></span>
                  <span class="fa fa-pencil font font__edit" v-on:click="edit(clinic)"></span>
                </td>
              </tr>
          </tbody>
        </table>




        <div id="editModal" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <span class="close" v-on:click="closemodal('editModal')">&times;</span>
            <p>
              Edit Clinic
            </p>
              <form @submit.prevent="updateclinic">
                  <div class="form-group">
                    <input class="hidden" type="text" name="hospitalClinicId" v-model="editClinic.hospitalClinicId"/>
                    <input type="text" id="hospitalClinicName" name="hospitalClinicName" class="form-control" v-model="editClinic.hospitalClinicName">
                  </div>
                  <div class="alert alert-danger" v-show="editerror">
                    {{editerror}}
                  </div>
                  <button type="submit" class="btn btn-primary">Submit</button>
              </form>
          </div>
        </div>




        <div id="newClinicModal" class="modal">

          <!-- Modal content -->
          <div class="modal-content">
            <span class="close" v-on:click="closemodal('newClinicModal')">&times;</span>
            <p>
              Edit Clinic
            </p>
            <form @submit.prevent="savenewclinic">
              <div class="form-group">
                <input type="text" name="hospitalClinicName" class="form-control" v-model="newclinic.hospitalClinicName">
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

    name: 'clinics',

    data(){
      return{
        clinics :[],
        editClinic : {},
        newclinic : {},
        editerror :''
      }
    },
    methods:{
      deleteClinic(index, clinic){
        axios.post(config.backendip+"/hospitalclinic/"+clinic.hospitalClinicId+"/deleteById").then(res=>{
          this.getallclinics();
        }).catch(err=>{
          alert(err.response.data.errorMessage);
        });
      },
      edit(clinic){
        this.editClinic = JSON.parse(JSON.stringify(clinic));
        this.openmodal("editModal");
      },
      updateclinic(){
        axios.post(config.backendip+"/hospitalclinic/newHospitalClinic",this.editClinic).then(res=>{
          this.getallclinics();
          this.closemodal("editModal");
        }).catch(err=>{
          this.editerror = err.response.data[0].errorMessage;
          setTimeout(()=>{
            this.editerror = '';
          },2000);
          this.getallclinics();
        });
      },
      savenewclinic(){
          //this.newclinic = JSON.parse(JSON.stringify(this.newclinic));
          axios.post(config.backendip+"/hospitalclinic/newHospitalClinic",this.newclinic).then(res=>{
            this.getallclinics();
            this.closemodal("newClinicModal");
          }).catch(err=>{
            this.editerror = err.response.data[0].errorMessage;
            setTimeout(()=>{
              this.editerror = '';
            },2000);
            this.getallclinics();
          });
      },
      openmodal(id){
        document.querySelector("#"+id).style.display = "block";
      },
      closemodal(id){
        document.querySelector("#"+id).style.display = "none";
      },
      getallclinics(){
        axios.get(config.backendip+"/hospitalclinic/getAll").then(res=>{
          this.clinics = res.data;
        });
      }

    }
    ,
    created:function(){
      this.getallclinics();
    }

  }
</script>


