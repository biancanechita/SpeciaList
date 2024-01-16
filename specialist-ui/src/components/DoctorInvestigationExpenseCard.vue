<template>
  <v-row class="d-flex align-center" style="padding-right: 0; line-height: 1.6">
    <v-col>
      <v-list-item style="font-size: 1rem">
        <template v-slot:prepend>
          <v-avatar image="@/assets/doctor.png" rounded="0"></v-avatar>
        </template>
        <a
          :href="'/doctors/' + doctorInvestigationExpense.doctorId"
          class="text-decoration-none"
          style="color: #e80054"
          >Dr. {{ fullName }}</a
        >
        <v-list-item-subtitle>
          <span class="text-caption">{{ clinic.clinic }} </span>
        </v-list-item-subtitle>

        <v-list-item-subtitle>
          <span class="text-caption"
            >{{ doctorInvestigationExpense.price.toFixed(2) }} Lei</span
          >
        </v-list-item-subtitle>
      </v-list-item>
    </v-col>

    <v-spacer></v-spacer>

    <v-col class="d-inline-flex">
      <v-spacer></v-spacer>
      <v-btn
        variant="text"
        prepend-icon="mdi-clock-time-four-outline"
        class="mx-2"
        @click="fetchAvailableTimes"
        color="#213547"
        >Solicita programare</v-btn
      >
    </v-col>
  </v-row>
</template>

<script>
import { doctorService } from "@/services/doctorService";
import { clinicsService } from "@/services/clinicsService";

export default {
  data() {
    return {
      fullName: "",
      clinic: "",
    };
  },
  props: ["doctorInvestigationExpense"],
  methods: {
    getFullName() {
      doctorService
        .getFullName(this.doctorInvestigationExpense.doctorId)
        .then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              this.fullName = text;
            });
          }
        });

      doctorService
        .findById(this.doctorInvestigationExpense.doctorId)
        .then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              const doctor = JSON.parse(text);

              clinicsService.findById(doctor.clinicId).then((response) => {
                if (response.ok) {
                  response.text().then((text) => {
                    if (text) {
                      this.clinic = JSON.parse(text);
                    }
                  });
                }
              });
            });
          }
        });
    },
    fetchAvailableTimes() {
      this.$store.state.queryOptionsAppointments = {
        filters: {
          doctorId: this.doctorInvestigationExpense.doctorId,
        },
        sortingList: [],
      };

      this.$router.push({
        name: "AppointmentBookingPage",
      });
    },
  },
  watch: {
    doctorInvestigationExpense: {
      handler() {
        this.getFullName();
      },
      immediate: true,
    },
  },
};
</script>
