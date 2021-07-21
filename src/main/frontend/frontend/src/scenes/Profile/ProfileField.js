import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './Profile.css';

const ProfileField = ({ value, title, icon }) => (
  <div className="profile-field">
    <FontAwesomeIcon
      className={`fas fa-${icon} fa-lg`}
      css={{
        color: 'black',
      }}
      icon={icon}
    />
    &nbsp;
    <span>{`${title}:`}</span>
    &emsp;&emsp;
    <span>{value}</span>
  </div>
);

export default ProfileField;
