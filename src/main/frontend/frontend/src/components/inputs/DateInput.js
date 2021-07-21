import React from 'react';

const DateInput = ({ labelName, labelId, nameForm }) => {
  return (
    <label htmlFor={labelId}>
      {labelName}
      {React.createElement('input', {
        id: labelId,
        className: 'input',
        type: 'date',
        name: nameForm,
        required: true,
      })}
    </label>
  );
};

export default DateInput;
