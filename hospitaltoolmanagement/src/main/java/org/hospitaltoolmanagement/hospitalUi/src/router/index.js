import Vue from 'vue'
import Router from 'vue-router'




Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: require('../components/welcome.vue').default
    },
    {
      path: '/clinics',
      name: 'Clinics',
      component: require('../components/clinics.vue').default
    },
    {
      path: '/rooms',
      name: 'Rooms',
      component: require('../components/hospitalrooms').default
    },
    {
      path: '/toolCategories',
      name: 'Tool Categories',
      component: require('../components/categories').default
    },
    {
      path: '/toolManufacturers',
      name: 'Tool Manufacturers',
      component: require('../components/manufacturers').default
    },
  ]
})
