import React from 'react';
import {connect} from 'react-redux';
import {LOGOUT} from '../../store/actions';

function LogOut({ logOut }) {
    return (
    <li>
      <div
        role="button" //
        tabIndex={0} // for lint
        onKeyDown={scene => logOut(scene)} //
        onClick={scene => logOut(scene)}
        className="button is-black"
        style={{ marginTop: '-5px' }}
      >
        <img src="/logout.png" alt="logo" width="45px" height="45px" />
      </div>
    </li>
  );
}

const mapDispatchToProps = dispatch => ({
  logOut: () => LOGOUT(dispatch),
});

export default connect(null, mapDispatchToProps)(LogOut);
