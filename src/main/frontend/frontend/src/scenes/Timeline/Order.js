import React from 'react';
import { NavLink } from 'react-router-dom';
import styles from './Timeline.scss';
import { timeTableTypeEnum, orderStatusEnum } from '../../constants';
import ButtonRound from '../../components/buttons/ButtonRound';

const Order = ({ event, removeOrder, scene }) => {
  const {
    orderId,
    timeTableId,
    timeTableTitle,
    startDate,
    endDate,
    titleMainAttrib = null,
    attr = null,
    slotSize,
    orderStatus,
  } = event;

  const startDateObj = new Date(Date.parse(startDate));
  const endDateObj = new Date(Date.parse(endDate));

  const getMainAttr = () => {
    if (titleMainAttrib) {
      if (attr) {
        if (attr.value) {
          return (
            <p>
              {titleMainAttrib}
              :&nbsp;
              <i>{attr.value}</i>
            </p>
          );
        }
        return null;
      }
      return null;
    }
    return null;
  };

  return (
    <div>
      <div className={styles.event}>
        <NavLink to={`/timeTable/${timeTableId}`}>
          <div className={styles.titleEvent}>{timeTableTitle}</div>
        </NavLink>
        <div>
          <p>
            <i>
              {slotSize === timeTableTypeEnum.HOUR
                ? `${startDateObj.getDate()}
                ${startDateObj.toLocaleString('eng', { month: 'long' })}
                ${startDateObj.getHours()}:00
                - 
                ${endDateObj.getHours()}:00`
                : `${startDateObj.getDate()}
                ${startDateObj.toLocaleString('eng', { month: 'long' })}`}
            </i>
          </p>
        </div>
        <div>
          {getMainAttr()}
          <p>
            Status:&nbsp;
            <i
              style={{
                color: `${(() => {
                  if (orderStatus === orderStatusEnum.CANCELED) {
                    return 'crimson';
                  }
                  if (orderStatus === orderStatusEnum.ACCEPTED) {
                    return 'mediumspringgreen';
                  }
                  return 'black';
                })()}`,
              }}
            >
              <b>{orderStatus}</b>
            </i>
          </p>
        </div>
        <ButtonRound title="remove order" handler={e => removeOrder(e, orderId, null, scene)} />
      </div>
    </div>
  );
};

export default Order;
