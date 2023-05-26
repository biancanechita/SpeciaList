// Composables
import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    component: () => import("@/layouts/default/Default.vue"),
    children: [
      {
        path: "",
        name: "Home",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
          import(/* webpackChunkName: "home" */ "@/views/Home.vue"),
      },
      {
        path: "/clinics",
        component: null,
        children: [
          { path: "", name: "Clinics", component: null },
          { path: "/:id", name: "ClinicDetailsPage", component: null },
        ],
      },
      {
        path: "/doctors",
        component: null,
        children: [
          { path: "", name: "Doctors", component: null },
          { path: "/:id", name: "DoctorProfilePage", component: null },
        ],
      },
      {
        path: "/specializations",
        component: null,
        children: [
          { path: "", name: "Specializations", component: null },
          { path: "/:id", name: "SpecializationDetailsPage", component: null },
        ],
      },
    ],
  },
  { path: "/:404(.*)", component: null },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
