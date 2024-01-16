<template>
  <v-card style="border: 1px solid rgba(60, 60, 60, 0.12)" elevation="0">
    <v-card-actions>
      <v-list-item class="w-100">
        <template v-slot:prepend>
          <!-- <v-avatar
            image="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
          ></v-avatar> -->
        </template>

        <v-list-item-title>{{
          specialization.specialization
        }}</v-list-item-title>

        <template v-slot:append>
          <div class="justify-self-end">
            <v-btn
              :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
              @click="show = !show"
            ></v-btn>
          </div>
        </template>
      </v-list-item>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show" class="px-15">
        <v-list>
          <v-list-item v-for="(item, index) in items" :key="index">
            <MedicalInvestigationCard
              :medical-investigation="item"
              :initial-query-options="queryOptions"
            ></MedicalInvestigationCard>
          </v-list-item>
        </v-list>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script>
import { medicalInvestigationService } from "@/services/medicalInvestigationService";
import MedicalInvestigationCard from "./MedicalInvestigationCard.vue";

export default {
  data() {
    return {
      items: [],
      queryOptions: null,

      show: false,
    };
  },
  props: ["specialization", "initialQueryOptions"],
  methods: {
    handleFilterUpdate(updatedFilterOptions) {
      medicalInvestigationService
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
  watch: {
    initialQueryOptions: {
      handler() {
        const updatedFilterOptions = {
          filters: {
            clinicId: this.initialQueryOptions.filters.clinicId,
            specializationId: this.specialization.id,
            fullName: "",
          },
          sortingList: [],
        };

        this.handleFilterUpdate(updatedFilterOptions);
      },
      deep: true,
    },
  },
  components: { MedicalInvestigationCard },
};
</script>
