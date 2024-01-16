<template>
  <v-container :fluid="true" class="d-flex justify-center">
    <v-row align="center" justify="center">
      <v-col cols="12">
        <v-card class="pa-9" elevation="0">
          <v-window v-model="step">
            <v-window-item :value="1">
              <v-card class="pa-6" elevation="0">
                <v-row align="center" justify="center">
                  <h4
                    style="
                      font-size: 20px;
                      letter-spacing: 0.1em;
                      color: #213547;
                      margin-bottom: 24px;
                    "
                  >
                    PROGRAMARI VIITOARE
                  </h4>
                </v-row>

                <v-row align="center" justify="start">
                  <v-list style="display: flex; flex-wrap: wrap">
                    <v-list-item
                      v-for="(item, index) in futureAppointments"
                      :key="index"
                    >
                      <FutureAppointmentCard
                        :appointment="item"
                        @delete="deleteById"
                      ></FutureAppointmentCard>
                    </v-list-item>
                    <v-list-item
                      ><v-btn
                        @click="
                          this.$router.push({ name: 'AppointmentBookingPage' })
                        "
                        color="#e80054"
                        variant="text"
                      >
                        Adaugă o <br />
                        programare
                      </v-btn></v-list-item
                    >
                  </v-list>
                </v-row>

                <v-row align="center" justify="center">
                  <h4
                    style="
                      font-size: 20px;
                      letter-spacing: 0.1em;
                      color: #213547;
                      margin-top: 60px;
                      margin-bottom: 24px;
                    "
                  >
                    ISTORIC CONSULTATII
                  </h4></v-row
                >

                <v-row align="center" justify="start">
                  <v-list style="display: flex; flex-wrap: wrap">
                    <v-list-item
                      v-for="(item, index) in pastAppointments"
                      :key="index"
                    >
                      <PastAppointmentCard
                        :appointment="item"
                      ></PastAppointmentCard>
                    </v-list-item>
                  </v-list>
                </v-row>
              </v-card>
            </v-window-item>

            <v-window-item :value="2">
              <v-row align="center" justify="center">
                <v-card class="pa-9" style="width: 75%" elevation="0">
                  <v-card-title>Cere o programare</v-card-title>

                  <v-list-item>
                    <v-list-item-title
                      >Programeaza-te din contul tau!</v-list-item-title
                    >
                    <v-list-item-subtitle>
                      Daca ai cont, fa-ti programarea direct din contul tau.
                      Daca nu ai, iti poti face rapid un cont.
                    </v-list-item-subtitle>

                    <template v-slot:append>
                      <div class="justify-self-end">
                        <v-card-actions class="d-flex align-start">
                          <v-btn
                            color="#e80054"
                            variant="flat"
                            theme="dark"
                            @click="this.$router.push({ name: 'LoginPage' })"
                            >Autentificare</v-btn
                          >
                        </v-card-actions>
                      </div>
                    </template>
                  </v-list-item>
                </v-card>

                <v-card class="mt-4 pa-9" style="width: 75%" elevation="0">
                  <v-row>
                    <v-col cols="6">
                      <v-card-title>Programare Online</v-card-title>

                      <v-card-text
                        >Alege varianta rapida si fa-ti o programare online,
                        completand campurile de mai jos. Un reprezentant te va
                        contacta in maximum 24 de ore, pentru a stabili
                        detaliile vizitei tale medicale.</v-card-text
                      >
                    </v-col>

                    <v-col cols="6">
                      <v-text-field
                        label="Nume de familie"
                        variant="outlined"
                      ></v-text-field>
                      <v-text-field
                        label="Prenume"
                        variant="outlined"
                      ></v-text-field>
                      <v-text-field
                        label="Telefon"
                        variant="outlined"
                      ></v-text-field>
                      <v-text-field
                        label="Email"
                        variant="outlined"
                      ></v-text-field>
                      <v-select
                        label="Oras"
                        :items="cities"
                        item-title="city"
                        item-value="id"
                        variant="outlined"
                      >
                        <!-- <template v-slot:prepend-item>
                          <v-text-field
                            v-model="specialization"
                            label="Cauta"
                            append-inner-icon="mdi-magnify"
                            variant="outlined"
                            color="#dc0028"
                            :hide-details="true"
                            style="padding: 12px 24px 0"
                          ></v-text-field>

                          <v-divider class="mt-2"></v-divider>
                        </template> -->
                      </v-select>
                      <v-select
                        label="Clinica"
                        :items="clinics"
                        item-title="clinic"
                        item-value="id"
                        variant="outlined"
                      >
                        <!-- <template v-slot:prepend-item>
                          <v-text-field
                            v-model="specialization"
                            label="Cauta"
                            append-inner-icon="mdi-magnify"
                            variant="outlined"
                            color="#dc0028"
                            :hide-details="true"
                            style="padding: 12px 24px 0"
                          ></v-text-field>

                          <v-divider class="mt-2"></v-divider>
                        </template> -->
                      </v-select>
                      <v-select
                        label="Specialitate"
                        :items="specializations"
                        item-title="specialization"
                        item-value="id"
                        variant="outlined"
                      >
                        <!-- <template v-slot:prepend-item>
                          <v-text-field
                            v-model="specialization"
                            label="Cauta"
                            append-inner-icon="mdi-magnify"
                            variant="outlined"
                            color="#dc0028"
                            :hide-details="true"
                            style="padding: 12px 24px 0"
                          ></v-text-field>

                          <v-divider class="mt-2"></v-divider>
                        </template> -->
                      </v-select>
                      <v-select
                        label="Investigatie"
                        :items="medicalInvestigations"
                        item-title="investigation"
                        item-value="id"
                        variant="outlined"
                      >
                        <!-- <template v-slot:prepend-item>
                          <v-text-field
                            v-model="specialization"
                            label="Cauta"
                            append-inner-icon="mdi-magnify"
                            variant="outlined"
                            color="#dc0028"
                            :hide-details="true"
                            style="padding: 12px 24px 0"
                          ></v-text-field>

                          <v-divider class="mt-2"></v-divider>
                        </template> -->
                      </v-select>

                      <p class="text-caption">
                        Prin completarea formularului de mai sus sunt de acord
                        sa fiu contactat/a de catre Reteaua de sanatate in
                        legatura cu serviciile medicale solicitate.
                      </p>

                      <v-btn
                        block
                        color="#e80054"
                        theme="dark"
                        variant="flat"
                        size="large"
                        class="py-7 my-7"
                        >Programeaza-te</v-btn
                      >
                    </v-col>
                  </v-row>
                </v-card>
              </v-row></v-window-item
            >
          </v-window>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { appointmentService } from "@/services/appointmentService";
import { specializationService } from "@/services/specializationService";
import FutureAppointmentCard from "@/components/FutureAppointmentCard.vue";
import PastAppointmentCard from "@/components/PastAppointmentCard.vue";

export default {
  data() {
    return {
      step: "",

      userId: "",

      cities: [],
      clinics: [],
      medicalInvestigations: [],
      specializations: [],

      items: [],

      pastAppointments: [],
      futureAppointments: [],

      login: "",
    };
  },
  computed: {
    token() {
      return this.$store.getters.getToken;
    },
  },
  methods: {
    findAllSpecializations() {
      const queryOptions = { filters: {}, sortingList: [] };

      specializationService.filterAndSortList(queryOptions).then((response) => {
        if (response.ok) {
          response.text().then((text) => {
            if (text) {
              this.specializations = JSON.parse(text);
              console.log(this.specializations);
            }
          });
        }
      });
    },
    findAllByPatientId() {
      const now = new Date();
      const currentHour = now.getHours() + ":" + now.getMinutes();

      this.futureAppointments = [];
      this.pastAppointments = [];

      appointmentService.findAllByPatientId().then((response) => {
        if (response.ok) {
          response
            .text()
            .then((text) => {
              if (text) {
                this.items = JSON.parse(text);
              }
            })
            .then(() => {
              const today = new Date().toISOString().substr(0, 10);

              this.items.forEach((item) => {
                if (item.date < today) {
                  this.pastAppointments.push(item);
                } else if (item.date === today) {
                  if (item.startTime < currentHour) {
                    this.pastAppointments.push(item);
                  } else {
                    this.futureAppointments.push(item);
                  }
                } else {
                  this.futureAppointments.push(item);
                }
              });
            });
        }
      });
    },
    async deleteById(id) {
      await appointmentService.deleteById(id);
      this.findAllByPatientId();
    },
  },
  watch: {
    token(newToken) {
      console.log("Token has changed:", newToken);
      this.login = !!newToken;
    },
    login() {
      if (this.login) {
        this.step = 1;
      } else {
        this.step = 2;
      }
    },
    step() {
      console.log(this.step);
      if (this.step === 1) {
        this.findAllByPatientId();
      } else {
        this.findAllSpecializations();
      }
    },
  },
  created() {
    this.login = !!sessionStorage.getItem("Token");
    console.log(this.login);
  },
  components: { FutureAppointmentCard, PastAppointmentCard },
};
</script>

<style>
.wrapped-text-button {
  white-space: pre-line;
  word-wrap: break-word;
}
</style>
