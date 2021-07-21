import React from 'react';
import { typeModalEnum } from '../../constants';
import style from './TimeTable.scss';
import { getNewIdGenerator } from '../../utils';

const attributeKeysGen = getNewIdGenerator();

const ModalBodyForm = ({ typeModal, orderedBy, nameEvent, attributes }) => {
  const attributeFields = [];
  let typeInput;

  // for (const attribute of attributes) {
  //   if (attribute.type_attr === 'DATE') {
  //     typeInput = 'date';
  //   } else typeInput = 'text';

  attributes.forEach(attribute => {
    if (attribute.type_attr === 'DATE') {
      typeInput = 'date';
    } else typeInput = 'text';

    attributeFields.push(
      <div className={style.modalBodyField} key={attributeKeysGen()}>
        <label htmlFor={attribute.title}>
          {`${attribute.title}${attribute.isRequired ? '*' : ''}`}
          {React.createElement('input', {
            id: attribute.title,
            className: 'input',
            type: typeInput,
            name: attribute.title,
            required: attribute.isRequired,
          })}
        </label>
      </div>,
    );
  });

  if (typeModal === typeModalEnum.CREATE_ORDER) {
    return (
      <div>
        {attributeFields}
        <div style={{ textAlign: 'center' }}>* - required atttributes</div>
      </div>
    );
  }

  return (
    <div>
      <p className={style.modalBodyField}>
        <b>Ordered By:</b>
        &nbsp;&nbsp;&nbsp;&nbsp;
        {orderedBy}
      </p>
      <p className={style.modalBodyField}>
        <b>Title Event:</b>
        &nbsp;&nbsp;&nbsp;&nbsp;
        {nameEvent}
      </p>
    </div>
  );
};

export default ModalBodyForm;
