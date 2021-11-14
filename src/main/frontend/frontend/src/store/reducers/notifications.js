import {actionsEnum} from '../../constants';

const updateAlert = (state, action) => {
  if (state === undefined) {
    return {
      visible: false,
      scene: undefined,
    };
  }

  switch (action.type) {
    case actionsEnum.SHOW_ALERT:
      return {
        ...action.payload,
        visible: true,
      };

    case actionsEnum.HIDE_ALERT:
      return {
        visible: false,
      };

    default:
      return state.notifications;
  }
};

export default updateAlert;
