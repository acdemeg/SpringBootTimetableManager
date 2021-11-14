import React from 'react';
import './Profile.css';
import {connect} from 'react-redux';
import {CANCEL_MODAL_PROFILE, OPEN_MODAL_PROFILE, UPDATE_PROFILE} from '../../store/actions';
import Modal from './Modal';
import ProfileField from './ProfileField';
import RedactFields from './RedactField';
import {scenesEnum} from '../../constants';
import Notification from '../../components/Notification';

function Profile({
  isOpenModal,
  profile,
  titleModal,
  typeModal,
  openModal,
  handleCancel,
  updateProfile,
  notifications,
}) {
  const imagePath = profile.imagePath ? profile.imagePath : 'default_avatar.png';
  return (
    <div>
      <div className="profile">
        <div className="profile-header">
          <b>My profile</b>
        </div>
        <div className="profile-container">
          <div className="image_container">
            <img src={`/upload/${imagePath}`} alt="avatar" width="350px" height="350px" />
          </div>
          <div style={{ float: 'left' }}>
            <ProfileField value={profile.name} title="Name" icon="user" />
            <ProfileField value={profile.email} title="Email" icon="envelope" />
          </div>

          <RedactFields openModal={openModal} />
        </div>
      </div>

      {!isOpenModal ? null : (
        <Modal
          title={titleModal}
          typeModal={typeModal}
          onCancel={handleCancel}
          onSubmit={updateProfile}
          profile={profile}
        />
      )}
      <Notification notifications={notifications} currentScene={scenesEnum.PROFILE} />
    </div>
  );
}

const mapStateToProps = ({
  profile: { isOpenModal, titleModal, typeModal },
  profile,
  notifications,
}) => ({
  isOpenModal,
  profile,
  titleModal,
  typeModal,
  notifications,
});

const mapDispatchToProps = dispatch => ({
  openModal: ({ type, title }) => dispatch(OPEN_MODAL_PROFILE(type, title)),
  handleCancel: () => dispatch(CANCEL_MODAL_PROFILE()),
  updateProfile: (data, alertText, typeModal, profile) =>
    UPDATE_PROFILE(data, alertText, typeModal, profile, dispatch),
});

export default connect(mapStateToProps, mapDispatchToProps)(Profile);
