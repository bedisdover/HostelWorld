/**
 * Created by song on 17-3-22.
 *
 * 退房
 */

$(document).ready(function () {
    CheckOut.init();
});

var CheckOut = {
    checkInList: {},
    init: function () {
        CheckOut.getAllCheckIn();
    },
    getAllCheckIn: function () {
        $.ajax({
            url: '/hostel/checkIn/all',
            type: 'post',
            success: function (data) {
                if (data.status) {
                    CheckOut.checkInList = data.object.slice();
                    CheckOut.showCheckIn();
                }
            }
        })
    },
    showCheckIn: function () {
        for (var i = 0; i < CheckOut.checkInList.length; i++) {
            $('#checkInList').append($(CheckOut.createCheckInItem(i)));
        }

        $('button[index]').on('click', function () {
            CheckOut.checkOut(parseInt($(this).attr('index')));
        });
    },
    createCheckInItem: function (index) {
        var checkIn = CheckOut.checkInList[index];

        var start = new Date(checkIn.startDate).toLocaleDateString();
        var end = new Date(checkIn.endDate).toLocaleDateString();
        var user = checkIn.cardNum === null ? '否' : '是';

        return '' +
            '<tr>' +
                '<td>' + (index + 1) + '</td>' +
                '<td>' + checkIn.roomID + '</td>' +
                '<td>' + start + '</td>' +
                '<td>' + end + '</td>' +
                '<td>' + user + '</td>' +
                '<td>' + checkIn.money + '</td>' +
                '<td>' +
                    '<button class="btn btn-floating waves-effect waves-light red" index="' + index + '">退房</button>' +
                '</td>' +
            '</tr>';
    },
    checkOut: function (index) {
        var checkIn = CheckOut.checkInList[index];

        var data = {
            checkInID: checkIn.id,
            payType: 'crash'
        };

        if (checkIn.cardNum !== null) {
            $('#payType').modal({
                complete: function () {
                    data.payType = $("input[name='payType']:checked").attr('id');

                    CheckOut.postCheckOut(data);
                }
            }).modal('open');
        } else {
            CheckOut.postCheckOut(data);
        }
    },
    postCheckOut: function (data) {
        $.ajax({
            url: '/hostel/checkOut',
            type: 'post',
            data: data,
            success: function (data) {
                if (data.status) {
                    $('#checkInList').empty();
                    CheckOut.init();
                }
            }
        })
    }
};
