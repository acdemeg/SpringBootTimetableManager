import {actionsEnum} from '../../constants';

const getAllUsers = (state, action) => {
  if (state === undefined) {
    return {
      users: [],
      loading: true,
      error: null,
    };
  }

  switch (action.type) {
    case actionsEnum.USERS_REQUESTED:
      return {
        users: [],
        loading: true,
        error: null,
      };
    case actionsEnum.USERS_LOADED:
      return {
        users: action.payload,
        loading: false,
        error: null,
      };

    case actionsEnum.USERS_ERROR:
      return {
        users: [],
        loading: false,
        error: action.payload,
      };

    default:
      return state.users;
  }
};

export default getAllUsers;
