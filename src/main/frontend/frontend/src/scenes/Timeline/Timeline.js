import React from 'react';
import styled from '@emotion/styled';
import timeTableHeader from '../TimeTables/TimeTables.scss';
import WithData from '../../components/hoc-helpers/WithData';
import { fetchTimeTables } from '../../store/actions';
import style from './Timeline.scss';
import Order from './Order';
import Notification from '../../components/Notification';
import searchOrdersOfUser from '../../utils/SearchOrdersOfUser';
import { scenesEnum } from '../../constants';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  align-items: center;
`;

const Timeline = ({ timeTables, profile, notifications, removeOrder }) => {
  const events = searchOrdersOfUser(timeTables, profile);

  return (
    <div>
      <div className={timeTableHeader.googleFont}>Timeline</div>
      <div>
        <p className={style.title}>Upcoming events</p>
        <Container>
          {events.map(event => {
            if (new Date(Date.parse(event.endDate)).getTime() > Date.now()) {
              return <Order key={event.orderId} event={event} removeOrder={removeOrder} />;
            }
            return null;
          })}
        </Container>
      </div>
      <div style={{ marginBottom: '10%' }}>
        <p className={style.title}>Previous events</p>
        <Container>
          {events.map(event => {
            if (new Date(Date.parse(event.endDate)).getTime() < Date.now()) {
              return (
                <Order
                  key={event.orderId}
                  event={event}
                  removeOrder={removeOrder}
                  scene={scenesEnum.TIMELINE}
                />
              );
            }
            return null;
          })}
        </Container>
      </div>
      <Notification notifications={notifications} currentScene={scenesEnum.TIMELINE} />
    </div>
  );
};

export default WithData(Timeline, fetchTimeTables);
