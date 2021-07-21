import React from 'react';
import style from './UsersInfo.scss';
import Order from '../Timeline/Order';
import header from '../TimeTables/TimeTables.scss';
import searchOrdersOfUser from '../../utils/SearchOrdersOfUser';
import { scenesEnum } from '../../constants';

const UserOrders = ({ user, timeTables, removeOrder }) => {
  if (!user) return null;
  const events = searchOrdersOfUser(timeTables, user);

  return (
    <div className={style.ordersUser}>
      <div className={header.googleFont}>
        List orders for &nbsp;
        {`${user.name}`}
      </div>
      <div className={style.ordersWrapper}>
        {events.map(event => (
          <Order
            key={event.orderId}
            event={event}
            removeOrder={removeOrder}
            scene={scenesEnum.USERS_INFO}
          />
        ))}
      </div>
    </div>
  );
};

export default UserOrders;
