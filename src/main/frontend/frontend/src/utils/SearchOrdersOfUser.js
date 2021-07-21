const searchOrdersOfUser = (timeTables, user) => {
  const events = [];

  timeTables.forEach(timeTable => {
    timeTable.orders.forEach(order => {
      if (user.id === order.authorId) {
        events.push({
          slotSize: timeTable.slotSize,
          orderId: order.id,
          timeTableId: timeTable.id,
          timeTableTitle: timeTable.title,
          startDate: order.startDate,
          endDate: order.endDate,
          orderStatus: order.status,
          titleMainAttrib: timeTable.attributes[0] ? timeTable.attributes[0].title : null,
          attr: order.attributeValues.find(v => {
            if (order.attributeValues[0]) {
              return v.attributeId === timeTable.attributes[0].id;
            }
            return null;
          }),
        });
      }
    });
  });

  return events;
};

export default searchOrdersOfUser;
