import React from 'react';
import styles from './Navbar.scss';
import NavigationForLogInUsers from './NavigationForLogInUsers';
import NavigationForUnLogInUsers from './NavigationForUnLogInUsers';

function Navigation({ isLoggedIn, profile }) {
  let navBar;

  if (isLoggedIn) {
    navBar = <NavigationForLogInUsers profile={profile} />;
  } else navBar = <NavigationForUnLogInUsers />;

  return (
    <div className={styles.wrapper}>
      <nav className={`panel-heading ${styles.navBar}`}>
        <div>
          <img src="/logo.png" alt="logo" width="50px" height="50px" />
        </div>
        <div className="tabs">{navBar}</div>
      </nav>
    </div>
  );
}

export default Navigation;
