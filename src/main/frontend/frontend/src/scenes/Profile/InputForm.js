import React from 'react';
import { typeModalEnum } from '../../constants';
import './Modal.css';

const InputForm = ({ typeModal, getValueFromInput }) => {
  switch (typeModal) {
    case typeModalEnum.NAME:
      return (
        <input
          id="modal"
          className="input-modal-profile"
          type="text"
          required
          minLength="1"
          placeholder="Example: Nastya"
          onChange={e => getValueFromInput(e.target.value)}
        />
      );

    case typeModalEnum.EMAIL:
      return (
        <input
          id="modal"
          className="input-modal-profile"
          type="email"
          required
          minLength="3"
          placeholder="Example: nastya@mail.ru"
          onChange={e => getValueFromInput(e.target.value)}
        />
      );

    default:
      return <input id="modal" type="text" onChange={e => getValueFromInput(e.target.value)} />;
  }
};

export default InputForm;
