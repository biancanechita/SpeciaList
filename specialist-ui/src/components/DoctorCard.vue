<template>
  <v-card variant="text" color="#734327" style="background-color: white">
    <div class="d-flex justify-space-between">
      <div class="d-flex justify-space-between align-center">
        <v-avatar class="ma-3" size="100">
          <v-img
            src="https://cdni.iconscout.com/illustration/premium/thumb/female-doctor-6608070-5604092.png"
          ></v-img>
        </v-avatar>
        <div>
          <v-card-title>{{
            doctor.firstName + " " + doctor.lastName
          }}</v-card-title>
          <v-card-subtitle>{{
            specialization ? specialization.name : ""
          }}</v-card-subtitle>
          <v-card-actions style="padding: 0 1rem">
            <v-btn
              prepend-icon="mdi-clock-time-four-outline"
              variant="outlined"
              color="#EF67A7"
              >Solicita programare</v-btn
            >
            <v-btn
              prepend-icon="mdi-calendar-multiselect-outline"
              variant="outlined"
              color="#EF67A7"
              >Vezi program</v-btn
            >
          </v-card-actions>
        </div>
      </div>
      <div class="d-flex flex-column justify-center align-center px-10">
        <span class="text-h4">{{
          doctor.averageRating ? doctor.averageRating : 0
        }}</span>
        <span class="text-subtitle-1"
          >{{ doctor.totalReviews ? doctor.totalReviews : 0 }} recenzii</span
        >
      </div>
    </div>
  </v-card>
</template>

<script>
import { specializationService } from "@/services/specializationService";

export default {
  data() {
    return {
      specialization: null,
    };
  },
  props: ["doctor"],
  methods: {
    findById() {
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
    },
  },
  watch: {
    doctor: {
      handler() {
        this.findById();
      },
      deep: true,
    },
  },
  created() {
    this.findById();
  },
};
</script>
