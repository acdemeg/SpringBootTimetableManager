import React from 'react';
import style from '../../scenes/TimeTable/TimeTable.scss';

function Button({ title, handler }) {
  return (
    <button className={style.buttonModal} type="submit">
      <div
        onClick={e => handler(e)}
        onKeyDown={handler}
        role="button"
        tabIndex={0} // for lint
        className="button"
        style={{
          backgroundColor: `${(() => {
            if (title === 'Reject') {
              return 'orangered';
            }
            if (
              title === 'Cancel' ||
              title === '+ Add attribute' ||
              title === '- Delete attribute' ||
              title === 'OK'
            ) {
              return 'bisque';
            }
            return 'mediumseagreen';
          })()}`,
          width: 'fit-content',
          alignSelf: 'center',
        }}
      >
        {title}
      </div>
    </button>
  );
}

export default Button;
