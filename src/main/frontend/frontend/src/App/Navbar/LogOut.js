import React from 'react';
import { connect } from 'react-redux';
import { LOG_OUT } from '../../store/actions';

function LogOut({ logOut }) {
  return (
    <li>
      <div
        role="button" //
        tabIndex={0} // for lint
        onKeyDown={logOut} //
        onClick={logOut}
        className="button is-black"
        style={{ marginTop: '-5px' }}
      >
        <img src="/logout.png" alt="logo" width="45px" height="45px" />
      </div>
    </li>
  );
}

const mapDispatchToProps = dispatch => ({
  logOut: () => dispatch(LOG_OUT()),
});

export default connect(null, mapDispatchToProps)(LogOut);
