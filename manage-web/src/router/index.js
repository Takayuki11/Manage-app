import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Task from '../views/Task.vue'
import store from '../store/index'
import CompanySignup from '../views/CompanySignup'
import Dashboard from '../views/Dashboard'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    meta: { requiresAuth: false }
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { requiresAuth: false }
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
    meta: { requiresAuth: true }
  },
  {
    path: '/tasks',
    name: 'Task',
    component: Task,
    meta: { requiresAuth: true }
  },
  {
    path: '/company/signup',
    name: 'CompanySignup',
    component: CompanySignup,
    meta: { requiresAuth: false }
  },
  {
    path: '*',
    redirect: '/',
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if(to.matched.some((record) => record.meta.requiresAuth)) {
      if(store.state.user.isLoggedIn != true){
        next({
          path: '/login',
          // query: {
          //   redirect: to.fullPath,
          // }
        })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
