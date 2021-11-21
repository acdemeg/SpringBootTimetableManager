import React from 'react';
import ReactDOM from 'react-dom';
import Alert from './Alert';
import {scenesEnum} from "../constants";

const notificationElement = document.getElementById('notifications');

const Notification = ({ notifications, currentScene }) => {
  const { scene, visible, text, typeAlert } = notifications;

  if (scene === currentScene || scene === scenesEnum.ANY) {
    return ReactDOM.createPortal(
      <Alert textAlert={text} typeAlert={typeAlert} visibleAlert={visible} />,
      notificationElement,
    );
  }
  return null;
};

export default Notification;
