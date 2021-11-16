import axios from 'axios';

const appServiceData = {
  async getTimeTables() {
    const res = await axios.get(`/api/timetables`);
    return res.data;
  },

  async getUsers() {
    const res = await axios.get(`/api/users`);
    return res.data;
  },

  async getTimeTableById(id) {
    if (!id) return null;
    const res = await axios.get(`/api/timetables/${id}`);
    return res.data;
  },

  async createTimeTable(timeTable) {
    const res = await axios.post(`/api/timetables`, timeTable);
    return res.status === 200;
  },

  async getOrdersOfUser(id) {
    if (!id) return [];
    const res = await axios.get(`/api/orders/${id}`);
    return res.data;
  },

  async getProfileOfUser(id) {
    if (!id) return {};
    const res = await axios.get(`/api/users/${id}`);
    return res.data;
  },
  async updateProfileById(id, data) {
    if (!id) return {};
    const res = await axios.post(`/api/users/${id}`, data);
    return res.data;
  },

  async createOrder(order) {
    const res = await axios.post(`/api/orders`, order);
    return res.status === 200;
  },

  async updateOrder(id, newStatus) {
    const res = await axios.post(`/api/orders/${id}`, { status: newStatus });
    return res.status === 200;
  },
  async removeOrder(id) {
    const res = await axios.delete(`/api/orders/${id}`);
    return res.status === 200;
  },
  async removeUser(id) {
    const res = await axios.delete(`/api/users/${id}`);
    return res.status === 200;
  },
  async removeTimeTable(id) {
    const res = await axios.delete(`/api/timetables/${id}`);
    return res.status === 200;
  },

  async regUser(user) {
    const res = await axios.post(`/api/users/register`, user).catch(err => `${err}`);
    return res.status === 200;
  },

  async logInUser(user) {
    const authStr = btoa(`${user.email}:${user.password}`);
    const config = {
      headers: {
        Authorization: authStr,
      }
    }
    const res = await axios.post(`/api/users/login`, null, config).catch(err => {
      return `${err}`;
    });
    return res.status === 200 ? res.data : false;
  },
};

export default appServiceData;
