import React, {useState} from 'react';
import InputIcon from './InputIcon';

function InputField({ inputColor, type, name, placeholder, minLength, iconLeft, iconRight }) {
  const [input, setInput] = useState({
    iconName: 'exclamation',
    colorName: 'grey',
    iconEmail: 'exclamation',
    colorEmail: 'grey',
    iconPassw: 'exclamation',
    colorPassw: 'grey',
  });

  const changeInput = e => {
    const isValid = e.currentTarget.validity.valid;
    const { value } = e.currentTarget;

    const changeState = (iconProp, colorProp) => {
      if (isValid) {
        return setInput({ ...input, [iconProp]: 'check', [colorProp]: 'green' });
      }
      return value
        ? setInput({ ...input, [iconProp]: 'exclamation', [colorProp]: 'red' })
        : setInput({ ...input, [iconProp]: 'exclamation', [colorProp]: 'grey' });
    };

    switch (e.currentTarget.name) {
      case 'name':
        return changeState('iconName', 'colorName');
      case 'email':
        return changeState('iconEmail', 'colorEmail');
      case 'password':
        return changeState('iconPassw', 'colorPassw');
      default:
        return null;
    }
  };

  return (
    <div className="field">
      <p className="control has-icons-left has-icons-right">
        <input
          style={{ borderColor: input[inputColor] }}
          onChange={changeInput}
          className="input"
          type={type}
          name={name}
          placeholder={placeholder}
          autoComplete="on"
          minLength={minLength}
          required
        />
        <InputIcon side="left" icon={iconLeft} />
        <InputIcon side="right" icon={input[iconRight]} />
      </p>
    </div>
  );
}

export default InputField;
