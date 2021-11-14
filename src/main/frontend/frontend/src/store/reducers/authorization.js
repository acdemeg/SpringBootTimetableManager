import {actionsEnum} from '../../constants';

const checkUserAuthorization = (state, action) => {
  if (state === undefined) {
    return {
      isLoggedIn: false,
      userId: undefined,
    };
  }

  switch (action.type) {
    case actionsEnum.LOG_IN:
      return {
        isLoggedIn: true,
        userId: action.payload,
      };

    case actionsEnum.LOG_OUT:
      return {
        isLoggedIn: false,
        userId: undefined,
      };

    default:
      return state.authorization;
  }
};

export default checkUserAuthorization;
