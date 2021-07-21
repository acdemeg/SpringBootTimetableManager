import React from 'react';
import { attributeTypeEnum, timeTableTypeEnum } from '../../constants';

const SelectInput = ({ type, labelName, labelId, nameForm }) => {
  const attributeTypes = (
    <div className="select">
      <select name={nameForm}>
        <option>{attributeTypeEnum.STRING}</option>
        <option>{attributeTypeEnum.DATE}</option>
        <option>{attributeTypeEnum.NUMBER}</option>
      </select>
    </div>
  );

  const timeTableTypes = (
    <div className="select">
      <select name={nameForm}>
        <option>{timeTableTypeEnum.HOUR}</option>
        <option>{timeTableTypeEnum.DAY}</option>
      </select>
    </div>
  );

  return (
    <label
      htmlFor={labelId}
      style={{
        marginLeft: `${labelName === 'Slot size' ? '0' : '5%'}`,
      }}
    >
      {labelName}
      <div id={labelId}>{type === 'timeTable' ? timeTableTypes : attributeTypes}</div>
    </label>
  );
};

export default SelectInput;
