<template>
  <v-card elevation="0">
    <v-card-actions class="py-0">
      <v-select
        v-model="specialization"
        label="Specialitate"
        :items="specializations"
        variant="plain"
        hide-details="true"
        style="padding: 12px 24px 0"
      ></v-select>
      <v-divider vertical></v-divider>

      <v-select
        v-model="city"
        label="Locatie"
        :items="cities"
        variant="plain"
        hide-details="true"
        style="padding: 12px 24px 0"
      ></v-select>
      <v-divider vertical></v-divider>

      <v-text-field
        v-model="fullName"
        label="Nume"
        append-inner-icon="mdi-magnify"
        variant="plain"
        hide-details="true"
        style="padding: 12px 24px 0"
      ></v-text-field>
      <v-divider vertical></v-divider>

      <v-select
        v-model="sort"
        label="Ordonare"
        :items="[
          'Prestabilit',
          'In ordine alfabetică',
          'De la cei mai buni doctori',
        ]"
        variant="plain"
        hide-details="true"
        style="padding: 12px 24px 0"
      ></v-select>
    </v-card-actions>
  </v-card>
</template>

<script>
import { citiesService } from "@/services/citiesService";
import { specializationService } from "@/services/specializationService";

export default {
  data() {
    return {
      cities: [],
      specializations: [],

      city: "Toate",
      fullName: "",
      sort: "Ordonare",
      specialization: "Toate",

      queryOptions: {
        filters: {
          city: "",
          fullName: "",
          specialization: "",
        },
        sortingList: [],
      },
    };
  },
  methods: {
    findAllCities() {
      citiesService.findAll().then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              text = JSON.parse(text);
              text.forEach((item) => {
                this.cities.push(item.name);
              });
            }
          });
        }
      });
    },
    findAllSpecializations() {
      specializationService.findAll().then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              text = JSON.parse(text);
              text.forEach((item) => {
                this.specializations.push(item.name);
              });
            }
          });
        }
      });
    },
  },
  watch: {
    city() {
      this.queryOptions.filters.city = this.city;
    },
    fullName() {
      this.queryOptions.filters.fullName = this.fullName;
    },
    specialization() {
      this.queryOptions.filters.specialization = this.specialization;
    },
    sort() {
      if (this.sort === "In ordine alfabetică") {
        this.queryOptions.sortingList = [
          { column: "firstName", order: "ASC" },
          { column: "lastName", order: "ASC" },
        ];
      } else if (this.sort === "De la cei mai buni doctori") {
        this.queryOptions.sortingList = [
          { column: "averageRating", order: "DESC" },
        ];
      }
    },
    queryOptions: {
      handler() {
        this.$emit("queryOptions", this.queryOptions);
      },
      deep: true,
    },
  },
  emits: ["queryOptions"],
  created() {
    this.findAllCities();
    this.findAllSpecializations();
  },
};
</script>
