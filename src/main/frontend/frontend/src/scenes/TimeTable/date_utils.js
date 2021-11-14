import {timeTableTypeEnum} from '../../constants';

const millisecInHour = 3600000;
const millisecInDay = 86400000;
const millisecInWeek = 604800000;

class DateUtils {
  constructor(slotSize, period) {
    this.slotSize = slotSize;
    this.time = period;
    this.currentCellTime = null;
  }

  getOrdersForColumn(orders) {
    if (this.slotSize === timeTableTypeEnum.HOUR) {
      const endDay = this.time.getTime() + millisecInDay;
      return orders.filter(
          order =>
              Date.parse(order.endDate) <= endDay && Date.parse(order.startDate) >= this.time.getTime(),
      );
    }

    const endWeek = this.time.getTime() + millisecInWeek;
    return orders.filter(
        order =>
            Date.parse(order.endDate) <= endWeek && Date.parse(order.startDate) >= this.time.getTime(),
    );
  }

  getOrderForCell(orders, numberCell, userId) {
    let cellTime;

    if (this.slotSize === timeTableTypeEnum.HOUR) {
      cellTime = this.time.date.getTime() + millisecInHour * (numberCell - 1);
    } else {
      cellTime = this.time.date.start.getTime() + millisecInDay * (numberCell - 1);
    }

    const ordersCell = orders.filter(order => Date.parse(order.startDate) === cellTime);
    // if some orders in cell user will see only its ordes or nothing
    if (ordersCell.length > 1) {
      const ownOrder = ordersCell.filter(order => order.authorId === userId);
      const [order] = ownOrder.length === 0 ? ordersCell : ownOrder;
      return order;
    }
    const [order] = ordersCell;
    return order;
  }

  getStartPeriodColumn() {
    return {
      string: `${this.time.getDate()}
              ${this.time.toLocaleString('eng', { month: 'long' })}
              ${this.time.getFullYear()}`,
      date: this.time,
    };
  }

  getEndPeriodColunm() {
    return {
      string: `${new Date(this.time.getTime() + millisecInWeek).getDate()}
              ${new Date(this.time.getTime() + millisecInWeek).toLocaleString('eng', {
                month: 'long',
              })}
              ${new Date(this.time.getTime() + millisecInWeek).getFullYear()}`,
      date: new Date(this.time.getTime() + millisecInWeek),
    };
  }

  getPeriodForColumns(currentCollumn) {
    if (this.slotSize === timeTableTypeEnum.HOUR) {
      if (currentCollumn === 1) {
        return this.getStartPeriodColumn();
      }
      this.time = new Date(this.time.getTime() + millisecInDay);
      return this.getStartPeriodColumn();
    }

    if (currentCollumn === 1) {
      return {
        string: `${this.getStartPeriodColumn().string}
                    -
                    ${this.getEndPeriodColunm().string}`,
        date: {
          start: this.getStartPeriodColumn().date,
          end: this.getEndPeriodColunm().date,
        },
      };
    }
    this.time = new Date(this.time.getTime() + millisecInWeek);
    return {
      string: `${this.getStartPeriodColumn().string}
                  -
                  ${this.getEndPeriodColunm().string}`,
      date: {
        start: this.getStartPeriodColumn().date,
        end: this.getEndPeriodColunm().date,
      },
    };
  }

  getPeriodForHour() {
    return {
      string: `${this.currentCellTime.getDate()}
              ${this.currentCellTime.toLocaleString('eng', { month: 'long' })}
              ${this.currentCellTime.getFullYear()}
              ${this.currentCellTime.getHours()}:00
              -
              ${new Date(this.currentCellTime.getTime() + millisecInHour).getHours()}:00`,

      date: this.currentCellTime,
    };
  }

  getPeriodForDay() {
    return {
      string: `${this.currentCellTime.getDate()}
              ${this.currentCellTime.toLocaleString('eng', { month: 'long' })}
              ${this.currentCellTime.getFullYear()}`,

      date: this.currentCellTime, // { date: { end, start } }
    };
  }

  getPeriodForCell(numberCell) {
    if (this.slotSize === timeTableTypeEnum.HOUR) {
      this.currentCellTime = new Date(this.time.date.getTime() + millisecInHour * (numberCell - 1));
      return this.getPeriodForHour();
    }
    this.currentCellTime = new Date(
      this.time.date.start.getTime() + millisecInDay * (numberCell - 1),
    );
    return this.getPeriodForDay();
  }
}

export default DateUtils;
