import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/pricelist',
    component: Layout,
    redirect: '/pricelist',
    name: 'pricelist',
    children: [
      {
        path: 'pricelist',
        name: 'pricelist',
        component: () => import('@/views/pricelist/list'),
        meta: { title: '价格清单', icon: 'register' }
      }

    ]
  },

  {
    path: '/prepare',
    component: Layout,
    redirect: '/prepare/register',
    name: 'prepare',
    meta: { title: '评估阶段', icon: 'prepare' },
    children: [
      {
        path: 'register',
        name: 'Register',
        component: () => import('@/views/prepare/register/list'),
        meta: { title: '项目登记', icon: 'register' }
      },
      {
        path: 'registerEdit/:operType/:id',
        name: 'RegisterEdit',
        hidden: true,
        component: () => import('@/views/prepare/register/edit'),
        meta: { title: '评估阶段->项目登记' }
      },
      {
        path: 'demand',
        name: 'Demand',
        component: () => import('@/views/prepare/register/list'),
        meta: { title: '需求收集', icon: 'register' }
      },
      {
        path: 'registerEdit/:operType/:id',
        name: 'RegisterEdit',
        component: () => import('@/views/prepare/register/list'),
        meta: { title: 'testmawq', icon: 'register' }
      }
    ]
  },
  {
    path: '/implementation',
    component: Layout,
    redirect: '/implementation/register',
    name: 'implementation',
    meta: { title: '实施阶段', icon: 'prepare' },
    children: [
      {
        path: 'register',
        name: 'register',
        component: () => import('@/views/prepare/register/list'),
        meta: { title: '项目登记', icon: 'register' }
      },
      {
        path: 'register',
        name: 'register',
        component: () => import('@/views/prepare/register/list'),
        meta: { title: '啦啦啦', icon: 'register' }
      }
    ]
  },
  {
    path: '/summary',
    component: Layout,
    redirect: '/summary/register',
    name: 'summary',
    meta: { title: '总结阶段', icon: 'prepare' },
    children: [
      {
        path: 'register',
        name: 'register',
        component: () => import('@/views/prepare/register/list'),
        meta: { title: '项目登记', icon: 'register' }
      },
      {
        path: 'register',
        name: 'register',
        component: () => import('@/views/prepare/register/list'),
        meta: { title: '啦啦啦', icon: 'register' }
      }
    ]
  },
  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: '样例', icon: 'example' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: '表格', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: '树', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: '表单', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: '外部链接', icon: 'link' }
      }
    ]
  },
  {
    path: '/setting',
    component: Layout,
    redirect: '/setting/datadic',
    name: 'Setting',
    meta: { title: '系统设置', icon: 'setting' },
    children: [
      {
        path: 'datadic',
        name: 'Datadic',
        component: () => import('@/views/setting/datadic/list'),
        meta: { title: '数据字典', icon: 'datadic' }
      },
      
      {
        path: 'itemedit/:id',
        name: 'itemedit',
        hidden: true,
        component: () => import('@/views/setting/datadic/detail/itemedit'),
        meta: { title: '数据字典->数据详细', noCache: true }
      },

      {
        path: 'dept',
        name: 'Dept',
        component: () => import('@/views/setting/dept/list'),
        meta: { title: '部门管理', icon: 'dept' },
      },
      {
        path: 'deptedit/:id',
        name: 'deptedit',
        hidden: true,
        component: () => import('@/views/setting/dept/detail/deptedit'),
        meta: { title: '部门管理->部门详细', noCache: true }
      },

      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/setting/user/list'),
        meta: { title: '用户管理', icon: 'users' }
      },
      {
        path: 'useredit_add/:opertype/:id',
        name: 'UserEditAdd',
        hidden: true,
        component: () => import('@/views/setting/user/edit'),
        meta: { title: '用户管理->新增用户信息', noCache: true }
      },
      {
        path: 'useredit_mod/:opertype/:id',
        name: 'UserEditMod',
        hidden: true,
        component: () => import('@/views/setting/user/edit'),
        meta: { title: '用户管理->编辑用户信息', noCache: true }
      },
      {
        path: 'userinfo',
        name: 'UserInfo',
        component: () => import('@/views/setting/user/userinfo'),
        meta: { title: '个人信息',icon: 'userinfo' }
      },
      {
        path: 'pwdchange',
        name: 'pwdchange',
        component: () => import('@/views/setting/user/pwdchange'),
        meta: { title: '密码变更',icon: 'password' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
