export const patientsService = {
  findById,
  facONT,
};

const url = "http://localhost:8081/patients";
const token = sessionStorage.getItem("Token");

function findById(id) {
  console.log("heer");
  console.log(token);

  const requestOptions = {
    method: "GET",
    headers: {
      Authorization: "Bearer " + token,
    },
  };
  return fetch(url + "/" + id, requestOptions);
}

function facONT() {
  console.log(token);
  const requestOptions = {
    method: "POST",
    headers: {
      Authorization: "Bearer " + token,
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      fullName: "a",
      cnp: "a",
      dateOfBirth: "2023-01-01",
      gender: "f",
      phoneNumber: "phoneNumber",
      emailAddress: "emailAddress",
      homeAddress: "homeAddress",
      country: "country",
      county: "county",
      postalCode: "postalCode",
    }),
  };
  return fetch(
    "http://localhost:8081/patients" + "/restricted",
    requestOptions
  );
}
