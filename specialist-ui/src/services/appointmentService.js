export const appointmentService = {
  fetchAvailableTimes,
  findAllByPatientId,
  deleteById,
  save,
  scheduleVisitorMode,
};

const url = "http://localhost:8089/appointments";
const token = sessionStorage.getItem("Token");

function fetchAvailableTimes(schedulingParameters) {
  const requestOptions = {
    method: "POST",
    headers: {
      Authorization: "Bearer " + sessionStorage.getItem("Token"),
      "Content-Type": "application/json",
    },
    body: JSON.stringify(schedulingParameters),
  };
  return fetch(url + "/fetch-available-times", requestOptions);
}

function findAllByPatientId() {
  const requestOptions = {
    method: "GET",
    headers: {
      Authorization: "Bearer " + sessionStorage.getItem("Token"),
    },
  };
  return fetch(url, requestOptions);
}

function deleteById(id) {
  const requestOptions = {
    method: "DELETE",
    headers: {
      Authorization: "Bearer " + sessionStorage.getItem("Token"),
    },
  };
  return fetch(url + "/" + id, requestOptions);
}

function save(appointment) {
  const requestOptions = {
    method: "POST",
    headers: {
      Authorization: "Bearer " + sessionStorage.getItem("Token"),
      "Content-Type": "application/json",
    },
    body: JSON.stringify(appointment),
  };
  return fetch(url + "/restricted", requestOptions);
}

function scheduleVisitorMode(appointment) {
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(appointment),
  };
  console.log(JSON.stringify(appointment));
  return fetch(url, requestOptions);
}
