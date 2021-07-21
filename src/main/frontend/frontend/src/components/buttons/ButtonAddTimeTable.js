import React from 'react';
import { NavLink } from 'react-router-dom';

function ButtonAddTimeTable() {
  return (
    <NavLink to="/createTimeTable">
      <div style={{ margin: '3% auto' }}>
        <button type="button" className="button is-primary">
          + Create new TimeTable
        </button>
      </div>
    </NavLink>
  );
}

export default ButtonAddTimeTable;
