export const specializationService = {
  findAll,
  findById,
};

const url = "http://localhost:8080/specializations";

function findAll() {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url, requestOptions);
}

function findById(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/" + id, requestOptions);
}
