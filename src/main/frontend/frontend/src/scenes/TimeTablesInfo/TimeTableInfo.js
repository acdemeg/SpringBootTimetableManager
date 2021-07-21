import React from 'react';
import { NavLink } from 'react-router-dom';
import styles from './TimeTablesInfo.scss';
import getNavLinkTitle from './NavLinkTitle';
import { scenesEnum } from '../../constants';
import ButtonRound from '../../components/buttons/ButtonRound';

const TimeTableInfo = ({ timeTable, removeTimeTable }) => {
  const { id, title, startDate, orders, endDate, slotSize } = timeTable;
  const startDateObj = new Date(Date.parse(startDate));
  const endDateObj = new Date(Date.parse(endDate));

  return (
    <div>
      <div className={styles.timeTableInfo}>
        <NavLink to={`/adminPanelTable/${id}`}>
          {getNavLinkTitle(timeTable.conflictsOrders.length, orders)}
        </NavLink>
        <div className={styles.bodyInfo}>
          <NavLink to={`/timeTable/${id}`}>
            <div className={styles.title}>{title}</div>
          </NavLink>
          <div>
            Timetable type: &nbsp;
            <i>{slotSize}</i>
          </div>
          <div>
            Timetable period: &nbsp;
            <i>
              {`${startDateObj.getDate()}
              ${startDateObj.toLocaleString('eng', { month: 'long' })}
              ${startDateObj.getFullYear()} year
                - 
                ${endDateObj.getDate()}  
                ${endDateObj.toLocaleString('eng', { month: 'long' })}  
                ${endDateObj.getFullYear()} year`}
            </i>
          </div>
          <div className={styles.removeTable}>
            <ButtonRound
              title="remove timetable"
              color="is-danger is-outlined"
              handler={e => removeTimeTable(e, id, scenesEnum.TIME_TABLES_INFO)}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default TimeTableInfo;
