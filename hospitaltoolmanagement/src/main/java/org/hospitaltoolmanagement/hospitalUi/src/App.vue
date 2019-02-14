<template>
  <div id="app">
      <nav class="navbar navbar-default">
        <div class="container-fluid"><!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="#/">Logo <span class="sr-only"></span></a></li>
              <li v-bind:class="{active :activeLink == 'clinics'}">
                <a href="#/clinics" v-on:click="handleActiveLink('clinics')">Clinics</a>
              </li>
              <li v-bind:class="{active :activeLink == 'rooms'}">
                <a href="#/rooms" v-on:click="handleActiveLink('rooms')">Rooms</a>
              </li>
              <li v-bind:class="{active :activeLink == 'toolCategories'}">
                <a href="#/toolCategories" v-on:click="handleActiveLink('toolCategories')">Tool Categories</a>
              </li>
              <li v-bind:class="{active :activeLink == 'toolManufacturers'}">
                <a href="#/toolManufacturers" v-on:click="handleActiveLink('toolManufacturers')">Tool Manufacturers</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      <router-view></router-view>


    </div>

</template>

<script>
export default {

  name: 'App',
  data(){
    return{
      activeLink:""
    }
  },
  methods:{
    handleActiveLink(activeLink){
      this.activeLink = activeLink;
    }
  }

}
</script>

