import React from 'react';
import styles from './Navbar.scss';
import NavLinkComponent from './NavLinkComponent';
import LogOut from './LogOut';
import MiniAvatar from './MiniAvatar';
import {usersRoleEnum} from '../../constants';

function NavigationForLogInUsers({ profile }) {
  const { name = 'username', role, imagePath } = profile;
  return (
    <>
      <ul>
        <NavLinkComponent path="/" title="Timetables" />
        <NavLinkComponent path="/timeline" title="Timeline" />
        {role === usersRoleEnum.ADMIN ? (
          <>
            <NavLinkComponent path="/timeTablesInfo" title="Orders" />
            <NavLinkComponent path="/usersInfo" title="Users" />
          </>
        ) : null}
      </ul>
      <ul className={styles.right}>
        <MiniAvatar image={imagePath} />
        <NavLinkComponent path="/profile" title={name} />
        <LogOut />
      </ul>
    </>
  );
}

export default NavigationForLogInUsers;
