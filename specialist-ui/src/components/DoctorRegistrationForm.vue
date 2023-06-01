<template>
  <v-form @submit.prevent="submitForm" ref="form">
    <v-text-field
      v-model="doctor.firstName"
      :rules="rules"
      label="First Name"
    ></v-text-field>

    <v-text-field
      v-model="doctor.lastName"
      :rules="rules"
      label="Last Name"
    ></v-text-field>

    <v-select
      v-model="doctor.specializationId"
      :rules="rules"
      label="Specialitate"
      :items="specializations"
      item-title="name"
      item-value="id"
    >
      <template v-slot:prepend-item>
        <v-text-field
          v-model="specialization"
          label="Cauta"
          append-inner-icon="mdi-magnify"
        ></v-text-field>

        <v-divider class="mt-2"></v-divider>
      </template>
    </v-select>

    <v-text-field
      v-model="doctor.contactInformation"
      :rules="rules"
      label="Contact Information"
    ></v-text-field>

    <v-textarea v-model="doctor.bio" label="Bio"></v-textarea>

    <v-select
      v-model="doctor.clinicId"
      :rules="rules"
      label="Locatie"
      :items="clinics"
      item-title="name"
      item-value="id"
    >
      <template v-slot:prepend-item>
        <v-text-field
          v-model="clinic"
          label="Cauta"
          append-inner-icon="mdi-magnify"
        ></v-text-field>

        <v-divider class="mt-2"></v-divider>
      </template>
    </v-select>

    <v-btn type="submit" class="mt-2">Submit</v-btn>
  </v-form>
</template>

<script>
import { clinicsService } from "@/services/clinicsService";
import { doctorService } from "@/services/doctorService";
import { specializationService } from "@/services/specializationService";

export default {
  data() {
    return {
      clinics: [],
      specializations: [],

      rules: [(v) => !!v || "Item is required"],

      doctor: {
        firstName: "",
        lastName: "",
        specializationId: null,
        contactInformation: "",
        bio: "",
        clinicId: null,
      },

      clinic: "",
      specialization: "",
    };
  },
  methods: {
    findAll() {
      clinicsService.findAll().then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.clinics = JSON.parse(text);
            }
          });
        }
      });

      specializationService.findAll().then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.specializations = JSON.parse(text);
            }
          });
        }
      });
    },
    async submitForm() {
      const { valid } = await this.$refs.form.validate();

      if (valid) {
        // Form validation succeeded, submit the form
        doctorService.save(this.doctor).then((response) => {
          if (response.ok) {
            alert("success");
          }
        });
      } else {
        // Form validation failed, do not submit the form
        // Display error messages or handle the failure
      }
    },
  },
  watch: {
    "doctor.clinicId"() {
      this.clinic = "";
    },
    clinic() {
      const queryOptions = {
        filters: {
          name: this.clinic,
        },
        sortingList: [],
      };

      clinicsService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.clinics = JSON.parse(text);
            }
          });
        }
      });
    },
    "doctor.specializationId"() {
      this.specialization = "";
    },
    specialization() {
      const queryOptions = {
        filters: {
          name: this.specialization,
        },
        sortingList: [],
      };

      specializationService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.specializations = JSON.parse(text);
            }
          });
        }
      });
    },
  },
  created() {
    this.findAll();
  },
};
</script>
