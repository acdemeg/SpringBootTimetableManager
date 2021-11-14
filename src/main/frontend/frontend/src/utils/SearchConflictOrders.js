import {orderStatusEnum} from '../constants';

const searchConflictOrders = orders => {
  const conflictOrders = new Set();
  orders.forEach(curOrder => {
    const confForCurOrder = orders.filter(order => {
      if (curOrder.id !== order.id) {
        if (curOrder.startDate === order.startDate) {
          if (
            curOrder.status === orderStatusEnum.CANCELED ||
            order.status === orderStatusEnum.CANCELED
          ) {
            return null;
          }
          return order;
        }
        return null;
      }
      return null;
    });
    if (confForCurOrder.length > 0) {
      confForCurOrder.forEach(order => conflictOrders.add(order));
    }
    return null;
  });

  return Array.from(conflictOrders);
};

export default searchConflictOrders;
