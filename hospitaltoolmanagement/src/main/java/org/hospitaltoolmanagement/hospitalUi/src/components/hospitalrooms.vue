<template>
  <div>
    <table class="table table-striped table-dark table-bordered">
      <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Clinic</th>
        <th scope="col">Options</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td></td>
        <td></td>
        <td>
          <button class="btn btn-primary" v-on:click="openmodal('newRoommodal')">Add new</button>
        </td>
      </tr>
      <tr v-for="(room, index) in rooms">
        <td>
          {{room.hospitalRoomName}}
        </td>
        <td>
          {{room.hospitalClinicName}}
        </td>
        <td>
          <span class="fa fa-trash font font__delete" v-on:click="deleteRoom(index,room)"></span>
          <span class="fa fa-pencil font font__edit" v-on:click="edit(room)"></span>
        </td>
      </tr>
      </tbody>
    </table>

    <div id="editModal" class="modal">
      <!-- Modal content -->
      <div class="modal-content">
        <span class="close" v-on:click="closemodal('editModal')">&times;</span>
        <p>
          Edit Room
        </p>
        <form @submit.prevent="updateRoom()">
          <div class="form-group">
            <label>Room Name</label>
            <input type="text" id="hospitalRoomName" name="hospitalRoomName" class="form-control" v-model="editroom.hospitalRoomName">
          </div>

          <div class="form-group">
            <label>Clinic</label>
            <select class="form-control" v-model="selectedClinic" required>
                <option
                  v-for="clinic in clinics"
                  :selected="editroom.hospitalClinicName == clinic.hospitalClinicName"
                  v-bind:value="{
                    hospitalClinicName:clinic.hospitalClinicName,
                    hospitalClinicId:clinic.hospitalClinicId
                  }"
                >
                  {{clinic.hospitalClinicName}}
                </option>
            </select>
          </div>
          <div class="alert alert-danger" v-show="editerror">
            {{editerror}}
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>




    <div id="newRoommodal" class="modal">

      <!-- Modal content -->
      <div class="modal-content">
        <span class="close" v-on:click="closemodal('newRoommodal')">&times;</span>
        <div class="modal-header">
          New clinic Room
        </div>
        <form @submit.prevent="savenewRoom">
          <div class="form-group">
            <label for="hospitalRoomName">Room Name</label>
            <input type="text" name="hospitalRoomName" class="form-control" v-model="newroom.hospitalRoomName">
          </div>
          <div class="form-group">
            <label>Clinic</label>
            <select class="form-control" name="hospitalClinicId" v-model="selectedClinic" required>
              <option v-for="clinic in clinics" v-bind:value="{
                  hospitalClinicName:clinic.hospitalClinicName,
                  hospitalClinicId:clinic.hospitalClinicId
              }">
                {{clinic.hospitalClinicName}}
              </option>
            </select>
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

    name: 'hospitalrooms',

    data(){
      return{
        clinics :[],
        rooms :[],
        editroom : {},
        newroom : {
          "hospitalRoomName": '',
          "hospitalClinicId": '',
          "hospitalClinicName": '',
          "toolSet": null
        },
        selectedClinic:{},
        editroomclinic:{},
        newclinic : {},
        editerror :''
      }
    },
    methods:{
      deleteRoom(index, room){
        axios.post(config.backendip+"/hospitalroom/"+room.hospitalRoomId+"/deleteById").then(res=>{
          this.getallrooms();
        }).catch(err=>{
          alert(err.response.data.errorMessage);
        });
      },
      edit(room){
        this.editroom = JSON.parse(JSON.stringify(room));
        this.openmodal("editModal");
      },
      updateRoom(){
        this.editroom.hospitalClinicId = this.selectedClinic.hospitalClinicId;
        this.editroom.hospitalClinicName = this.selectedClinic.hospitalClinicName;
        axios.post(config.backendip+"/hospitalroom/newHospitalRoom",this.editroom).then(res=>{
          this.getallrooms();
          this.closemodal("editModal");
        }).catch(err=>{
          this.editerror = err.response.data[0].errorMessage;
          setTimeout(()=>{
            this.editerror = '';
          },2000);
          this.getallclinics();
        });
      },
      savenewRoom(){
        this.newroom.hospitalClinicName = this.selectedClinic.hospitalClinicName;
        this.newroom.hospitalClinicId = this.selectedClinic.hospitalClinicId;
        axios.post(config.backendip+"/hospitalroom/newHospitalRoom",this.newroom).then(res=>{
          this.getallrooms();
          this.closemodal("newRoommodal");
          this.newroom = {};
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
      },
      getallrooms(){
        axios.get(config.backendip+"/hospitalroom/getAll").then(res=>{
          this.rooms = res.data;
        });
      }

    }
    ,
    created:function(){
      this.getallclinics();
      this.getallrooms();
    }

  }
</script>


