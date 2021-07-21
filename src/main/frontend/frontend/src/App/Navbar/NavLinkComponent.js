import React from 'react';
import { NavLink } from 'react-router-dom';

const NavLinkComponent = ({ path, title }) => (
  <li>
    <NavLink className="nav-link" to={path}>
      {title}
    </NavLink>
  </li>
);

export default NavLinkComponent;
