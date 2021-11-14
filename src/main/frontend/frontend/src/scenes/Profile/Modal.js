import React from 'react';
import {messages, typeModalEnum} from '../../constants';
import InputForm from './InputForm';
import ButtonModal from '../../components/buttons/ButtonModal';
import './Modal.css';

const Modal = ({ title, typeModal, onCancel, onSubmit, profile }) => {
  let inputValue = 'unknown';
  const getValueFromInput = value => {
    inputValue = value;
  };

  const getAlertText = () => {
    if (typeModal === typeModalEnum.EMAIL) {
      return messages.EMAIL_UPDATE;
    }

    if (typeModal === typeModalEnum.NAME) {
      return messages.NAME_UPDATE;
    }

    return messages.FAIL_UPDATE;
  };

  return (
    <div className="modalOverlay">
      <form
        id="ProfileModalSubmit"
        onSubmit={event => {
          event.preventDefault();
          onSubmit(inputValue, getAlertText(), typeModal, profile);
        }}
      >
        <div className="modalWindow">
          <div className="modalHeader">
            <div className="modalTitle">{title}</div>
          </div>
          <div className="modalBody">
            <InputForm typeModal={typeModal} getValueFromInput={getValueFromInput} />
          </div>
          <div className="modalFooter">
            <ButtonModal view={{ title: 'Cancel', style: 'cancel' }} handler={onCancel} />
            <ButtonModal view={{ title: 'Submit', style: 'submit' }} handler={() => {}} />
          </div>
        </div>
      </form>
    </div>
  );
};

export default Modal;
