import React from 'react';
import {typeModalEnum} from '../../constants';
import './Profile.css';

const RedactFields = ({ openModal }) => (
  <div style={{ float: 'left' }}>
    <Field
      openModal={openModal}
      modalInfo={{ type: typeModalEnum.NAME, title: 'Enter your new Name' }}
    />
    <Field
      openModal={openModal}
      modalInfo={{ type: typeModalEnum.EMAIL, title: 'Enter your new Email' }}
    />
  </div>
);

export default RedactFields;

function Field({ openModal, modalInfo }) {
  return (
    <div
      role="button" //
      tabIndex={0} // for lint
      onKeyDown={() => openModal(modalInfo)} //
      onClick={() => openModal(modalInfo)}
      className="redact"
      style={{ fontSize: '20pt' }}
    >
      <span aria-labelledby="jsx-a11y/accessible-emoji" role="img">
        &#9997;
      </span>
    </div>
  );
}
