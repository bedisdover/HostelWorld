/**
 * Created by song on 17-3-22.
 *
 * plan相关
 */

$(document).ready(function () {
    Plan.init();

    $('#confirm').on('click', function () {
        Plan.publishPlan();
    })
});

var Plan = {
    planList: [],
    init: function () {
        $.ajax({
            url: '/hostel/plan',
            type: 'post',
            success: function (data) {
                if (data.status) {
                    Plan.planList = data.object.slice();
                    Plan.showPlanList();
                }
            }
        })
    },
    showPlanList: function () {
        for (var i = 0; i < Plan.planList.length; i++) {
            $('#planList').append($(Plan.createPlanItem(i)));
        }
    },
    createPlanItem: function (index) {
        var plan = Plan.planList[index];

        var start = new Date(plan.startDate).toLocaleDateString();
        var end = new Date(plan.endDate).toLocaleDateString();

        return '' +
            '<tr>' +
                '<td>' + (index + 1) + '</td>' +
                '<td>' + start + '</td>' +
                '<td>' + end + '</td>' +
                '<td>' + plan.single + '</td>' +
                '<td>' + plan.singlePrice + '</td>' +
                '<td>' + plan.normal + '</td>' +
                '<td>' + plan.normalPrice + '</td>' +
            '</tr>';
    },
    getData: function () {
        return {
            startDate: new Date($('#startDate').val()).getTime(),
            endDate: new Date($('#endDate').val()).getTime(),
            single: parseInt($('#single').val()),
            singlePrice: parseFloat($('#singlePrice').val()),
            normal: parseInt($('#normal').val()),
            normalPrice: parseFloat($('#normalPrice').val())
        }
    },
    publishPlan: function () {
        $.ajax({
            url: '/hostel/plan/publish',
            type: 'post',
            data: Plan.getData(),
            success: function (data) {
                if (data.status) {
                    Materialize.toast("发布成功", 3000);
                    Plan.clear();
                }
            }
        })
    },
    clear: function () {
        $('input[type=text], input[type=date]').val('');
        $('#planList').empty();
        Plan.init();
    }
};
