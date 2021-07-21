import React from 'react';
import ReactDOM from 'react-dom';
import Alert from './Alert';

const notificationElement = document.getElementById('notifications');

const Notification = ({ notifications, currentScene }) => {
  const { scene, visible, text, typeAlert } = notifications;
  if (scene === currentScene) {
    return ReactDOM.createPortal(
      <Alert textAlert={text} typeAlert={typeAlert} visibleAlert={visible} />,
      notificationElement,
    );
  }
  return null;
};

export default Notification;
