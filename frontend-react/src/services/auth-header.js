export default function authHeader() {

    //In the case we access protected resources, the HTTP request needs Authorization header. Hence we also pass JWT in the header for every http request
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.accessToken) {
      return { Authorization: 'Bearer ' + user.accessToken };
    } else {
      return {};
    }
  }