/**
 * Created by song on 17-3-23.
 *
 * 统计
 */

$(document).ready(function () {
    Statistics.init();
});

var Statistics = {
    data: {},
    init: function () {
        $.ajax({
            url: '/hostel/manager/statistics',
            type: 'post',
            success: function (data) {
                Statistics.data = Object.assign({}, data.object);

                if (data.status) {
                    Statistics.showData();
                }
            }
        })
    },
    showData: function () {
        $('#userNum').html(Statistics.data.userNum);
        $('#userConsume').html(Statistics.data.userConsume);

        $('#hostelNum').html(Statistics.data.hostelNum);
        $('#planNum').html(Statistics.data.planNum);

        $('#orderNum').html(Statistics.data.orderNum);
        $('#orderSum').html(Statistics.data.orderSum);
    }
};
