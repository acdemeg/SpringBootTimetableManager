import React from 'react';
import { orderStatusEnum } from '../../constants';
import styles from './TimeTablesInfo.scss';

const getNavLinkTitle = (countConflictOrders, orders) => {
  let countCreatedOrders = 0;

  orders.forEach(order => {
    if (order.status === orderStatusEnum.CREATED) {
      countCreatedOrders += 1;
    }
  });

  if (countConflictOrders) {
    return (
      <div className={styles.conflictOrders}>
        {`${countConflictOrders} orders entered into conflict`}
      </div>
    );
  }
  if (countCreatedOrders) {
    return (
      <div className={styles.newOrders}>{`${countCreatedOrders} orders awaiting approval`}</div>
    );
  }

  return <div className={styles.oldOrders}>No new order requests</div>;
};

export default getNavLinkTitle;
