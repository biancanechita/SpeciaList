<template>
  <v-container class="fill-height">
    <v-row
      style="height: 100%; display: flex; align-items: stretch"
      class="py-5"
    >
      <v-col cols="6" style="height: 100%; display: inline">
        <v-row style="height: 100%; padding: 48px">
          <v-card elevation="0">
            <v-list-item class="w-100">
              <template v-slot:prepend>
                <v-avatar image="@/assets/doctor.png" size="100"></v-avatar>
              </template>

              <v-card-title style="font-size: 38px; font-weight: 600"
                >Dr.
                {{ doctor.firstName + " " + doctor.lastName }}</v-card-title
              >

              <v-list-item-title
                style="padding: 0.5rem 1rem; white-space: pre-line"
                >Medic
                {{
                  specialization.specialization + " " + clinic.clinic
                }}</v-list-item-title
              >
            </v-list-item>
            <div style="margin-top: 26px">
              <v-list-item-title style="padding: 0.5rem 1rem"
                >Ofera consultatii in judetele:</v-list-item-title
              >
              <div style="padding: 0.5rem 1rem">
                <v-chip color="#e80054">{{ city.city }}</v-chip>
              </div>

              <v-list-item-title style="padding: 0.5rem 1rem"
                >Recenzii</v-list-item-title
              >

              <v-card-text
                style="
                  padding: 0.5rem 1rem;
                  display: flex;
                  flex-direction: column;
                "
              >
                <v-progress-linear
                  :model-value="doctor.averageRating * 10"
                  color="#e80054"
                  rounded
                  height="7"
                ></v-progress-linear>
                <span style="font-size: 14px; margin-top: 8px; font-weight: 600"
                  >{{ doctor.averageRating }} din 10
                </span>
              </v-card-text>
            </div>
          </v-card>
        </v-row>
      </v-col>

      <v-col cols="6">
        <GuestBookingForm
          :doctor="doctor"
          style="margin-bottom: 24px"
          @appointment-request="scheduleVisitorMode"
        ></GuestBookingForm>
      </v-col>
    </v-row>

    <v-snackbar v-model="snackbar">
      {{ text }}

      <template v-slot:actions>
        <v-btn color="#e80054" variant="text" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import { doctorService } from "@/services/doctorService";
import { specializationService } from "@/services/specializationService";
import { clinicsService } from "@/services/clinicsService";
import { citiesService } from "@/services/citiesService";
import { appointmentService } from "@/services/appointmentService";
import GuestBookingForm from "@/components/GuestBookingForm.vue";

export default {
  data() {
    return {
      doctor: {},
      specialization: "",
      clinic: "",
      city: "",

      snackbar: false,
      text: `Hello, I'm a snackbar`,
    };
  },
  methods: {
    findById() {
      const queryOptions = {
        filters: { id: this.$route.params.id },
        sortingList: [],
      };

      doctorService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.doctor = JSON.parse(text)[0];

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

                      citiesService
                        .findOne(this.clinic.cityId)
                        .then((response) => {
                          response.text().then((text) => {
                            this.city = JSON.parse(text);
                          });
                        });
                    }
                  });
                }
              });
            }
          });
        }
      });
    },
    scheduleVisitorMode(appointment) {
      console.log(appointment);
      appointmentService.scheduleVisitorMode(appointment).then((response) => {
        if (response.ok) {
          this.$router.push({ name: "Confirmation" });
        } else {
          response.text().then((text) => {
            this.dialog = false;
            this.text = JSON.parse(text).message || "Failed";
            this.snackbar = true;
          });
        }
      });
    },
  },
  created() {
    this.findById();
  },
  components: { GuestBookingForm },
};
</script>
