<template>
  <v-card variant="plain">
    <v-card-actions>
      <v-select
        v-model="cityId"
        label="Locatie"
        :items="cities"
        item-title="city"
        item-value="id"
        variant="solo-filled"
        :flat="true"
        color="#e80054"
        :hide-details="true"
        style="padding: 12px 24px; width: min-content"
      >
        <template v-slot:prepend-item>
          <v-text-field
            v-model="city"
            label="Cauta"
            append-inner-icon="mdi-magnify"
            variant="outlined"
            color="#e80054"
            :hide-details="true"
            style="padding: 12px 24px"
          ></v-text-field>

          <v-divider class="mt-2"></v-divider>
        </template>
      </v-select>

      <v-select
        v-model="specializationId"
        label="Specialitate"
        :items="specializations"
        item-title="specialization"
        item-value="id"
        variant="solo-filled"
        :flat="true"
        color="#e80054"
        :hide-details="true"
        style="padding: 12px 24px; width: min-content"
      >
        <template v-slot:prepend-item>
          <v-text-field
            v-model="specialization"
            label="Cauta"
            append-inner-icon="mdi-magnify"
            variant="outlined"
            color="#e80054"
            :hide-details="true"
            style="padding: 12px 24px"
          ></v-text-field>

          <v-divider class="mt-2"></v-divider> </template
      ></v-select>

      <v-spacer></v-spacer>

      <v-text-field
        :focused="true"
        v-model="name"
        label="Nume"
        append-inner-icon="mdi-magnify"
        variant="outlined"
        color="#e80054"
        :hide-details="true"
        style="padding: 12px 24px"
      ></v-text-field>
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

      city: "",
      specialization: "",
      name: "",

      cityId: "",
      specializationId: null,

      queryOptions: {
        filters: {
          cityId: "",
        },
        sortingList: [],
      },
    };
  },
  props: ["autocomplete"],
  methods: {
    findAllCities() {
      const queryOptions = {
        filters: {
          city: this.city,
        },
        sortingList: [],
      };

      citiesService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response
            .text()
            .then((text) => {
              if (text) {
                this.cities = JSON.parse(text);
              }
            })
            .then(() => {
              this.cities.unshift({ id: "", city: "Selecteaza un oras" });
            });
        }
      });
    },
    findAllSpecializations() {
      const queryOptions = {
        filters: {
          specialization: this.specialization,
        },
        sortingList: [],
      };

      specializationService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response
            .text()
            .then((text) => {
              if (text) {
                this.specializations = JSON.parse(text);
              }
            })
            .then(() => {
              this.specializations.unshift({
                id: "",
                specialization: "Selecteaza o specialitate",
              });
            });
        }
      });
    },
    handleFilterUpdate() {
      const filterOptions = {
        filters: {
          cityId: this.cityId,
          specializationId: this.specializationId,
          clinic: this.name,
        },
        sortingList: [{ column: "clinic", order: "ASC" }],
      };
      console.log(filterOptions);
      this.$emit("filter-updated", filterOptions);
    },
  },
  watch: {
    cityId: "handleFilterUpdate",
    specializationId: "handleFilterUpdate",
    specialization() {
      this.findAllSpecializations();
    },
    city() {
      this.findAllCities();
    },
    name: "handleFilterUpdate",
  },
  emits: ["filter-updated"],
  created() {
    this.findAllCities();
    this.findAllSpecializations();

    this.specializationId = this.autocomplete.filters.specializationId;
  },
};
</script>

<style>
.v-select__selection-text {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
