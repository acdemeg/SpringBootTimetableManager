import React from 'react';
import {orderStatusEnum, scenesEnum, typeModalEnum} from '../../constants';
import Button from '../../components/buttons/Button';

const ModalFooterForm = ({ typeModal, onCancel, orderUpdateStatus, timeTableId, orderId }) => {
  if (typeModal === typeModalEnum.CREATE_ORDER) {
    return (
      <div>
        <Button title="Place order" handler={() => {}} />
        <Button title="Cancel" handler={onCancel} />
      </div>
    );
  }
  if (typeModal === typeModalEnum.INFO_ORDER) {
    return <Button title="OK" handler={onCancel} />;
  }

  return (
    <div>
      <Button
        title="Approve"
        handler={event =>
          orderUpdateStatus(
            event,
            orderId,
            timeTableId,
            orderStatusEnum.ACCEPTED,
            scenesEnum.TIME_TABLE,
          )
        }
      />
      <Button
        title="Reject"
        handler={event =>
          orderUpdateStatus(
            event,
            orderId,
            timeTableId,
            orderStatusEnum.CANCELED,
            scenesEnum.TIME_TABLE,
          )
        }
      />
      <Button title="Cancel" handler={onCancel} />
    </div>
  );
};

export default ModalFooterForm;
