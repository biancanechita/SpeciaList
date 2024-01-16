export const userAuthenticationService = {
  login,
  signup,
};

const url = "http://localhost:8089/user-authentication";

function login(userCredentials) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(userCredentials),
  };

  return fetch(url + "/login", requestOptions);
}

function signup(registrationData) {
    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(registrationData),
    };
  
    return fetch(url + "/signup", requestOptions);
  }
