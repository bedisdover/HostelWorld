/**
 * Created by song on 17-3-21.
 *
 * 入住
 */

$(document).ready(function () {
    $('#confirm').on('click', function () {
        CheckIn.checkIn();
    });

    $('#non-confirm').on('click', function () {
        CheckIn.nonUserCheckIn();
    });
});

var CheckIn = {
    getData: function () {
        var type = $("input[name='type']:checked").attr('id');

        return {
            cardNum: $('#cardNum').val(),
            roomID: $('#roomID').val(),
            endDate: new Date($('#endDate').val()).getTime(),
            type: type,
            orderID: parseInt($('#orderID').val())
        };
    },
    nonUserGetData: function () {
        var type = $("input[name='non-type']:checked").attr('id').substr(4);

        return {
            name: $('#non-name').val(),
            roomID: $('#non-roomID').val(),
            endDate: new Date($('#non-endDate').val()).getTime(),
            type: type
        };
    },
    checkIn: function () {
        var data = CheckIn.getData();

        if (!isNaN(data.orderID)) { // 有订单
            CheckIn.postData('/hostel/checkIn/user/order', data);
        } else {
            CheckIn.postData('/hostel/checkIn/user', data);
        }
    },
    nonUserCheckIn: function () {
        var data = CheckIn.nonUserGetData();

        CheckIn.postData('/hostel/checkIn', data);
    },
    postData: function (url, checkIn) {
        $.ajax({
            url: url,
            type: 'post',
            data: checkIn,
            success: function (data) {
                if (data.status) {
                    Materialize.toast("入住成功", 3000);
                    CheckIn.clear();
                }
            }
        })
    },
    // 清空input
    clear: function () {
        $('input[type=text], input[type=date]').val('');
    }
};

