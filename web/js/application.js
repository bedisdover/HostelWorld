/**
 * Created by song on 17-3-23.
 *
 * 申请
 */

$(document).ready(function () {
    Application.init();
});

var Application = {
    // 开店申请
    open: [],
    // 修改信息申请
    modify: [],
    init: function () {
        $.ajax({
            url: '/hostel/manager/application',
            type: 'post',
            success: function (data) {
                if (data.status) {
                    Application.open = data.object.open.slice();
                    Application.modify = data.object.modify.slice();

                    Application.showApplication();
                }
            }
        })
    },
    showApplication: function () {
        var openList = $('#openList');
        var modifyList = $('#modifyList');

        for (var i = 0; i < Application.open.length; i++) {
            $(openList).append($(Application.createOpenItem(i)));
        }

        for (var j = 0; j < Application.modify.length; j++) {
            $(modifyList).append($(Application.createModifyItem(j)));
        }

        $(openList).find('button').on('click', function () {
            if ($(this).hasClass('pass')) {
                Application.approveOpen($(this).attr('hostelID'), 'pass');
            } else {
                Application.approveOpen($(this).attr('hostelID'), 'reject');
            }
        });

        $(modifyList).find('button').on('click', function () {
            if ($(this).hasClass('pass')) {
                Application.approveModify($(this).attr('id'), 'pass');
            } else {
                Application.approveModify($(this).attr('id'), 'reject');
            }
        });
    },
    approveOpen: function (hostelID, pass) {
        $.ajax({
            url: '/hostel/manager/approve/open',
            type: 'post',
            data: {
                hostelID: hostelID,
                pass: pass
            },
            success: function (data) {
                Materialize.toast(data.info, 3000);

                if (data.status) {
                    Application.clear();
                }
            }
        })
    },
    approveModify: function (id, pass) {
        $.ajax({
            url: '/hostel/manager/approve/modify',
            type: 'post',
            data: {
                id: id,
                pass: pass
            },
            success: function (data) {
                Materialize.toast(data.info, 3000);

                if (data.status) {
                    Application.clear();
                }
            }
        })
    },
    createOpenItem: function (index) {
        var temp = Application.open[index];

        return '' +
            '<tr>' +
            '<td>' + (index + 1) + '</td>' +
            '<td>' + temp.name + '</td>' +
            '<td>' + temp.address + '</td>' +
            '<td>' +
            '<button class="btn btn-floating waves-effect waves-light pass" hostelID="' + temp.hostelID + '">' +
            '<i class="material-icons">done</i>' +
            '</button>' +
            '</td>' +
            '<td>' +
            '<button class="btn btn-floating red waves-effect waves-light reject" hostelID="' + temp.hostelID + '">' +
            '<i class="material-icons">clear</i>' +
            '</button>' +
            '</td>' +
            '</tr>';
    },
    createModifyItem: function (index) {
        var temp = Application.modify[index];

        return '' +
            '<tr>' +
            '<td>' + (index + 1) + '</td>' +
            '<td>' + temp.name_before + '</td>' +
            '<td>' + temp.address_before + '</td>' +
            '<td>' + temp.name_after + '</td>' +
            '<td>' + temp.address_after + '</td>' +
            '<td>' +
            '<button class="btn btn-floating waves-effect waves-light pass" id="' + temp.id + '">' +
            '<i class="material-icons">done</i>' +
            '</button>' +
            '</td>' +
            '<td>' +
            '<button class="btn btn-floating red waves-effect waves-light reject" id="' + temp.id + '">' +
            '<i class="material-icons">clear</i>' +
            '</button>' +
            '</td>' +
            '</tr>';
    },
    clear: function () {
        $('#openList').empty();
        $('#modifyList').empty();

        Application.init();
    }
};
