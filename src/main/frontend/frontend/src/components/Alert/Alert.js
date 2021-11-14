import React from 'react';
import {connect} from 'react-redux';
import {CSSTransition} from 'react-transition-group';
import {HIDE_ALERT} from '../../store/actions';
import Button from './Button';
import './Alert.css';

const Alert = ({ visibleAlert, textAlert, typeAlert, onAlert }) => (
  <CSSTransition
    in={visibleAlert}
    timeout={{
      enter: 1300,
      exit: 200,
    }}
    classNames="alert"
    mountOnEnter
    unmountOnExit
  >
    <div className={`notification ${typeAlert}-alert`}>
      <Button onAlert={onAlert} />
      <strong>{textAlert}</strong>
    </div>
  </CSSTransition>
);

const mapDispatchToProps = dispatch => ({
  onAlert: () => dispatch(HIDE_ALERT()),
});

export default connect(null, mapDispatchToProps)(Alert);
