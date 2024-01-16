<template>
  <v-card variant="plain">
    <v-card-actions>
      <v-select
        v-model="clinicId"
        label="Locatie"
        :items="clinics"
        item-title="clinic"
        item-value="id"
        variant="solo-filled"
        color="#e80054"
        :flat="true"
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

          <v-divider class="mt-2"></v-divider>
        </template>
      </v-select>

      <v-spacer></v-spacer>

      <v-text-field
        :focused="true"
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
import { clinicsService } from "@/services/clinicsService";
import { specializationService } from "@/services/specializationService";

export default {
  data() {
    return {
      clinics: [],
      specializations: [],

      clinicId: "",
      specializationId: null,
      fullName: "",

      city: "",
      specialization: "",
    };
  },
  props: ["autocomplete"],
  methods: {
    findAllClinics() {
      const queryOptions = {
        filters: {
          clinic: this.city,
        },
        sortingList: [],
      };

      clinicsService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response
            .text()
            .then((text) => {
              if (text) {
                this.clinics = JSON.parse(text);
              }
            })
            .then(() => {
              this.clinics.unshift({ id: "", clinic: "Selecteaza un oras" });
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
          id: this.specializationId,
          clinicId: this.clinicId,
          fullName: this.fullName,
        },
        sortingList: [],
      };
      this.$emit("filter-updated", filterOptions);
    },
  },
  watch: {
    clinicId: "handleFilterUpdate",
    specializationId: "handleFilterUpdate",
    fullName: "handleFilterUpdate",
    city() {
      this.findAllClinics();
    },
    specialization() {
      this.findAllSpecializations();
    },
  },
  emits: ["filter-updated"],
  created() {
    this.findAllClinics();
    this.findAllSpecializations();

    this.specializationId = this.autocomplete.filters.id;
    this.clinicId = this.autocomplete.filters.clinicId;
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
