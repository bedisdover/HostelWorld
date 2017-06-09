/**
 * Created by song on 17-3-23.
 *
 * hostel统计
 */

$(document).ready(function () {
    HostelStatistics.init();
});

var HostelStatistics = {
    data: {},
    init: function () {
        $.ajax({
            url: '/hostel/statistics',
            type: 'post',
            success: function (data) {
                HostelStatistics.data = Object.assign({}, data.object);

                if (data.status) {
                    HostelStatistics.showData();
                }
            }
        })
    },
    showData: function () {
        var orderList = HostelStatistics.data.order;
        var paymentList = HostelStatistics.data.payment;

        var sum = 0;

        for (var i = 0; i < orderList.length; i++) {
            $('#orderList').append($(HostelStatistics.createOrderItem(i)));
        }

        for (var j = 0; j < paymentList.length; j++) {
            sum += paymentList[j].amount;

            $('#paymentList').append($(HostelStatistics.createPaymentItem(j)));
        }

        HostelStatistics.showSum(sum);
    },
    showSum: function (sum) {
        $('#paymentList').append('<tr><td>总 计</td><td></td><td>' + sum + '</td></tr>');
    },
    createOrderItem: function (index) {
        var order = HostelStatistics.data.order[index];

        var start = new Date(order.startDate).toLocaleDateString();
        var end = new Date(order.endDate).toLocaleDateString();
        var type = order.type === 'single' ? '单间' : '标间';

        return '<tr>' +
            '<td>' + (index + 1) + '</td>' +
            '<td>' + order.cardNum + '</td>' +
            '<td>' + start + '</td>' +
            '<td>' + end + '</td>' +
            '<td>' + type + '</td>' +
            '<td>' + order.amount + '</td>' +
            '</tr>';
    },
    createPaymentItem: function (index) {
        var payment = HostelStatistics.data.payment[index];

        var date = new Date(payment.date).toLocaleDateString();

        return '<tr>' +
            '<td>' + (index + 1) + '</td>' +
            '<td>' + date + '</td>' +
            '<td>' + payment.amount + '</td>' +
            '</tr>';
    }
};
