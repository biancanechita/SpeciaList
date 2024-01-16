<template>
  <v-card
    class="pa-5 text-center"
    elevation="0"
    style="border: 1px solid rgba(60, 60, 60, 0.12)"
  >
    <div class="text-left d-flex">
      <v-icon size="20" color="#e80054"
        >mdi-calendar-multiselect-outline</v-icon
      >
      <v-spacer></v-spacer>
      <v-dialog v-model="dialog" persistent width="512">
        <template v-slot:activator="{ props }">
          <v-icon v-bind="props" size="20" color="#e80054">mdi-close</v-icon>
        </template>
        <v-card style="padding: 24px">
          <v-card-title style="border-bottom: 1px solid rgba(60, 60, 60, 0.12)"
            >Anulează programare</v-card-title
          >
          <v-card-text>
            Ești sigur că vrei să renunti la această programare? Dacă te
            răzgândești ulterior, va trebui să efectuezi din nou programarea.
          </v-card-text>
          <v-card-actions>
            <v-btn @click="dialog = !dialog" color="#e80054">Anulează</v-btn>
            <v-spacer></v-spacer>
            <v-btn @click="cancel" variant="flat" color="#e80054" theme="dark"
              >Trimite</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <v-list-item class="mb-4">
      <v-list-item-title style="font-weight: 500">{{
        formattedDate
      }}</v-list-item-title>
      <v-list-item-subtitle style="color: #e80054; margin-top: 8px">{{
        appointment.startTime.substr(0, 5)
      }}</v-list-item-subtitle>
    </v-list-item>

    <v-card-subtitle class="mb-4">{{ clinic.clinic }}</v-card-subtitle>

    <v-divider class="mb-4"></v-divider>

    <v-avatar image="@/assets/doctor.png" class="mb-1"></v-avatar>

    <v-card-title style="font-size: 18px; line-height: 20px; color: #e80054"
      >Dr. {{ doctor.firstName + " " + doctor.lastName }}</v-card-title
    >
    <v-card-subtitle class="text-uppercase">{{
      specialization.specialization
    }}</v-card-subtitle>
  </v-card>
</template>

<script>
import { doctorService } from "@/services/doctorService";
import { clinicsService } from "@/services/clinicsService";
import { specializationService } from "@/services/specializationService";

export default {
  data() {
    return {
      doctor: {},
      clinic: {},
      specialization: {},

      dialog: false,
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
              clinicsService.findById(this.doctor.clinicId).then((response) => {
                if (response.ok) {
                  response.text().then((text) => {
                    if (text) {
                      this.clinic = JSON.parse(text);
                    }
                  });
                }
              });

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
            });
        }
      });
    },
    cancel() {
      this.dialog = false;
      this.$emit("delete", this.appointment.id);
    },
  },
  emits: ["delete"],
  created() {
    this.findById();
  },
};
</script>
