export const medicalInvestigationService = {
  filterAndSortList,
  findAllByDoctorId,
  findAllBySpecializationId,
  findById,
};

const url = "http://localhost:8089/medical-investigations";

function filterAndSortList(queryOptions) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(queryOptions),
  };
  return fetch(url + "/filter-and-sort-list", requestOptions);
}

function findAllByDoctorId(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(
    url + "/find-all-by-doctor-id/" + id,
    requestOptions
  );
}

function findAllBySpecializationId(specializationId) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(
    url + "/find-all-by-specialization-id/" + specializationId,
    requestOptions
  );
}

function findById(id) {
  const requestOptions = {
    method: "GET",
  };
  return fetch(url + "/" + id, requestOptions);
}
