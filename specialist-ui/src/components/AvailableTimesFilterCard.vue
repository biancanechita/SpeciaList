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
            style="padding: 12px 24px"
          ></v-text-field>

          <v-divider class="mt-2"></v-divider> </template
      ></v-select>

      <v-select
        v-model="cityId"
        label="Locatie"
        :items="cities"
        item-title="city"
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
        v-model="doctor"
        label="Medic"
        :items="doctors"
        item-title="firstName"
        item-value="id"
        return-object
        variant="solo-filled"
        color="#e80054"
        :flat="true"
        :hide-details="true"
        :disabled="true"
        style="padding: 12px 12px; width: min-content"
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

      <v-spacer></v-spacer>

      <v-text-field
        v-model="tomorrowFormatted"
        append-inner-icon="mdi-calendar"
        label="Incepand cu data"
        variant="solo-filled"
        :flat="true"
        :hide-details="true"
        style="padding: 12px 24px; width: min-content"
      ></v-text-field>

      <v-btn @click="getTodayAndNextDay" style="width: min-content"
        >Ziua urmatoare</v-btn
      >
    </v-card-actions>
  </v-card>
</template>

<script>
import { specializationService } from "@/services/specializationService";
import { citiesService } from "@/services/citiesService";
import { doctorService } from "@/services/doctorService";
import { clinicsService } from "@/services/clinicsService";

export default {
  data() {
    return {
      specializations: [],
      cities: [],
      doctors: [],

      today: null,
      tomorrow: null,

      city: "",
      specialization: "",

      specializationId: "",
      cityId: "",
      doctor: { id: "", firstName: "" },
      currentDate: "",

      queryOptions: {
        filters: {
          cityId: "",
        },
        sortingList: [],
      },

      allowContentUpdate: false,
    };
  },
  props: ["autocomplete"],
  computed: {
    todayFormatted() {
      return this.today.toISOString().substr(0, 10);
    },
    tomorrowFormatted() {
      return this.tomorrow.toISOString().substr(0, 10);
    },
  },
  methods: {
    getTodayAndTomorrowDates() {
      this.today = new Date();
      this.getNextDay();
    },
    getTodayAndNextDay() {
      this.today.setDate(this.today.getDate() + 1);
      this.getNextDay();
    },
    getNextDay() {
      const tomorrow = new Date(this.today);
      tomorrow.setDate(tomorrow.getDate() + 1);

      this.tomorrow = tomorrow;
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
    findAll() {
      let queryOptions = {
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
              this.cities.unshift({ id: "", city: "Selectează un oras" });
            });
        }
      });

      queryOptions = { filters: {}, sortingList: [] };

      doctorService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response
            .text()
            .then((text) => {
              if (text) {
                this.doctors = JSON.parse(text);
              }
            })
            .then(() => {
              this.doctors.unshift({ id: "", city: "Selectează un medic" });
            });
        }
      });
    },
    handleFilterUpdate() {
      if (this.allowContentUpdate) {
        const schedulingParameters = {
          specializationId: this.specializationId,
          cityId: this.cityId,
          doctorId: this.doctor.id,
          dateAfter: this.tomorrowFormatted,
        };

        this.$emit("filter-updated", schedulingParameters);
      }
    },
  },
  watch: {
    today(newValue, oldValue) {
      console.log(oldValue);
      console.log(newValue);
      this.handleFilterUpdate();
    },
    specializationId() {
      if (this.allowContentUpdate) {
        this.doctor.id = "";
      }

      this.handleFilterUpdate();
    },
    cityId() {
      if (this.allowContentUpdate) {
        this.doctor.id = "";
      }

      this.handleFilterUpdate();
    },
    specialization() {
      this.findAllSpecializations();
    },
    city() {
      this.findAll();
    },
    tomorrow(newValue, oldValue) {
      this.handleFilterUpdate();
    },
  },
  emits: ["filter-updated"],
  created() {
    this.findAll();
    this.findAllSpecializations();

    this.getTodayAndTomorrowDates();

    if (this.autocomplete.filters.doctorId !== "") {
      console.log("here?");
      doctorService
        .findById(this.autocomplete.filters.doctorId)
        .then((response) => {
          if (response.ok) {
            response
              .text()
              .then((text) => {
                if (text) {
                  this.doctor = JSON.parse(text);
                  this.specializationId = this.doctor.specializationId;
                }
              })
              .then(() => {
                clinicsService
                  .findById(this.doctor.clinicId)
                  .then((response) => {
                    if (response.ok) {
                      response.text().then((text) => {
                        if (text) {
                          const item = JSON.parse(text);

                          this.allowContentUpdate = true;
                          this.cityId = item.cityId;
                        }
                      });
                    }
                  });
              });
          }
        });
    } else {
      this.allowContentUpdate = true;
    }
  },
};
</script>
