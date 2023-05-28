<template>
  <v-card
    variant="text"
    style="background-color: white"
    width="912"
    class="d-inline-flex pa-4"
  >
    <div class="d-inline-flex" style="width: 100%">
      <div class="d-flex align-center">
        <v-avatar class="ma-3" size="60">
          <v-img
            src="https://cdn-icons-png.flaticon.com/512/507/507597.png"
          ></v-img>
        </v-avatar>
        <div style="width: 400px">
          <v-card-title>{{ clinic.name }}</v-card-title>

          <v-expand-transition>
            <div v-show="show">
              <v-card-text>
                Pacientii au acces la peste 30 de cabinete in regim ambulatoriu
                si 30 de specialitati medicale si echipe medici cu experienta in
                diagnosticarea, tratarea si urmarirea pacientilor cu afectiuni
                acute sau cronice.
              </v-card-text>
              <v-card-actions style="padding: 0 1rem">
                <v-btn variant="outlined" color="#FFAA20">Vezi detalii</v-btn>
                <v-btn variant="outlined" color="#FFAA20">Vezi pe harta</v-btn>
              </v-card-actions>
            </div>
          </v-expand-transition>
        </div>
      </div>

      <v-spacer></v-spacer>

      <div class="d-flex flex-column justify-center align-start px-5">
        <span class="text-caption font-weight-bold"
          >Adresa
          <span class="font-weight-regular"> {{ clinic.address }}</span>
        </span>
        <span class="text-caption font-weight-bold"
          >Telefon
          <span class="font-weight-regular">
            {{ clinic.contactInformation }}</span
          >
        </span>
      </div>
    </div>

    <v-spacer></v-spacer>

    <v-card-actions>
      <v-btn
        :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
        elevation="0"
        @click="show = !show"
      ></v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return { show: false };
  },
  props: ["clinic"],
};
</script>
