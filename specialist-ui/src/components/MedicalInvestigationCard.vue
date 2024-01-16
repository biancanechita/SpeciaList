<template>
  <v-card elevation="0">
    <div style="width: 100%" class="d-flex">
      <v-card-title
        style="font-size: 1rem; font-weight: 400; padding-left: 0"
        class="text-left"
        >{{ medicalInvestigation.investigation }}</v-card-title
      >

      <v-spacer></v-spacer>

      <v-card-actions>
        <v-btn
          :append-icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
          elevation="0"
          @click="show = !show"
          class="text-none text-body-2 font-weight-regular"
          color="#e80054"
        >
          Vezi preturi</v-btn
        >
      </v-card-actions>
    </div>

    <v-expand-transition>
      <div v-show="show">
        <v-list>
          <v-list-item
            v-for="(item, index) in items"
            :key="index"
            style="padding-left: 0"
          >
            <DoctorInvestigationExpenseCard
              :doctor-investigation-expense="item"
            ></DoctorInvestigationExpenseCard>
          </v-list-item>
        </v-list>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script>
import { doctorInvestigationExpenseService } from "@/services/doctorInvestigationExpenseService";
import DoctorInvestigationExpenseCard from "./DoctorInvestigationExpenseCard.vue";

export default {
  data() {
    return {
      items: [],
      queryOptions: null,

      show: false,
    };
  },
  props: ["medicalInvestigation", "initialQueryOptions"],
  methods: {
    filterAndSortList() {
      doctorInvestigationExpenseService
        .filterAndSortList(this.queryOptions)
        .then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              if (text) {
                this.items = JSON.parse(text);
              }
            });
          }
        });
    },
  },
  watch: {
    initialQueryOptions: {
      handler() {
        this.queryOptions = {
          filters: {
            clinicId: this.initialQueryOptions.filters.clinicId,
            investigationId: this.medicalInvestigation.id,
            fullName: "",
          },
          sortingList: [],
        };

        this.filterAndSortList();
      },
      deep: true,
    },
  },
  components: { DoctorInvestigationExpenseCard },
};
</script>
