import React, { useState } from 'react';
import style from './UsersInfo.scss';
import SearchInput from '../../components/inputs/SearchInput';
import { getNewIdGenerator } from '../../utils';

const keysGen = getNewIdGenerator();

const UserLine = ({ user, setUser }) => {
  return (
    <div
      className={style.userLine}
      onClick={() => setUser(user)}
      onKeyDown={() => setUser(user)} //
      role="button" //
      tabIndex={0} // for lint
    >
      {user.name}
    </div>
  );
};

const SearchBar = ({ users, setUser }) => {
  const [usersList, setUsersList] = useState([
    users.map(user => <UserLine key={keysGen()} user={user} setUser={setUser} />),
  ]);

  const sortUserList = e => {
    const name = e.target.value.toLowerCase();
    const filter = users.filter(user => user.name.toLowerCase().includes(name));
    const [firstUser] = filter;
    setUser(firstUser);
    return setUsersList([
      filter.map(user => <UserLine key={keysGen()} user={user} setUser={setUser} />),
    ]);
  };

  return (
    <div className={style.searchBar}>
      <div className={style.searchInput}>
        <SearchInput sortUserList={sortUserList} />
        <p style={{ paddingLeft: '10px' }}>Name</p>
      </div>
      <div className={style.userList}>{usersList}</div>
    </div>
  );
};

export default SearchBar;
