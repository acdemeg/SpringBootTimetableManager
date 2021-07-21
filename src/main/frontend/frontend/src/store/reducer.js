import updateTimeTables from './reducers/timeTables';
import updateAlert from './reducers/notifications';
import updateProfile from './reducers/profile';
import updateOrders from './reducers/orders';
import getAllUsers from './reducers/users';
import checkUserAuthorization from './reducers/authorization';

const reducer = (state, action) => ({
  timeTablesList: updateTimeTables(state, action),
  notifications: updateAlert(state, action),
  profile: updateProfile(state, action),
  orders: updateOrders(state, action),
  users: getAllUsers(state, action),
  authorization: checkUserAuthorization(state, action),
});

export default reducer;
