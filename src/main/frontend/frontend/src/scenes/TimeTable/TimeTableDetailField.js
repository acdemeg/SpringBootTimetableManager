import React from 'react';

const TimeTableDetailField = ({ fieldName, info, color }) => {
  if (color) {
    return (
      <div>
        <div
          style={{
            borderRadius: '50%',
            width: '20px',
            height: '20px',
            backgroundColor: `${color}`,
            float: 'left',
          }}
        />
        &nbsp;-&nbsp;
        {info}
      </div>
    );
  }
  return (
    <div>
      <b>{fieldName}</b>
      &emsp;
      {info}
    </div>
  );
};

export default TimeTableDetailField;
