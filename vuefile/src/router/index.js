import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Index from '@/components/Index'
import ArticleList from '@/components/ArticleList'
import PostArticle from '@/components/PostArticle'
import UserMana from '@/components/UserMana'
import FileUpload from '@/components/FileUpload'
import FileDownload from '@/components/FileDownload'

Vue.use(Router)

export default new Router({
  routes: [
  // 下面都是固定的写法
    {
      path: '/',
      name: 'Login',
      hidden: true,
      component: Login
    },
    {
      path: '/login',
      name: 'Login',
      hidden: true,
      component: Login
    },
    {
      path: '/index',
      name: '首页',
      component: Index,
      hidden: true
    }, {
      path: '/index',
      component: Index,
      name: 'Test',
      iconCls: 'fa fa-file-text-o',
      children: [
        {
          path: '/articleList',
          name: '1',
          component: ArticleList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/postArticle',
          name: '2',
          component: PostArticle,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/index',
      component: Index,
      name: '用户管理',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: '用户管理',
          component: UserMana
        }
      ]
    }, {
      path: '/index',
      component: Index,
      name: '文件管理',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/upload',
          iconCls: 'fa fa-bar-chart',
          name: '文件上传',
          component: FileUpload
        },{
          path: '/download',
          iconCls: 'fa fa-user-o',
          name: '文件下载',
          component: FileDownload
        }
      ]
    }
  ]
})
