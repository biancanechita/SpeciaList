<template>
  <v-container>
    <SpecializationFilterCard
      @filter-updated="handleFilterUpdate"
      class="mb-9"
    ></SpecializationFilterCard>
    <v-list style="padding: 16px">
      <v-list-item v-for="(item, index) in items" :key="index">
        <ClinicSpecializationCard
          :specialization="item"
          :index="index"
        ></ClinicSpecializationCard>
      </v-list-item>
    </v-list>
  </v-container>
</template>

<script>
import { specializationService } from "@/services/specializationService";
import ClinicSpecializationCard from "@/components/ClinicSpecializationCard.vue";
import SpecializationFilterCard from "@/components/SpecializationFilterCard.vue";

export default {
  data() {
    return {
      items: [],
    };
  },
  methods: {
    handleFilterUpdate(updatedFilterOptions) {
      specializationService
        .filterAndSortList(updatedFilterOptions)
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
  components: { ClinicSpecializationCard, SpecializationFilterCard },
};
</script>
