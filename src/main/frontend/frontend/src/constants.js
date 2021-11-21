const actionsEnum = Object.freeze({
  TIME_TABLES_LOADED: 'FETCH_TIME_TABLES_SUCCESS',
  TIME_TABLES_REQUESTED: 'FETCH_TIME_TABLES_REQUESTED',
  TIME_TABLES_ERROR: 'FETCH_TIME_TABLES_FAILURE',
  TIME_TABLE_LOADED: 'FETCH_TIME_TABLE_SUCCESS',
  TIME_TABLE_REQUESTED: 'FETCH_TIME_TABLE_REQUESTED',
  TIME_TABLE_ERROR: 'FETCH_TIME_TABLE_FAILURE',
  PROFILE_LOADED: 'FETCH_PROFILE_SUCCESS',
  PROFILE_ERROR: 'FETCH_PROFILE_FAILURE',
  ORDERS_ERROR: 'FETCH_ORDERS_FAILURE',
  ORDERS_LOADED: 'FETCH_ORDERS_SUCCESS',
  ORDERS_REQUESTED: 'FETCH_ORDERS_REQUESTED',
  USERS_ERROR: 'FETCH_USERS_FAILURE',
  USERS_LOADED: 'FETCH_USERS_SUCCESS',
  USERS_REQUESTED: 'FETCH_USERS_REQUESTED',
  SHOW_ALERT: 'SHOW_ALERT',
  HIDE_ALERT: 'HIDE_ALERT',
  OPEN_MODAL_PROFILE: 'OPEN_MODAL_PROFILE',
  CANCEL_MODAL_PROFILE: 'CANCEL_MODAL_PROFILE',
  SUBMIT_MODAL_PROFILE: 'SUBMIT_MODAL_PROFILE',
  OPEN_MODAL_ORDER: 'OPEN_MODAL_ORDERS',
  CANCEL_MODAL_ORDER: 'CANCEL_MODAL_ORDERS',
  SUBMIT_MODAL_ORDER: 'SUBMIT_MODAL_ORDERS',
  REJECT_MODAL_ORDER: 'REJECT_MODAL_ORDERS',
  MAKE_ORDER: 'MAKE_ORDER',
  UPDATE_ORDER: 'UPDATE_ORDER',
  SET_CONFLICT_ORDERS: 'ADD_CONFLICT_ORDERS',
  RESOLVE_CONFLICT_ORDER: 'RESOLVE_CONFLICT_ORDER',
  LOG_IN: 'LOG_IN',
  LOG_OUT: 'LOG_OUT',
});

const scenesEnum = Object.freeze({
  PROFILE: 'Profile',
  TIME_TABLES: 'Timetables',
  TIME_TABLES_INFO: 'TimetablesInfo',
  CREATE_TIME_TABLE: 'Createtimetable',
  TIME_TABLE: 'Timetable',
  ADMIN_PANEL_TABLE: 'AdminPanelTable',
  USERS_INFO: 'UsersInfo',
  TIMELINE: 'Timeline',
  LOG_IN: 'Log-In',
  REG: 'Reg',
  ANY: 'Any'
});

const typeAlertEnum = Object.freeze({
  INFO: 'info',
  ERROR: 'error',
});

const usersRoleEnum = Object.freeze({
  ADMIN: 'ADMIN',
  USER: 'USER',
});

const orderStatusEnum = Object.freeze({
  CREATED: 'CREATED',
  ACCEPTED: 'ACCEPTED',
  CANCELED: 'CANCELED',
});

const typeModalEnum = Object.freeze({
  NAME: 'name_redact',
  EMAIL: 'email_redact',
  CREATE_ORDER: 'CREATE_ORDER',
  INFO_ORDER: 'INFO_ORDER',
  ACCEPT_ORDER: 'ACCEPT_ORDER',
});

const timeTableTypeEnum = Object.freeze({
  HOUR: 'HOUR',
  DAY: 'DAY',
  WEEK: 'WEEK',
});

const attributeTypeEnum = Object.freeze({
  STRING: 'STRING',
  DATE: 'DATE',
  NUMBER: 'NUMBER',
});

const messages = Object.freeze({
  MAKE_ORDER: 'You make to order',
  EMAIL_UPDATE: 'Your email update',
  NAME_UPDATE: 'Your name update',
  LOG_IN: 'You logged your account',
  LOG_OUT: 'You logout from account',
  LOG_OUT_ERROR: 'Logout failed',
  LOG_IN_ERROR: 'Incorrect email or password',
  REG: 'You succses registered',
  CREATE_TIME_TABLE: 'You succses create timetable',
  CREATE_TIME_TABLE_ERROR: 'Create timetable is failed',
  REG_ERROR: 'Registraton failed',
  ORDER_ADDED: 'Your order added',
  ORDER_ADDED_ERROR: 'Your order do not added',
  ORDER_REJECTED: 'You reject order',
  ORDER_REJECTED_ERROR: "Can't reject order",
  ORDER_ACCEPTED: 'You accetp order',
  ORDER_ACCEPTED_ERROR: "Can't accetp order",
  ORDER_REMOVED: 'You removed order',
  ORDER_REMOVED_ERROR: 'You do not removed order',
  USER_REMOVED: 'You removed user',
  USER_REMOVED_ERROR: 'You do not removed of user',
  TIME_TABLE_REMOVED: 'You removed timetable',
  TIME_TABLE_REMOVED_ERROR: 'You do not removed of timetable',
});

export {
  actionsEnum,
  scenesEnum,
  timeTableTypeEnum,
  typeModalEnum,
  messages,
  attributeTypeEnum,
  orderStatusEnum,
  usersRoleEnum,
  typeAlertEnum,
};
