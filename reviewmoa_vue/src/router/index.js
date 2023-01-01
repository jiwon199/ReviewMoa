import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import WriteView from '../views/WriteView.vue'
import UpdateView from '../views/UpdateView.vue'
import DetailView from '../views/DetailView.vue'
import AppUser from '../views/AppUser.vue'
import UserRegist from '../components/user/UserRegist'
import UserLogin from '../components/user/UserLogin'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/write',
    name: 'write',
    component: WriteView
  },
  {
    path: '/detail',
    name: 'detail',
    component: DetailView
  },
  {
    path: '/update',
    name: 'update',
    component: UpdateView
  },
  {
    path: '/user',
    name: 'user',
    component: AppUser,
    children: [
      {
        path: 'regist',
        name: 'regist',
        component: UserRegist,
      },
      {
        path: 'login',
        name: 'login',
        component:UserLogin,
      }
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;

