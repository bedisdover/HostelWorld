/**
 * Created by song on 17-3-21.
 *
 * 历史
 */

$(document).ready(function () {
    History.init();
});

var History = {
    init: function () {
        $.ajax({
            url: '/history/all',
            type: 'post',
            success: function (data) {
                if (data.status) {
                    History.showHistory(data.object);
                } else {
                    Materialize.toast("出错了", 3000);
                }
            }
        })
    },
    showHistory: function (history) {
        History.showOrder(history.order);

        History.showCheckIn(history.checkIn);
    },
    showOrder: function (orderList) {
        for (var i = 0; i < orderList.length; i++) {
            $('#order').children().append(History.createOrderItem(orderList[i]));
        }

        $('button[orderID]').on('click', function () {
            var orderID = $(this).attr('orderID');

            History.dropOrder($(this).attr('orderID'));
        });
    },
    showCheckIn: function (checkInList) {
        for (var i = 0; i < checkInList.length; i++) {
            $('#check-in').children().append(History.createCheckInItem(checkInList[i]));
        }
    },
    showConsume: function () {

    },
    dropOrder: function(orderID) {
        $.ajax({
            url: '/order/drop',
            type: 'post',
            data: {
                id: orderID
            },
            success: function (data) {
                if (data.status) {
                    $('li[orderID="' + orderID + '"]').addClass('hide');
                } else {
                    Materialize.toast('出错了', 3000);
                }
            }
        })
    },
    createOrderItem: function (order) {
        var startDate = new Date(order.startDate).toLocaleDateString();
        var endDate = new Date(order.endDate).toLocaleDateString();
        var type = order.type === 'single' ? '单间' : '标准间';
        var checkIn = order.finish ? '是' : '否';
        var disabled = order.finish ? 'disabled' : '';

        return $('' +
            '<li orderID="' + order.id + '">' +
                '<div class="collapsible-header">' +
                    '<span class="left">' +
                        '<span class="startDate left">' + startDate + '</span>' +
                        '<i class="material-icons">remove</i>' +
                        '<span class="endDate right">' + endDate  + '</span>' +
                    '</span>' +
                '</div>' +
                '<div class="collapsible-body grey lighten-3">' +
                    '<div class="item-wrapper">' +
                        '<div class="column">' +
                            '<div>类 型： ' + type + '</div>' +
                            '<div>入 住： ' + checkIn + '</div>' +
                        '</div>' +
                        '<div class="column">' +
                            '<div>金 额： ' + order.amount + '</div>' +
                            '<div><button class="btn red' + disabled + '" orderID="' + order.id + '">取 消</button></div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</li>');
    },
    createCheckInItem: function (checkIn) {
        var startDate = new Date(checkIn.startDate).toLocaleDateString();
        var endDate = new Date(checkIn.endDate).toLocaleDateString();
        var type = checkIn.type === 'single' ? '单间' : '标准间';
        var disabled = order.finish ? 'disabled' : '';

        return $('' +
            '<li>' +
                '<div class="collapsible-header">' +
                    '<span class="left">' +
                        '<span class="startDate left">' + startDate + '</span>' +
                        '<i class="material-icons">remove</i>' +
                        '<span class="endDate right">' + endDate  + '</span>' +
                    '</span>' +
                '</div>' +
                '<div class="collapsible-body grey lighten-3">' +
                    '<div class="item-wrapper">' +
                        '<div class="column">' +
                            '<div>类 型： ' + type + '</div>' +
                            '<div>房间号： ' + checkIn.roomID + '</div>' +
                        '</div>' +
                        '<div class="column">' +
                            '<div>金 额： ' + checkIn.money + '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</li>');
    }
};