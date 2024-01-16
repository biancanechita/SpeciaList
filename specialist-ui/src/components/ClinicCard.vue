<template>
  <v-card
    style="padding: 24px 28px; border: 1px solid rgba(60, 60, 60, 0.12)"
    elevation="0"
  >
    <v-card-title class="title-hover">{{ clinic.clinic }}</v-card-title>
    <v-card-subtitle>{{ clinicCity }}</v-card-subtitle>
    <v-card-text
      >Pacientii au acces la peste 30 de cabinete in regim ambulatoriu si 30 de
      specialitati medicale si echipe medici cu experienta in diagnosticarea,
      tratarea si urmarirea pacientilor cu afectiuni acute sau cronice.
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        @click="goToClinicProfile"
        color="#e80054"
        variant="flat"
        theme="dark"
        >Vezi detalii</v-btn
      >
      <!-- <v-btn color="#f1f1f1" variant="flat">Share</v-btn> -->
    </v-card-actions>
  </v-card>
</template>

<script>
import { citiesService } from "@/services/citiesService";

export default {
  data() {
    return { show: false, clinicCity: "" };
  },
  props: ["clinic"],
  methods: {
    findOne() {
      citiesService.findOne(this.clinic.cityId).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.clinicCity = JSON.parse(text).name;
            }
          });
        }
      });
    },
    goToClinicProfile() {
      this.$router.push({
        name: "ClinicProfile",
        params: { id: this.clinic.id },
      });
    },
  },
  watch: {
    clinic: {
      handler() {
        this.findOne();
      },
      immediate: true,
    },
  },
};
</script>

<style>
.card-hover:hover .title-hover {
  color: #e80054;
}
</style>
