import React from 'react';

function MiniAvatar({ image }) {
  const imagePath = image || 'default_avatar.png';
  return (
    <li>
      <div style={{ marginTop: '-5px' }}>
        <img src={`/upload/${imagePath}`} alt="avatar" width="45px" height="45px" />
      </div>
    </li>
  );
}

export default MiniAvatar;
