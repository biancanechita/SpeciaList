<template>
  <div class="d-flex flex-column align-center">
    <v-list lines="one" style="background-color: #f7f7f7">
      <v-list-item v-for="(item, index) in clinics" :key="index">
        <div class="d-flex flex-column">
          <span class="text-center text-h5">{{ item.city.name }}</span>
          <v-list lines="one" style="background-color: #f7f7f7">
            <v-list-item
              v-for="(item, index) in item.clinics"
              :key="index"
              style="background-color: #f7f7f7"
            >
              <ClinicCard :clinic="item"></ClinicCard>
            </v-list-item>
          </v-list>
        </div>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { citiesService } from "@/services/citiesService";
import { clinicsService } from "@/services/clinicsService";
import ClinicCard from "@/components/ClinicCard.vue";

export default {
  data() {
    return {
      clinics: [],
    };
  },
  methods: {
    findAllCities() {
      citiesService.findAll().then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.findAllClinics(JSON.parse(text));
            }
          });
        }
      });
    },
    findAllClinics(cities) {
      cities.forEach((city) => {
        clinicsService.findByCityId(city.id).then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              if (text) {
                const clinics = JSON.parse(text);

                if (clinics.length > 0) {
                  this.clinics.push({
                    city: city,
                    clinics: clinics,
                  });
                }
              }
            });
          }
        });
      });
    },
  },
  created() {
    this.findAllCities();
  },
  components: { ClinicCard },
};
</script>
