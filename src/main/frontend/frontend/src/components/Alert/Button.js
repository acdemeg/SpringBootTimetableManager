import React from 'react';
import './Alert.css';

const Button = ({ onAlert }) => (
  <button aria-label="close notice" type="button" onClick={onAlert} className="delete" />
);

export default Button;
