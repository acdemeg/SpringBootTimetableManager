import {
    actionsEnum,
    messages,
    orderStatusEnum,
    scenesEnum,
    timeTableTypeEnum,
    typeAlertEnum,
    typeModalEnum,
} from '../constants';
import appServiceData from '../App/appServiceData';

const TIME_TABLE_LOADED = newTimeTable => ({
  type: actionsEnum.TIME_TABLE_LOADED,
  payload: newTimeTable,
});

const TIME_TABLE_REQUESTED = () => ({
  type: actionsEnum.TIME_TABLE_REQUESTED,
});

const TIME_TABLE_ERROR = error => ({
  type: actionsEnum.TIME_TABLE_ERROR,
  payload: error,
});

const TIME_TABLES_LOADED = newTimeTables => ({
  type: actionsEnum.TIME_TABLES_LOADED,
  payload: newTimeTables,
});

const TIME_TABLES_REQUESTED = () => ({
  type: actionsEnum.TIME_TABLES_REQUESTED,
});

const TIME_TABLES_ERROR = error => ({
  type: actionsEnum.TIME_TABLES_ERROR,
  payload: error,
});

const PROFILE_ERROR = error => ({
  type: actionsEnum.PROFILE_ERROR,
  payload: error,
});

const PROFILE_LOADED = profile => ({
  type: actionsEnum.PROFILE_LOADED,
  payload: profile,
});

const ORDERS_REQUESTED = () => ({
  type: actionsEnum.ORDERS_REQUESTED,
});

const ORDERS_LOADED = newOrders => ({
  type: actionsEnum.ORDERS_LOADED,
  payload: newOrders,
});

const ORDERS_ERROR = error => ({
  type: actionsEnum.ORDERS_ERROR,
  payload: error,
});

const USERS_REQUESTED = () => ({
  type: actionsEnum.USERS_REQUESTED,
});

const USERS_LOADED = users => ({
  type: actionsEnum.USERS_LOADED,
  payload: users,
});

const USERS_ERROR = error => ({
  type: actionsEnum.USERS_ERROR,
  payload: error,
});

const SHOW_ALERT = (scene, text, typeAlert = typeAlertEnum.INFO) => ({
  type: actionsEnum.SHOW_ALERT,
  payload: { scene, text, typeAlert },
});

const HIDE_ALERT = () => ({
  type: actionsEnum.HIDE_ALERT,
});

const OPEN_MODAL_PROFILE = (type, title) => ({
  type: actionsEnum.OPEN_MODAL_PROFILE,
  payload: { type, title },
});

const CANCEL_MODAL_PROFILE = () => ({
  type: actionsEnum.CANCEL_MODAL_PROFILE,
});

const SUBMIT_MODAL_PROFILE = data => ({
  type: actionsEnum.SUBMIT_MODAL_PROFILE,
  payload: data,
});

const OPEN_MODAL_ORDER = (type, title, orderInfo) => {
  const { orderId, orderedBy, nameEvent } = orderInfo;
  return {
    type: actionsEnum.OPEN_MODAL_ORDER,
    payload: { type, title, orderId, orderedBy, nameEvent },
  };
};

const CANCEL_MODAL_ORDER = () => ({
  type: actionsEnum.CANCEL_MODAL_ORDER,
});

const SUBMIT_MODAL_ORDER = () => ({
  type: actionsEnum.SUBMIT_MODAL_ORDER,
});

const REJECT_MODAL_ORDER = () => ({
  type: actionsEnum.REJECT_MODAL_ORDER,
});

const LOG_IN = userId => ({
  type: actionsEnum.LOG_IN,
  payload: userId,
});

const LOG_OUT = () => ({
  type: actionsEnum.LOG_OUT,
});


const LOGOUT = (dispatch) => {
  appServiceData.logOutUser().then(res => {
    if(res){
      dispatch(LOG_OUT());
      dispatch(SHOW_ALERT(scenesEnum.ANY, messages.LOG_OUT));
    }
    else dispatch(SHOW_ALERT(scenesEnum.ANY, messages.LOG_OUT_ERROR, typeAlertEnum.ERROR))
  })
};

const LOGIN = (event, dispatch) => {
  event.preventDefault();

  const formDate = new FormData(document.getElementById('LogInForm'));

  const userRegData = {
    email: formDate.get('email'),
    password: formDate.get('password'),
  };

  appServiceData.logInUser(userRegData).then(user => {
    if (user) {
      dispatch(SHOW_ALERT(scenesEnum.LOG_IN, messages.LOG_IN));
      dispatch(LOG_IN(user.id));
      dispatch(PROFILE_LOADED(user));
    } else {
      dispatch(SHOW_ALERT(scenesEnum.LOG_IN, messages.LOG_IN_ERROR, typeAlertEnum.ERROR));
    }
  });
};

const UPLOAD_PROFILE = (dispatch) => {
  appServiceData.loadProfileOfUser().then(user => {
    if (user) {
      dispatch(LOG_IN(user.id));
      dispatch(PROFILE_LOADED(user));
    }
  });
};

const REGISTER = (event, dispatch) => {
  event.preventDefault();
  const formDate = new FormData(document.getElementById('RegForm'));
  const user = {
    name: formDate.get('name'),
    email: formDate.get('email'),
    password: formDate.get('password'),
  };

  appServiceData.regUser(user).then(res => {
    if (res) {
      dispatch(SHOW_ALERT(scenesEnum.REG, messages.REG));
    } else dispatch(SHOW_ALERT(scenesEnum.REG, messages.REG_ERROR, typeAlertEnum.ERROR));
  });
};

const fetchTimeTables = (id, dispatch) => {
  if (id) {
    dispatch(TIME_TABLE_REQUESTED());
    appServiceData
      .getTimeTableById(id)
      .then(data => dispatch(TIME_TABLE_LOADED(data)))
      .catch(err => dispatch(TIME_TABLE_ERROR(err)));
  } else {
    dispatch(TIME_TABLES_REQUESTED());
    appServiceData
      .getTimeTables()
      .then(data => dispatch(TIME_TABLES_LOADED(data)))
      .catch(err => dispatch(TIME_TABLES_ERROR(err)));
  }
};

const fetchOrders = (dispatch, userId) => {
  dispatch(ORDERS_REQUESTED());
  appServiceData
    .getOrdersOfUser(userId)
    .then(data => dispatch(ORDERS_LOADED(data)))
    .catch(err => dispatch(ORDERS_ERROR(err)));
};

const fetchUsers = dispatch => {
  dispatch(USERS_REQUESTED());
  appServiceData
    .getUsers()
    .then(data => dispatch(USERS_LOADED(data)))
    .catch(err => dispatch(USERS_ERROR(err)));
};

const fetchFullInfo = (id, dispatch) => {
  fetchUsers(dispatch);
  fetchTimeTables(id, dispatch);
};

const ORDER_UPDATE_STATUS = (event, orderId, timeTableId, newStatus, scene, dispatch) => {
  event.preventDefault();
  appServiceData.updateOrder(orderId, newStatus).then(res => {
    if (newStatus === orderStatusEnum.CANCELED) {
      if (res) {
        dispatch(REJECT_MODAL_ORDER());
        dispatch(SHOW_ALERT(scene, messages.ORDER_REJECTED));
      } else {
        dispatch(SHOW_ALERT(scene, messages.ORDER_REJECTED_ERROR, typeAlertEnum.ERROR));
      }
    } else if (res) {
      dispatch(SUBMIT_MODAL_ORDER());
      dispatch(SHOW_ALERT(scenesEnum.TIME_TABLE, messages.ORDER_ACCEPTED));
    } else {
      dispatch(
        SHOW_ALERT(scenesEnum.TIME_TABLE, messages.ORDER_ACCEPTED_ERROR, typeAlertEnum.ERROR),
      );
    }
  });
  fetchTimeTables(timeTableId, dispatch);
};

const UPDATE_PROFILE = (data, alertText, typeModal, profile, dispatch) => {
  let newData;
  dispatch(SUBMIT_MODAL_PROFILE(data));
  if (typeModal === typeModalEnum.NAME) {
    newData = { name: data, email: profile.email };
  } else newData = { name: profile.name, email: data };

  appServiceData.updateProfileById(profile.id, newData).then(res => {
    if (res) {
      dispatch(SHOW_ALERT(scenesEnum.PROFILE, alertText));
    } else dispatch(SHOW_ALERT(scenesEnum.PROFILE, `${alertText} failed`, typeAlertEnum.ERROR));
  });
};

const ORDER_REMOVE = (event, orderId, timeTableId, scene, dispatch) => {
  event.preventDefault();
  appServiceData.removeOrder(orderId).then(res => {
    if (res) {
      dispatch(SHOW_ALERT(scene, messages.ORDER_REMOVED));
    } else {
      dispatch(SHOW_ALERT(scene, messages.ORDER_REMOVED_ERROR, typeAlertEnum.ERROR));
    }
  });
  if (scene === scenesEnum.USERS_INFO) {
    fetchFullInfo(timeTableId, dispatch);
  } else fetchTimeTables(timeTableId, dispatch);
};

const USER_REMOVE = (event, userId, scene, dispatch) => {
  event.preventDefault();
  appServiceData.removeUser(userId).then(res => {
    if (res) {
      dispatch(SHOW_ALERT(scene, messages.USER_REMOVED));
    } else {
      dispatch(SHOW_ALERT(scene, messages.USER_REMOVED_ERROR, typeAlertEnum.ERROR));
    }
  });
  fetchFullInfo(null, dispatch);
};

const TIME_TABLE_REMOVE = (event, timeTableId, scene, dispatch) => {
  event.preventDefault();
  appServiceData.removeTimeTable(timeTableId).then(res => {
    if (res) {
      dispatch(SHOW_ALERT(scene, messages.TIME_TABLE_REMOVED));
    } else {
      dispatch(SHOW_ALERT(scene, messages.TIME_TABLE_REMOVED_ERROR, typeAlertEnum.ERROR));
    }
  });
  fetchFullInfo(null, dispatch);
};

const CREATE_ORDER = (
  event,
  alertText,
  attributes,
  profile,
  timeOrder,
  timeTableId,
  slotSize,
  dispatch,
) => {
  event.preventDefault();
  fetchTimeTables(timeTableId, dispatch);
  const formDate = new FormData(document.getElementById('OrderSubmit'));
  const attributeValues = [];

  attributes.forEach(attribute => {
    attributeValues.push({
      timeTableId: attribute.timeTableId,
      attributeId: attribute.id,
      value: formDate.get(attribute.title),
      orderId: null,
    });
  });

  const millisec = slotSize === timeTableTypeEnum.HOUR ? 3600000 : 86400000;

  const order = {
    authorId: profile.id,
    authorName: profile.name,
    startDate: timeOrder,
    endDate: new Date(timeOrder.getTime() + millisec),
    timeTableId,
    attributeValues,
  };

  appServiceData.createOrder(order).then(res => {
    if (res) {
      dispatch(SUBMIT_MODAL_ORDER());
      dispatch(SHOW_ALERT(scenesEnum.TIME_TABLE, alertText));
    } else {
      dispatch(SHOW_ALERT(scenesEnum.TIME_TABLE, messages.ORDER_ADDED_ERROR, typeAlertEnum.ERROR));
    }
  });
};

const CREATE_TIME_TABLE = (event, dispatch) => {
  event.preventDefault();

  const formDate = new FormData(document.getElementById('CreateTimeTableForm'));

  const timeTable = {
    title: formDate.get('Timetable name'),
    startDate: formDate.get('Start range'),
    endDate: formDate.get('End range'),
    slotSize: formDate.get('Slot size'),
  };

  const attributes = [];

  attributes.push({
    title: formDate.get(`Attribute name 1`),
    type_attr: formDate.get(`Attr type 1`),
    isRequired: Boolean(formDate.get(`Checkbox 1`)),
    timeTableId: null,
  });

  // 50 max count attributes
  for (let i = 2; i < 100; i += 2) {
    if (formDate.get(`Attribute name ${i}`)) {
      attributes.push({
        title: formDate.get(`Attribute name ${i}`),
        type_attr: formDate.get(`Attr type ${i}`),
        isRequired: Boolean(formDate.get(`Checkbox ${i}`)),
        timeTableId: null,
      });
    }
  }
  timeTable.attributes = attributes;

  appServiceData.createTimeTable(timeTable).then(res => {
    if (res) {
      dispatch(SHOW_ALERT(scenesEnum.CREATE_TIME_TABLE, messages.CREATE_TIME_TABLE));
    } else
      dispatch(
        SHOW_ALERT(
          scenesEnum.CREATE_TIME_TABLE,
          messages.CREATE_TIME_TABLE_ERROR,
          typeAlertEnum.ERROR,
        ),
      );
  });
};

export {
  fetchFullInfo,
  fetchTimeTables,
  fetchOrders,
  fetchUsers,
  SHOW_ALERT,
  HIDE_ALERT,
  OPEN_MODAL_PROFILE,
  CANCEL_MODAL_PROFILE,
  SUBMIT_MODAL_PROFILE,
  OPEN_MODAL_ORDER,
  CANCEL_MODAL_ORDER,
  SUBMIT_MODAL_ORDER,
  REJECT_MODAL_ORDER,
  PROFILE_ERROR,
  ORDER_REMOVE,
  LOG_IN,
  LOG_OUT,
  LOGOUT,
  UPLOAD_PROFILE,
  REGISTER,
  LOGIN,
  CREATE_TIME_TABLE,
  CREATE_ORDER,
  ORDER_UPDATE_STATUS,
  UPDATE_PROFILE,
  USER_REMOVE,
  TIME_TABLE_REMOVE,
};
