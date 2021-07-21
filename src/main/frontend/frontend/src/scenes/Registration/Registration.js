import React from 'react';
import { connect } from 'react-redux';
import styles from './Registration.scss';
import register from '../TimeTables/TimeTables.scss';
import InputField from '../../components/inputs/InputField';
import PassAndEmailInputs from '../../components/inputs/PassAndEmailInputs';
import { REGISTER } from '../../store/actions';
import { scenesEnum } from '../../constants';
import Notification from '../../components/Notification';
import ButtonConfirm from '../../components/buttons/ButtonConfirm';

const Registration = ({ onReg, notifications }) => {
  return (
    <form id="RegForm" onSubmit={onReg}>
      <div className={register.googleFont}>
        Registration
        <div className={styles.regForm}>
          <InputField
            inputColor="colorName"
            type="text"
            name="name"
            placeholder="Username"
            minLength="1"
            iconLeft="user"
            iconRight="iconName"
          />
          <PassAndEmailInputs />
          <ButtonConfirm />
        </div>
      </div>
      <Notification notifications={notifications} currentScene={scenesEnum.REG} />
    </form>
  );
};

const mapStateToProps = ({ notifications }) => ({
  notifications,
});

const mapDispatchToProps = dispatch => ({
  onReg: event => {
    REGISTER(event, dispatch);
  },
});

export default connect(mapStateToProps, mapDispatchToProps)(Registration);
