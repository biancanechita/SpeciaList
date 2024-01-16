<template>
  <v-container class="fill-height">
    <v-card
      style="
        border: 1px solid rgba(60, 60, 60, 0.12);
        padding: 48px;
        width: 100%;
      "
      elevation="0"
    >
      <v-card-text>
        <v-row>
          <v-col
            style="display: flex; justify-content: center; align-items: center"
          >
            <v-avatar image="@/assets/doctor.png" size="150"></v-avatar>
          </v-col>
          <v-col cols="8">
            <v-row>
              <v-col cols="6">
                <v-text-field
                  label="Nume de familie"
                  v-model="patient.firstName"
                  variant="outlined"
                  :disabled="true"
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  label="Prenume"
                  variant="outlined"
                  v-model="patient.lastName"
                  :disabled="true"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field
                  label="CNP"
                  variant="outlined"
                  v-model="patient.cnp"
                  :disabled="true"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="6">
            <v-text-field
              variant="outlined"
              v-model="patient.dateOfBirth"
              label="Email"
            ></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-text-field
              label="Numar de telefon"
              variant="outlined"
              v-model="patient.gender"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="6">
            <v-text-field
              variant="outlined"
              v-model="patient.dateOfBirth"
              label="Data nasterii"
            ></v-text-field>
          </v-col>
          <v-col cols="6">
            <v-text-field
              label="Sex"
              variant="outlined"
              v-model="patient.gender"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-actions style="padding: 0px 1rem 36px">
        <v-btn
          color="#e80054"
          variant="outlined"
          @click="
            () => {
              this.$router.go(-1);
            }
          "
        >
          Anulează
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn color="#e80054" variant="flat" theme="dark" @click="save">
          Salvează
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import { patientsService } from "@/services/patientsService";

export default {
  data() {
    return { patient: {} };
  },
  methods: {
    findById() {
      patientsService
        .findById(sessionStorage.getItem("userId"))
        .then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              this.patient = JSON.parse(text);
              console.log(this.patient);
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
