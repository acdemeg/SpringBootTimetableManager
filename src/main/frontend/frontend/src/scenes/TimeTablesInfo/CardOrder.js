import React from 'react';
import styles from './TimeTablesInfo.scss';
import { orderStatusEnum, scenesEnum } from '../../constants';
import ButtonRound from '../../components/buttons/ButtonRound';

const CartOrder = ({ order, attributes, removeOrder, orderUpdateStatus }) => {
  const { id, authorName, status, timeTableId, attributeValues } = order;

  const getMainAttr = () => {
    if (attributes.length > 0) {
      if (attributeValues.length > 0) {
        const [attr] = attributeValues;
        const [{ title }] = attributes;
        if (attr.value) {
          return (
            <p>
              {title}
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
      <div className={styles.cartOrder}>
        <p>
          Author :&nbsp;
          <i>{authorName}</i>
        </p>
        <div>
          {getMainAttr()}
          <p>
            Status:&nbsp;
            <i
              style={{
                color: `${(() => {
                  if (status === orderStatusEnum.CANCELED) {
                    return 'crimson';
                  }
                  if (status === orderStatusEnum.ACCEPTED) {
                    return 'mediumspringgreen';
                  }
                  return 'black';
                })()}`,
              }}
            >
              <b>{status}</b>
            </i>
          </p>
        </div>
        <div className={styles.buttonsCartOrder}>
          <ButtonRound
            title="remove"
            color="is-danger"
            handler={event => removeOrder(event, id, timeTableId, scenesEnum.ADMIN_PANEL_TABLE)}
          />
          <ButtonRound
            title="cancel"
            handler={event =>
              orderUpdateStatus(
                event,
                id,
                timeTableId,
                orderStatusEnum.CANCELED,
                scenesEnum.ADMIN_PANEL_TABLE,
              )
            }
          />
        </div>
      </div>
    </div>
  );
};

export default CartOrder;
