import React from 'react';
import { connect } from 'react-redux';
import styles from './SignIn.scss';
import register from '../TimeTables/TimeTables.scss';
import { LOGIN } from '../../store/actions';
import { scenesEnum } from '../../constants';
import Notification from '../../components/Notification';
import PassAndEmailInputs from '../../components/inputs/PassAndEmailInputs';
import ButtonConfirm from '../../components/buttons/ButtonConfirm';

const SignIn = ({ onLogin, notifications }) => {
  return (
    <form id="LogInForm" onSubmit={onLogin}>
      <div className={register.googleFont}>
        Sign In
        <div className={styles.signInForm}>
          <PassAndEmailInputs />
          <ButtonConfirm />
        </div>
      </div>
      <Notification notifications={notifications} currentScene={scenesEnum.LOG_IN} />
    </form>
  );
};

const mapStateToProps = ({ notifications }) => ({ notifications });

const mapDispatchToProps = dispatch => ({
  onLogin: event => {
    LOGIN(event, dispatch);
  },
});

export default connect(mapStateToProps, mapDispatchToProps)(SignIn);
