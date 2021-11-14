import React, {useState} from 'react';
import header from '../TimeTables/TimeTables.scss';
import WithData from '../../components/hoc-helpers/WithData';
import {fetchFullInfo} from '../../store/actions';
import style from './UsersInfo.scss';
import UserProfile from './UserProfile';
import UserOrders from './UserOrders';
import SearchBar from './SearchBar';
import {scenesEnum} from '../../constants';
import Notification from '../../components/Notification';

const UsersInfo = ({ users, timeTables, removeOrder, notifications, removeUser }) => {
  const [user, setUser] = useState(users[0]);

  return (
    <div>
      <div className={header.googleFont}>Users Info</div>
      <div className={style.wrapper}>
        <div className={style.wrapperUser}>
          <SearchBar users={users} setUser={setUser} />
          <UserProfile user={user} timeTables={timeTables} removeUser={removeUser} />
        </div>
        <UserOrders user={user} timeTables={timeTables} removeOrder={removeOrder} />
      </div>
      <Notification notifications={notifications} currentScene={scenesEnum.USERS_INFO} />
    </div>
  );
};

export default WithData(UsersInfo, fetchFullInfo);
