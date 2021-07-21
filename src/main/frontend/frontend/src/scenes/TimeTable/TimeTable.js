import React from 'react';
import { useParams } from 'react-router-dom';
import { fetchTimeTables } from '../../store/actions';
import Table from './Table';
import { timeTableTypeEnum, scenesEnum } from '../../constants';
import styles from './TimeTable.scss';
import timeTableHeader from '../TimeTables/TimeTables.scss';
import WithData from '../../components/hoc-helpers/WithData';
import OrderModal from './OrderModal';
import Notification from '../../components/Notification';
import TimeTableDetailField from './TimeTableDetailField';

const TimeTable = ({
  timeTables,
  currentTimeTable,
  openModal,
  handleCancel,
  orderSubmit,
  orderUpdateStatus,
  notifications,
  profile,
  isLoggedIn,
  orders: { isOpenModal, nameEvent, orderId, orderedBy, titleModal, typeModal },
}) => {
  let timeTable;

  if (currentTimeTable) {
    timeTable = currentTimeTable;
  } else {
    const params = useParams();
    const scheduleId = Number(params.id);
    [timeTable] = timeTables.filter(val => val.id === scheduleId);
  }

  const { id, title, startDate, endDate, slotSize, attributes, orders } = timeTable;
  const startDateObj = new Date(Date.parse(startDate));
  const endDateObj = new Date(Date.parse(endDate));
  const millisecInWeek = 604800000;
  const millisecInDay = 86400000;
  const countColumns =
    slotSize === timeTableTypeEnum.DAY
      ? Math.ceil((endDateObj - startDateObj) / millisecInWeek)
      : Math.ceil((endDateObj - startDateObj) / millisecInDay);

  return (
    <div className={styles.timeTable}>
      <div className={timeTableHeader.googleFont}>Timetable details</div>
      <div className={styles.detailFields}>
        <TimeTableDetailField fieldName="Name:" info={title} />
        <TimeTableDetailField fieldName="Type:" info={slotSize} />
        <TimeTableDetailField
          fieldName="Period:"
          info={`${startDateObj.getDate()}
            ${startDateObj.toLocaleString('eng', { month: 'long' })}
            ${startDateObj.getFullYear()} year
              - 
            ${endDateObj.getDate()}  
            ${endDateObj.toLocaleString('eng', { month: 'long' })}  
            ${endDateObj.getFullYear()} year`}
        />
        <TimeTableDetailField role={profile.role} color="crimson" info="Canceled order" />
        <TimeTableDetailField role={profile.role} color="mediumaquamarine" info="Accepted order" />
        <TimeTableDetailField role={profile.role} color="gray" info="Created order" />
      </div>

      <div className={styles.table}>
        <Table
          slotSize={slotSize}
          countColumns={countColumns}
          startDate={startDateObj}
          openModal={openModal}
          orders={orders}
          profile={profile}
        />
      </div>
      {!isOpenModal ? null : (
        <OrderModal
          orderUpdateStatus={orderUpdateStatus}
          titleModal={titleModal}
          typeModal={typeModal}
          onCancel={handleCancel}
          onSubmit={orderSubmit}
          orderedBy={orderedBy}
          nameEvent={nameEvent}
          orderId={orderId}
          attributes={attributes}
          profile={profile}
          isLoggedIn={isLoggedIn}
          slotSize={slotSize}
          timeTableId={id}
        />
      )}
      <Notification notifications={notifications} currentScene={scenesEnum.TIME_TABLE} />
    </div>
  );
};

export default WithData(TimeTable, fetchTimeTables);
