export default class ApiService {
  static url = "http://127.0.0.1:9000";
  static async login(data) {
    const url = this.url + "/api/user/login";
    console.log(data);
    const res = await fetch(url, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
      },
    });
    console.log(res);
    return res;
  }

  static async register(data) {
    const url = this.url + "/api/user/register";
    const res = await fetch(url, {
      method: "POST",
      body: JSON.stringify(data),

      headers: {
        "Content-Type": "application/json",
      },
    });

    return res;
  }

  static async updateUser(userId, data) {
    const url = this.url + `/api/user/${userId}/update`;
    const res = await fetch(url, {
      method: "PATCH",
      body: data,
      headers: {
        "Content-Type": "application/json",
      },
    });

    return res;
  }

  static async addColis(userId, data) {
    const url = this.url + "/api/colis/add/" + userId;
    const res = await fetch(url, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
      },
    });
    return res;
  }

  static async getColisById(colisId) {
    const url = this.url + "/api/colis/" + colisId;
    const res = await fetch(url);
    return res;
  }

  static async getColisListByTown(town) {
    const url = this.url + "/api/colis/list/" + town+"/";
    const res = await fetch(url);
    return res;
  }

  static async sendColis(userId, colisId) {
    const url = this.url + `/api/colis/send/${colisId}/${userId}`;
    const res = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });

    return res;
  }

  static async withdDrawColis(userId, colisId) {
    const url = this.url + `/api/colis/withdraw/${colisId}/${userId}`;
    const res = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });

    return res;
  }

  static async markColisAsArrive(userId, colisId) {
    const url = this.url + `/api/colis/arrive/${colisId}/${userId}`;
    const res = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });

    return res;
  }

  static async filterColis(town, from_date, to_date, state) {
    const url = state
      ? this.url +
        `/api/colis/filter/${town}?from_date=${from_date}&to_date=${to_date}&state=${state}`
      : this.url +
        `/api/colis/filter/${town}?from_date=${from_date}&to_date=${to_date}`;

    const res = await fetch(url);

    return res;
  }
}
