<template>
  <v-card
    style="padding: 48px; border: 1px solid rgba(60, 60, 60, 0.12)"
    elevation="0"
  >
    <!-- <v-select
      v-model="clinic"
      label="Locatie *"
      :items="[clinic]"
      item-title="clinic"
      item-value="id"
      variant="outlined"
      :disabled="true"
    ></v-select>
    <v-select
      v-model="specialization"
      label="Specialitate *"
      :items="[specialization]"
      item-title="specialization"
      item-value="id"
      variant="outlined"
      :disabled="true"
    ></v-select> -->
    <v-select
      label="Investigatie"
      :items="investigations"
      item-title="investigation"
      item-value="id"
      return-object
      variant="outlined"
      v-model="investigation"
    ></v-select>
    <v-text-field
      v-model="firstName"
      label="Nume *"
      variant="outlined"
      :rules="requiredRule"
    ></v-text-field>
    <v-text-field
      v-model="lastName"
      label="Prenume *"
      variant="outlined"
      :rules="requiredRule"
    ></v-text-field>
    <v-text-field
      v-model="phoneNumber"
      label="Telefon *"
      variant="outlined"
      :rules="requiredRule"
    ></v-text-field>
    <v-text-field
      v-model="email"
      label="Email *"
      variant="outlined"
      :rules="requiredRule"
    ></v-text-field>

    <p class="text-caption">
      Prin completarea formularului de mai sus sunt de acord sa fiu contactat/a
      de catre Reteaua de sanatate in legatura cu serviciile medicale
      solicitate.
    </p>

    <v-btn
      block
      color="#e80054"
      theme="dark"
      variant="flat"
      size="large"
      class="py-7 my-4"
      @click="
        () => {
          if (email && firstName && lastName && phoneNumber) {
            scheduleVisitorMode();
          } else {
            text = 'Field is required';
            snackbar = true;
          }
        }
      "
      >Programeaza-te</v-btn
    >

    <p class="text-caption">
      Acest site este protejat de reCAPTCHA si se aplica Politica de
      confidentialitate si Termeni si conditii.
    </p>
  </v-card>

  <v-snackbar v-model="snackbar">
    {{ text }}

    <template v-slot:actions>
      <v-btn color="#e80054" variant="text" @click="snackbar = false">
        Close
      </v-btn>
    </template>
  </v-snackbar>
</template>

<script>
import { clinicsService } from "@/services/clinicsService";
import { specializationService } from "@/services/specializationService";
import { medicalInvestigationService } from "@/services/medicalInvestigationService";

export default {
  data() {
    return {
      clinic: {},
      specialization: {},

      investigation: { id: "", investigation: "Selecteaza o investigatie" },
      investigations: [],

      firstName: "",
      lastName: "",
      phoneNumber: "",
      email: "",

      requiredRule: [(v) => !!v || "Field is required"],

      snackbar: false,
      text: `Hello, I'm a snackbar`,
    };
  },
  props: ["doctor"],
  methods: {
    scheduleVisitorMode() {
      const appointment = {
        doctorId: this.doctor.id,
        medicalInvestigationId: this.investigation.id,
        firstName: this.firstName,
        lastName: this.lastName,
        phoneNumber: this.phoneNumber,
        email: this.email,
      };

      console.log(appointment);

      this.$emit("appointmentRequest", appointment);
    },
  },
  watch: {
    doctor() {
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

      medicalInvestigationService
        .findAllByDoctorId(this.doctor.id)
        .then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              if (text) {
                this.investigations = JSON.parse(text);
                console.log(this.investigations);
              }
            });
          }
        });
    },
  },
  emits: ["appointmentRequest"],
};
</script>
