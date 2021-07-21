import React from 'react';
import Schedule from '../../components/Schedule';
import styles from './TimeTables.scss';
import { fetchFullInfo } from '../../store/actions';
import ButtonAddTimeTable from '../../components/buttons/ButtonAddTimeTable';
import WithData from '../../components/hoc-helpers/WithData';
import { usersRoleEnum } from '../../constants';
import Container from './ContainerStyle';

const TimeTables = ({ timeTables, profile }) => {
  return (
    <div style={{ textAlign: 'center' }}>
      <div className={styles.googleFont}>Available TimeTables</div>
      <Container>
        {timeTables.map(schedule => (
          <Schedule key={schedule.id} schedule={schedule} />
        ))}
      </Container>
      {profile.role === usersRoleEnum.ADMIN ? <ButtonAddTimeTable /> : null}
    </div>
  );
};

export default WithData(TimeTables, fetchFullInfo);
