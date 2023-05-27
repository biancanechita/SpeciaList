export const doctorService = {
  findAll,
};

const url = "http://localhost:8080/doctors";

function findAll() {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url, requestOptions);
}
