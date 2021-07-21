import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { useParams } from 'react-router-dom';
import Spinner from '../Spinner';
import ErrorIndicator from '../Error-boundry/Error-indicator';
import {
  OPEN_MODAL_ORDER,
  CANCEL_MODAL_ORDER,
  CREATE_ORDER,
  ORDER_UPDATE_STATUS,
  ORDER_REMOVE,
  USER_REMOVE,
  TIME_TABLE_REMOVE,
} from '../../store/actions';

const Wrapped = (View, fetchAction) => {
  const WithData = props => {
    const { loading, error, fetchData } = props;

    const params = useParams();
    const id = Number(params.id);

    useEffect(() => {
      fetchData(id);
    }, []);

    if (loading) {
      return <Spinner />;
    }
    if (error) {
      return <ErrorIndicator />;
    }

    return <View {...props} />;
  };

  const mapStateToProps = ({
    timeTablesList: { currentTimeTable, timeTables, loading, error },
    authorization: { isLoggedIn },
    orders,
    profile,
    notifications,
    users: { users },
  }) => ({
    timeTables,
    loading,
    error,
    orders,
    notifications,
    profile,
    isLoggedIn,
    currentTimeTable,
    users,
  });

  const mapDispatchToProps = dispatch => ({
    fetchData: id => fetchAction(id, dispatch),
    openModal: ({ type, title, orderInfo }) => dispatch(OPEN_MODAL_ORDER(type, title, orderInfo)),
    handleCancel: event => {
      event.preventDefault();
      dispatch(CANCEL_MODAL_ORDER());
    },
    orderSubmit: (event, alertText, attributes, profile, timeOrder, timeTableId, slotSize) =>
      CREATE_ORDER(
        event,
        alertText,
        attributes,
        profile,
        timeOrder,
        timeTableId,
        slotSize,
        dispatch,
      ),
    orderUpdateStatus: (event, orderId, timeTableId, newStatus, scene) =>
      ORDER_UPDATE_STATUS(event, orderId, timeTableId, newStatus, scene, dispatch),
    removeOrder: (event, orderId, timeTableId, scene) =>
      ORDER_REMOVE(event, orderId, timeTableId, scene, dispatch),
    removeUser: (event, userId, scene) => USER_REMOVE(event, userId, scene, dispatch),
    removeTimeTable: (event, timeTableId, scene) =>
      TIME_TABLE_REMOVE(event, timeTableId, scene, dispatch),
  });

  return connect(mapStateToProps, mapDispatchToProps)(WithData);
};

export default Wrapped;
