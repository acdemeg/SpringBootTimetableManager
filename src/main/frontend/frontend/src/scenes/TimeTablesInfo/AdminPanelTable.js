import React from 'react';
import { useParams } from 'react-router-dom';
import { fetchTimeTables } from '../../store/actions';
import { scenesEnum } from '../../constants';
import timeTableHeader from '../TimeTables/TimeTables.scss';
import WithData from '../../components/hoc-helpers/WithData';
import Notification from '../../components/Notification';
import SlotInfo from './SlotInfo';

const AdminPanelTable = ({
  timeTables,
  currentTimeTable,
  notifications,
  orderUpdateStatus,
  removeOrder,
}) => {
  let timeTable;
  if (currentTimeTable) {
    timeTable = currentTimeTable;
  } else {
    const params = useParams();
    const scheduleId = Number(params.id);
    [timeTable] = timeTables.filter(val => val.id === scheduleId);
  }

  const { title, conflictsOrders, slotSize, orders, attributes } = timeTable;
  const noConflictOrders = orders.filter(order => {
    if (conflictsOrders.find(confOrder => confOrder.id === order.id)) {
      return false;
    }
    return true;
  });
  const conflictSlots = Array.from(new Set(conflictsOrders.map(v => v.startDate)));

  return (
    <div>
      <div className={timeTableHeader.googleFont}>{title}</div>
      {conflictSlots.map(slot => (
        <SlotInfo
          key={slot}
          slotSize={slotSize}
          attributes={attributes}
          removeOrder={removeOrder}
          orderUpdateStatus={orderUpdateStatus}
          orders={conflictsOrders.filter(v => v.startDate === slot)}
        />
      ))}
      {noConflictOrders.map(order => (
        <SlotInfo
          key={order.id}
          removeOrder={removeOrder}
          orderUpdateStatus={orderUpdateStatus}
          slotSize={slotSize}
          orders={order}
          attributes={attributes}
        />
      ))}
      <Notification notifications={notifications} currentScene={scenesEnum.ADMIN_PANEL_TABLE} />
    </div>
  );
};

export default WithData(AdminPanelTable, fetchTimeTables);
