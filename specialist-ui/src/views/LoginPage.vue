<template>
  <v-container :fluid="true" class="d-flex justify-center">
    <v-row align="center" justify="center">
      <v-col cols="12" sm="10">
        <v-card style="border: 1px solid rgba(60, 60, 60, 0.12)" elevation="0">
          <v-window v-model="step">
            <v-window-item :value="1">
              <v-row>
                <v-col cols="12" sm="6">
                  <v-card-text class="mt-12">
                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">
                        <v-card-title class="text-center"
                          >Conectați-vă la contul dvs.</v-card-title
                        >
                        <v-card-subtitle
                          class="px-0"
                          style="white-space: pre-line; word-wrap: break-word"
                          >Vă rugăm să introduceți acreditările dvs. de mai jos
                          pentru a accesa contul dvs.</v-card-subtitle
                        >
                      </v-col>
                    </v-row>

                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">
                        <v-text-field
                          label="Email"
                          variant="outlined"
                          v-model="userLogin.email"
                          :rules="requiredRule"
                        ></v-text-field>

                        <v-text-field
                          label="Parola"
                          variant="outlined"
                          v-model="userLogin.password"
                          :rules="requiredRule"
                          :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                          :type="show1 ? 'text' : 'password'"
                          @click:append-inner="show1 = !show1"
                        ></v-text-field>

                        <v-row align="center" justify="center" class="mx-0">
                          <v-col cols="12" sm="6"
                            ><v-checkbox
                              label="Tine-ma logat"
                              color="#e80054"
                              :hide-details="true"
                            ></v-checkbox
                          ></v-col>
                          <v-col cols="12" sm="6">
                            <v-btn variant="text" size="small"
                              >Ai uitat parola?</v-btn
                            >
                          </v-col>
                        </v-row>

                        <v-row class="mb-16">
                          <v-col cols="12">
                            <v-btn
                              :disabled="dialog"
                              :loading="dialog"
                              :block="true"
                              variant="flat"
                              color="#e80054"
                              theme="dark"
                              @click="
                                () => {
                                  if (userLogin.email && userLogin.password) {
                                    login();
                                  } else {
                                    text = 'Field is required';
                                    snackbar = true;
                                  }
                                }
                              "
                              >Autentificare</v-btn
                            >
                          </v-col>
                        </v-row>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
                <v-col
                  cols="12"
                  sm="6"
                  style="
                    background-color: #e80054;
                    border-bottom-left-radius: 300px !important;
                  "
                >
                  <div style="text-align: center; padding: 180px 0">
                    <v-card-text class="text-white">
                      <v-list-item>
                        <v-list-item-title>
                          Nu aveți încă un cont?
                        </v-list-item-title>
                        <v-list-item-subtitle
                          >Alăturați-vă nouă și deblocați o lume de
                          posibilități!</v-list-item-subtitle
                        >
                      </v-list-item>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn variant="flat" @click="step++"
                        >Înscrieți-vă acum</v-btn
                      >
                    </div>
                  </div>
                </v-col>
              </v-row>
            </v-window-item>
            <v-window-item :value="2">
              <v-row>
                <v-col
                  cols="12"
                  sm="6"
                  style="
                    background-color: #e80054;
                    border-bottom-right-radius: 300px !important;
                  "
                >
                  <div style="text-align: center; padding: 180px 0">
                    <v-card-text class="text-white">
                      <v-list-item>
                        <v-list-item-title>
                          Aveți deja un cont?
                        </v-list-item-title>
                        <v-list-item-subtitle
                          >Autentificați-vă aici</v-list-item-subtitle
                        >
                      </v-list-item>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn variant="flat" @click="step--"
                        >Autentificare</v-btn
                      >
                    </div>
                  </div>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-card-text class="mt-12">
                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8" style="padding-bottom: 6px">
                        <v-card-title class="text-center"
                          >Creați un cont nou</v-card-title
                        >
                        <v-card-subtitle
                          class="px-0"
                          style="white-space: pre-line; word-wrap: break-word"
                          >Începeți călătoria dumneavoastră în sănătate cu un
                          cont nou.</v-card-subtitle
                        >
                      </v-col>
                    </v-row>

                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">
                        <v-window v-model="form" style="padding-top: 6px">
                          <v-window-item :value="1">
                            <v-text-field
                              label="Adresa de E-mail"
                              variant="outlined"
                              :rules="requiredRule"
                              v-model="userRegistration.email"
                            ></v-text-field>

                            <v-text-field
                              label="Numar de telefon"
                              variant="outlined"
                              v-model="userRegistration.phoneNumber"
                            ></v-text-field>

                            <v-text-field
                              label="Parola"
                              variant="outlined"
                              v-model="userRegistration.password"
                              :append-inner-icon="
                                show1 ? 'mdi-eye' : 'mdi-eye-off'
                              "
                              :type="show1 ? 'text' : 'password'"
                              @click:append-inner="show1 = !show1"
                            ></v-text-field>

                            <v-row class="mb-16">
                              <v-col cols="12">
                                <v-btn
                                  :block="true"
                                  variant="flat"
                                  color="#e80054"
                                  theme="dark"
                                  @click="
                                    () => {
                                      if (
                                        userRegistration.email &&
                                        userRegistration.phoneNumber &&
                                        userRegistration.password
                                      ) {
                                        form = 2;
                                      }
                                    }
                                  "
                                  >Creați contul</v-btn
                                >
                              </v-col>
                            </v-row>
                          </v-window-item>
                          <v-window-item :value="2">
                            <v-text-field
                              label="Nume de familie"
                              variant="outlined"
                              v-model="userRegistration.personalData.firstName"
                            ></v-text-field>
                            <v-spacer></v-spacer>
                            <v-text-field
                              label="Prenume"
                              variant="outlined"
                              v-model="userRegistration.personalData.lastName"
                            ></v-text-field>

                            <v-text-field
                              label="CNP"
                              variant="outlined"
                              v-model="userRegistration.personalData.cnp"
                            ></v-text-field>

                            <v-row class="mb-16">
                              <v-col cols="12">
                                <v-btn
                                  :block="true"
                                  variant="flat"
                                  color="#e80054"
                                  theme="dark"
                                  @click="
                                    () => {
                                      if (
                                        userRegistration.personalData
                                          .firstName &&
                                        userRegistration.personalData
                                          .lastName &&
                                        userRegistration.personalData.cnp
                                      ) {
                                        signup();
                                      }
                                    }
                                  "
                                  >Trimite datele</v-btn
                                >
                              </v-col>
                            </v-row>
                          </v-window-item>
                        </v-window>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
              </v-row>
            </v-window-item>
          </v-window>
        </v-card>
      </v-col>
    </v-row>

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
import { userAuthenticationService } from "@/services/userAuthenticationService";

export default {
  data: () => ({
    step: 1,
    form: 1,
    show1: false,
    userLogin: {
      email: "",
      password: "",
    },
    userRegistration: {
      email: "",
      phoneNumber: "",
      password: "",
      personalData: { firstName: "", lastName: "", cnp: "" },
    },

    requiredRule: [(v) => !!v || "Field is required"],

    dialog: false,
    snackbar: false,
    text: `Hello, I'm a snackbar`,
  }),
  methods: {
    required(v) {
      return !!v || "Field is required";
    },
    async login() {
      this.dialog = true;

      await userAuthenticationService
        .login(this.userLogin)
        .then((response) => {
          if (response.ok) {
            response.text().then((text) => {
              if (text) {
                sessionStorage.setItem("Token", text);
                console.log(text);
                this.$router.push({ name: "AppointmentsDashboard" });
              }
            });
          } else {
            response.text().then((text) => {
              this.dialog = false;
              this.text = JSON.parse(text).message || "Failed";
              this.snackbar = true;
            });
          }
        })
        .catch((error) => {
          this.text = error;
          this.snackbar = true;
        });
    },
    async signup() {
      await userAuthenticationService
        .signup(this.userRegistration)
        .then((response) => {
          if (response.ok) {
            response.text().then(() => {
              this.userLogin.email = this.userRegistration.email;
              this.userLogin.password = this.userRegistration.password;

              this.login();
            });
          } else {
            response.text().then((text) => {
              this.dialog = false;
              this.text = JSON.parse(text).message || "Failed";
              this.snackbar = true;
            });
          }
        })
        .catch((error) => {
          this.text = error;
          this.snackbar = true;
        });
    },
  },
};
</script>
