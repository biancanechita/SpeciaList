<template>
  <v-card style="border: 1px solid rgba(60, 60, 60, 0.12)" elevation="0">
    <v-card-actions>
      <v-list-item class="w-100">
        <template v-slot:prepend>
          <v-avatar image="@/assets/doctor.png" rounded="0"></v-avatar>
        </template>

        <v-card-title class="px-0 py-0" style="font-size: 1rem"
          >Dr. {{ doctor.firstName + " " + doctor.lastName }}</v-card-title
        >

        <v-list-item-title style="font-size: 14px">{{
          specialization.specialization + " " + clinic.clinic
        }}</v-list-item-title>

        <v-list-item-subtitle>
          <span style="font-size: 14px">{{ doctor.averageRating }}</span>
          <span style="font-size: 10px">
            / {{ doctor.totalReviews }} recenzii</span
          >
        </v-list-item-subtitle>

        <template v-slot:append>
          <div class="justify-self-end">
            <v-card-actions class="d-flex align-start">
              <v-btn
                size="small"
                prepend-icon="mdi-heart-outline"
                class="awesome"
                @click="fetchAvailableTimes"
                >Solicita programare</v-btn
              >
            </v-card-actions>
          </div>
        </template>
      </v-list-item>
    </v-card-actions>
  </v-card>
</template>

<script>
import gsap from "gsap";
import { specializationService } from "@/services/specializationService";
import { clinicsService } from "@/services/clinicsService";

export default {
  data() {
    return {
      specialization: "",
      clinic: "",
      number: 0,
      tweened: 0,
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

      clinicsService.findById(this.doctor.clinicId).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.clinic = JSON.parse(text);
            }
          });
        }
      });
    },
    fetchAvailableTimes() {
      this.$store.state.queryOptionsAppointments = {
        filters: {
          doctorId: this.doctor.id,
        },
        sortingList: [],
      };

      console.log(this.$store.state.queryOptionsAppointments);

      this.$router.push({
        name: "AppointmentBookingPage",
      });
    },
  },
  watch: {
    doctor: {
      handler() {
        this.findById();
      },
      immediate: true,
      deep: true,
    },
    number(n) {
      gsap.to(this, { duration: 5, tweened: Number(n) || 0 });
    },
  },
  created() {
    if (this.doctor.averageRating > 0.02) {
      this.tweened = this.doctor.averageRating - 0.02;
    }

    this.number = this.doctor.averageRating;
  },
};
</script>

<style scoped>
.awesome {
  border: 1px solid #e80054;
  color: #e80054;
}

.awesome:hover {
  /* background-color: #73abfe; */
  background-color: #e80054;
  color: white;
}
</style>
