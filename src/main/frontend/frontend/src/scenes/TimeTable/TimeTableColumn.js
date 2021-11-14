import React from 'react';
import {TimeCell, TimeTableCell} from './TimeTableCell';
import DateUtils from './date_utils';

const TimeTableColumn = ({ orders, period, countCell, openModal, slotSize, profile }) => {
  const rows = [];
  const date = new DateUtils(slotSize, period);
  rows.push(<TimeCell time={period.string} key={0} />);

  for (let i = 1; i <= countCell; i += 1) {
    rows.push(
      <TimeTableCell
        key={i}
        openModal={openModal}
        order={date.getOrderForCell(orders, i, profile.id)}
        date={date.getPeriodForCell(i)}
        profile={profile}
      />,
    );
  }
  return rows;
};

export default TimeTableColumn;
