/**
 * Created by song on 17-3-16.
 *
 * 预订
 */

$(document).ready(function () {

    $('#search').on('click', function () {
        setTimeout(function () {
            $('#search').addClass('disabled');
        }, 0);
        Order.search();
    });

    $('#order-single').on('click', function () {
        Order.orderSingle();
    });

    $('#order-normal').on('click', function() {
        Order.orderNormal();
    })
});

var Order = {
    hostelID: getUrlParam("hostelID"),
    startDate: 0,
    endDate: 0,
    roomDefault: {
        single: '---',
        normal: '---',
        singlePrice: '---',
        normalPrice: '---'
    },
    room: {
        single: '---',
        normal: '---',
        singlePrice: '---',
        normalPrice: '---'
    },
    initDate: function () {
        Order.startDate = new Date($('#startDate').val()).getTime();
        Order.endDate = new Date($('#endDate').val()).getTime();
        // Order.startDate = new Date('2017-04-21').getTime();
        // Order.endDate = new Date('2017-04-28').getTime();
    },
    checkDate: function () {
        if (Order.startDate >= Order.endDate) {
            $('#endDate').addClass('invalid');
            Materialize.toast('结束日期必须晚于开始日期', 3000, '', function () {
                $('#endDate').removeClass('invalid');
            });

            return false;
        } else {
            return true;
        }
    },
    search: function () {
        Order.initDate();

        if (Order.checkDate()) {
            Room.getAvailableRoomByDate(Order.hostelID, Order.startDate, Order.endDate, Order.showRoom);
        } else {
            // 恢复默认数据
            Order.room = Object.assign({}, Order.roomDefault);
        }
    },
    showRoom: function (room) {
        if (room !== null) {
            Order.room = Object.assign({}, Order.room, room);
        } else {
            Order.room = Object.assign({}, Order.room, Order.roomDefault);
        }

        Order.renderTable();
        $('#search').removeClass('disabled');
    },
    renderTable: function () {
        $('#single').html(Order.room.single);
        $('#singlePrice').html(Order.room.singlePrice);
        $('#normal').html(Order.room.normal);
        $('#normalPrice').html(Order.room.normalPrice);

        if (Order.room.single === 0) {
            $('#order-single').addClass('disabled');
        } else {
            $('#order-single').removeClass('disabled');
        }

        if (Order.room.normal === 0) {
            $('#order-normal').addClass('disabled');
        } else {
            $('#order-normal').removeClass('disabled');
        }
    },
    orderSingle: function () {
        Order.order('single');
    },
    orderNormal: function () {
        Order.order('normal');
    },
    order: function (type) {
        $.ajax({
            url: '/order',
            type: 'post',
            data: {
                hostelID: Order.hostelID,
                type: type,
                startDate: Order.startDate,
                endDate: Order.endDate
            },
            success: function (data) {
                if (data.status) {
                    Order.showModal(data.info, data.object);
                } else {
                    Materialize.toast(data.info, 3000);
                }
            }
        })
    },
    showModal: function (orderID, amount) {
        $('#orderID').html(orderID);
        // 设置预定日期
        $('#order-date').html($('#endDate').val());
        $('#amount').html(amount);

        $('.modal').modal('open');
    }
};
