<template>
  <v-container>
    <AvailableTimesFilterCard
      :autocomplete="$store.state.queryOptionsAppointments"
      @filter-updated="fetchAvailableTimes"
      class="mb-9"
    ></AvailableTimesFilterCard>
    <v-list style="display: flex; flex-wrap: wrap">
      <h3 v-if="usageInstructions" style="color: #213547; font-weight: 400;">
        Selecteaza de mai sus detaliile programarii pentru a vedea intervalele
        disponibile.
      </h3>
      <v-list-item
        v-for="(item, index) in items"
        :key="index"
        style="width: 33%; margin-bottom: 18px"
      >
        <AvailableTimesCard :available-times="item"></AvailableTimesCard>
      </v-list-item>
    </v-list>

    <v-dialog v-model="dialog" :scrim="false" persistent width="auto">
      <v-card style="padding: 12px 20px">
        <v-card-text>
          Vă rugăm să așteptați
          <v-progress-linear indeterminate class="mt-2"></v-progress-linear>
        </v-card-text>
      </v-card>
    </v-dialog>

    <v-snackbar v-model="snackbar">
      {{ text }}

      <template v-slot:actions>
        <v-btn color="#e80054" variant="text" @click="snackbar = false">
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import { appointmentService } from "@/services/appointmentService";
import AvailableTimesCard from "@/components/AvailableTimesCard.vue";
import AvailableTimesFilterCard from "@/components/AvailableTimesFilterCard.vue";

export default {
  data() {
    return {
      items: [],
      usageInstructions: true,

      dialog: false,

      snackbar: false,
      text: `Hello, I'm a snackbar`,
    };
  },
  methods: {
    fetchAvailableTimes(updatedFilterOptions) {
      this.dialog = true;

      if (
        updatedFilterOptions.specializationId === "" ||
        updatedFilterOptions.cityId === ""
      ) {
        this.usageInstructions = true;
        this.dialog = false;
      } else {
        this.usageInstructions = false;

        appointmentService
          .fetchAvailableTimes(updatedFilterOptions)
          .then((response) => {
            if (response.ok) {
              response.text().then((text) => {
                if (text) {
                  this.items = JSON.parse(text);
                  this.dialog = false;
                }
              });
            } else {
              response.text().then((text) => {
                this.dialog = false;
                this.text = JSON.parse(text).message || "Failed";
                this.snackbar = true;
              });
            }
          });
      }
    },
  },
  created() {
    const login = !!sessionStorage.getItem("Token");

    if (!login) {
      this.$router.push({ name: "DoctorProfilePage", params: { id: 1 } });
    }
  },
  components: {
    AvailableTimesCard,
    AvailableTimesFilterCard,
  },
};
</script>
