import { actionsEnum } from '../../constants';
import searchConflictOrders from '../../utils/SearchConflictOrders';

const updateTimeTables = (state, action) => {
  if (state === undefined) {
    return {
      currentTimeTable: null,
      timeTables: [],
      loading: true,
      error: null,
    };
  }

  const withConflictOrders = payload => {
    if (Array.isArray(payload)) {
      return payload.map(value => {
        const timeTable = value;
        timeTable.conflictsOrders = searchConflictOrders(timeTable.orders);
        return timeTable;
      });
    }
    const timeTable = payload;
    const conflictsOrders = searchConflictOrders(payload.orders);
    timeTable.conflictsOrders = conflictsOrders;
    return timeTable;
  };

  switch (action.type) {
    case actionsEnum.TIME_TABLES_REQUESTED:
      return {
        currentTimeTable: state.timeTablesList.currentTimeTable,
        timeTables: [],
        loading: true,
        error: null,
      };

    case actionsEnum.TIME_TABLES_LOADED:
      return {
        currentTimeTable: state.timeTablesList.currentTimeTable,
        timeTables: withConflictOrders(action.payload),
        loading: false,
        error: null,
      };

    case actionsEnum.TIME_TABLES_ERROR:
      return {
        currentTimeTable: state.timeTablesList.currentTimeTable,
        timeTables: [],
        loading: false,
        error: action.payload,
      };
    case actionsEnum.TIME_TABLE_REQUESTED:
      return {
        currentTimeTable: null,
        timeTables: state.timeTablesList.timeTables,
        loading: true,
        error: null,
      };

    case actionsEnum.TIME_TABLE_LOADED:
      return {
        currentTimeTable: withConflictOrders(action.payload),
        timeTables: state.timeTablesList.timeTables,
        loading: false,
        error: null,
      };

    case actionsEnum.TIME_TABLE_ERROR:
      return {
        currentTimeTable: null,
        timeTables: state.timeTablesList.timeTables,
        loading: false,
        error: action.payload,
      };

    default:
      return state.timeTablesList;
  }
};

export default updateTimeTables;
