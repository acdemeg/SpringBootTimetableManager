import { actionsEnum, typeModalEnum } from '../../constants';

const updateOrders = (state, action) => {
  if (state === undefined) {
    return {
      orderId: null,
      isOpenModal: false,
      orderedBy: '',
      nameEvent: 'New event',
      typeModal: undefined,
      titleModal: '',
    };
  }

  const updateInfo = () => {
    const { typeModal } = state.orders;

    if (typeModal === typeModalEnum.CREATE_ORDER || typeModal === typeModalEnum.ACCEPT_ORDER) {
      return {
        ...state.orders,
        isOpenModal: false,
      };
    }

    return state.orders;
  };

  const openNeedModal = () => ({
    isOpenModal: true,
    orderId: action.payload.orderId,
    orderedBy: action.payload.orderedBy,
    nameEvent: action.payload.nameEvent,
    typeModal: action.payload.type,
    titleModal: action.payload.title,
  });

  const cancelModal = () => ({
    orderId: state.orders.orderId,
    orderedBy: state.orders.orderedBy,
    nameEvent: state.orders.nameEvent,
    isOpenModal: false,
  });

  switch (action.type) {
    case actionsEnum.OPEN_MODAL_ORDER:
      return openNeedModal(state, action);

    case actionsEnum.CANCEL_MODAL_ORDER:
      return cancelModal();

    case actionsEnum.SUBMIT_MODAL_ORDER:
      return updateInfo();

    case actionsEnum.REJECT_MODAL_ORDER:
      return {
        orderId: null,
        isOpenModal: false,
        orderedBy: '',
        nameEvent: 'New event',
        typeModal: undefined,
        titleModal: '',
      };

    case actionsEnum.ORDERS_LOADED:
      return {
        ...state.orders,
        nameEvent: action.payload.nameEvent,
        orderedBy: action.payload.orderedBy,
      };

    case actionsEnum.ORDERS_ERROR:
      return {
        ...state.orders,
      };

    default:
      return state.orders;
  }
};

export default updateOrders;
