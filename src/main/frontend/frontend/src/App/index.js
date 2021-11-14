import React from 'react';
import {Route, Switch} from 'react-router-dom';
import {connect} from 'react-redux';
import TimeTables from '../scenes/TimeTables';
import TimeTable from '../scenes/TimeTable';
import AdminPanelTable from '../scenes/TimeTablesInfo/AdminPanelTable';
import CreateTimeTable from '../scenes/CreateTimeTable';
import Timeline from '../scenes/Timeline';
import Profile from '../scenes/Profile';
import Registration from '../scenes/Registration';
import SignIn from '../scenes/SignIn';
import TimeTablesInfo from '../scenes/TimeTablesInfo';
import UsersInfo from '../scenes/UsersInfo';
import Navigation from './Navbar';
import {usersRoleEnum} from '../constants';
import './styles.css';

const App = ({ isLoggedIn, role, profile }) => {
  return (
    <>
      <Navigation isLoggedIn={isLoggedIn} profile={profile} />
      <Switch>
        <Route path="/" exact component={TimeTables} />
        <Route path="/registration" component={Registration} />
        <Route path="/timeTable/:id" component={TimeTable} />
        <Route path="/signIn" component={SignIn} />
        <Route path="/profile" component={isLoggedIn ? Profile : null} />
        <Route path="/timeline" component={isLoggedIn ? Timeline : null} />
        <Route
          path="/createTimeTable"
          exact
          component={role === usersRoleEnum.ADMIN ? CreateTimeTable : null}
        />
        <Route
          path="/timeTablesInfo"
          component={isLoggedIn && role === usersRoleEnum.ADMIN ? TimeTablesInfo : null}
        />
        <Route
          path="/usersInfo"
          component={isLoggedIn && role === usersRoleEnum.ADMIN ? UsersInfo : null}
        />
        <Route
          path="/adminPanelTable/:id"
          component={isLoggedIn && role === usersRoleEnum.ADMIN ? AdminPanelTable : null}
        />
      </Switch>
    </>
  );
};

const mapStateToProps = ({ authorization: { isLoggedIn }, profile: { role }, profile }) => ({
  isLoggedIn,
  role,
  profile,
});

export default connect(mapStateToProps, null)(App);
