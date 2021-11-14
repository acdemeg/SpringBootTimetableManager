import React from 'react';
import styles from '../TimeTables/TimeTables.scss';
import {fetchTimeTables} from '../../store/actions';
import WithData from '../../components/hoc-helpers/WithData';
import TimeTableInfo from './TimeTableInfo';
import Container from '../TimeTables/ContainerStyle';
import {scenesEnum} from '../../constants';
import Notification from '../../components/Notification';

const TimeTablesInfo = ({ timeTables, removeTimeTable, notifications }) => {
  if (timeTables.length === 0) {
    return <div className={styles.googleFont}>Empty list Orders</div>;
  }

  return (
    <div>
      <div className={styles.googleFont}>Orders</div>
      <Container>
        {timeTables.map(timeTable => (
          <TimeTableInfo
            key={timeTable.id}
            timeTable={timeTable}
            removeTimeTable={removeTimeTable}
          />
        ))}
      </Container>
      <Notification notifications={notifications} currentScene={scenesEnum.TIME_TABLES_INFO} />
    </div>
  );
};

export default WithData(TimeTablesInfo, fetchTimeTables);
