import React from 'react';

function ButtonRound({ title, size = 'is-small', color = 'is-warning', handler }) {
  return (
    <button
      type="button"
      className={`button ${size} is-rounded ${color}`}
      style={{ marginTop: '10px', position: 'inherit' }}
      onClick={event => handler(event)}
    >
      {title}
    </button>
  );
}

export default ButtonRound;
