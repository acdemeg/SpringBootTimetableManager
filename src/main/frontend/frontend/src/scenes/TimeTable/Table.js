import React from 'react';
import styles from './TimeTable.scss';
import TimeTableColumn from './TimeTableColumn';
import {timeTableTypeEnum} from '../../constants';
import {TimeCell} from './TimeTableCell';
import DateUtils from './date_utils';

const Table = ({ slotSize, countColumns, startDate, openModal, orders, profile }) => {
  const columns = [];
  const countCell = slotSize === timeTableTypeEnum.DAY ? 7 : 24;
  const date = new DateUtils(slotSize, startDate);

  if (slotSize === timeTableTypeEnum.DAY) {
    columns.push(
      <div className={styles.column} key={0}>
        <TimeCell />
        <TimeCell time="Monday" />
        <TimeCell time="Thuesday" />
        <TimeCell time="Wednesday" />
        <TimeCell time="Thursday" />
        <TimeCell time="Friday" />
        <TimeCell time="Saturday" />
        <TimeCell time="Sunday" />
      </div>,
    );
  } else {
    const cells = [];

    for (let i = 1; i <= 25; i += 1) {
      if (i === 1) {
        cells.push(<TimeCell key={i} />);
      } else {
        cells.push(<TimeCell time={`${i - 2}:00`} key={i} />);
      }
    }

    columns.push(
      <div className={styles.column} key={0}>
        {cells}
      </div>,
    );
  }

  for (let i = 1; i <= countColumns; i += 1) {
    columns.push(
      <div className={styles.column} key={i}>
        <TimeTableColumn
          period={date.getPeriodForColumns(i)}
          orders={date.getOrdersForColumn(orders)}
          countCell={countCell}
          openModal={openModal}
          slotSize={slotSize}
          profile={profile}
        />
      </div>,
    );
  }

  return columns;
};

export default Table;
