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
          {
            path: "",
            name: "Clinics",
            component: () => import("@/views/Clinics.vue"),
          },
          { path: "/:id", name: "ClinicDetailsPage", component: null },
        ],
      },
      {
        path: "/doctors",
        component: null,
        children: [
          {
            path: "",
            name: "Doctors",
            component: () => import("@/views/Doctors.vue"),
          },
          { path: "/:id", name: "DoctorProfilePage", component: null },
        ],
      },
      {
        path: "/specializations",
        component: null,
        children: [
          {
            path: "",
            name: "Specializations",
            component: () => import("@/views/Specializations.vue"),
          },
          { path: "/:id", name: "SpecializationDetailsPage", component: null },
        ],
      },
    ],
  },
  {
    path: "/doctor-registration",
    name: "DoctorRegistrationPage",
    component: () => import("@/views/DoctorRegistration.vue"),
  },
  {
    path: "/:404(.*)",
    name: "NotFoundPage",
    component: () => import("@/views/NotFound.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
