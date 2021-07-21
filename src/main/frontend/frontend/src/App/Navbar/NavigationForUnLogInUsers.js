import React from 'react';
import styles from './Navbar.scss';
import NavLinkComponent from './NavLinkComponent';

function NavigationForUnLogInUsers() {
  return (
    <>
      <ul>
        <NavLinkComponent path="/" title="Timetables" />
      </ul>
      <ul className={styles.right}>
        <NavLinkComponent path="/signIn" title="Sign In" />
        <NavLinkComponent path="/registration" title="Register" />
      </ul>
    </>
  );
}

export default NavigationForUnLogInUsers;
