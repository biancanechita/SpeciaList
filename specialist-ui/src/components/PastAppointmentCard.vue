<template>
  <v-card
    class="pa-5"
    style="
      display: flex;
      flex-direction: row;
      border: 1px solid rgba(60, 60, 60, 0.12);
    "
    elevation="0"
  >
    <v-col cols="3">
      <v-list-item-title
        style="font-weight: 500; white-space: pre-line; word-wrap: break-word"
        >{{ formattedDate }}</v-list-item-title
      >
    </v-col>
    <v-divider vertical></v-divider>
    <v-col>
      <v-list-item class="w-100">
        <template v-slot:prepend>
          <v-avatar image="@/assets/doctor.png"></v-avatar>
        </template>

        <v-card-title class="px-0 py-0" style="font-size: 1rem; color: #e80054"
          >Dr. {{ doctor.firstName + " " + doctor.lastName }}</v-card-title
        >

        <v-list-item-title class="text-uppercase" style="font-size: 14px">{{
          specialization.specialization
        }}</v-list-item-title>
      </v-list-item>

      <v-list-item class="pb-3 w-100">
        <v-list-item-subtitle>Diagnostic</v-list-item-subtitle>

        <v-list-item-title style="font-size: 14px"></v-list-item-title>
      </v-list-item>

      <v-list-item class="pb-3 w-100">
        <v-list-item-subtitle>Serviciu</v-list-item-subtitle>

        <v-list-item-title style="font-size: 14px">{{
          medicalInvestigation.investigation
        }}</v-list-item-title>
      </v-list-item>
    </v-col>
  </v-card>
</template>

<script>
import { doctorService } from "@/services/doctorService";
import { specializationService } from "@/services/specializationService";
import { medicalInvestigationService } from "@/services/medicalInvestigationService";

export default {
  data() {
    return {
      doctor: {},
      specialization: {},
      medicalInvestigation: {},
    };
  },
  props: ["appointment"],
  computed: {
    formattedDate() {
      const date = new Date(this.appointment.date);

      const options = {
        day: "numeric",
        month: "long",
        year: "numeric",
      };

      const formattedDate = date.toLocaleDateString("ro-RO", options);
      return formattedDate;
    },
  },
  methods: {
    findById() {
      doctorService.findById(this.appointment.doctorId).then((response) => {
        if (response.ok) {
          response
            .text()
            .then((text) => {
              if (text) {
                this.doctor = JSON.parse(text);
              }
            })
            .then(() => {
              specializationService
                .findById(this.doctor.specializationId)
                .then((response) => {
                  if (response.ok) {
                    response.text().then((text) => {
                      if (text) {
                        this.specialization = JSON.parse(text);
                      }
                    });
                  }
                });

              medicalInvestigationService
                .findById(this.appointment.medicalInvestigationId)
                .then((response) => {
                  if (response.ok) {
                    response.text().then((text) => {
                      if (text) {
                        this.medicalInvestigation = JSON.parse(text);
                      }
                    });
                  }
                });
            });
        }
      });
    },
  },
  created() {
    this.findById();
  },
};
</script>
