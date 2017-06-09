/**
 * Created by song on 17-3-16.
 *
 * hostel列表
 */

$(document).ready(function () {
    HostelList.getAllHostel();
});

var HostelList = {
    hostelList: [],
    getAllHostel: function () {
        Room.getAllHostel(this.showAllHostel);
    },
    showAllHostel: function (hostelList) {
        this.hostelList = hostelList;

        for (var i = 0; i < hostelList.length; i++) {
            $('#hostel-list').append($(HostelList.createHostelElement(hostelList[i])));
        }

        $('.materialboxed').materialbox();
        $('.tooltipped').tooltip();
    },
    createHostelElement: function (hostel) {
        console.log(hostel);
        return '<div class="card">' +
            '<img class="materialboxed" src="../img/hostel/bg/bg_h000003.jpg" alt="" width="300px">' +
            '<div class="card-content">' +
            '<span class="card-title activator grey-text text-darken-4">' + hostel.name +
            '<i class="material-icons right tooltipped" data-tooltip="点击查看详情">more_vert</i>' +
            '</span>' +
            '</div>' +
            '<div class="card-reveal">' +
            '<span class="card-title grey-text text-darken-4">' + hostel.name +
            '<i class="material-icons right">close</i>' +
            '</span>' +
            '<p>' + hostel.address + '</p>' +
            '<p>单间： ￥' + hostel.singlePrice + '</p>' +
            '<p>标准间： ￥' + hostel.normalPrice + '</p>' +
            '<a class="btn waves-effect waves-light" href="order.html?hostelID=' + hostel.hostelID + '">预 订</a>' +
            '</div>' +
            '</div>';
    }
};