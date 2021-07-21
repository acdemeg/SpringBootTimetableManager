import React from 'react';

const TextInput = ({ labelName, labelId, nameForm }) => {
  return (
    <label htmlFor={labelId}>
      {labelName}
      {React.createElement('input', {
        id: labelId,
        className: 'input',
        type: 'text',
        name: nameForm,
        required: true,
      })}
    </label>
  );
};

export default TextInput;
