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
        name: "Clinics",
        component: () => import("@/views/Clinics.vue"),
      },
      {
        path: "/clinics/:id",
        component: import("@/views/ClinicDetailsPage.vue"),
        children: [
          {
            path: "profile",
            name: "ClinicProfile",
            component: () => import("@/views/ClinicProfile.vue"),
          },
          {
            path: "doctors",
            name: "ClinicDoctors",
            component: () => import("@/views/Doctors.vue"),
          },
          {
            path: "specializations",
            name: "ClinicSpecializations",
            component: () => import("@/views/ClinicSpecializations.vue"),
          },
        ],
      },
      {
        path: "/doctors",
        name: "Doctors",
        component: () => import("@/views/Doctors.vue"),
      },
      {
        path: "/doctors/:id",
        name: "DoctorProfilePage",
        component: import("@/views/DoctorProfilePage.vue"),
      },
      {
        path: "/specializations",
        name: "Specializations",
        component: import("@/views/Specializations.vue"),
      },
      {
        path: "/specializations/:id",
        component: import("@/views/DoctorProfilePage.vue"),
        children: [
          {
            path: "doctors",
            name: "",
            component: () => import("@/views/Doctors.vue"),
          },
        ],
      },
      {
        path: "/appointments-dashboard",
        name: "AppointmentsDashboard",
        component: () => import("@/views/AppointmentsDashboard.vue"),
      },
      {
        path: "/medical-investigations",
        name: "MedicalInvestigations",
        component: () => import("@/views/MedicalInvestigations.vue"),
      },
      {
        path: "/appointments",
        name: "AppointmentBookingPage",
        component: () => import("@/views/AppointmentBookingPage.vue"),
      },
      {
        path: "/user-profile",
        name: "UserProfilePage",
        component: () => import("@/views/UserProfilePage.vue"),
      },
    ],
  },
  {
    path: "/confirmation",
    name: "Confirmation",
    component: () => import("@/views/Confirmation.vue"),
  },
  {
    path: "/doctor-registration",
    name: "DoctorRegistrationPage",
    component: () => import("@/views/DoctorRegistration.vue"),
  },
  {
    path: "/login",
    name: "LoginPage",
    component: () => import("@/views/LoginPage.vue"),
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
