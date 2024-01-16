<template>
  <v-container>
    <MedicalInvestigationFilterCard
      :autocomplete="$store.state.queryOptionsInvestigations"
      @filter-updated="handleFilterUpdate"
      class="mb-9"
    ></MedicalInvestigationFilterCard>
    <v-list style="padding: 16px">
      <v-list-item v-for="(item, index) in items" :key="index">
        <SpecializationInvestigationsCard
          :specialization="item"
          :initial-query-options="queryOptions"
        ></SpecializationInvestigationsCard
      ></v-list-item>
    </v-list>
  </v-container>
</template>

<script>
import { specializationService } from "@/services/specializationService";
import SpecializationInvestigationsCard from "@/components/SpecializationInvestigationsCard.vue";
import MedicalInvestigationFilterCard from "@/components/MedicalInvestigationFilterCard.vue";

export default {
  data() {
    return {
      items: [],
      queryOptions: null,
    };
  },
  methods: {
    handleFilterUpdate(updatedFilterOptions) {
      specializationService
        .filterAndSortList(updatedFilterOptions)
        .then((response) => {
          if (response.ok) {
            response
              .text()
              .then((text) => {
                if (text) {
                  this.items = JSON.parse(text);
                }
              })
              .then(() => {
                this.queryOptions = updatedFilterOptions;
              });
          }
        });
    },
  },
  components: {
    SpecializationInvestigationsCard,
    MedicalInvestigationFilterCard,
  },
  created() {
    console.log(this.$store.state.queryOptionsInvestigations);
  },
};
</script>
