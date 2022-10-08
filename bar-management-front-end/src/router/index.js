import { createRouter, createWebHistory } from 'vue-router'
import axios from "axios";

var employeeRole = null;
var employees = [];

function getCookie(name) {
  const value = document.cookie;
  const parts = value.split(name);
  if (parts.length === 2) return parts.pop().split(';').shift();
}

function getEmployeeRole(){
  var base64Url = getCookie('Token').split('.')[1];
  var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));
  var user = JSON.parse(jsonPayload);

  if(employees) {
    for (var i = 0; i < employees.length; i++) {
      if (employees[i].username === user.sub) {
        employeeRole = employees[i].accountType;
      }
    }
  }
}

const routes = [
  {
    path: '/',
    name: 'Dashboard',
    component: () => import('../views/Dashboard.vue'),
    beforeEnter: (to, from, next) => {
      var token = getCookie("Token");
      axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
      }).then(response => {
        employees = response.data;
        getEmployeeRole();
        if (employeeRole === 'BAR_MANAGER') {
          return next();
        }
      })
          .catch(function (response) {
            console.log(response);
            alert("Could not load employees");
        });
    }
  },
  {
    path: '/accounts',
    name: 'Accounts',
    component: () => import('../views/Accounts.vue'),
    beforeEnter: (to, from, next) => {
      var token = getCookie("Token");
      axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
      }).then(response => {
        employees = response.data;
        getEmployeeRole();
        if (employeeRole === 'BAR_MANAGER') {
          return next();
        }
      })
          .catch(function (response) {
            console.log(response);
            alert("Could not load employees");
          });
    }
  },
  {
    path: '/bars',
    name: 'Bars',
    component: () => import('../views/Bars.vue'),
    beforeEnter: (to, from, next) => {
      var token = getCookie("Token");
      axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
      }).then(response => {
        employees = response.data;
        getEmployeeRole();
        if (employeeRole === 'BAR_MANAGER') {
          return next();
        }
      })
          .catch(function (response) {
            console.log(response);
            alert("Could not load employees");
          });
    }
  },
  {
    path: '/stockrequests',
    name: 'StockRequests',
    component: () => import('../views/StockRequests.vue'),
    beforeEnter: (to, from, next) => {
      var token = getCookie("Token");
      axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
      }).then(response => {
        employees = response.data;
        getEmployeeRole();
        if (employeeRole === 'BAR_MANAGER') {
          return next();
        }
      })
          .catch(function (response) {
            console.log(response);
            alert("Could not load employees");
          });
    }
  },
  {
    path: '/warehouse',
    name: 'Warehouse',
    component: () => import('../views/Warehouse.vue'),
    beforeEnter: (to, from, next) => {
      var token = getCookie("Token");
      axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
      }).then(response => {
        employees = response.data;
        getEmployeeRole();
        if (employeeRole === 'BAR_MANAGER') {
          return next();
        }
      })
          .catch(function (response) {
            console.log(response);
            alert("Could not load employees");
          });
    }
  },
  {
    path: '/warehouse-account-stock',
    name: 'Warehouse-account-stock',
    component: () => import('../views/Warehouse-account-stock.vue'),
    beforeEnter: (to, from, next) => {
      var token = getCookie("Token");
      axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
      }).then(response => {
        employees = response.data;
        getEmployeeRole();
        if (employeeRole === 'WAREHOUSE_KEEPER') {
          return next();
        }
      })
          .catch(function (response) {
            console.log(response);
            alert("Could not load employees");
          });
    }
  },
  {
  path: '/websocket',
    name: 'WebSocket',
    component: () => import('../views/WebSocket.vue'),
    beforeEnter: (to, from, next) => {
  var token = getCookie("Token");
  axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
  }).then(response => {
    console.log(response);
      return next();

  })
      .catch(function (response) {
        console.log(response);
        alert("Could not load employees");
      });
}
},
  {
    path: '/personal-info',
    name: 'Personal-info',
    component: () => import('../views/PersonalInfo.vue'),
    beforeEnter: (to, from, next) => {
      var token = getCookie("Token");
      axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
      }).then(response => {
        console.log(response);
          return next();
      })
          .catch(function (response) {
            console.log(response);
            alert("Could not load employees");
          });
    }
  },

  {
  path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const publicPages = ['/login'];

  var user;
  let cookieToken = null;
  const value = document.cookie;
  console.log(document.cookie);
  const parts = value.split("Token");
  if (parts.length === 2){
    cookieToken = parts.pop().split(';').shift();
  }
  if(cookieToken) {
    var base64Url = cookieToken.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');

    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    user = JSON.parse(jsonPayload);
    console.log(user);
  }

  const authRequired = !publicPages.includes(to.path);

  if (authRequired && !user) {
    return next('/login');
  }
  next();
})

export default router
