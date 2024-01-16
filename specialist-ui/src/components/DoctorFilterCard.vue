<template>
  <v-card variant="plain">
    <v-card-actions>
      <v-select
        v-model="specializationId"
        label="Specialitate"
        :items="specializations"
        item-title="specialization"
        item-value="id"
        variant="solo-filled"
        color="#e80054"
        :flat="true"
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
            style="padding: 12px 24px 0"
          ></v-text-field>

          <v-divider class="mt-2"></v-divider>
        </template>
      </v-select>

      <v-select
        v-model="selectedCity"
        label="Locatie"
        :items="cityClinicsCollection"
        item-title="city"
        item-value="id"
        return-object
        variant="solo-filled"
        color="#e80054"
        :flat="true"
        :hide-details="true"
        style="padding: 12px 24px; width: min-content"
      >
      </v-select>

      <v-select
        v-model="sortId"
        label="Ordonare"
        :items="sortingOptions"
        item-title="name"
        item-value="id"
        variant="solo-filled"
        color="#e80054"
        :flat="true"
        :hide-details="true"
        style="padding: 12px 24px; width: min-content"
      ></v-select>

      <v-text-field
        :focused="true"
        label="Cauta"
        v-model="fullName"
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
import { clinicsService } from "@/services/clinicsService";
import { specializationService } from "@/services/specializationService";

export default {
  data() {
    return {
      cityClinicsCollection: [],
      specializations: [],
      sortingOptions: [
        { id: "", name: "Ordonare" },
        { id: 1, name: "In ordine alfabetică" },
        { id: 2, name: "De la cei mai buni doctori" },
      ],

      selectedCity: { id: "", city: "Toate" },
      fullName: "",
      sortId: "",

      cityId: "",
      clinicId: "",
      specializationId: null,

      specialization: "",

      sortingList: [],

      disableWatcher: true,
    };
  },
  props: ["autocomplete"],
  methods: {
    async findAllCities() {
      const queryOptions = { filters: {}, sortingList: [] };
      const response = await citiesService.filterAndSortList(queryOptions);
      if (response.ok) {
        const text = await response.text();
        if (text) {
          const items = JSON.parse(text);

          for (const item of items) {
            const queryOptions = {
              filters: {
                cityId: item.id,
              },
              sortingList: [],
            };

            this.cityClinicsCollection.push({
              id: item.id,
              city: item.city,
            });

            await this.findAllClinics(queryOptions);
          }

          this.cityClinicsCollection.unshift({ id: "", city: "Toate" });
          console.log(this.cityClinicsCollection);
        }
      }
    },
    async findAllClinics(queryOptions) {
      const response = await clinicsService.filterAndSortList(queryOptions);
      if (response.ok) {
        const text = await response.text();
        if (text) {
          const items = JSON.parse(text);

          for (const item of items) {
            this.cityClinicsCollection.push({
              id: item.id,
              city: item.clinic,
              cityId: item.cityId,
            });
          }
        }
      }
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
              this.specializations.unshift({ id: "", specialization: "Toate" });
            });
        }
      });
    },
    handleFilterUpdate() {
      const filterOptions = {
        filters: {
          specializationId: this.specializationId,
          fullName: this.fullName,
          cityId: this.cityId,
          clinicId: this.clinicId,
        },
        sortingList: this.sortingList,
      };

      this.$emit("filter-updated", filterOptions);
    },
  },
  watch: {
    specializationId: "handleFilterUpdate",
    selectedCity() {
      if (this.selectedCity.cityId) {
        this.clinicId = this.selectedCity.id;
        this.cityId = "";
      } else {
        this.clinicId = "";
        this.cityId = this.selectedCity.id;
      }

      this.handleFilterUpdate();
    },
    fullName: "handleFilterUpdate",
    sortId() {
      if (this.sortId === "") {
        this.sortingList = [];
      } else if (this.sortId === 1) {
        this.sortingList = [
          { column: "firstName", order: "ASC" },
          { column: "lastName", order: "ASC" },
        ];
      } else if (this.sortId === 2) {
        this.sortingList = [{ column: "averageRating", order: "DESC" }];
      }

      this.handleFilterUpdate();
    },
    specialization() {
      this.findAllSpecializations();
    },
  },
  emits: ["filter-updated"],
  created() {
    this.findAllCities();
    this.findAllSpecializations();

    if (this.autocomplete.filters.clinicId !== "") {
      clinicsService
        .findById(this.autocomplete.filters.clinicId)
        .then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              if (text) {
                const item = JSON.parse(text);

                this.selectedCity = {
                  id: item.id,
                  city: item.clinic,
                  cityId: item.cityId,
                };
              }
            });
          }
        });
    }

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
