<template>
  <v-card
    style="padding: 28px 24px; border: 1px solid rgba(60, 60, 60, 0.12)"
    elevation="0"
  >
    <v-list-item class="w-100">
      <template v-slot:prepend>
        <v-avatar image="@/assets/doctor.png" rounded="0"></v-avatar>
      </template>

      <v-card-title class="px-0" style="color: #e80054"
        >Dr. {{ doctor.firstName + " " + doctor.lastName }}</v-card-title
      >

      <v-list-item-title class="text-uppercase" style="font-size: 12px">{{
        specialization.specialization
      }}</v-list-item-title>

      <v-list-item-subtitle>
        <span style="font-size: 14px">{{ doctor.averageRating }}</span>
        <span style="font-size: 10px">
          / {{ doctor.totalReviews }} recenzii</span
        >
      </v-list-item-subtitle>
    </v-list-item>

    <v-list-item class="py-3 w-100">
      <v-list-item-subtitle>Locatie</v-list-item-subtitle>

      <v-list-item-title style="font-size: 14px">{{
        clinic.clinic
      }}</v-list-item-title>
    </v-list-item>

    <v-list-item class="w-100">
      <v-list-item-subtitle>Disponibilitate</v-list-item-subtitle>

      <v-list style="display: flex; flex-wrap: wrap" class="py-0">
        <v-list-item
          v-for="(item, index) in availableTimes.startTimes.slice(0, 3)"
          :key="index"
          class="pl-0 py-0"
        >
          <v-btn
            size="small"
            color="#e80054"
            variant="tonal"
            @click="save(item.substr(0, 5))"
            >{{ item.substr(0, 5) }}</v-btn
          >
        </v-list-item>
      </v-list>
    </v-list-item>

    <v-dialog v-model="dialog" persistent width="1024">
      <template v-slot:activator="{ props }">
        <v-btn
          v-bind="props"
          class="text-none text-caption"
          variant="plain"
          append-icon="mdi-arrow-right"
          >Vezi toate orele disponibile</v-btn
        >
      </template>
      <v-card>
        <v-list-item style="padding: 32px 40px 10px">
          <template v-slot:prepend>
            <v-avatar image="@/assets/doctor.png" rounded="0"></v-avatar>
          </template>

          <v-card-title style="padding-left: 0; padding-bottom: 0"
            >Dr. {{ doctor.firstName + " " + doctor.lastName }}</v-card-title
          >

          <v-list-item-title class="text-uppercase text-caption">{{
            specialization.specialization
          }}</v-list-item-title>

          <v-list-item-subtitle class="text-caption">
            <span><b>Nota</b> {{ doctor.averageRating }}</span>
          </v-list-item-subtitle>
        </v-list-item>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                  label="Locatia"
                  v-model="clinic.clinic"
                  variant="outlined"
                  color="#e80054"
                  :disabled="true"
                ></v-text-field>
              </v-col>

              <v-col cols="6">
                <v-text-field
                  label="Ziua programarii"
                  variant="outlined"
                  v-model="availableTimes.date"
                  :disabled="true"
                  color="#e80054"
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-select
                  :items="availableTimes.startTimes"
                  v-model="startTime"
                  label="Ora programarii"
                  variant="outlined"
                  color="#e80054"
                ></v-select>
              </v-col>
              <v-col cols="12">
                <v-select
                  :items="medicalInvestigations"
                  item-title="investigation"
                  item-value="id"
                  v-model="medicalInvestigationId"
                  label="Alege un serviciu"
                  variant="outlined"
                  color="#e80054"
                ></v-select>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions style="padding: 0px 36px 36px">
          <v-btn color="#e80054" variant="outlined" @click="dialog = false">
            Anulează
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="#e80054" variant="flat" theme="dark" @click="save">
            Programează-te
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import { doctorService } from "@/services/doctorService";
import { specializationService } from "@/services/specializationService";
import { clinicsService } from "@/services/clinicsService";
import { appointmentService } from "@/services/appointmentService";
import { medicalInvestigationService } from "@/services/medicalInvestigationService";

export default {
  data() {
    return {
      dialog: false,
      doctor: {},
      specialization: {},
      clinic: {},
      medicalInvestigations: [],

      startTime: null,
      medicalInvestigationId: null,
    };
  },
  props: ["availableTimes"],
  methods: {
    findById() {
      doctorService.findById(this.availableTimes.doctorId).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.doctor = JSON.parse(text);
            }
          });
        }
      });
    },
    async save() {
      const appointment = {
        patientId: sessionStorage.getItem("userId"),
        doctorId: this.doctor.id,
        date: this.availableTimes.date,
        startTime: this.startTime,
        medicalInvestigationId: this.medicalInvestigationId,
      };

      appointmentService
        .save(appointment)
        .then((response) => {
          if (response.ok) {
            // this.$router.go(-1);
            this.dialog = false;
            this.$router.push({ name: "AppointmentsDashboard" });
          }
        })
        .then(() => {});
    },
  },
  watch: {
    availableTimes() {
      this.findById();
    },
    doctor: {
      handler() {
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
      deep: true,
    },
    dialog() {
      if (this.dialog) {
        medicalInvestigationService
          .findAllByDoctorId(this.doctor.id)
          .then((response) => {
            if (response.ok) {
              response.text().then((text) => {
                if (text) {
                  this.medicalInvestigations = JSON.parse(text);
                  console.log(this.medicalInvestigations);
                }
              });
            }
          });
      }
    },
  },
  created() {
    this.findById();
  },
};
</script>
