import style from './TimeTablesInfo.scss';
import Container from '../TimeTables/ContainerStyle';
import { timeTableTypeEnum } from '../../constants';
import CartOrder from './CardOrder';

const SlotInfo = ({ orders, slotSize, attributes, orderUpdateStatus, removeOrder }) => {
  const slotOrders = Array.isArray(orders) ? orders : [orders];
  const [{ startDate, endDate }] = slotOrders;
  const startDateObj = new Date(Date.parse(startDate));
  const endDateObj = new Date(Date.parse(endDate));

  return (
    <div
      style={{
        marginTop: '5%',
        backgroundColor: `${slotOrders.length > 1 ? 'lightpink' : 'whitesmoke'}`,
      }}
    >
      <div className={style.slotTitle}>
        <p>
          <i>
            {slotSize === timeTableTypeEnum.HOUR
              ? `${startDateObj.getDate()}
            ${startDateObj.toLocaleString('eng', { month: 'long' })}
            ${startDateObj.getHours()}:00
            - 
            ${endDateObj.getHours()}:00`
              : `${startDateObj.getDate()}
            ${startDateObj.toLocaleString('eng', { month: 'long' })}`}
          </i>
        </p>
      </div>
      <Container>
        {slotOrders.map(order => (
          <CartOrder
            key={order.id}
            order={order}
            attributes={attributes}
            removeOrder={removeOrder}
            orderUpdateStatus={orderUpdateStatus}
          />
        ))}
      </Container>
    </div>
  );
};

export default SlotInfo;
