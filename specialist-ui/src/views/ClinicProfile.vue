<template>
  <v-container>
    <v-card style="padding: 32px" elevation="0">
      <v-card-actions style="align-items: stretch">
        <v-col cols="4">
          <v-card-title
            style="
              font-size: 38px;
              font-weight: 600;
              letter-spacing: -0.02em;
              color: #213547;
              white-space: pre-line;
              margin-bottom: 12px;
            "
            >{{ clinic.clinic }}</v-card-title
          >
          <v-card-subtitle>
            {{ clinic.address }}, {{ city.city }}
          </v-card-subtitle>
          <v-card-subtitle>{{ clinic.contactInformation }}</v-card-subtitle>
        </v-col>
        <v-col cols="8" style="height: 640px">
          <div id="mapContainer"></div>
        </v-col>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import { clinicsService } from "@/services/clinicsService";
import { citiesService } from "@/services/citiesService";

export default {
  data() {
    return {
      clinic: {},
      city: {},
    };
  },
  methods: {
    async findById() {
      await clinicsService.findById(this.$route.params.id).then((response) => {
        if (response.ok) {
          response
            .text()
            .then((text) => {
              if (text) {
                this.clinic = JSON.parse(text);
              }
            })
            .then(() => {
              citiesService.findOne(this.clinic.cityId).then((response) => {
                if (response.ok) {
                  response.text().then((text) => {
                    if (text) {
                      this.city = JSON.parse(text);
                    }
                  });
                }
              });
            });
        }
      });
    },
    async injectBingMapsApiScript() {
      var scriptTag = document.createElement("script");
      scriptTag.src =
        "https://www.bing.com/api/maps/mapcontrol?callback=OnLoadBingMapsApi&key=Ahj8QvcZzYwxHjmfjsF1BiGWKdwnq2kXkwxNygT5ieoSxZGeRL7XPxvAv-3QWPe7";
      scriptTag.id = "scriptBingMaps";

      await new Promise((resolve, reject) => {
        scriptTag.onload = resolve;
        scriptTag.onerror = reject;
        document.head.appendChild(scriptTag);
      });
    },
    async setMarker() {
      await this.injectBingMapsApiScript();

      const address = this.clinic.address + "," + this.city.city + ", Romania";

      const geocodeUrl = `https://dev.virtualearth.net/REST/v1/Locations?query=${encodeURIComponent(
        address
      )}&key=Ahj8QvcZzYwxHjmfjsF1BiGWKdwnq2kXkwxNygT5ieoSxZGeRL7XPxvAv-3QWPe7`;

      await fetch(geocodeUrl)
        .then((response) => response.json())
        .then((data) => {
          if (
            data.resourceSets.length > 0 &&
            data.resourceSets[0].resources.length > 0
          ) {
            const location =
              data.resourceSets[0].resources[0].point.coordinates;

            const map = new Microsoft.Maps.Map("#mapContainer", {
              credentials:
                "Ahj8QvcZzYwxHjmfjsF1BiGWKdwnq2kXkwxNygT5ieoSxZGeRL7XPxvAv-3QWPe7",
              center: new Microsoft.Maps.Location(location[0], location[1]),
              zoom: 12,
            });

            const pin = new Microsoft.Maps.Pushpin(map.getCenter());
            map.entities.push(pin);
          } else {
            console.error("Geocoding failed. Unable to locate the address.");
          }
        })
        .catch((error) => {
          console.error("Error geocoding the address:", error);
        });
    },
  },
  created() {
    this.findById();
    this.setMarker();
  },
};
</script>
