import React from 'react';
import InputIcon from './InputIcon';

const SearchInput = ({ sortUserList }) => {
  return (
    <div className="field">
      <p className="control has-icons-right">
        <input onChange={e => sortUserList(e)} className="input" placeholder="Enter name user" />
        <InputIcon side="right" icon="search" />
      </p>
    </div>
  );
};

export default SearchInput;
