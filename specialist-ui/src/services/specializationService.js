export const specializationService = {
  findById,
};

const url = "http://localhost:8080/specializations";

function findById(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/" + id, requestOptions);
}
