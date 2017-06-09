/**
 * Created by song on 17-3-23.
 *
 * 结算
 */

$(document).ready(function () {
    Payment.init();

    $('#payAll').on('click', function () {
        Payment.payAll();
    })
});

var Payment = {
    payment: {},
    init: function () {
        $.ajax({
            url: '/hostel/manager/payment',
            type: 'post',
            success: function (data) {
                Payment.payment = Object.assign({}, data.object);

                Payment.showPayment();
            }
        })
    },
    pay: function (hostelID) {
        $.ajax({
            url: '/hostel/manager/payment/pay',
            type: 'post',
            data: {
                hostelID: hostelID
            },
            success: function (data) {
                Materialize.toast(data.info, 3000);

                if (data.status) {
                    $('tr[hostelID="' + hostelID + '"').remove();
                }
            }
        });
    },
    payAll: function () {
        $.ajax({
            url: '/hostel/manager/payment/pay/all',
            type: 'post',
            success: function (data) {
                Materialize.toast(data.info, 3000);

                if (data.status) {
                    $('#paymentList').empty();
                }
            }
        });
    },
    showPayment: function () {
        var index = 0;
        for (var hostelID in Payment.payment) {
            index++;
            if (Payment.payment.hasOwnProperty(hostelID)) {
                var temp = Payment.payment[hostelID];
                var amount = 0;

                for (var i = 0; i < temp.length; i++) {
                    amount += temp[i].amount;
                }

                $('#paymentList').append($(Payment.createItem(index, temp[0].hostelID, amount)));
            }
        }

        $('button[hostelID]').on('click', function () {
            Payment.pay($(this).attr('hostelID'));
        });
    },
    createItem: function (index, hostelID, amount) {
        return '' +
            '<tr hostelID="' + hostelID + '">' +
            '<td>' + index + '</td>' +
            '<td>' + hostelID + '</td>' +
            '<td>' + amount + '</td>' +
            '<td>' +
            '<button class="btn btn-floating waves-effect waves-light" hostelID="' + hostelID + '">' +
            '<i class="material-icons">done</i>' +
            '</button>' +
            '</td>' +
            '</tr>';
    }
};
