import React from 'react';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

function InputIcon({ side, icon }) {
  return (
    <span className={`icon is-small is-${side}`}>
      <i className="fas fa-user">
        <FontAwesomeIcon
          className={`fas fa-${icon}`}
          css={{
            color: 'grey',
          }}
          icon={icon}
        />
      </i>
    </span>
  );
}

export default InputIcon;
